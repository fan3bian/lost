/**
 * 
 */
package com.inspur.incdr.base.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inspur.incdr.http.ResponseResult;

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
public class HttpClient {
    private HttpClient() {
    };

    private static Map<String, String> cookieMap = new HashMap<String, String>();

    private static String getCookie(String manageIp) {
        return cookieMap.get(manageIp);
    }

    private static boolean isHaveCookie(String manageIp) {
        return cookieMap.containsKey(manageIp);
    }

    private static void putCookie(String username, String cookie) {
        cookieMap.put(username, cookie);
    }

    public static ResponseResult doPost(String uri, List<Header> headers,
            String jsonString) throws IOException {
        if (headers == null)
            headers = Collections.emptyList();
        ResponseResult responseResult = new ResponseResult();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000)
                .setConnectionRequestTimeout(10000).setSocketTimeout(30000)
                .build();
        httpPost.setConfig(config);
        httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        httpPost.setEntity(new StringEntity(jsonString));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            Integer status = response.getStatusLine().getStatusCode();
            responseResult.setStatus(status);
            HttpEntity entity = response.getEntity();
            responseResult.setEntity(EntityUtils.toString(entity, "utf-8"));
            EntityUtils.consume(entity);
        } catch (IOException e) {
            if (e.getMessage() != null) {
                responseResult.setMsg(e.getMessage());
            } else {
                responseResult.setMsg(e.getCause().toString());
            }
            // logger.error(e);
        } finally {
            response.close();
        }
        return responseResult;
    }
    public static ResponseResult doPost(String uri, List<Header> headers,
            List<NameValuePair> params) throws IOException {
        if (headers == null)
            headers = Collections.emptyList();
        ResponseResult responseResult = new ResponseResult();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000)
                .setConnectionRequestTimeout(10000).setSocketTimeout(30000)
                .build();
        httpPost.setConfig(config);
        httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
//        httpPost.setEntity(new StringEntity(jsonString));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            Integer status = response.getStatusLine().getStatusCode();
            responseResult.setStatus(status);
            HttpEntity entity = response.getEntity();
            responseResult.setEntity(EntityUtils.toString(entity, "utf-8"));
            EntityUtils.consume(entity);
        } catch (IOException e) {
            if (e.getMessage() != null) {
                responseResult.setMsg(e.getMessage());
            } else {
                responseResult.setMsg(e.getCause().toString());
            }
            // logger.error(e);
        } finally {
            response.close();
        }
        return responseResult;
    }
    public static ResponseResult doGet(String url, List<Header> headers,
            String jsonString) {
        // logger.debug("发送请求： " + url);
        ResponseResult responseResult = new ResponseResult();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000)
                .setConnectionRequestTimeout(10000).setSocketTimeout(30000)
                .build();
        httpget.setConfig(config);
        httpget.setHeaders(headers.toArray(new Header[headers.size()]));
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            int status = response.getStatusLine().getStatusCode();
            responseResult.setStatus(status);
            HttpEntity entity = response.getEntity();
            responseResult.setEntity(EntityUtils.toString(entity, "utf-8"));
            EntityUtils.consume(entity);
            // logger.debug("请求结果： " + responseResult.getEntity());
        } catch (IOException e) {
            if (e.getMessage() != null) {
                responseResult.setMsg(e.getMessage());
            } else {
                responseResult.setMsg(e.getCause().toString());
            }
            // logger.error(e);
        }
        return responseResult;
    }
    public static boolean islogin(){
        return false;
    }
    public static boolean login(String uri, String username, String password) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String jsonString = JSON.toJSONString(map);
        ResponseResult responseResult = null;
        try {
            responseResult = doPost(uri, headers, jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = null;
        if (responseResult.getStatus() == 200) {
            String responseEntity = responseResult.getEntity();
            token = JSONObject.parseObject(responseEntity).getString("token");
        }
        boolean flag = false;
        if (StringUtils.isBlank(token)) {
            flag = true;
            putCookie(username, token);
        }
        return flag;
    }
    

}
