/* 
 * Copyright (C) 2013,中油瑞飞, 
 * All Rights Reserved 
 * Description: 文件相关流程
 * 
 * @project 数据中心运行管理系统
 * @author  田广文
 * @date    2014年5月20日-下午2:12:07
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年5月20日	       Melvin		     	Create
 **********************************************************
 */

package com.richfit.onlinescore.utils;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库操作工具类
 * @author 田广文
 * @date 2014年5月20日-下午2:12:07
 */
public class SQLiteUtils
{
	//插入操作
    public static boolean insert(SQLiteDatabase db, String tableName, ContentValues values)
    {
        long i = db.insert(tableName, null, values);
//        db.close();
        return i > 0;
    }
    //创建表
    public static void createTable(SQLiteDatabase db, String tableName, String... cols)
    {
        StringBuffer sql = new StringBuffer("CREATE TABLE ");
        sql.append(tableName).append("(");
        for (int i = 0; i < cols.length - 1; i++)
        {
            sql.append(cols[i]).append(" ").append(cols[i + 1]).append(",");
            i++;
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        db.execSQL(sql.toString());
    }
    //删除表
    public static void dropTable(SQLiteDatabase db, String tableName)
    {
        try
        {
            String sql = "DROP TABLE " + tableName;
            db.execSQL(sql);
        }
        catch (Exception ex)
        {
        }
    }
    //删除数据库
    public static void deleteDatabase(Context ctx, String databaseName)
    {
        try
        {
            ctx.deleteDatabase(databaseName);
        }
        catch (Exception ex)
        {
        }
    }
    //更新表数据
    public static boolean update(SQLiteDatabase db, String tableName, ContentValues values, String where, String[] whereArgs)
    {
        int i = db.update(tableName, values, where, whereArgs);
//        db.close();
        return i > 0;
    }

    public static void delete(SQLiteDatabase db, String tableName, String where, String[] whereArgs)
    {
        db.delete(tableName, where, whereArgs);
//        db.close();
    }
    //删除数据库
    public static void dropDatabase(SQLiteDatabase db, String database)
    {
        db.execSQL("DROP Database " + database);
//        db.close();
    }
    //查询
    public static <E> List<E> query(SQLiteDatabase db, String tableName, String where, String[] whereArgs, String orderBy, String limit,
            BeanConverter<E> converter)
    {
        List<E> lst = new LinkedList<E>();
        try
        {
            Cursor c = null;
            c = db.query(tableName, null, where, whereArgs, null, null, orderBy, limit);

            while (c != null && c.moveToNext())
            {
                E item = converter.convert(c);
                lst.add(item);
            }
            if (c != null)
                c.close();
            return lst;
        }
        catch (Exception e)
        {
        }
        return lst;
    }
    //类型转化接口
    public static interface BeanConverter<E>
    {
        public E convert(Cursor c);
    }
}
