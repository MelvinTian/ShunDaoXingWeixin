package com.sdx.template;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class DFOATest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{  
    		String url="http://10.88.112.66/MiddleWhatService/EdgeProxy";
    		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            //HttpClient
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
    		//POST的URL
    		HttpPost httppost=new HttpPost(url);
            // 把数据写入请求的Body  
            JSONObject parameters = new JSONObject();
            parameters.put("UserID", "gaojunli");
            parameters.put("PageIndex", "0");
            parameters.put("PageSize", "10");
            parameters.put("Title", "");
            
            JSONObject body = new JSONObject();
            body.put("interface", "oaWorkItemList");
            body.put("parameters", parameters);
            
            JSONObject Body = new JSONObject();
            
            Body.put("reqId", "0");
            Body.put("actionId", "0");
            Body.put("status", "");
            Body.put("message", "");
            Body.put("body", body);
            
    		httppost.setHeader("serviceName", "oasp");
    		StringEntity reqEntity = new StringEntity(Body.toString()); 
    		//StringEntity reqEntity = new StringEntity(Body.toString(),HTTP.UTF_8); 
    		reqEntity.setContentType("application/x-www-form-urlencoded");   
    		//添加参数
    		httppost.setEntity(reqEntity);
    		//设置编码
    		HttpResponse response=closeableHttpClient.execute(httppost);

    		if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
    			String result=EntityUtils.toString(response.getEntity());
    			System.out.println("result----"+result);
    		}
        }catch(Exception e){  
            
        }  
	}

}
