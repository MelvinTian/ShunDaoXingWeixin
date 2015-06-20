
package com.sdx.utils.common;

import java.util.HashSet;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class RandomUtils extends RandomStringUtils
{

    /**
     * 从myint字符串字数中组成你需要的随机字符串
     * @param mylength
     *        长度
     * @param count
     *        数量
     * @param stard
     *        开始字符串
     * @param end
     *        结束字符串
     * @param myint
     *        数组
     * @return
     */
    public static HashSet<String> defineRand(int mylength, int count, String stard, String end, String[] myint)
            throws IllegalArgumentException
    {
        if (count > 1000)
            throw new IllegalArgumentException("count is too lang");

        HashSet<String> myhset = new java.util.HashSet<String>();
        String str = "";
        int j = 0;

        while (j < count)
        {
            str = getDefinRandString(stard, end, mylength, myint);
            if (!myhset.contains(str.toString()))
            {
                myhset.add(str.toString());
                j++;
            }
        }
        return myhset;
    }

    /**
     * 从自定义字符串数组中产生一个随机的字符串
     * @param myLength
     *        长度
     * @param str
     *        数组
     * @return
     */
    public static String getRandString(int myLength, String[] str)
    {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < myLength; i++)
        {
            sbf.append(getOneString(str));
        }
        return sbf.toString();
    }

    /**
     * 从自定义字符串数组中产生一个随机的字符
     * @param str
     *        数组
     * @return
     */
    public static String getOneString(String[] str)
    {
        Random r = new Random();
        return str[r.nextInt(str.length)];
    }

    /**
     * 自定义随机字符串
     * @param start
     *        开始字符串
     * @param end
     *        结束字符串
     * @param middleLength
     *        长度
     * @param str
     *        数组
     * @return
     */
    public static String getDefinRandString(String start, String end, int middleLength, String[] str)
    {
        StringBuffer sbf = new StringBuffer();
        sbf.append(start);
        sbf.append(getRandString(middleLength, str));
        sbf.append(end);
        return sbf.toString();
    }
}
