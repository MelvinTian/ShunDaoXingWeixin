package com.sdx.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;

public class HttpClientDownload {
	public static final String RFOA_BTN_RADIO = "RFOA_BTN_RADIO";
	public static final String PROXY = "proxy3.bj.petrochina";
	public static final String PROXY_PORT = "8080";
	public static final String TARGET_PORT = "5555";
	public static final String TARGET = "127.0.0.1:8080/auth_center/testDownload";
	public static final String SCHEME = "http";
	public static void main(String args[]) throws Exception {
		Map params = new HashMap<String,String>();
		String attachName = "xxx.txt";
		//params.put("AttachmentID", "ae6e9e20-0252-4897-8833-aa5d69679996");
		//params.put("AttachTitle", "报表样式的CSS控制.doc");
		doGetAttachment(params, PROXY,PROXY_PORT,TARGET_PORT,TARGET,SCHEME,"f:/"//BaseControler.getPath()
				+attachName);
		 //byte[] ba = FileUtils.readFileToByteArray(new File("f:/abc.txt"));
		 /*File storeFile = new File("f:/test.txt");  
         FileOutputStream output = new FileOutputStream(storeFile);
         output.write(ba);
         output.flush();
         output.close();*/
	}
	
	public static String doGetAttachment(Map<String,String> map,String proxy,
			String proxyPort,String targetPort,String target,String sheme,String... strings) {
		String retJson = "";
		//创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        //------------------------------代理end---------------------------------------------
        HttpPost httpPost = new HttpPost(sheme+"://"+target);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String>m:map.entrySet()){
        	formparams.add(new BasicNameValuePair(m.getKey(), m.getValue()));
        }

        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(entity);
            //post请求
              CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                 //设置本地保存的文件
                 File storeFile = new File(strings[0]);  
                 FileOutputStream output = new FileOutputStream(storeFile);
                 //得到网络资源并写入文件
                 InputStream input = httpEntity.getContent();
                 byte b[] = new byte[1024];
                 int j = 0;
                 while( (j = input.read(b))!=-1){
                      output.write(b,0,j);
                 }
                 output.flush();
                 output.close(); 
            }
            //释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return retJson;
	}
}