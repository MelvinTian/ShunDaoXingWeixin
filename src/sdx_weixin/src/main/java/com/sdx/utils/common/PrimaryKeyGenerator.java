/**
 * 
 */

package com.sdx.utils.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heliang
 */
public final class PrimaryKeyGenerator
{
    private static final long ONE_STEP = 100;

    private static long lastTime = System.currentTimeMillis();

    private static short count = 0;

    public static Map<String, String> systemMap = new HashMap<String, String>()
    {
        /**
         * 
         */
    	{
    		put("plat", "270");
    		put("raw", "280");
    	}
        private static final long serialVersionUID = 8721127431655705723L;

        {
//            put("sys", "100");
        }
    };

    public static Map<String, String> workMap = new HashMap<String, String>()
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1211295677461138424L;

        {
//            put("INC_INCIDENT", "INC");
        }
    };

    public static Map<String, String> tableMap = new HashMap<String, String>()
    {
        /**
         * 
         */
    	{
    		put("platform_info", "001");
    		put("pages_info", "002");
    	}
        private static final long serialVersionUID = -2899324534846845193L;

        {
//            put("cfg_configurationitem", "001");
        }
    };

    static public synchronized Long getPrimaryKey(String sysname, String tablename, int i)
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
        String result =lastTime + "" + formatCount;
        return  Long.valueOf(result);
    }
}
