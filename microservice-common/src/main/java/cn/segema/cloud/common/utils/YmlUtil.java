package cn.segema.cloud.common.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

public class YmlUtil {

	private static String bootstrap_file = "bootstrap.yml";
	private static Map<String, String> result = new HashMap<>();

	public static Map<String, String> getYmlByFileName(String file) {
		result = new HashMap<>();
		if (file == null) {
			file = bootstrap_file;
			InputStream in = YmlUtil.class.getClassLoader().getResourceAsStream(file);
			Yaml props = new Yaml();
			Object obj;
			if (in != null) {
				obj = props.loadAs(in, Map.class);
			} else {
				Resource app = new ClassPathResource("application.yml");
//				Resource appDev = new ClassPathResource("application-dev.yml");
//				Resource appLocalDev = new ClassPathResource("application-prod.yml");
//				Resource appPre = new ClassPathResource("application-test.yml");
				YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//				yamlPropertiesFactoryBean.setResources(app, appDev, appLocalDev, appPre);
				yamlPropertiesFactoryBean.setResources(app);
				obj = yamlPropertiesFactoryBean.getObject();
			}
			Map<String, Object> param = (Map<String, Object>) obj;
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				String key = entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Map) {
					forEachYaml(key, (Map<String, Object>) val);
				} else {
					result.put(key, val.toString());
				}
			}
		}
		return result;
	}

	public static String getValue(String key) {
		Map<String, String> map = getYmlByFileName(null);
		if (map == null)
			return null;
		return map.get(key);
	}

	public static Map<String, String> forEachYaml(String key_str, Map<String, Object> obj) {
		for (Map.Entry<String, Object> entry : obj.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			String str_new = "";
			if (!StringUtils.isEmpty(key_str)) {
				str_new = key_str + "." + key;
			} else {
				str_new = key;
			}
			if (val instanceof Map) {
				forEachYaml(str_new, (Map<String, Object>) val);
			} else {
				result.put(str_new, val.toString());
			}
		}
		return result;
	}

	public static String getApplicationName() {
		return getYmlByFileName(bootstrap_file).get("spring.application.name");
	}

}
