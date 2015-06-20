/**
 * 
 */

package com.sdx.utils.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang3.Validate;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author heliang
 */
public class Digests
{

    private static final String SHA1 = "SHA-1";

    private static final String MD5 = "MD5";

    private static SecureRandom random = new SecureRandom();

    private static String[] HexCode = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 对输入字符串进行sha1散列.
     */
    public static byte[] sha1(byte[] input)
    {
        return digest(input, SHA1, null, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt)
    {
        return digest(input, SHA1, salt, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations)
    {
        return digest(input, SHA1, salt, iterations);
    }

    public static FileAbs genFileAbs(File file)
    {
        if (!file.isFile())
        {
            return null;
        }
        MessageDigest digestSha1 = null;
        MessageDigest digestMd5 = null;
        FileInputStream in = null;
        byte[] buffer = new byte[8196];
        int len;
        try
        {
            digestMd5 = MessageDigest.getInstance(MD5);
            digestSha1 = MessageDigest.getInstance(SHA1);
            in = new FileInputStream(file);
            long totalSize = 0;
            while ((len = in.read(buffer)) != -1)
            {
                digestMd5.update(buffer, 0, len);
                digestSha1.update(buffer, 0, len);
                totalSize += len;
            }
            BigInteger bigIntMd5 = new BigInteger(1, digestMd5.digest());
            BigInteger bigIntSha1 = new BigInteger(1, digestSha1.digest());
            FileAbs fileAbs = new FileAbs();
            fileAbs.md5 = bigIntMd5.toString(16).toUpperCase();
            fileAbs.sha1 = bigIntSha1.toString(16).toUpperCase();
            fileAbs.size = totalSize;
            return fileAbs;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static byte[] md5(byte[] input)
    {
        return digest(input, MD5, null, 1);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null)
            {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++)
            {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        }
        catch (GeneralSecurityException e)
        {

            throw new RuntimeException(e);

        }
    }

    /**
     * 生成随机的Byte[]作为salt.
     * @param numBytes
     *        byte数组的大小
     */
    public static byte[] generateSalt(int numBytes)
    {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对文件进行md5散列.
     */
    public static byte[] md5(InputStream input) throws IOException
    {
        return digest(input, MD5);
    }

    /**
     * 对文件进行sha1散列.
     */
    public static byte[] sha1(InputStream input) throws IOException
    {
        return digest(input, SHA1);
    }

    private static byte[] digest(InputStream input, String algorithm) throws IOException
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1)
            {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
        }
        catch (GeneralSecurityException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String encodeByMd5(String sourceString)
    {

        if (sourceString != null)
        {
            byte[] results = md5(sourceString.getBytes());
            return byteArrayToHexString(results);
        }
        return null;
    }

    public static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
        {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HexCode[d1] + HexCode[d2];
    }

    public static String byteArrayToHexString(byte[] b)
    {
        String result = "";
        for (int i = 0; i < b.length; i++)
        {
            result = result + byteToHexString(b[i]);
        }
        return result;
    }

    public static String encodeByShiroMd5(String sourceString)
    {
        return new Md5Hash(sourceString).toHex();
    }

    public static class FileAbs
    {
        private String md5;

        private String sha1;

        private long size;

        /**
         * @return the md5
         */
        public String getMd5()
        {
            return md5;
        }

        /**
         * @return the sha1
         */
        public String getSha1()
        {
            return sha1;
        }

        /**
         * @return the size
         */
        public long getSize()
        {
            return size;
        }
    }
}