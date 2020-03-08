package cn.segema.cloud.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import cn.segema.cloud.common.constants.CommonConstant;

/**
 * 原生UrlConnection请求
 * 
 * @author wangyong
 *
 */
public class HttpUrlConnectionUtil {
	private static final String TAG = "HttpUrlConnectionUtil";
	private static final int mReadTimeOut = 1000 * 10; // 10秒
	private static final int mConnectTimeOut = 1000 * 5; // 5秒
	private static final String CHAR_SET = CommonConstant.DEFAULT_ENCODING;
	private static final int mRetry = 2; // 默认尝试访问次数

	public static final int THREAD_POOL_SIZE = 5; // 最大线程池

	public interface HttpDownLoadProgress {
		public void onProgress(int progress);
	}

	private static HttpUrlConnectionUtil httpDownload;
	private ExecutorService downloadExcutorService;

	private HttpUrlConnectionUtil() {
		downloadExcutorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	public static HttpUrlConnectionUtil getInstance() {
		if (httpDownload == null) {
			httpDownload = new HttpUrlConnectionUtil();
		}
		return httpDownload;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param filePath
	 */
	public void download(final String url, final String filePath) {
		downloadExcutorService.execute(new Runnable() {
			@Override
			public void run() {
				doDownloadFile(url, filePath, null, null);
			}
		});
	}

	/**
	 * 下载文件
	 *
	 * @param url
	 * @param filePath
	 * @param progress
	 *            进度回调
	 */
	public void download(final String url, final String filePath, final HttpDownLoadProgress progress) {
		downloadExcutorService.execute(new Runnable() {

			@Override
			public void run() {
				doDownloadFile(url, filePath, progress, null);
			}
		});
	}

	/**
	 * 下载文件
	 *
	 * @param url
	 * @param filePath
	 */
	private void doDownloadFile(final String url, final String filePath, final HttpDownLoadProgress progress,
			Map<String, String> headers) {
		HttpURLConnection connection = null;
		InputStream is = null;
		try {
			connection = getConnection(url);
			configConnection(connection);
			connection.setDoOutput(true);// 使用 URL 连接进行输出
			connection.setDoInput(true);// 使用 URL 连接进行输入
			connection.setUseCaches(false);// 忽略缓存
			connection.setRequestMethod("GET");// 设置URL请求方法
			// 可设置请求头
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			connection.setRequestProperty("Charset", "UTF-8");

			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			connection.setInstanceFollowRedirects(true);
			is = connection.getInputStream();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = is.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			byte[] in2b = swapStream.toByteArray();
			File file = new File(filePath);
			FileOutputStream os = new FileOutputStream(file);
			os.write(in2b);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String get(String url) throws Exception {
		return get(url, null);
	}

	public static String get(String url, Map<String, ? extends Object> params) throws Exception {
		return get(url, params, null);
	}

	public static String get(String url, Map<String, ? extends Object> params, Map<String, String> headers)
			throws Exception {
		if (url == null || url.trim().length() == 0) {
			throw new Exception(TAG + ": url is null or empty!");
		}

		if (params != null && params.size() > 0) {
			if (!url.contains("?")) {
				url += "?";
			}

			if (url.charAt(url.length() - 1) != '?') {
				url += "&";
			}

			url += buildParams(params);
		}

		return tryToGet(url, headers);
	}

	public static String buildParams(Map<String, ? extends Object> params) throws UnsupportedEncodingException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
			if (entry.getKey() != null && entry.getValue() != null)
				builder.append(entry.getKey().trim()).append("=")
						.append(URLEncoder.encode(entry.getValue().toString(), CHAR_SET)).append("&");
		}

		if (builder.charAt(builder.length() - 1) == '&') {
			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	private static String tryToGet(String url, Map<String, String> headers) throws Exception {
		int tryTime = 0;
		Exception ex = null;
		while (tryTime < mRetry) {
			try {
				return doGet(url, headers);
			} catch (Exception e) {
				if (e != null)
					ex = e;
				tryTime++;
			}
		}
		if (ex != null)
			throw ex;
		else
			throw new Exception("未知网络错误 ");
	}

	private static String doGet(String strUrl, Map<String, String> headers) throws Exception {
		HttpURLConnection connection = null;
		InputStream stream = null;
		try {

			connection = getConnection(strUrl);
			configConnection(connection);
			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			connection.setInstanceFollowRedirects(true);
			connection.connect();

			stream = connection.getInputStream();
			ByteArrayOutputStream obs = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			for (int len = 0; (len = stream.read(buffer)) > 0;) {
				obs.write(buffer, 0, len);
			}
			obs.flush();
			obs.close();
			stream.close();

			return new String(obs.toByteArray());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (stream != null) {
				stream.close();
			}
		}
	}

	public static String post(String url) throws Exception {
		return post(url, null);
	}

	public static String post(String url, Map<String, ? extends Object> params) throws Exception {
		return post(url, params, null);
	}

	public static String post(String url, Map<String, ? extends Object> params, Map<String, String> headers)
			throws Exception {
		if (url == null || url.trim().length() == 0) {
			throw new Exception(TAG + ":url is null or empty!");
		}

		if (params != null && params.size() > 0) {
			return tryToPost(url, buildParams(params), headers);
		} else {
			return tryToPost(url, null, headers);
		}
	}

	public static String post(String url, String content, Map<String, String> headers) throws Exception {
		return tryToPost(url, content, headers);
	}

	private static String tryToPost(String url, String postContent, Map<String, String> headers) throws Exception {
		int tryTime = 0;
		Exception ex = null;
		while (tryTime < mRetry) {
			try {
				return doPost(url, postContent, headers);
			} catch (Exception e) {
				if (e != null)
					ex = e;
				tryTime++;
			}
		}
		if (ex != null)
			throw ex;
		else
			throw new Exception("未知网络错误 ");
	}

	private static String doPost(String strUrl, String postContent, Map<String, String> headers) throws Exception {
		HttpURLConnection connection = null;
		InputStream stream = null;
		try {
			connection = getConnection(strUrl);
			configConnection(connection);
			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			if (null != postContent && !"".equals(postContent)) {
				DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				dos.write(postContent.getBytes(CHAR_SET));
				dos.flush();
				dos.close();
			}
			stream = connection.getInputStream();
			ByteArrayOutputStream obs = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			for (int len = 0; (len = stream.read(buffer)) > 0;) {
				obs.write(buffer, 0, len);
			}
			obs.flush();
			obs.close();

			return new String(obs.toByteArray());

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (stream != null) {
				stream.close();
			}
		}

	}

	private static void configConnection(HttpURLConnection connection) {
		if (connection == null)
			return;
		connection.setReadTimeout(mReadTimeOut);
		connection.setConnectTimeout(mConnectTimeOut);

		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
	}

	private static HttpURLConnection getConnection(String strUrl) throws Exception {
		if (strUrl == null) {
			return null;
		}
		if (strUrl.toLowerCase().startsWith("https")) {
			return getHttpsConnection(strUrl);
		} else {
			return getHttpConnection(strUrl);
		}
	}

	private static HttpURLConnection getHttpConnection(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		return conn;
	}

	private static HttpsURLConnection getHttpsConnection(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setHostnameVerifier(hnv);
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		if (sslContext != null) {
			TrustManager[] tm = { xtm };
			sslContext.init(null, tm, null);
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			conn.setSSLSocketFactory(ssf);
		}

		return conn;
	}

	private static X509TrustManager xtm = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	private static HostnameVerifier hnv = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
}
