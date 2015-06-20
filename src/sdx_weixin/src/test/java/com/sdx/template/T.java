package com.sdx.template;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.sdx.utils.PropertiesCacheController;

public class T {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//System.out.println(FileUtils.readFileToString(new File(root+"/js/meditor/core/component.js")););
		String html = FileUtils.readFileToString(new File("src/test/java/com/umapp/template/index.html"));
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("head");
		//System.out.println(elements.html());
		String a = "<input />";
		String head = "<meta charset=\"utf-8\"><title>UI组件</title><meta name=\"viewport\" content=\"width=device-width\" />"+ 
				"<script src=\"../../../js/jquery.js\" type=\"text/javascript\"></script><script src=\"../../../js/common.js\" type=\"text/javascript\"></script><script src=\"../../../js/date.js\" type=\"text/javascript\"></script>"+
				"<script src=\"../../../cordova.js\" type=\"text/javascript\"></script><script src=\"../../../coreLib/UmappLib.js\" type=\"text/javascript\"></script>"+
				"<link href=\"../../../css/skin.css\" rel=\"stylesheet\" type=\"text/css\"/>";
		String foot ="<script src='a.js' type=\"text/javascript\"></script>"+
				"<link href=\"../../../css/jquery.mobile-1.4.2.min.css\" rel=\"stylesheet\" type=\"text/css\"/><link href=\"../../../css/global.css\" rel=\"stylesheet\" type=\"text/css\"/>"+
				"<script src=\"../../../js/jquery.mobile-1.4.2.min.js\" type=\"text/javascript\"></script>";
		
		//String xxx = "'<option value=\\""{dataitems[\"status\"].Value}==1,444444\\"">ddd</option>';"
		//System.out.println(elements.get(0).html());
		/*Element t = elements.get(0).empty();
		t.append(head);
		elements = doc.getElementsByTag("body");
		t = elements.get(0).attr("id", "aaazx");
		t.append(a);
		t.append(foot);*/
		
		//System.out.println(doc.getElementById("a1").siblingElements().toString());
		/*JSONObject o = new JSONObject();
		o.put("a", "a");
		JSONArray retArr = new JSONArray();
		retArr.add("b");
		retArr.add("c");
		o.put("m", retArr);
		o.remove("a");
		JSONArray j = (JSONArray) o.get("m");
		j.add("d");
		System.out.println(o.toString());*/
		
