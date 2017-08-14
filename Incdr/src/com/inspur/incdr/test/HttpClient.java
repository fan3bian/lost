/**
 * 
 */
package com.inspur.incdr.test;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.inspur.incdr.http.ResponseResult;

/**
 * simple introduction
 * 
 * <p>
 * detailed comment
 * 
 * @author zhangshuyi 2017年8月2日
 * @see
 * @since 1.0
 */
public class HttpClient {

    public static ResponseResult doPost(URI uri, List<Header> headers,
            List<NameValuePair> params) throws ClientProtocolException,
            IOException {
        if (headers == null)
            headers = Collections.emptyList();
        if (params == null)
            params = Collections.emptyList();
        ResponseResult responseResult = new ResponseResult();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            int status = response.getStatusLine().getStatusCode();
            responseResult.setStatus(status);
            HttpEntity entity = response.getEntity();
            responseResult.setEntity(EntityUtils.toString(entity, "utf-8"));
        } finally {
            response.close();
        }
        return responseResult;
    }

    public static ResponseResult doPost(String uri, List<Header> headers,
            List<NameValuePair> params) throws ClientProtocolException,
            IOException {
        return doPost(URI.create(uri), headers, params);
    }

    public static void main(String[] args) {
        // URIBuilder uriBuilder = new URIBuilder();
        // URI uri =null;
        // try {
        // uri = uriBuilder.setHost("httpbin.org").build();
        // } catch (URISyntaxException e1) {
        // e1.printStackTrace();
        // }
        // System.out.println(uri);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("zhang", "shuyi"));
        ResponseResult responseResult = null;
        Map map = new HashMap();
        map.put("username", "vip");
        map.put("password", "secret");
        // params.add(new BasicNameValuePair("username", "vip"));
        // params.add(new BasicNameValuePair("password", "secret"));
        String jsonStrng = JSON.toJSONString(map);
        System.out.println(jsonStrng);
        //
        // try {
        // responseResult= doPost("http://httpbin.org/post", headers, params);
        // } catch (ClientProtocolException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        System.out.println(responseResult);
    }
}
