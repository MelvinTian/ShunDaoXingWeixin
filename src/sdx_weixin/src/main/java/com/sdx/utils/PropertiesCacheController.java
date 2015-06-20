package com.sdx.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author QI
 * 属性文件缓存
 */
public abstract class PropertiesCacheController {
 

  private static  Map<String,String> rfoaConvertListMap = new HashMap<String,String>();
  private static  Map<String,String> rfoaComTypeListMap;
  private static  Map<String,String> scriptcssMap;
  private static JSONObject jsonData;
  
  public static Map<String, String> getScriptcssMap() {
	  if (scriptcssMap==null){
		  initScriptCss();
	  }
	  return scriptcssMap;
  }


public static void setScriptcssMap(Map<String, String> scriptcssMap) {
	PropertiesCacheController.scriptcssMap = scriptcssMap;
}


public static Map<String, String> getRfoaComTypeListMap() {
	return rfoaComTypeListMap;
  }


 
  public static Map<String, String> getRfoaConvertListMap() {
	  return rfoaConvertListMap;
  }

  
public static void init(String root) {
	  Locale locale = Locale.getDefault();   
	  ResourceBundle localResource = ResourceBundle.getBundle("basicData", locale);
	  Set<String> keys= localResource.keySet();
	  HashMap<String,String> map=new HashMap<String,String>();
	  map.put("root", root);
	  for(String key: keys){
		  if (key.equals("rfoaConvertGB")){
			  String vals = localResource.getString(key);
			  String[] val = StringUtils.split(vals, ",");
			  for (String k:val){
				  map.put(k, k);
			  }
		  }else{
			  String vals = localResource.getString(key);
			  map.put(key, vals);
		  }
	  }
	  rfoaConvertListMap=map;
}
public static void initScriptCss() {
	Locale locale = Locale.getDefault();   
	ResourceBundle localResource = ResourceBundle.getBundle("scriptcss", locale);
	Set<String> keys= localResource.keySet();
	HashMap<String,String> map=new HashMap<String,String>();
	for(String key: keys){
			String vals = localResource.getString(key);
			map.put(key, vals);
	}
	scriptcssMap=map;
}

public static void initJsonData(String root){
	try {
		String fileStr = FileUtils.readFileToString(new File(root+"/js/meditor/core/component.js"));
		String str = StringUtils.substringAfter(fileStr, "=");
		jsonData = JSONObject.fromObject(str);
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static JSONObject getJsonData(String root) {
	if (jsonData==null){
		initJsonData(root);
	}
	return jsonData;
}


public static void setRfoaComTypeListMap(Map<String, String> rfoaComTypeListMap) {
	PropertiesCacheController.rfoaComTypeListMap = rfoaComTypeListMap;
}

}
