package cn.segema.cloud.common.utils;

public class UtilsTestMain {

	public static void main(String[] args) {
		System.out.println("测试工具类");
		long id = IdGeneratorUtil.generateSnowFlakeId();
		System.out.println("id:"+id);
	}

}
