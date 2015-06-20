
package com.sdx.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sdx.utils.common.SortMaintainMap;

/**
 * 该类将通过规则管理对象，每个规则对应一个或个对象，而后通过提供输入字符串，查抄匹配至该字符串的规则对应的实体信息。
 * @author 田广文
 * @version 1.0
 * @created 06-3月-2014 13:36:35
 */
public class RuleMap<E>
{
    /*
     * 规则列表
     */
    private Map<String, List<E>> map = new HashMap<String, List<E>>();

    /**
     * 按照优先级获取匹配程度最高的规则，并返回其对应的对象
     * @param input 输入字符串
     * @return 匹配输入字符串的规则对应的对象
     */
    public E getSingleMatchedValue(String input)
    {
        LinkedList<String> matchedList = new LinkedList<String>();
        for (String key : map.keySet())
        {
            if (matches(input, key) && map.get(key) != null)
            {
                matchedList.add(key);
            }
        }
        if (matchedList.size() > 0)
        {
            // 按照字符串顺序排序
            Collections.sort(matchedList);
            return map.get(matchedList.getLast()).get(0);
        }
        return null;
    }

    /**
     * 获取全部匹配的规则对应的对象列表
     * @param input 输入字符串
     * @return 匹配输入字符串的规则对应的对象列表
     */
    public List<E> getMatchedValues(String input)
    {
        LinkedList<String> resultStr = new LinkedList<String>();
        LinkedList<E> result = new LinkedList<E>();
        for (String key : map.keySet())
        {
            if (matches(input, key))
                resultStr.add(key);
        }
        Collections.sort(resultStr);
        Collections.reverse(resultStr);
        for (String key : resultStr)
        {
            List<E> obj = map.get(key);
            for (E e : obj)
            {
                if (!result.contains(e))
                    result.add(e);
            }
        }
        return result;
    }

    /**
     * 获取全部匹配的规则对应的对象列表
     * @param input 输入字符串
     * @return 匹配输入字符串的规则对应的对象列表
     */
    public Map<String, E> getMatchedMap(String input)
    {
        Map<String, E> resultMap = new SortMaintainMap<String, E>();
        LinkedList<String> result = new LinkedList<String>();
        for (String key : map.keySet())
        {
            if (matches(input, key))
                result.add(key);
        }
        Collections.sort(result);
        Collections.reverse(result);
        for (String key : result)
        {
            List<E> obj = map.get(key);
            for (E e : obj)
            {
                resultMap.put(key, e);
            }
        }
        return resultMap;
    }

    /**
     * 增加规则
     * @param rule 规则
     * @param obj 规则对应对象
     */
    public void addRule(String rule, E obj)
    {
        List<E> objs = map.get(rule);
        if (objs == null)
        {
            objs = new LinkedList<E>();
            map.put(rule, objs);
        }
        if (!objs.contains(obj))
            objs.add(obj);
    }

    /**
     * 增加规则
     * @param rule 规则
     * @param obj 规则对应对象
     */
    public void addRules(String rules, E obj)
    {
        if (StringUtils.isNotBlank(rules))
        {
            String[] rs = rules.split(";");
            for (String r : rs)
                this.addRule(r, obj);
        }
    }

    /**
     * 清除规则
     */
    public void clear()
    {
        this.map.clear();
    }

    public boolean containsRule(String rule)
    {
        return map.containsKey(rule);
    }

    /*
     * 检查规则和输入字符串是否匹配，目前仅支持?和*的通配符
     * @param input 输入字符串
     * @param rule 规则
     * @return 是否匹配
     */
    private static boolean matches(CharSequence input, CharSequence rule)
    {
        int length = rule.length() < input.length() ? rule.length() : input.length();
        char current, next;
        int p = 0;
        for (int i = 0; p < length; p++, i++)
        {
            switch (current = rule.charAt(p))
            {
                case '?':
                    continue;
                case '\\':
                    p++;
                    continue;
                case '*':
                    if (length == 1 || p == length - 1 || p == length)
                    {
                        continue;
                    }
                    next = rule.charAt(++p);
                    i = StringUtils.indexOf(input, next, i);
                    continue;
                default:
                    if (input.charAt(i) == current)
                    {
                        continue;
                    }
                    return false;
            }
        }
        if (input.length() < rule.length())
            while (p < rule.length())
            {
                current = rule.charAt(p++);
                if (current != '*')
                    return false;
            }
        return true;
    }
}