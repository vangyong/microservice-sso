package cn.segema.cloud.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {

	public static Properties read(String fileName) {
		InputStream in = null;
		try {
			Properties prop = new Properties();
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			if (in == null) {
				return null;
			}
			prop.load(in);
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String readKeyValue(String fileName, String key) {
		Properties prop = read(fileName);
		if (prop != null) {
			return prop.getProperty(key);
		}
		return null;
	}

	public static String readKeyValue(Properties prop, String key) {
		if (prop != null) {
			return prop.getProperty(key);
		}
		return null;
	}

	public static void writeValueByKey(String fileName, String key, String value) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(key, value);
		writeValues(fileName, properties);
	}

	public static void writeValues(String fileName, Map<String, String> properties) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			if (in == null) {
				throw new RuntimeException("the file (" + fileName + ") not exist");
			}
			Properties prop = new Properties();
			prop.load(in);
			String path = PropertiesUtil.class.getResource("/" + fileName).getPath();
			out = new FileOutputStream(path);
			if (properties != null) {
				Set<String> set = properties.keySet();
				for (String string : set) {
					prop.setProperty(string, properties.get(string));
				}
			}
			prop.store(out, "update properties");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
