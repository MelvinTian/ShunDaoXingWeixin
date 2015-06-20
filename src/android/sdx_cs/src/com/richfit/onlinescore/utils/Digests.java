/**
 * 
 */

package com.richfit.onlinescore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Locale;

/**
 * @author heliang
 */
public class Digests
{
    private static final String MD5 = "MD5";

    public static FileAbs genFileAbs(File file)
    {
        if (!file.isFile())
        {
            return null;
        }
        MessageDigest digestMd5 = null;
        FileInputStream in = null;
        byte[] buffer = new byte[8196];
        int len;
        try
        {
            digestMd5 = MessageDigest.getInstance(MD5);
            in = new FileInputStream(file);
            long totalSize = 0;
            while ((len = in.read(buffer)) != -1)
            {
                digestMd5.update(buffer, 0, len);
                totalSize += len;
            }
            BigInteger bigIntMd5 = new BigInteger(1, digestMd5.digest());
            FileAbs fileAbs = new FileAbs();
            fileAbs.md5 = bigIntMd5.toString(16).toUpperCase(Locale.CHINESE);
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
     * 对文件进行md5散列.
     */
    public static byte[] md5(InputStream input) throws IOException
    {
        return digest(input, MD5);
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

    public static class FileAbs
    {
        private String md5;

        private long size;

        /**
         * @return the md5
         */
        public String getMd5()
        {
            return md5;
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