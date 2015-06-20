package com.richfit.onlinescore.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密相关工具类
 * 
 * @author	田广文
 * @date	2012-4-7 下午05:48:56
 */
public class EncryptUtils
{
	public static byte[] encryptDES(byte[] key, byte[] content) throws GeneralSecurityException
	{
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(key, "DES");
		// 加密
		Cipher c1 = Cipher.getInstance("DES");
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		return c1.doFinal(content);
	}

	public static byte[] decryptDES(byte[] key, byte[] content) throws GeneralSecurityException
	{
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(key, "DES");
		// 加密
		Cipher c1 = Cipher.getInstance("DES");
		c1.init(Cipher.DECRYPT_MODE, deskey);
		return c1.doFinal(content);
	}

	public static String urlDcode(String content, String encoding) throws IOException
	{
		return URLDecoder.decode(content, encoding);
	}

	public static String urlEncode(String content, String encoding) throws IOException
	{
		return URLEncoder.encode(content, encoding);
	}
}