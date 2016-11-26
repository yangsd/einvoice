package com.management.util;

import java.util.Random;

/**
 * 获取随机KEY
 * 
 * @author sdyang
 * @date 2015年12月31日 上午11:02:17
 */
public class RandomKey {

	private static int LENGTH = 8;

	private static String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String getRandomKey() {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < LENGTH; i++) {
			sb.append(BASE.charAt(random.nextInt(BASE.length())));
		}
		return sb.toString();
	}

	public static String getRandomKey(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(BASE.charAt(random.nextInt(BASE.length())));
		}
		return sb.toString();
	}
}
