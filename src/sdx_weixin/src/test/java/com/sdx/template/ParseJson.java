package com.sdx.template;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class ParseJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String s = FileUtils.readFileToString(new File("src/test/java/com/umapp/template/component.js"));
		    String a = StringUtils.substringAfter(s, "=");
		   // System.out.println(a);
			JSONObject o = JSONObject.fromObject(a);
			System.out.println(o.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
