package com.sdx.template;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class JsonPares {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<form id=\"pageForm\"><ul class=\"phoneScreen\">");
		sb.append("<li><input type=\"text\" id=\"idId1\" auth_data=\"a\" auth_range=\"system\" /></li>");
		sb.append("<li><input type=\"hidden\" id=\"idId2\" auth_data=\"a1\" auth_range=\"system\" /></li>");
		sb.append("<li><label id=\"idId3\" auth_data=\"a3\" auth_range=\"system\" ></label></li>");
		sb.append("<li><ul id=\"m_listView\"><li><a id=\"c\"></a></li></ul></li>");
		sb.append("<li><div id=\"m_list\"></div></li>");
		sb.append("</ul></form><div id=\"a\"></div><div id=\"b\"></div>");
		String s = FileUtils.readFileToString(new File("src/test/java/com/umapp/template/component.js"));
	    String as = StringUtils.substringAfter(s, "=");
		JSONObject o = JSONObject.fromObject(as);
		org.jsoup.nodes.Document doc = Jsoup.parse(sb.toString());
		Elements els = doc.select(".phoneScreen li");
		for (Element ele:els){
			Element e = ele.children().get(0);
			if (e.tagName().equals("input")&&e.attr("type").equals("hidden")){
				Element rp =  doc.createElement("label");
				for (Attribute a:e.attributes()){
					rp.attr(a.getKey(), a.getValue());
				}
				e.remove();
				ele.append(rp.toString());
				System.out.println(ele.children().size());
			}
		}
		String html = doc.getElementById("pageForm").html();
		html+=doc.getElementById("a").toString();
		html+=doc.getElementById("b").toString();
		System.out.println(html);
		/*Elements els = doc.select(".phoneScreen li");
		//System.out.println(els.size());
		JSONObject jo2 = new JSONObject();
		for (Element ele:els){
			Element e = ele.children().get(0);
			JSONObject jo = new JSONObject();			
			System.out.println(e.tagName());
			if (e.tagName().equals("div")||e.tagName().equals("ul")){
				continue;
			}
			for (Attribute a:e.attributes()){
				jo.put(a.getKey(), a.getValue());
				//System.out.println(a.getKey()+","+a.getValue());
			}
			jo.put("tagName", e.tagName());
			jo2.put(e.attr("id"), jo);
		}
		Element listView = doc.getElementById("m_listView");
		JSONObject joListView = new JSONObject();
		if (listView!=null){
			Element lv = listView.select("li a").get(0);
			for (Attribute a:lv.attributes()){
				joListView.put(a.getKey(), a.getValue());
			}
			joListView.put("tagName", "a");
			jo2.put(lv.attr("id"), joListView);
		}
		Element list = doc.getElementById("m_list");
		JSONObject joList = new JSONObject();
		if (list!=null){
			joList.put("tagName", "list");
			joList.put("id", list.attr("m_list"));
			joList.put("name", list.attr("m_list"));
			jo2.put("m_list", joList);
		}
		System.out.println("===="+jo2.toString());*/
		//String s =HtmlParse2JSON.parseJson(sb.toString());
		//System.out.println(s);
		//String ss="{{a:1}}";
		//System.out.println(ss.substring(1, ss.length()-1));
		
		
		
	}

}
