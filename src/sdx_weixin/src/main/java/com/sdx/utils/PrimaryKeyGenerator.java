/**
 * 
 */

package com.sdx.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author qi
 */
public final class PrimaryKeyGenerator
{
    private static Random random = new Random(System.currentTimeMillis());

    private static int subNum = random.nextInt(9);

    private static final long ONE_STEP = 100;

    private static long lastTime = System.currentTimeMillis();

    private static short count = 0;

    private static String tablename = "flow";

    public static Map<String, String> systemMap = new HashMap<String, String>()
    {
        {
            put("flow", "66");
            put("raw_res_info", "67");
        }
    };

    public static Map<String, String> tableMap = new HashMap<String, String>()
    {
        {
            put("flow", "01");
            put("raw_res_info", "02");
        }
    };

    /**
     * 1秒内浮动数字
     */
    static private int getSubNum()
    {
        if (subNum > 1000)
        {
            subNum = random.nextInt(9);
        }
        return subNum++;
    }

    static public synchronized long getPrimaryKeyByLong(String sysname)
    {
        return Long.parseLong(getPrimaryKey(sysname, 0));
    }

    static public synchronized BigDecimal getPrimaryKeyByDecimal(String sysname)
    {
        return new BigDecimal(getPrimaryKey(sysname, 0));
    }

    static public synchronized String getPrimaryKey(String sysname, int i)
    {
        try
        {
            if (count == ONE_STEP)
            {
                boolean done = false;
                while (!done)
                {
                    long now = System.currentTimeMillis();
                    if (now == lastTime)
                    {
                        try
                        {
                            Thread.sleep(1);
                        }
                        catch (java.lang.InterruptedException e)
                        {
                        }
                        continue;
                    }
                    else
                    {
                        lastTime = now;
                        count = 0;
                        done = true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        String formatCount = "";
        count++;
        if (count < 10)
        {
            formatCount = "0" + count;
        }
        else
        {
            formatCount = Short.toString(count);
        }
        String result = systemMap.get(sysname) + tableMap.get(tablename) + lastTime + "" + formatCount;
        return result;
    }
}
