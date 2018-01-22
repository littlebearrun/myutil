package com.likang.myutil.util.tools;

import java.util.Random;
import java.util.UUID;

/**
 *
 *@Author likang  2018年1月11日 下午3:43:22
 *
 */
public class UUIDUtil {

	public static String getIdString(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static long createLongId() {
		Random random = new Random();
		long time = System.currentTimeMillis();
		long id = time+random.nextInt(10000);
		return id;
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		System.err.println(random.nextInt(10000));
	}
}
