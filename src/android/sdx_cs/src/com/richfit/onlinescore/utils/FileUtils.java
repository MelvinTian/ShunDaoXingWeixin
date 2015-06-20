/* 
 * Copyright (C) 2013,中油瑞飞, 
 * All Rights Reserved 
 * Description: 文件相关工具类
 * 
 * @project 数据中心运行管理系统
 * @author  田广文
 * @date    2014年5月15日-下午4:00:59
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年5月15日	       Melvin		     	Create
 **********************************************************
 */

package com.richfit.onlinescore.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.os.Environment;
import android.util.Log;

/**
 * 文件相关工具类
 * @author 田广文
 * @date 2014年5月15日-下午4:00:59
 */
public class FileUtils
{

    public static final String SDPATH = Environment.getExternalStorageDirectory().getPath() + File.separator;

    public static final boolean SDEXIST = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

    public static final String ASSET_DIR = "file:///android_asset/";

    public static final int FILE_FAILURE = 0;

    public static final int FILE_SUCCESS = 1;

    public static final int FILE_EXIST = 2;

    public static final int SD_NOT_EXIST = 3;

    private static final String TAG = "FILE_UTILS";

    private static final int BUFFER_SIZE = 40 * 1024;

    /**
     * 在SD卡上创建文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File createSDFile(String fileName) throws IOException
    {
        File file = new File(SDPATH + fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     * @param dirName
     * @return
     */
    public static File createSDDir(String dirName)
    {
        File dir = new File(SDPATH + dirName);
        dir.mkdirs();
        return dir;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName)
    {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     * @param path
     * @param fileName
     * @param input
     * @return
     */
    public static File write2FileromInput(String fileName, InputStream input)
    {
        File file = null;
        OutputStream output = null;
        try
        {
            file = new File(fileName);
            file.getParentFile().mkdirs();
            output = new FileOutputStream(file);
            byte[] buffer = new byte[BUFFER_SIZE];

            /*
             * 真机测试，这段可能有问题，请采用下面网友提供的 while((input.read(buffer)) != -1){
             * output.write(buffer); }
             */

            /* 网友提供 begin */
            int length;
            while ((length = (input.read(buffer))) > 0)
            {
                output.write(buffer, 0, length);
            }
            /* 网友提供 end */

            output.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                output.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static int removeFile(String filePath)
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        File file = new File(filePath);
        if (file == null || !file.exists() || file.isDirectory())
            return FILE_FAILURE;
        Log.d(TAG, "remove file isSuccess" + file.delete());
        return FILE_SUCCESS;
    }

    public static int renameFile(String oldFileName, String newFileName)
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        File oldFile = new File(oldFileName);
        File newFile = new File(newFileName);
        if (newFile.exists())
            return FILE_EXIST;
        oldFile.renameTo(newFile);
        return FILE_SUCCESS;
    }

    public static int removeDir(String dirPath)
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        File dir = new File(dirPath);
        if (dir == null || !dir.exists() || dir.isFile())
            return FILE_FAILURE;
        for (File file : dir.listFiles())
        {
            if (file.isFile())
            {
                file.delete();
            }
            else if (file.isDirectory())
            {
                // 递归
                removeDir(file.getPath());
            }
        }
        dir.delete();
        Log.d(TAG, "remove dir is success");
        return FILE_SUCCESS;
    }

