
package com.sdx.utils.security;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密相关工具类
 * @author 田广文
 * @date 2012-4-7 下午05:48:56
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

    public static byte[] base64Decode(String content)
    {
        Base64 decoder = new Base64();
        return decoder.decode(content);
    }

    public static String base64Encode(byte[] content) throws IOException
    {
        Base64 decoder = new Base64();
        return decoder.encodeToString(content);
    }

    public static String urlDcode(String content, String encoding) throws IOException
    {
        return URLDecoder.decode(content, encoding);
    }

    public static String urlEncode(String content, String encoding) throws IOException
    {
        return URLEncoder.encode(content, encoding);
    }

    private static final int[] ENCRYPTE_KEY = new int[] { -14, -249, -50, -412, -1752, -3216, -1311, -13118 };

    private static byte[] key = null;

    public static final byte[] getKey()
    {
        if (key == null)
        {
            key = new byte[ENCRYPTE_KEY.length];
            for (int i = 0; i < ENCRYPTE_KEY.length; i++)
            {
                key[i] = back(ENCRYPTE_KEY[i], i);
            }
        }
        return key;
    }

    private static byte back(int t, int i)
    {
        int temp = ~t;
        return (byte) (temp >>> i);
    }
}