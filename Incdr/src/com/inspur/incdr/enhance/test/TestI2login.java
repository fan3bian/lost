/**
 * 
 */
package com.inspur.incdr.enhance.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

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
public class TestI2login {
    public static void main(String[] args) {
//        http://172.23.63.12:58080/i2/api2/?signature=4LM5ySBu18NYj3FLYjqfLBx8=&pwd=baadc39eb5055a2515fd371056eb6e4e&service=Auth.Token&username=admin
        String pwd = "baadc39eb5055a2515fd371056eb6e4e";
//        pwd = new BASE64Encoder().encode(pwd.getBytes());
//        System.out.println(pwd);
        //   signature=4LM5ySBu18NYj3FLYjqfLBx8=&pwd=baadc39eb5055a2515fd371056eb6e4e&service=Auth.Token&username=admin
        String uri = "http://172.23.63.12:58080/i2/api2/";
        List<Header> headers = new ArrayList<>();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("signature", "4LM5ySBu18NYj3FLYjqfLBx8="));
        params.add(new BasicNameValuePair("pwd", pwd));
        params.add(new BasicNameValuePair("username", "admin"));
        params.add(new BasicNameValuePair("service", "Auth.Token"));
//      
//        String jsonStrng = JSON.toJSONString(map);
        ResponseResult responseResult = null;
//        responseResult = HttpClient.doGet(uri, headers, params);
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
