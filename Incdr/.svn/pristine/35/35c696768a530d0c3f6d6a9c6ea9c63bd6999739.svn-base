/**
 * 
 */
package com.inspur.incdr.enhance.client;

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
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inspur.incdr.base.client.HttpClient;
import com.inspur.incdr.enhance.modal.request.InCdreRequest;
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
public class InCdreI2Client {
    private InCdreI2Client() {
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

    public static ResponseResult doPost(InCdreRequest request)
            throws IOException {
        if (!islogin(request)) {
            login(request);
        }
        URI uri = null;
        ;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(request.getPath()).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Map<String, String> paramMap = request.getParams();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        nvps.add(new BasicNameValuePair("token", getCookie(request
                .getUsername())));
        nvps.add(new BasicNameValuePair("signature", "4LM5ySBu18NYj3FLYjqfLBx8="));

        return executePost(uri, null, nvps);
    }

    public static ResponseResult executePost(URI uri,
            Map<String, String> paramMap) throws IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        return executePost(uri, null, nvps);
    }

    public static ResponseResult executeGet(URI uri, List<Header> headers,
            List<NameValuePair> params) throws ClientProtocolException,
            IOException {
        if (headers == null)
            headers = Collections.emptyList();
        ResponseResult responseResult = new ResponseResult();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(uri);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000)
                .setConnectionRequestTimeout(10000).setSocketTimeout(30000)
                .build();
        httpGet.setConfig(config);
        httpGet.setHeaders(headers.toArray(new Header[headers.size()]));

        CloseableHttpResponse response = httpclient.execute(httpGet);

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

    public static ResponseResult executePost(URI uri, List<Header> headers,
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

    public static boolean islogin(InCdreRequest request) {
        if (StringUtils.isBlank(request.getUsername())) {
            throw new IllegalArgumentException("");
        }
        if (!existCookie(request.getUsername())) {  
            return false;
        }
        String cookie = getCookie(request.getUsername());

        Map<String, String> map = new HashMap<>();
        map.put("signature", "signature");
        map.put("token", cookie);
        String path = "/i2/api2/";
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(path).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        ResponseResult responseResult = null;
        try {
            responseResult = executePost(uri, map);
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

    public static boolean login(InCdreRequest request) {
        String path = "/i2/api2/";
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(path).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("signature", "4LM5ySBu18NYj3FLYjqfLBx8=");
        map.put("pwd", request.getPassword());
        map.put("username", request.getUsername());
        map.put("service", "Auth.Token");
        ResponseResult responseResult = null;
        try {
            responseResult = executePost(uri, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = null;
        if (responseResult.getStatus() == 200) {
            String responseEntity = responseResult.getEntity();
            JSONObject reMsg = JSONObject.parseObject(responseEntity);
            reMsg = reMsg.getJSONObject("data");
            token = reMsg.getString("token");
        }
        boolean flag = false;
        if (StringUtils.isNotBlank(token)) {
            flag = true;
            putCookie(request.getUsername(), token);
        }
        return flag;
    }

    public static ResponseResult doGet(InCdreRequest request)
            throws IOException {
        Map<String, String> paramMap = request.getParams();
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(request.getScheme())
                    .setHost(request.getHost()).setPort(request.getPort())
                    .setPath(request.getPath()).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (islogin(request)) {
            login(request);
        }

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        // List<Header> headers = request.getHeaders();
        // headers.add(new BasicHeader("Cookie2", getCookie(username)));
        nvps.add(new BasicNameValuePair("token", getCookie(request
                .getUsername())));
        nvps.add(new BasicNameValuePair("signature", ""));
        return executeGet(uri, null, nvps);

    }

    public static ResponseResult executeGet(URI uri,
            Map<String, String> paramMap) throws IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        return executePost(uri, null, nvps);
    }

    public static void main(String[] args) throws IOException {
        String pwd = "baadc39eb5055a2515fd371056eb6e4e";
        String uri = "http://172.23.63.12:58080/i2/api2/";
        List<Header> headers = new ArrayList<>();
        // headers.add(new BasicHeader("Content-Type", "application/json"));
        // headers.add(new BasicHeader("Accept", "application/json"));
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("signature",
                "4LM5ySBu18NYj3FLYjqfLBx8="));
        params.add(new BasicNameValuePair("pwd", pwd));
        params.add(new BasicNameValuePair("username", "admin"));
        params.add(new BasicNameValuePair("service", "Auth.Token"));
        //
        ResponseResult responseResult = null;
        try {
            responseResult = HttpClient.doPost(uri, headers, params);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseResult);
    }
}
