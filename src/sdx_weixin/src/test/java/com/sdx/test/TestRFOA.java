package com.sdx.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestRFOA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONArray retArr = new JSONArray();
		JSONObject o1 = new JSONObject();
		o1.put("A", "A");
		retArr.add(o1);
		JSONObject o2 = new JSONObject();
		o2.put("A", "B");
		retArr.add(o2);
		JSONObject o3 = new JSONObject();
		o3.put("A", "C");
		retArr.add(o3);
		JSONObject o4 = new JSONObject();
		o4.put("A", "D");
		retArr.add(o4);
		StringBuffer sb = new StringBuffer();
		sb.append("var opts=\"");
		for (int i=0;i<retArr.size();i++){
			String btnValue = ((JSONObject)retArr.get(i)).get("A").toString();
			String btnName = ((JSONObject)retArr.get(i)).get("A").toString();
			sb.append("<option value='").append(btnValue).append("' >").append(btnName).append("</option>");
		}
		sb.append("\";");
		sb.append(" var btnObj = $('select[name=AuditBtnInfo]');$(btnObj).empty();$(btnObj).append(opts);");
		System.out.println(sb.toString());
	}

}
