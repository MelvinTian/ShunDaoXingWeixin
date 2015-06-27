package com.sdx.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.sdx.utils.web.SdxHttpClient;

public class TestHttpClient
{

	public static void main(String args[]) throws Exception
	{
//		String url = "https://app.richfit.com/html/test.json";
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
//		test2(url);
		test(url);
//		test1(url);
	}
	public static HttpResponse test2(String url) throws Exception
	{
		BasicHttpEntity requestEntity = new BasicHttpEntity();
		requestEntity.setContentType("application/json");
		HttpResponse response = null;
		HttpPost post = new HttpPost(url);
		post.setEntity(requestEntity);
		HttpParams httpParameters = new BasicHttpParams();
		// 设置连接超时
		HttpConnectionParams.setConnectionTimeout(httpParameters, 6000);
		// 设置socket超时
		HttpConnectionParams.setSoTimeout(httpParameters, 10000);
		HttpClient client = initHttpClient(httpParameters);
		// client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
		// CONNECTION_TIMEOUT_TIME);
		// client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
		// SO_TIMEOUT_TIME);

		HttpResponse reponse = null;
		try
		{
			reponse = client.execute(post);
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println(EntityUtils.toString(requestEntity));
		return reponse;
	}
	/**
	 * 初始化HttpClient对象
	 * @param params
	 * @return
	 */
	public static synchronized HttpClient initHttpClient(HttpParams params)
	{
		HttpClient client = null;
		if (client == null)
		{
			try
			{
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);

				SSLSocketFactory sf = new SSLSocketFactory(trustStore);
				// 允许所有主机的验证
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
				// 设置http和https支持
				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				registry.register(new Scheme("https", sf, 443));

				ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

				return new DefaultHttpClient(ccm, params);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return new DefaultHttpClient(params);
			}
		}
		return client;
	}
	public static void test(String url) throws Exception
	{
		System.out.println(SdxHttpClient.doHttpsGet(url));
	}

	public static void test1(String url) throws Exception
	{


		try
		{

			// Secure Protocol implementation.
			SSLContext ctx = SSLContext.getInstance("SSL");
			// Implementation of a trust manager for X509 certificates
			X509TrustManager tm = new X509TrustManager()
			{

				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException
				{
					System.out.println("checkClientTrusted");
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException
				{
					System.out.println("checkServerTrusted");
				}

				public X509Certificate[] getAcceptedIssuers()
				{
					System.out.println("getAcceptedIssuers");
					return null;
				}
			};
			ctx.init(null, new TrustManager[]
			{ tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);

//			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			HttpClient httpclient = new DefaultHttpClient();
			ClientConnectionManager ccm = httpclient.getConnectionManager();
			// register https protocol in httpclient's scheme registry
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
			sr.register(new Scheme("https", 2000, ssf));

			HttpGet httpget = new HttpGet(url);
			HttpParams params = httpclient.getParams();

			params.setParameter("param1", "paramValue1");

			httpget.setParams(params);
			System.out.println("REQUEST:" + httpget.getURI());
			ResponseHandler responseHandler = new BasicResponseHandler();
			String responseBody;

			responseBody = httpclient.execute(httpget, responseHandler);

			System.out.println(responseBody);

			// Create a response handler

		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();

		}
	}
	
}
