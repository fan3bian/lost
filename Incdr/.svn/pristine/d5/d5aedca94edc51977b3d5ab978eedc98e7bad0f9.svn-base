/**
 * 
 */
package com.inspur.incdr.base.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inspur.incdr.base.client.HttpClient;
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
public class TestCVlogin {
    
    public static String genCookie() {
        String pwd = "123456aA?";
        pwd = new BASE64Encoder().encode(pwd.getBytes());
        System.out.println(pwd);
        String uri = "http://172.23.0.122:81/SearchSvc/CVWebService.svc/Login";
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));
        headers.add(new BasicHeader("Charset", "utf-8"));
        // List<NameValuePair> params = new ArrayList<>();
        // params.add(new BasicNameValuePair("username", "admin"));
        // params.add(new BasicNameValuePair("password", pwd));
        Map<String, String> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", pwd);
        String jsonStrng = JSON.toJSONString(map);
        ResponseResult responseResult = null;
        try {
            responseResult = HttpClient.doPost(uri, headers, jsonStrng);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseResult);
        String responseEntity =responseResult.getEntity();
        JSONObject jo= JSONObject.parseObject(responseEntity);
        String token =jo.getString("token");
        System.out.println(token);
        return token;
    }
    public static void main(String[] args) {
      String token = genCookie();
//      String uri = "http://172.23.0.122:81/SearchSvc/CVWebService.svc/Login";
      String uri = "http://172.23.0.122:81/SearchSvc/CVWebService.svc/client";
      List<Header> headers = new ArrayList<>();
      headers.add(new BasicHeader("Content-Type", "application/json"));
      headers.add(new BasicHeader("Accept", "application/json"));
      headers.add(new BasicHeader("Charset", "utf-8"));
      headers.add(new BasicHeader("Cookie2", token));
      Map<String, String> map = new HashMap<>();
      String jsonStrng = JSON.toJSONString(map);
      ResponseResult responseResult = null;
      responseResult = HttpClient.doGet(uri, headers, jsonStrng);
//          responseResult = HttpClient.doPost(uri, headers, jsonStrng);
      
      System.out.println(responseResult);
    }
}
