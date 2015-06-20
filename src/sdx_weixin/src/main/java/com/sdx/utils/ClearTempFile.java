package com.sdx.utils;

import java.io.File;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO What the class does
 * 
 * @author zz
 * @date 2013-8-6-上午9:53:41
 * 
 */
@Transactional
@Component("clearTempFile") 
public class ClearTempFile {
	private static Map<String, String> basicDataMap = PropertiesCacheController.getRfoaConvertListMap();
	@Scheduled(cron = "0 0 0 * * ?")
	public  void clearTemp() {
		String path = basicDataMap.get("root")+basicDataMap.get("formTempFilePath");
		File files = new File(path);
		for (File file:files.listFiles()){
			file.delete();
		}
	}
}
