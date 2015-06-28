package com.sdx.client;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;

import com.sdx.common.service.SdxConstants;

public class FileTest
{
	private static final String TEST_DATA_PATH = "/testJson/";
	private static final String DEFAULT_CHARSET = "UTF-8";

	private void testFiles()
	{
		File dir = new File(SdxConstants.WEB_CLASS_PATH + TEST_DATA_PATH);
		Iterator<File> itr = FileUtils.iterateFiles(dir, new String[]{"json"}, false);
		File newDir = new File(dir.getAbsolutePath() + "/1/");
		newDir.mkdir();
		while (itr.hasNext())
		{
			File file = itr.next();
			testFile(file, newDir);
		}
	}
	
	private void testFile(File file, File newDir)
	{
		try
		{
			String fileContent = FileUtils.readFileToString(file, DEFAULT_CHARSET);
			JSONObject.fromObject(fileContent);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