		/*String aa = "{\"RetCode\":\"MA==\",\"HrOpinion\":eyJDb21t}";
		String bb = aa.substring(1, aa.length()-1);
		String[] cc=StringUtils.split(bb, ",");
		Map<String,String> m = new HashMap<String,String>();
		for (int i=0;i<cc.length;i++){
			String[] dd = StringUtils.split(cc[i],":");
			System.out.println(dd[0]+","+dd[1]);
			m.put(dd[0], dd[1]);
		}*/
		String m = "{\"RetCode\":\"MA==\",\"RetMessage\":\"sunRr7PJuaY=\"," +
				/*"\"HaveContractValue\":\"MQ==\",\"HaveContract\":\"09A=\",\"ContractNo\":\"MjAxM0tKMDY5\"," +
				"\"ContractName\":\"yMvUsc3isPy3/s7xv/K83NCt0uktsuLK1L/VvOQ=\",\"PayCntType\":\"zu/StbfR\"," +
				"\"PayContent\":\"MTEx\",\"UseType_Is\":\"ysc=\",\"ApplyDate\":\"MjAxNC8wOC8xOQ==\"," +
				"\"PayPartBName\":\"ta21rbXY\",\"ApplyPayPrice\":\"MjAwMDAuMDA=\"," +
				"\"NotPayPrice\":\"LTIwMDAwLjAw\",\"AgentPartName\":\"ta21rbXY\"," +
				"\"BankAccountNo\":\"MTExMTExMQ==\",\"BankName\":\"ta21rbXY\",\"PayMethod\":\"tee74w==\"," +
				"\"MPContractNo\":\"\",\"MPContractName\":\"\",\"MPContractPrice\":\"\"," +
				"\"ContractType_IsValue\":\"Q29udHJhY3RUeXBlX0lzLS0y\"," +
				"\"ContractType_Is\":\"t/E=\",\"ContractOrgName\":\"u/q52NawxNw=\"," +
				"\"ContractSubOrgName\":\"ssbO8b6t06qyvw==\",\"ContractBeginDate\":\"\"," +
				"\"ContractEndDate\":\"\",\"ContractApplyDate\":\"\",\"ContractAgentName\":\"wfXTsQ==\"," +
				"\"ContractAgentTel\":\"MTU1MTA1MjEyOTg=\",\"ContractHanleName\":\"wLzM7A==\"," +
				"\"ContractHanleTel\":\"MTM4MTA2NjE3OTY=\",\"ContractAgentName\":\"wfXTsQ==\"," +*/
				"\"ContractAgentName\":\"wfXTsQ==\",\"ContractAgentName\":\"wfXTsQ==\",\"HavePayPrice\":\"MC4wMA==\"," +
				/*"\"ShoulPayPriceSum\":\"MC4wMA==\",\"Filed1\":\"yMvD8bHS\"," +
				"\"ContractPayDetail\":[{\"PayInfo_A\":\"MTExMTE=\"," +
				"\"ShoulPayPrice_A\":\"MTExMTEuMDA=\",\"PayPrice_A\":\"MjAwMDAuMDA=\"}," +
				"{\"PayInfo_B\":\"\",\"ShoulPayPrice_B\":\"\",\"PayPrice_B\":\"\"}," +
				"{\"PayInfo_C\":\"\",\"ShoulPayPrice_C\":\"\",\"PayPrice_C\":\"\"}," +
				"{\"PayInfo_D\":\"\",\"ShoulPayPrice_D\":\"\",\"PayPrice_D\":\"\"},{\"PayInfo_E\":\"\"," +
				"\"ShoulPayPrice_E\":\"\",\"PayPrice_E\":\"\"}],\"AttachmentList\":[]," +
				"\"AuditBtnInfo\":[{\"RuleID\":\"ZDc1OGNkYTQtMzQ0ZC00Y2IxLWJjYzMtZjQwYzQ2MmU3MWFm\"," +
				"\"TmpID\":\"ZjM2YzE0MTctYTE3Yy00NWViLWExNjMtNzE3MWE3ZDM4ZjZi\",\"RuleName\":\"QjE=\"," +
				"\"PreActivityName\":\"sr\\/Dxb6twO0=\",\"PostActivityName\":\"1ve53NK1zvG4sdfcvq3A7cnzxfo=\"," +
				"\"RuleExpress\":\"e0RhdGFJdGVtc1sic3RhdHVzIl0uVmFsdWV9ID09IDE=\"," +
				"\"IsElse\":\"MA==\",\"IsBackward\":\"MA==\"}]," +
				"\"AuditHistory\":[{\"ManaItem\":\"18q98MnzxfrJ6sfr\",\"Manager\":\"wLzM7A==\"," +*/
				//"\"ManagerAdvice\":\"\",\"ManaOpera\":\"18q98MnzxfrJ6sfr\",\"ManaDate\":\"MjAxNC04LTE5IDE0OjU4OjU5\"}]" +
				"}";
		JSONObject o = JSONObject.fromObject(m);
		//String aa = new String(decode(m),"gb2312");
		for (Iterator iter=(Iterator) o.keys();iter.hasNext();){
        	String key =(String)iter.next();
        	/*if (PropertiesCacheController.getRfoaConvertListMap().get(key)!=null){
        		JSONArray wr = (JSONArray)o.get(key);
        		for (int i=0;i<wr.size();i++){
        			JSONObject jo = (JSONObject)wr.get(i);
        			for (Iterator ik= jo.keys();ik.hasNext();){
        				String k = (String)ik.next();
        				//System.out.println(k+","+jo.get(k).toString());	
        			}
        			//System.out.println("----------------------你懂的--------------------------");
        		}
        	}else{*/
        		System.out.println("字段==="+key+",值==="+o.get(key));	
        	//}
        }
		String ax = "{\"a\":\"wfXTsQ==\"}";
		JSONObject j = JSONObject.fromObject(ax);
		System.out.println(new String(decode(j.get("a").toString()),"gb2312"));
		
//		JSONArray.fromObject(object)
		//System.out.println(aa);
	}
	
	 public static byte[] decode(String str){    
		   byte[] bt = null;    
		   try {    
		       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
		       bt = decoder.decodeBuffer( str );    
		   } catch (IOException e) {    
		       e.printStackTrace();    
		   }    
		   
		       return bt;    
	 }
}
