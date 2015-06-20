
package com.sdx.client;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 协议测试类
 * @author 田广文
 * @date 2014年5月19日-下午4:58:48
 */
public class ClientTest
{
//    private static String SERVICE_NAME = "ServiceInfo";
//    private static String SERVICE_NAME = "CheckService";
//    private static String SERVICE_NAME = "GenerateResource";
//    private static String SERVICE_NAME = "BacklogList";
	private static String SERVICE_NAME = "DataTransfer";

//    private static String HOST = "http://localhost:8080/umapp";
    private static String HOST = "http://11.0.46.24:8080/umapp";

    private static String URL = HOST + "/client/clientService";

    public static void main(String[] args) throws Exception
    {
        // 建立HttpPost对象
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("clientType", "android");
        // 设置编码
        httpPost.setEntity(new StringEntity(readFileContent(SERVICE_NAME), "utf-8"));
        // 发送Post,并返回一个HttpResponse对象
        HttpResponse response = new DefaultHttpClient().execute(httpPost);
        // 可以得到指定的header
        // Header header = response.getFirstHeader("Content-Length");
        // String Length=header.getValue();
        // System.out.println(header.getName());
        // 如果状态码是200，则正常返回
        if (response.getStatusLine().getStatusCode() == 200)
        {
            // 获得返回的字符串
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            // 打印输出
            System.out.println("\r\n\r\n\r\n" + result);
            // 如果是下载的文件，可以用response.getEntity().getContent返回InputStream
        }
        else
        {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    public static String readFileContent(String fileName) throws Exception
    {
        File file = new File("src/test/java/com/umapp/client/" + fileName + ".json");
        return FileUtils.readFileToString(file);
    }
}
