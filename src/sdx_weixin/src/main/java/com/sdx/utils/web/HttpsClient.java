package com.sdx.utils.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.sdx.common.exception.CustomMsgException;

public class HttpsClient
{
	private static final Logger log = Logger.getLogger(HttpsClient.class);
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	private static final String DEFAULT_CHARSET = "utf-8";

	public static String doGet(String url) throws CustomMsgException
	{
		return doGet(url, DEFAULT_CHARSET, 10*1000, 10*1000);
	}
	
	public static String doGet(String url, String charset, int connectTimeout, int readTimeout) throws CustomMsgException
	{
		String ctype = "application/json;charset=" + charset;

		return doConnect(url, ctype, new byte[]{}, connectTimeout, readTimeout, METHOD_GET);
	}

	public static String doPost(String url, String params, String charset, int connectTimeout, int readTimeout) throws CustomMsgException
	{
		String ctype = "application/json;charset=" + charset;
		byte[] content =
		{};
		if (params != null)
		{
			try
			{
				content = params.getBytes(charset);
			}
			catch (UnsupportedEncodingException e)
			{
				log.error(e.getMessage(), e);
				throw new CustomMsgException("接口返回字符编码不正确");
			}
		}

		return doConnect(url, ctype, content, connectTimeout, readTimeout, METHOD_POST);
	}

	public static String doConnect(String url, String ctype, byte[] content, int connectTimeout, int readTimeout, String httpMethod) throws CustomMsgException
	{
		HttpsURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try
		{
			try
			{
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0], new TrustManager[]
				{ new DefaultTrustManager() }, new SecureRandom());
				SSLContext.setDefault(ctx);

				conn = getConnection(new URL(url), httpMethod, ctype);
				conn.setHostnameVerifier(new HostnameVerifier()
				{
					@Override
					public boolean verify(String hostname, SSLSession session)
					{
						return true;
					}
				});
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			}
			catch (Exception e)
			{
				log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
				throw new CustomMsgException("服务器连接错误");
			}
			try
			{
				out = conn.getOutputStream();
				out.write(content);
				rsp = getResponseAsString(conn);
			}
			catch (IOException e)
			{
				log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
				throw new CustomMsgException("服务器连接错误");
			}

		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (IOException e)
				{
					log.warn(e.getMessage(), e);
				}
			}
			if (conn != null)
			{
				conn.disconnect();
			}
		}

		return rsp;
	}

	private static class DefaultTrustManager implements X509TrustManager
	{

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
		{
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
		{
		}

		@Override
		public X509Certificate[] getAcceptedIssuers()
		{
			return null;
		}

	}

	private static HttpsURLConnection getConnection(URL url, String method, String ctype) throws IOException
	{
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
		conn.setRequestProperty("User-Agent", "stargate");
		conn.setRequestProperty("Content-Type", ctype);
		return conn;
	}

	protected static String getResponseAsString(HttpURLConnection conn) throws IOException
	{
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null)
		{
			return getStreamAsString(conn.getInputStream(), charset);
		}
		else
		{
			String msg = getStreamAsString(es, charset);
			if (StringUtils.isEmpty(msg))
			{
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			}
			else
			{
				throw new IOException(msg);
			}
		}
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0)
			{
				writer.write(chars, 0, count);
			}

			return writer.toString();
		}
		finally
		{
			if (stream != null)
			{
				stream.close();
			}
		}
	}

	private static String getResponseCharset(String ctype)
	{
		String charset = DEFAULT_CHARSET;

		if (!StringUtils.isEmpty(ctype))
		{
			String[] params = ctype.split(";");
			for (String param : params)
			{
				param = param.trim();
				if (param.startsWith("charset"))
				{
					String[] pair = param.split("=", 2);
					if (pair.length == 2)
					{
						if (!StringUtils.isEmpty(pair[1]))
						{
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}
}
