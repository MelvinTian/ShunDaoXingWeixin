
package com.sdx.utils.date;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 遍历两个日期之间天数的算法
 */
public class DateTimeUtils extends DateUtils
{

    public static long MS_IN_MINUTE = 1000L * 60;

    public static long MS_IN_HOUR = MS_IN_MINUTE * 60;

    public static long MS_IN_DAY = MS_IN_HOUR * 24;

    public static Date min(Date... dates)
    {
        List<Date> dls = Arrays.asList(dates);
        sortDate(dls, "");
        if (CollectionUtils.isNotEmpty(dls))
        {
            return dls.iterator().next();
        }
        return null;
    }

    public static Date max(Date... dates)
    {
        List<Date> dls = Arrays.asList(dates);
        sortDate(dls, "desc");
        if (CollectionUtils.isNotEmpty(dls))
        {
            return dls.iterator().next();
        }
        return null;
    }

    public static List<Date> sortDate(List<Date> dates, final String descOrAsc)
    {
        Collections.sort(dates, new Comparator<Date>()
        {
            int t1 = 1;

            int t0 = 0;

            public int compare(Date o1, Date o2)
            {
                if (StringUtils.isNotBlank(descOrAsc) && StringUtils.equalsIgnoreCase(descOrAsc, "desc"))
                {
                    t1 = 0;
                    t0 = 1;
                }
                if (null != o1 && null != o2)
                {
                    if (o1.after(o2))
                    {
                        return t1;
                    }
                }
                else if (null == o1)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
                return t0;
            }
        });
        return dates;
    }

    /**
     * 两个日期之间相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int startToEndCount(Date startDate, Date endDate)
    {
        String shortDataFormat = "yyyy-MM-dd";
        try
        {
            startDate = DateTimeUtils.parseDate(DateFormatUtils.format(startDate, shortDataFormat), shortDataFormat);
            endDate = DateTimeUtils.parseDate(DateFormatUtils.format(endDate, shortDataFormat), shortDataFormat);
            return (int) ((endDate.getTime() - startDate.getTime()) / MS_IN_DAY);
        }
        catch (ParseException e)
        {
            return -1;
        }
    }
}