    public static int copyFileTo(File srcFile, File destFile) throws FileNotFoundException, IOException
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        if (destFile.exists())
            return FILE_EXIST;
        if (srcFile.isDirectory() || destFile.isDirectory())
            return FILE_FAILURE;
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        int readLength = 0;
        byte[] buf = new byte[1024];
        while ((readLength = fis.read(buf)) != -1)
        {
            fos.write(buf, 0, readLength);
        }
        fis.close();
        fos.close();
        return FILE_SUCCESS;
    }

    public static int copyFilesTo(File srcDir, File destDir, boolean isForce)
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        if (destDir.exists() && !isForce)
            return FILE_EXIST;
        // 判断是否是目录
        if (!srcDir.isDirectory() || !destDir.isDirectory())
            return FILE_FAILURE;
        // 判断目标目录是否存在
        if (!srcDir.exists())
            return FILE_FAILURE;
        File[] srcFiles = srcDir.listFiles();
        for (int i = 0; i < srcFiles.length; i++)
        {
            if (srcFiles[i].isFile())
            {
                // 获得目标文件
                File destFile = new File(destDir.getPath() + File.separator + srcFiles[i].getName());
                copyFilesTo(srcFiles[i], destFile, isForce);
            }
            else if (srcFiles[i].isDirectory())
            {
                File theDestDir = new File(destDir.getPath() + File.separator + srcFiles[i].getName());
                // 递归
                copyFilesTo(srcFiles[i], theDestDir, isForce);
            }
        }
        return FILE_SUCCESS;
    }

    public static int moveFileTo(String srcFilePath, String destFilePath) throws FileNotFoundException, IOException
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        File srcFile = new File(srcFilePath);
        File destFile = new File(destFilePath);
        if (destFile.exists())
            return FILE_EXIST;
        int isCopy = copyFileTo(srcFile, destFile);
        if (isCopy == 0)
            return FILE_FAILURE;
        Log.d(TAG, "move file isSuccess :" + removeFile(srcFile.getPath()));
        return FILE_SUCCESS;
    }

    public static int moveDirTo(File srcDir, File destDir) throws FileNotFoundException, IOException
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        if (destDir.exists())
            return FILE_EXIST;
        if (!srcDir.isDirectory() || !destDir.isDirectory())
            return FILE_FAILURE;
        if (!destDir.exists())
            return FILE_FAILURE;
        File[] srcFiles = srcDir.listFiles();
        for (int i = 0; i < srcFiles.length; i++)
        {
            if (srcFiles[i].isFile())
            {
                File destFile = new File(destDir.getPath() + File.separator + srcFiles[i].getName());
                copyFileTo(srcFiles[i], destFile);
                removeFile(srcFiles[i].getPath());
            }
            else if (srcFiles[i].isDirectory())
            {
                File theDestDir = new File(destDir.getPath() + File.separator + srcFiles[i].getName());
                theDestDir.mkdir();
                moveDirTo(srcFiles[i], theDestDir);
            }
        }
        // 删除源目录的子文件夹
        for (int i = 0; i < srcFiles.length; i++)
        {
            if (srcFiles[i].isDirectory())
                srcFiles[i].delete();
        }
        // 删除源目录的根目录
        srcDir.delete();
        return FILE_SUCCESS;
    }

    public static int write(String fileName, String content) throws IOException
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "big5");
        osw.write(content);
        osw.close();
        // fos.write(content.getBytes("UTF-8"));
        // fos.close();
        return FILE_SUCCESS;
    }

    public static int overWrite(String srcName, String destName) throws FileNotFoundException, IOException
    {
        if (!SDEXIST)
            return SD_NOT_EXIST;
        File srcFile = new File(srcName);
        File destFile = new File(destName);
        if (destFile.isFile())
        {
            removeFile(destName);
            copyFileTo(srcFile, destFile);
        }
        else if (destFile.isDirectory())
        {
            removeDir(destName);
            copyFilesTo(srcFile, destFile, true);
        }
        return FILE_SUCCESS;
    }

    public static String read(String filePath) throws FileNotFoundException, IOException
    {
        if (!SDEXIST)
            return "SDcard";
        File file = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        char[] ch = new char[1024];
        StringBuffer buffer = new StringBuffer();
        int readLen = FILE_FAILURE;
        while ((readLen = reader.read(ch)) != -1)
        {
            buffer.append(new String(ch, 0, readLen));
        }
        reader.close();
        Log.d(TAG, "read file content :" + buffer.toString());
        return buffer.toString();
    }

    public static void doWithFile(File dir, DoSomeThing<File> handler, boolean isRecursive)
    {
        if (dir.exists())
        {
            if (dir.isDirectory())
            {
                if (isRecursive)
                {
                    for (File d : dir.listFiles(new FileFilter()
                    {
                        @Override
                        public boolean accept(File pathname)
                        {
                            return pathname.isDirectory();
                        }
                    }))
                    {
                        doWithFile(d, handler, isRecursive);
                    }
                }
            }
            else
            {
                handler.doWith(dir);
            }
        }
    }

    public static boolean Unzip(InputStream inputStream, String targetDir)
    {
        int BUFFER = 4096; // 这里缓冲区我们使用4KB，
        String strEntry; // 保存每个zip的条目名称
        try
        {
            BufferedOutputStream dest = null; // 缓冲输出流
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(inputStream));
            ZipEntry entry; // 每个zip条目的实例
            while ((entry = zis.getNextEntry()) != null)
            {
                try
                {
                    int count;
                    byte data[] = new byte[BUFFER];
                    strEntry = entry.getName();
                    File entryFile = new File(targetDir + strEntry);
                    if (strEntry.endsWith(File.separator))
                    {
                        entryFile.mkdirs();
                    } else {
                        File entryDir = new File(entryFile.getParent());
                        if (!entryDir.exists())
                        {
                            entryDir.mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(entryFile);
                        dest = new BufferedOutputStream(fos, BUFFER);
                        while ((count = zis.read(data, 0, BUFFER)) != -1)
                        {
                            dest.write(data, 0, count);
                        }
                        dest.flush();
                        dest.close();
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            zis.close();
            return true;
        }
        catch (Exception cwj)
        {
            Log.e(TAG, cwj.getMessage(), cwj);
            return false;
        }
    }

    public static boolean Unzip(String zipFile, String targetDir)
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(zipFile);
            return Unzip(fis, targetDir);
        }
        catch (FileNotFoundException e)
        {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }
}