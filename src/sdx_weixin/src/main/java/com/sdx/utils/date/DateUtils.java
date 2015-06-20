
package com.sdx.utils.date;

import org.apache.commons.lang.StringUtils;

import com.sdx.utils.common.Exceptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: hbweilingfeng@126.com Date: 13-5-16 Time: 下午3:38 Descrption: update by
 * : lsk update time : 13-8-22
 */
public class DateUtils
{
    public static String DEFAULT_DATEFORMAT = "yyyy-MM-dd";

    private DateUtils()
    {

    }

    private static DateUtils instance = new DateUtils();

    public static DateUtils getInstance()
    {
        return instance;
    }

    public static Date GetCurrentDate()
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        return c.getTime();
    }

    public static String formatDate(Date date, String format/* "yyyy-MM-dd hh:mm:ss" */)
    {
        DateFormat df = new SimpleDateFormat(format);
        if (null == date || StringUtils.isEmpty(format))
        {
            return "";
        }
        return df.format(date);
    }

    public static Date formatStringDate(String dateStr, String format)
    {
        DateFormat df = new SimpleDateFormat(format);
        try
        {
            Date date = df.parse(dateStr);
            return date;
        }
        catch (ParseException e)
        {
            Exceptions.unchecked(e);
        }
        return null;
    }

    public static Date parseDate(String date, String format) throws ParseException
    {
        Date d = org.apache.commons.lang3.time.DateUtils.parseDate(date, format);
        return d;
    }

    public static Date addDayToDate(Date date, int day)
    {
        if (null == date)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static String addDayFormatDate(Date date, int day, String format)
    {
        DateFormat df = new SimpleDateFormat(format);
        if (null == date || StringUtils.isEmpty(format))
        {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return df.format(calendar.getTime());
    }

    // 获得当前日期与本周日相差的天数
    public static int getMondayPlus()
    {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1)
        {
            return 0;
        }
        else
        {
            return 1 - dayOfWeek;
        }
    }

    // 获得计算后的日期
    public static String getMathday(int plus, String format)
    {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + plus);
        Date date = currentDate.getTime();
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    // 本周一
    public static String getMonDay()
    {
        return getMathday(0, DEFAULT_DATEFORMAT);
    }

    // 本周日
    public static String getSunDay()
    {
        return getMathday(6, DEFAULT_DATEFORMAT);
    }

    // 上周一
    public static String getLastMonDay()
    {
        return getMathday(-7, DEFAULT_DATEFORMAT);
    }

    // 上周日
    public static String getLastSunDay()
    {
        return getMathday(-1, DEFAULT_DATEFORMAT);
    }

    // 获取当月第一天
    public static String getFirstDayOfMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATEFORMAT);
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        return sdf.format(lastDate.getTime());
    }

    // 获取当月最后一天
    public static String getLastDayOfMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATEFORMAT);
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        return sdf.format(lastDate.getTime());
    }

    // 获取上月第一天
    public static String getFirstDayOfLastMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATEFORMAT);
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, -1);// 减一个月，变为上月的1号
        return sdf.format(lastDate.getTime());
    }

    // 获取上月最后一天
    public static String getLastDayOfLastMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATEFORMAT);
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为上月最后一天
        return sdf.format(lastDate.getTime());
    }
}
