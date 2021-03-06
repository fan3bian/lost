/**
 * 
 */
package com.inspur.incdr.base.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inspur.incdr.base.modal.request.InCdrbRequest;
import com.inspur.incdr.http.ResponseResult;
import com.inspur.incdr.util.EncryptionUtil;

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
public class InCdrbCVClient {
    private InCdrbCVClient() {
    };

    private static Map<String, String> cookieMap = new HashMap<String, String>();

    private static String getCookie(String username) {
        return cookieMap.get(username);
    }

    private static boolean existCookie(String username) {
        return cookieMap.containsKey(username);
    }

    private static void putCookie(String username, String cookie) {
        cookieMap.put(username, cookie);
    }

    public static ResponseResult executePost(URI uri, List<Header> headers,
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
            System.out.println(response);
            Integer status = response.getStatusLine().getStatusCode();
            responseResult.setStatus(status);
            HttpEntity entity = response.getEntity();
            responseResult.setEntity(EntityUtils.toString(entity, "utf-8"));
            EntityUtils.consume(entity);
        } catch (IOException e) {
            System.out.println(e);
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

    // public static ResponseResult doPost(String uri, List<Header> headers,
    // List<NameValuePair> params) throws IOException {
    // if (headers == null)
    // headers = Collections.emptyList();
    // CloseableHttpClient httpclient = HttpClients.createDefault();
    //
    // HttpPost httpPost = new HttpPost(uri);
    // RequestConfig config = RequestConfig.custom().setConnectTimeout(30000)
    // .setConnectionRequestTimeout(10000).setSocketTimeout(30000)
    // .build();
    // httpPost.setConfig(config);
    // httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
    // // httpPost.setEntity(new StringEntity(jsonString));
    // httpPost.setEntity(new UrlEncodedFormEntity(params));
    // CloseableHttpResponse response = httpclient.execute(httpPost);
    //
    // try {
    // Integer status = response.getStatusLine().getStatusCode();
    // responseResult.setStatus(status);
    // HttpEntity entity = response.getEntity();
    // responseResult.setEntity(EntityUtils.toString(entity, "utf-8"));
    // EntityUtils.consume(entity);
    // } catch (IOException e) {
    // if (e.getMessage() != null) {
    // responseResult.setMsg(e.getMessage());
    // } else {
    // responseResult.setMsg(e.getCause().toString());
    // }
    // // logger.error(e);
    // } finally {
    // response.close();
    // }
    // return responseResult;
    // }
    public static ResponseResult executeGet(URI url, List<Header> headers,
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

    public static boolean islogin(String username, InCdrbRequest request) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("");
        }
        if (!existCookie(username)) {
            return false;
        }
        String cookie = getCookie(username);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));
        Map<String, String> map = new HashMap<>();
        map.put("Cookie2", cookie);
        // map.put("password", password);
        String path = "/SearchSvc/CVWebService.svc/Login";
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(path).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        String jsonString = JSON.toJSONString(map);
        ResponseResult responseResult = null;
        try {
            responseResult = executePost(uri, headers, jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = null;
        if (responseResult.getStatus() == 200) {
            String responseEntity = responseResult.getEntity();
            token = JSONObject.parseObject(responseEntity).getString("token");
        }
        boolean flag = false;
        if (StringUtils.isNotBlank(token)) {
            flag = true;
            putCookie(username, token);
        }
        return flag;
    }

    public static boolean login(InCdrbRequest request) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));
        // Map<String, String> map = new HashMap<>();
        // map.put("Cookie2", cookie);
        // map.put("password", password);
        String path = "/SearchSvc/CVWebService.svc/Login";
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(path).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", request.getUsername());
        map.put("password", request.getPassword());
        String jsonString = JSON.toJSONString(map);
        ResponseResult responseResult = null;
        try {
            responseResult = executePost(uri, headers, jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = null;
        if (responseResult.getStatus() == 200) {
            String responseEntity = responseResult.getEntity();
            token = JSONObject.parseObject(responseEntity).getString("token");
        }
        boolean flag = false;
        if (StringUtils.isNotBlank(token)) {
            flag = true;
            putCookie(request.getUsername(), token);
        }
        return flag;
    }

    @Deprecated
    public static boolean login(URI uri, String username, String password) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));
        // Map<String, String> map = new HashMap<>();
        // map.put("Cookie2", cookie);
        // map.put("password", password);

        String path = "SearchSvc/CVWebService.svc/Login";
        // List<Header> headers = new ArrayList<>();
        // headers.add(new BasicHeader("Content-Type", "application/json"));
        // headers.add(new BasicHeader("Accept", "application/json"));
        // uri = new
        // URIBuilder().setScheme(request.getScheme()).setHost(request.getHost())
        // .setPort(request.getPort()).setPath(request.getPath())
        // .build();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String jsonString = JSON.toJSONString(map);
        ResponseResult responseResult = null;
        try {
            responseResult = executePost(uri, headers, jsonString);
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

    public static ResponseResult doGet(InCdrbRequest request) {
        String username = request.getUsername();
        if (!islogin(username, request)) {
            login(request);
        }
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(request.getPath()).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        List<Header> headers = request.getHeaders();
        System.out.println(getCookie(username));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));
        headers.add(new BasicHeader("Charset", "utf-8"));
        headers.add(new BasicHeader("Cookie2", getCookie(username)));
        String jsonString = JSON.toJSONString(request.getParams());
        return executeGet(uri, headers, jsonString);

    }

    public static ResponseResult doPost(InCdrbRequest request)
            throws IOException {
        String username = request.getUsername();
        if (!islogin(username, request)) {
            login(request);
        }
        // String password = request.getPassword();
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(request.getPath()).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        List<Header> headers = request.getHeaders();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));
        headers.add(new BasicHeader("Charset", "utf-8"));
        headers.add(new BasicHeader("Cookie2", getCookie(username)));
        String jsonString = JSON.toJSONString(request.getParams());
        return executePost(uri, headers, jsonString);

    }

    // String uri = "http://172.23.0.122:81/SearchSvc/CVWebService.svc/Login";
    public static void main(String[] args) throws IOException {
        InCdrbRequest request = new InCdrbRequest();
        request.setUsername("admin");
        request.setPassword(EncryptionUtil.base64("123456aA?"));
        request.setHost("172.23.0.122");
        request.setPort(81);
        request.setPath("/SearchSvc/CVWebService.svc/client");
        ResponseResult re = doPost(request);
        // request.setHeaders();
    }
}
