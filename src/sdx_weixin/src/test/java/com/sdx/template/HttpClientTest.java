package com.sdx.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;

public class HttpClientTest {
	public static void main(String args[]) throws Exception {
		JSONObject json = new JSONObject();
        json.put("ComType", "login");
        json.put("UserName", "wang_b");
        json.put("Password", "1234567");
        json.put("IpadUDID", "dd6d552818bed42055658bca52c350d826f7638f");
        json.put("IsWLan", "1");
		JSONObject retJson = init(json);
		String sessionId = retJson.get("SessionID").toString();
		JSONObject jsonB = new JSONObject();
		jsonB.put("ComType", "GetWorkCount");
		jsonB.put("SessionID", sessionId);
		JSONObject retJsonC = init(jsonB);
		System.out.println(retJsonC.toString());
		//JSONObject jsonC = new JSONObject();
		//jsonC.put("ComType", "ReviewCapitalApply");
		/*String decInId = new String(decode("OGM3ZDRjODUtNGM2ZC00NzkxLWI3NTYtOWFmN2RkYmFkZjdh"),"gb2312");
		String inId = "NzI0NjFiN2MtYTdhNS00N2ExLWE0YjAtMzE4NGQzNDUwNjc1";
		String decFlowTid = new String(decode("MTMxYzlhY2EtMzEwOC00YmQyLWI1YzUtMWUxNTcwN2VkZjE3"),"gb2312");
		String flowTid = "MTMxYzlhY2EtMzEwOC00YmQyLWI1YzUtMWUxNTcwN2VkZjE3";*/
		/*jsonC.put("InstanceID", "7bf8f586-944c-417a-a1b1-e5d73113ab53");
		jsonC.put("FlowTypeID", "f36c1417-a17c-45eb-a163-7171a7d38f6b");
		jsonC.put("aaa", "1");
		jsonC.put("SessionID", sessionId);*/
		
		/*jsonB.put("QueryType", "1");
		jsonB.put("FlowTitle", "");
		jsonB.put("FlowID", "");*/
		/*JSONObject retJsonD = init(jsonC);
		System.out.println(retJsonD.toString());*/
		/*JSONObject jsonD = new JSONObject();
		jsonC.put("ComType", "GetAuditUser");
		jsonC.put("InstanceID", "938acd03-5f93-4bec-a59c-64f41305fa19");
		jsonC.put("SessionID", sessionId);
		jsonC.put("Rule", "");
		jsonC.put("TmpID", "");
		System.out.println(jsonD.toString());*/
		//System.out.println(new String(decode(retJsonD.get("RetMessage").toString()),"gb2312"));
	}
	
