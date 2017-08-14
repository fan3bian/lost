/**
 * 
 */
package com.inspur.incdr.test;

import java.io.IOException;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * simple introduction
 * 
 * <p>
 * detailed comment
 * 
 * @author zhangshuyi 2017年8月3日
 * @see
 * @since 1.0
 */
public class Snippet {
    public String HttpPostWithJson(String url, String json) {
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            // 第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            // 第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            // 第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            // 第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost, responseHandler); // 调接口获取返回值时，必须用此方法
            // CloseableHttpResponse httpResonse = httpClient.execute(httpPost);
            // int statusCode = httpResonse.getStatusLine().getStatusCode();
            // if(statusCode!=200)
            // {
            // System.out.println("请求发送失败，失败的返回参数为："+httpResonse.getStatusLine());
            // returnValue = httpResonse.getStatusLine().toString();
            // }
            //

        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 第五步：处理返回值
        return returnValue;
    }
}
