package com.example.hardware.Util;

import java.security.MessageDigest;

public class Md5Utils {
	/*
	 * 另外一种实现方式
	private String GetMd5(String imei){
		String result = "";
		if(imei != null){
			try{
				MessageDigest messagedigest = MessageDigest.getInstance("MD5");
				messagedigest.update(imei.getBytes());
				result = String.format("%1$032X", new BigInteger(1, messagedigest.digest()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result.toLowerCase();
	}
	*/
	
	public static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}