
package com.sdx.utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class UtilMisc
{
    protected static class SimpleMap implements Map, Serializable
    {

        private static final long serialVersionUID = 0xcfbd64c09abc8e8aL;

        protected Map realMapIfNeeded;

        String names[];

        Object values[];

        protected void makeRealMap()
        {
            realMapIfNeeded = new HashMap();
            for (int i = 0; i < names.length; i++)
                realMapIfNeeded.put(names[i], values[i]);

            names = null;
            values = null;
        }

        public void clear()
        {
            if (realMapIfNeeded != null)
            {
                realMapIfNeeded.clear();
            }
            else
            {
                realMapIfNeeded = new HashMap();
                names = null;
                values = null;
            }
        }

        public boolean containsKey(Object obj)
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.containsKey(obj);
            for (int i = 0; i < names.length; i++)
            {
                if (obj == null && names[i] == null)
                    return true;
                if (names[i] != null && names[i].equals(obj))
                    return true;
            }

            return false;
        }

        public boolean containsValue(Object obj)
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.containsValue(obj);
            for (int i = 0; i < names.length; i++)
            {
                if (obj == null && values[i] == null)
                    return true;
                if (values[i] != null && values[i].equals(obj))
                    return true;
            }

            return false;
        }

        public Set entrySet()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.entrySet();
            }
            else
            {
                makeRealMap();
                return realMapIfNeeded.entrySet();
            }
        }

        public Object get(Object obj)
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.get(obj);
            for (int i = 0; i < names.length; i++)
            {
                if (obj == null && names[i] == null)
                    return values[i];
                if (names[i] != null && names[i].equals(obj))
                    return values[i];
            }

            return null;
        }

        public boolean isEmpty()
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.isEmpty();
            return names.length == 0;
        }

        public Set keySet()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.keySet();
            }
            else
            {
                makeRealMap();
                return realMapIfNeeded.keySet();
            }
        }

        public Object put(String s, Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.put(s, obj);
            }
            else
            {
                makeRealMap();
                return realMapIfNeeded.put(s, obj);
            }
        }

        public void putAll(Map map)
        {
            if (realMapIfNeeded != null)
            {
                realMapIfNeeded.putAll(map);
            }
            else
            {
                makeRealMap();
                realMapIfNeeded.putAll(map);
            }
        }

        public Object remove(Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.remove(obj);
            }
            else
            {
                makeRealMap();
                return realMapIfNeeded.remove(obj);
            }
        }

        public int size()
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.size();
            else
                return names.length;
        }

        public Collection values()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.values();
            }
            else
            {
                makeRealMap();
                return realMapIfNeeded.values();
            }
        }

        public String toString()
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.toString();
            StringBuffer stringbuffer = new StringBuffer("{");
            for (int i = 0; i < names.length; i++)
            {
                if (i > 0)
                    stringbuffer.append(',');
                stringbuffer.append('{');
                stringbuffer.append(names[i]);
                stringbuffer.append(',');
                stringbuffer.append(values[i]);
                stringbuffer.append('}');
            }

            stringbuffer.append('}');
            return stringbuffer.toString();
        }

        public int hashCode()
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.hashCode();
            int i = 0;
            for (int j = 0; j < names.length; j++)
            {
                int k = (names[j] != null ? names[j].hashCode() : 0) ^ (values[j] != null ? values[j].hashCode() : 0);
                i += k;
            }

            return i;
        }

        public boolean equals(Object obj)
        {
            if (realMapIfNeeded != null)
                return realMapIfNeeded.equals(obj);
            Map map = (Map) obj;
            if (map.size() != names.length)
                return false;
            for (int i = 0; i < names.length; i++)
            {
                if (!map.containsKey(names[i]))
                    return false;
                Object obj1 = map.get(names[i]);
                if (obj1 == null)
                {
                    if (values[i] != null)
                        return false;
                    continue;
                }
                if (!obj1.equals(values[i]))
                    return false;
            }

            return true;
        }

        public Object put(Object obj, Object obj1)
        {
            return put((String) obj, obj1);
        }

        public SimpleMap()
        {
            realMapIfNeeded = null;
            names = new String[0];
            values = new Object[0];
        }

        public SimpleMap(String s, Object obj)
        {
            realMapIfNeeded = null;
            names = new String[1];
            values = new Object[1];
            names[0] = s;
            values[0] = obj;
        }

        public SimpleMap(String s, Object obj, String s1, Object obj1)
        {
            realMapIfNeeded = null;
            names = new String[2];
            values = new Object[2];
            names[0] = s;
            values[0] = obj;
            names[1] = s1;
            values[1] = obj1;
        }

        public SimpleMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2)
        {
            realMapIfNeeded = null;
            names = new String[3];
            values = new Object[3];
            names[0] = s;
            values[0] = obj;
            names[1] = s1;
            values[1] = obj1;
            names[2] = s2;
            values[2] = obj2;
        }

        public SimpleMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2, String s3, Object obj3)
        {
            realMapIfNeeded = null;
            names = new String[4];
            values = new Object[4];
            names[0] = s;
            values[0] = obj;
            names[1] = s1;
            values[1] = obj1;
            names[2] = s2;
            values[2] = obj2;
            names[3] = s3;
            values[3] = obj3;
        }
    }

    public static final String UNICODE = "unicode";

    public UtilMisc()
    {
    }

    public static void main(String args[]) throws IOException
    {
        File file = new File(
                "E:/wangwen/\u6784\u67B6\u6587\u6863/2.\u67B6\u6784\u8BBE\u8BA1/MAR2\u5E73\u53F0/ui/UI\u5236\u4F5C\u89C4\u8303.docx");
        System.out.println(getWidthAndHeight(FileUtils.openInputStream(file))[0]);
    }

    public static int[] getWidthAndHeight(InputStream inputstream) throws IOException
    {
        int ai[] = new int[2];
        java.awt.image.BufferedImage bufferedimage = ImageIO.read(inputstream);
        ai[0] = bufferedimage.getWidth();
        ai[1] = bufferedimage.getHeight();
        return ai;
    }

    public static int[] getWidthAndHeight(File file) throws IOException
    {
        FileInputStream fileinputstream = null;
        int ai[];
        fileinputstream = FileUtils.openInputStream(file);
        ai = getWidthAndHeight(((InputStream) (fileinputstream)));
        IOUtils.closeQuietly(fileinputstream);
        return ai;
    }

    public static String getFormatName(Object obj)
    {
        ImageInputStream imageinputstream;
        Iterator iterator;
        ImageReader imagereader;
        try
        {
            imageinputstream = ImageIO.createImageInputStream(obj);
            iterator = ImageIO.getImageReaders(imageinputstream);
            if (!iterator.hasNext())
                return null;

            imagereader = (ImageReader) iterator.next();
            imageinputstream.close();
            return imagereader.getFormatName();
        }
        catch (IOException ioexception)
        {
            return null;
        }
    }

    public static Iterator toIterator(Collection collection)
    {
        if (collection == null)
            return null;
        else
            return collection.iterator();
    }

    public static Map toMap(String s, Object obj)
    {
        return new SimpleMap(s, obj);
    }

    public static Map toMap(String s, Object obj, String s1, Object obj1)
    {
        return new SimpleMap(s, obj, s1, obj1);
    }

    public static Map toMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2)
    {
        return new SimpleMap(s, obj, s1, obj1, s2, obj2);
    }

    public static Map toMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2, String s3, Object obj3)
    {
        return new SimpleMap(s, obj, s1, obj1, s2, obj2, s3, obj3);
    }

    public static Map toMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2, String s3, Object obj3, String s4,
            Object obj4)
    {
        HashMap hashmap = new HashMap();
        hashmap.put(s, obj);
        hashmap.put(s1, obj1);
        hashmap.put(s2, obj2);
        hashmap.put(s3, obj3);
        hashmap.put(s4, obj4);
        return hashmap;
    }

    public static Map toMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2, String s3, Object obj3, String s4,
            Object obj4, String s5, Object obj5)
    {
        HashMap hashmap = new HashMap();
        hashmap.put(s, obj);
        hashmap.put(s1, obj1);
        hashmap.put(s2, obj2);
        hashmap.put(s3, obj3);
        hashmap.put(s4, obj4);
        hashmap.put(s5, obj5);
        return hashmap;
    }

    public static Map toMap(String s, Object obj, String s1, Object obj1, String s2, Object obj2, String s3, Object obj3, String s4,
            Object obj4, String s5, Object obj5, String s6, Object obj6)
    {
        HashMap hashmap = new HashMap();
        hashmap.put(s, obj);
        hashmap.put(s1, obj1);
        hashmap.put(s2, obj2);
        hashmap.put(s3, obj3);
        hashmap.put(s4, obj4);
        hashmap.put(s5, obj5);
        hashmap.put(s6, obj6);
        return hashmap;
    }

    public static Map toMap(Object aobj[])
    {
        if (aobj == null)
            return null;
        if (aobj.length % 2 == 1)
            throw new IllegalArgumentException("You must pass an even sized array to the toMap method");
        HashMap hashmap = new HashMap();
        for (int i = 0; i < aobj.length;)
            hashmap.put(aobj[i++], aobj[i++]);

        return hashmap;
    }

    public static String printMap(Map map)
    {
        StringBuffer stringbuffer = new StringBuffer();
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); stringbuffer.append("\n"))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
            stringbuffer.append(entry.getKey());
            stringbuffer.append(" --> ");
            stringbuffer.append(entry.getValue());
        }

        return stringbuffer.toString();
    }

    public static Object removeFirst(List list)
    {
        return list.remove(0);
    }

    public static List toList(Object obj)
    {
        List list = Collections.singletonList(obj);
        return list;
    }

    public static List toList(Object obj, Object obj1)
    {
        ArrayList arraylist = new ArrayList(2);
        arraylist.add(obj);
        arraylist.add(obj1);
        return arraylist;
    }

    public static List toList(Object obj, Object obj1, Object obj2)
    {
        ArrayList arraylist = new ArrayList(3);
        arraylist.add(obj);
        arraylist.add(obj1);
        arraylist.add(obj2);
        return arraylist;
    }

    public static List toList(Object obj, Object obj1, Object obj2, Object obj3)
    {
        ArrayList arraylist = new ArrayList(4);
        arraylist.add(obj);
        arraylist.add(obj1);
        arraylist.add(obj2);
        arraylist.add(obj3);
        return arraylist;
    }

    public static List toList(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
    {
        ArrayList arraylist = new ArrayList(5);
        arraylist.add(obj);
        arraylist.add(obj1);
        arraylist.add(obj2);
        arraylist.add(obj3);
        arraylist.add(obj4);
        return arraylist;
    }

    public static List toList(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        ArrayList arraylist = new ArrayList(6);
        arraylist.add(obj);
        arraylist.add(obj1);
        arraylist.add(obj2);
        arraylist.add(obj3);
        arraylist.add(obj4);
        arraylist.add(obj5);
        return arraylist;
    }

    public static List toList(Collection collection)
    {
        if (collection == null)
            return null;
        if (collection instanceof List)
            return (List) collection;
        else
            return new ArrayList(collection);
    }

    public static List toList(Object aobj[])
    {
        if (aobj == null)
            return null;
        ArrayList arraylist = new ArrayList(aobj.length);
        for (int i = 0; i < aobj.length; i++)
            arraylist.add(aobj[i]);

        return arraylist;
    }

    public static void addToDoubleInMap(Map map, Object obj, Double double1)
    {
        Double double2 = (Double) map.get(obj);
        if (double2 != null)
            map.put(obj, new Double(double2.doubleValue() + double1.doubleValue()));
        else
            map.put(obj, double1);
    }

    public static Locale parseLocale(String s)
    {
        if (s == null || s.length() == 0)
            return null;
        Locale locale = null;
        if (s.length() == 2)
            locale = new Locale(s);
        else if (s.length() == 5)
        {
            String s1 = s.substring(0, 2);
            String s3 = s.substring(3, 5);
            locale = new Locale(s1, s3);
        }
        else if (s.length() > 6)
        {
            String s2 = s.substring(0, 2);
            String s4 = s.substring(3, 5);
            String s5 = s.substring(6);
            locale = new Locale(s2, s4, s5);
        }
        return locale;
    }

    public static Locale ensureLocale(Object obj)
    {
        if (obj != null && (obj instanceof String))
            obj = parseLocale((String) obj);
        if (obj != null && (obj instanceof Locale))
            return (Locale) obj;
        else
            return Locale.getDefault();
    }

    public static boolean isPeer(int i)
    {
        return i % 2 == 0;
    }

    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
            return true;
        else
            return StringUtils.isBlank(obj.toString());
    }

    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }
}
