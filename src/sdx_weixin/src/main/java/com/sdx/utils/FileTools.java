package com.sdx.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class FileTools
{
	private static final Log log = LogFactory.getLog(FileTools.class);

	// 复制文件
	public static void copyFile(File sourceFile, File targetFile) throws IOException
	{
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try
		{
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1)
			{
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		}
		finally
		{
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException
	{
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++)
		{
			if (file[i].isFile())
			{
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory())
			{
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * @param srcFileName
	 * @param destFileName
	 * @param srcCoding
	 * @param destCoding
	 * @throws IOException
	 */
	public static void copyFile(File srcFileName, File destFileName, String srcCoding, String destCoding) throws IOException
	{// 把文件转换为GBK文件
		BufferedReader br = null;
		BufferedWriter bw = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFileName), srcCoding));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFileName), destCoding));
			char[] cbuf = new char[1024 * 5];
			int len = cbuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = br.read(cbuf, off, len)) > 0)
			{
				off += ret;
				len -= ret;
			}
			bw.write(cbuf, 0, off);
			bw.flush();
		}
		finally
		{
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		}
	}

	/**
	 * @param filepath
	 * @throws IOException
	 */
	public static void del(String filepath) throws IOException
	{
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory())
		{// 判断是文件还是目录
			if (f.listFiles().length == 0)
			{// 若目录下没有文件则直接删除
				f.delete();
			}
			else
			{// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++)
				{
					if (delFile[j].isDirectory())
					{
						del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					delFile[j].delete();// 删除文件
				}
			}
		}
	}

	/**
	 * 获取路径下所有文件名
	 * @param path
	 * @return
	 */
	public static String[] getFile(String path)
	{
		File file = new File(path);
		String[] name = file.list();
		return name;
	}

	/**
	 * @param sourceDirPath
	 * @param targetDirPath
	 * @throws IOException
	 */
	public static void copyDir(String sourceDirPath, String targetDirPath) throws IOException
	{
		// 创建目标文件夹
		(new File(targetDirPath)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDirPath)).listFiles();
		for (int i = 0; i < file.length; i++)
		{
			if (file[i].isFile())
			{
				// 复制文件
				String type = file[i].getName().substring(file[i].getName().lastIndexOf(".") + 1);

				if (type.equalsIgnoreCase("txt"))
					copyFile(file[i], new File(targetDirPath + file[i].getName()), "UTF-8", "UTF-8");
				else
					copyFile(file[i], new File(targetDirPath + file[i].getName()));
			}
			if (file[i].isDirectory())
			{
				// 复制目录
				String sourceDir = sourceDirPath + File.separator + file[i].getName();
				String targetDir = targetDirPath + File.separator + file[i].getName();
				copyDirectiory(sourceDir, targetDir);
			}
		}
	}

	/**
	 * 读取文件中内容
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(String path) throws IOException
	{
		String resultStr = null;
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(path);
			byte[] inBuf = new byte[2000];
			int len = inBuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = fis.read(inBuf, off, len)) > 0)
			{
				off += ret;
				len -= ret;
			}
			resultStr = new String(new String(inBuf, 0, off, "UTF-8").getBytes());
		}
		finally
		{
			if (fis != null)
				fis.close();
		}
		return resultStr;
	}

	/**
	 * 文件转成字节数组
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(String path) throws IOException
	{
		byte[] b = null;
		InputStream is = null;
		File f = new File(path);
		try
		{
			is = new FileInputStream(f);
			b = new byte[(int) f.length()];
			is.read(b);
		}
		finally
		{
			if (is != null)
				is.close();
		}
		return b;
	}

	/**
	 * 将byte写入文件中
	 * @param fileByte
	 * @param filePath
	 * @throws IOException
	 */
	public static void byteToFile(byte[] fileByte, String filePath) throws IOException
	{
		OutputStream os = null;
		try
		{
			os = new FileOutputStream(new File(filePath));
			os.write(fileByte);
			os.flush();
		}
		finally
		{
			if (os != null)
				os.close();
		}
	}

	/**
	 * 判空字串
	 * @param str
	 * @return 为空true
	 */
	public static boolean strIsNull(String str)
	{
		return str == null || str.equals("");
	}

	public static void GenerateImage(String imgStr, String filePath)
	{// 对字节数组字符串进行Base64解码并生成图片
		BASE64Decoder decoder = new BASE64Decoder();
		try
		{
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i)
			{
				if (b[i] < 0)
				{// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(filePath);
			out.write(b);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	public static String readFileQuietly(File file)
	{
		try
		{
			return FileUtils.readFileToString(file, "UTF-8");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			return null;
		}
	}
}