	public static JSONObject init(JSONObject json){
		//创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        //HttpHost target = new HttpHost("10.88.112.102:5555/Default.aspx", 5555, "http");
        //HttpHost proxy = new HttpHost("proxy3.bj.petrochina", 8080, "http");
      //  RequestConfig config = RequestConfig.custom()
               // .setProxy(proxy)
          //      .build();

        HttpPost httpPost = new HttpPost("http://10.88.112.102:5555/Default.aspx");
       // HttpPost httpPost = new HttpPost("http://localhost:8080/auth_center/laodModulePage");
       // httpPost.setConfig(config);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        
        /* json.put("ComType", "GetTaskList");
        json.put("SessionID", "d452c35939b6442faf392375aefb0f84");
        json.put("RecordStart", "1");
        json.put("RecordLength", "5");
        json.put("QueryType", "1");
        json.put("FlowTitle", "1");
        json.put("FlowID", "1");*/
        //json.put("ComType", "GetHrOption");
        formparams.add(new BasicNameValuePair("IncomeMessage",json.toString()));
         //formparams.add(new BasicNameValuePair("pid", "2"));
        
        UrlEncodedFormEntity entity;
        JSONObject j = new JSONObject();
        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(entity);
            
            /*JSONObject json = new JSONObject();
            JSONObject ja= new JSONObject();
            json.put("ComType", "login");
            json.put("UserName", "Mengxianguo");
            json.put("Password", "1234567");
            json.put("IpadUDID", "dd6d552818bed42055658bca52c350d826f7638f");
            json.put("IsWLan", "1");
            ja.put("IncomeMessage", json.toString());*/
           // StringEntity s = new StringEntity(ja.toString());  
           // s.setContentEncoding("UTF-8");  
            //s.setContentType("application/json");  
           // httpPost.setEntity(s);
            //post.setEntity(s); 

            HttpResponse response;
            //post请求
            response = closeableHttpClient.execute(httpPost);
            //CloseableHttpResponse response = closeableHttpClient.execute(target, httpPost);

            //getEntity()
            HttpEntity httpEntity = response.getEntity();
            
            if (httpEntity != null) {
            	/*try{
            		File storeFile = new File("c:/2.docx");    
                    FileOutputStream output = new FileOutputStream(storeFile);  
                    //得到网络资源并写入文件  
                    InputStream input = entity.getContent();  
                    byte b[] = new byte[1024];  
                    int jx = 0;  
                    while( (jx = input.read(b))!=-1){  
                        output.write(b,0,jx);  
                    }  
                    output.flush();  
                    output.close();
            	}catch(Exception e){
            		e.printStackTrace();
            	}*/
                //打印响应内容
                //System.out.println("response:" + EntityUtils.toString(httpEntity, "UTF-8"));
                String ss = EntityUtils.toString(httpEntity, "UTF-8");
               // System.out.println(ss);
                j = JSONObject.fromObject(ss);
                for (Iterator iter=(Iterator) j.keys();iter.hasNext();){
                	String key =(String)iter.next();
                	//System.out.println(key+","+new String(decode(j.get(key).toString()),"gb2312"));
                	if (key.equals("WorkList")||key.equals("AuditHistory")||key.equals("AttachmentList")){
                		JSONArray wr = (JSONArray)j.get(key);
                		for (int i=0;i<wr.size();i++){
                			JSONObject jo = (JSONObject)wr.get(i);
                			for (Iterator ik= jo.keys();ik.hasNext();){
                				String k = (String)ik.next();
                				if (k.equals("a")){
                					jo.put(k, jo.get(k).toString());
                				}else{
                					jo.put(k, new String(decode(jo.get(k).toString()),"gb2312"));
                				}
                				//System.out.println(new String(decode(jo.get(k).toString()),"gb2312"));	
                			}
                		}
                	}
                }
                for (Iterator iter=(Iterator) j.keys();iter.hasNext();){
                	String key =(String)iter.next();
                	//System.out.println(key+","+new String(decode(j.get(key).toString()),"gb2312"));
                	if (key.equals("WorkList")||key.equals("AuditHistory")||key.equals("AttachmentList")){
                		JSONArray wr = (JSONArray)j.get(key);
                		for (int i=0;i<wr.size();i++){
                			JSONObject jo = (JSONObject)wr.get(i);
                			for (Iterator ik= jo.keys();ik.hasNext();){
                				String k = (String)ik.next();
                				//jo.put(k, new String(decode(jo.get(k).toString()),"gb2312"));
                				System.out.println(k+","+jo.get(k).toString());	
                			}
                			System.out.println("----------------------你懂的--------------------------");
                		}
                	}else{
                		//if (decode(j.get(key).toString())!=null){
                			System.out.println("字段==="+key+",值==="+new String(decode(j.get(key).toString()),"gb2312"));	
                		//}
                	}
                }
                //System.out.println(new String(decode(j.get("RetCode").toString())));
               // System.out.println(decode(j.get("HrOpinion").toString()));
				/*String resp = EntityUtils.toString(httpEntity, "UTF-8");
				String bb = resp.substring(1, resp.length()-1);
				String[] cc=StringUtils.split(bb, ",");
				Map<String,String> m = new HashMap<String,String>();
				for (int i=0;i<cc.length;i++){
					String[] dd = StringUtils.split(cc[i],":");
					System.out.println(dd[0]+","+dd[1]);
					m.put(dd[0], dd[1]);
				}
				JSONObject o = JSONObject.fromObject(m);
				System.out.println(o.toString());*/
            }
            //释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
      // System.out.println(GenerateImage("z/vPos6qv9WjoQ=="));
	}
	public static String encode(String b){  
			byte[] bstr = b.getBytes();
		   return new sun.misc.BASE64Encoder().encode(bstr);    
	}
	
	 public static byte[] decode(String str){    
		   byte[] bt = null;    
		   try {    
		       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
		       bt = decoder.decodeBuffer( str );    
		       //bt = Base64.decode(str);
		   } catch (Exception e) {    
		       e.printStackTrace();    
		   }    
		   
		       return bt;    
	 }
	 public static String GenerateImage(String imgStr){//对字节数组字符串进行Base64解码并生成图片
	        BASE64Decoder decoder = new BASE64Decoder();
	        String a ="";
	        try {
	            //Base64解码
	            byte[] b = decoder.decodeBuffer(imgStr);
	            for(int i=0;i<b.length;++i){
	                if(b[i]<0){//调整异常数据
	                    b[i]+=256;
	                }
	            }
	           a = b.toString();
	        } 
	        catch (Exception e){
	        	e.printStackTrace();
	        }
	        return a;
	    }
}