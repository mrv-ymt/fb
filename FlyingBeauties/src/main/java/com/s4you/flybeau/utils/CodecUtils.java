package com.s4you.flybeau.utils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * CodecUtils 
 * Date: 10/05/2016 
 * ThienMV
 * 
 * */
public class CodecUtils {
	
	/**
	 * 
	 * @param input
	 * @return String MD5 Code
	 */
	public static String encrypt(String input)
	{
		return DigestUtils.md5Hex(input + "!@#$%^&*()");
	}	
}