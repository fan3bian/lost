package com.inspur.incdr.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Encoder;

public class HttpUtil {
	public static String serviceUrl = "http://172.24.0.240:81/SearchSvc/CVWebService.svc";
	
	public static String doPost(String requestUrl, String params){
		if(params == null){
			params = "";
		}
		String responseText = null;
        PrintWriter printWriter = null;  
        HttpURLConnection connection = null;
		try {  
			URL url = new URL(requestUrl);  
			// 打开连接  
			connection = (HttpURLConnection) url.openConnection();  
			// 设置通用的请求属性  
			connection.setRequestProperty("accept", "application/xml");  
			connection.setRequestProperty("connection", "Keep-Alive");  
			connection.setRequestProperty("Content-Length", String  
					.valueOf(params.length())); 
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setUseCaches(false);
			connection.setRequestMethod("POST"); 
			
			// 发送POST请求必须设置如下两行  
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			// 获取URLConnection对象对应的输出流  
			printWriter = new PrintWriter(connection.getOutputStream());  
			// 发送请求参数  
			printWriter.write(params);  
			// flush输出流的缓冲  
			printWriter.flush();
			printWriter.close();
			
			// 根据ResponseCode判断连接是否成功  
			int responseCode = connection.getResponseCode(); 
			if (responseCode != HttpURLConnection.HTTP_OK) {  
				System.out.println(requestUrl + " Error=== responseCode:" + responseCode);
			} else {
				System.out.println("Post Success,URL is : "+requestUrl);
			}
			responseText = readStream(connection.getInputStream());
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {  
			try {  
				if (printWriter != null) {  
					printWriter.close();  
				}  
				connection.disconnect();  
			} catch (Exception ex) {  
				ex.printStackTrace();  
			}  
			
		}  
		return responseText;
	}
	public static String doPostByToken(String requestUrl, String params){
		String token = getToken();
		return doPostByToken(requestUrl, params, token);
	}
	public static String doPostByToken(String requestUrl, String params, String token){
		if(params == null){
			params = "";
		}
		String responseText = null;
        PrintWriter printWriter = null;  
        HttpURLConnection connection = null;
		try {  
			URL url = new URL(requestUrl);  
			// 打开连接  
			connection = (HttpURLConnection) url.openConnection();  
			// 设置通用的请求属性  
			connection.setRequestProperty("accept", "application/xml");  
			connection.setRequestProperty("connection", "Keep-Alive");  
			connection.setRequestProperty("Content-Length", String  
					.valueOf(params.length())); 
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Cookie2", token);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST"); 
			
			// 发送POST请求必须设置如下两行  
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			// 获取URLConnection对象对应的输出流  
			printWriter = new PrintWriter(connection.getOutputStream());  
			// 发送请求参数  
			printWriter.write(params);  
			// flush输出流的缓冲  
			printWriter.flush();
			printWriter.close();
			
			// 根据ResponseCode判断连接是否成功  
			int responseCode = connection.getResponseCode();  
			if (responseCode != 200) {  
				System.out.println(" Error===" + responseCode);
			} else {
				System.out.println("Post Success,URL is : "+requestUrl);
			}  
			responseText = readStream(connection.getInputStream());
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {  
			try {  
				if (printWriter != null) {  
					printWriter.close();  
				}  
				connection.disconnect();  
			} catch (Exception ex) {  
				ex.printStackTrace();  
			}  
			
		}  
		return responseText;
	}
	
	public static String doGetByToken(String requestUrl){
		String token = getToken();
		return doGetByToken(requestUrl, token);
	}
	/**
	 * @param requestUrl 请求url
	 * @param token token
	 * */
	public static String doGetByToken(String requestUrl, String token){
		String responseText = null;
        HttpURLConnection connection= null; 
		try {  
			URL url = new URL(requestUrl);  
			// 打开和URL之间的连接  
			connection= (HttpURLConnection) url.openConnection();  
			// 设置通用的请求属性  
			connection.setRequestProperty("accept", "application/xml");  
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Cookie2", token);
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setUseCaches(false);
			connection.setRequestMethod("GET"); 
			connection.setDoOutput(false);  
			connection.setDoInput(true); 
			// 根据ResponseCode判断连接是否成功  
			int responseCode = connection.getResponseCode(); 
			if (responseCode != HttpURLConnection.HTTP_OK) {  
				System.out.println(requestUrl + " Error=== responseCode:" + responseCode);
			} else {
				System.out.println("Get Success,URL is : "+requestUrl);
			} 
			responseText = readStream(connection.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			try {  
				connection.disconnect();  
			} catch (Exception ex) {  
				ex.printStackTrace();  
			}  
		}
		return responseText;
	}
	
    /**
     * 获取token
     * */
    public static String getToken(){
    	String token = null;
    	try {
    		String requestUrl = serviceUrl + "/Login";
    		String password = "123456aA?";
    		String params = "<DM2ContentIndexing_CheckCredentialReq mode=\"Webconsole\" "
    				+ "username=\"admin\" password=\""+getBase64(password)+"\" />";
    		String text = doPost(requestUrl, params);
    		Document document = DocumentHelper.parseText(text);
			Element root = document.getRootElement();
			Attribute tokenAttr = root.attribute("token");
			token = tokenAttr.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			token = null;
		}
    	return token;
    }
	/**
	 * 加密
	 * */   
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }
    
    public static String readStream(InputStream inStream){
    	StringBuffer sb = new StringBuffer();
    	BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(  
					inStream, "UTF-8"));
			String line = null;  
			while ((line = bufferedReader.readLine()) != null) {  
				sb.append(line).append("\r\n");  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {  
				if (bufferedReader != null) {  
					bufferedReader.close();  
				}  
			} catch (Exception ex) {  
				ex.printStackTrace();  
			}  
		} 
		return sb.toString();
    }
}
