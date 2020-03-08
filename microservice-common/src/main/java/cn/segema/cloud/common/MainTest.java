package cn.segema.cloud.common;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.HttpClientUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;

public class MainTest {
	public static void main(String[] args) {
		/**
		 * 测试下载文件 异步下载
		 */
//		HttpClientUtil.getInstance().download("http://zhaopin.feo.test/zhaopin/rs/zhilian/5831241667.html",
//				"C:\\Users\\xiekun\\Desktop\\resume download\\5831241667.html",
//				new HttpClientUtil.HttpClientDownLoadProgress() {
//
//					@Override
//					public void onProgress(int progress) {
//						System.out.println("download progress = " + progress);
//					}
//				});

		// POST 同步方法
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("username", "admin");
//		params.put("password", "admin");
//		HttpClientUtil.getInstance().httpPost("http://192.168.31.183:8080/SSHMySql/register", params);
//
//		// GET 同步方法
//		HttpClientUtil.getInstance().httpGet("http://wthrcdn.etouch.cn/weather_mini?city=北京");
//
//		// 上传文件 POST 同步方法
//		try {
//			Map<String, String> uploadParams = new LinkedHashMap<String, String>();
//			uploadParams.put("userImageContentType", "image");
//			uploadParams.put("userImageFileName", "testaa.png");
//			HttpClientUtil.getInstance().uploadFileImpl("http://192.168.31.183:8080/SSHMySql/upload",
//					"android_bug_1.png", "userImage", uploadParams);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//snowId生成
//		long snowId = IdGeneratorUtil.generateSnowFlakeId();
//		System.out.println(snowId);
		
		//时间生成
		LocalTime localTime = DateTimeUtil.getCurrentLocalTime();
		System.out.println(localTime);
	}
}
