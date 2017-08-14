/**
 * 
 */
package com.inspur.incdr.enhance.test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author zhangshuyi 2017年8月8日
 * @see
 * @since 1.0
 */
public class TestSort {
    @Test
    public void test(){
        Map<String,String> map = new TreeMap<>();
        map.put("service", "Node.Auth");
        map.put("ip", "172.23.63.13");
        map.put("ospasswd", "MTIz");
        map.put("osuser", "root");
        map.put("port", "26821");
        System.out.println(map);
        StringBuffer buffer =new StringBuffer();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();  
        while (it.hasNext())  
        {  
            Map.Entry<String, String> entry = it.next();  
            Object key = entry.getKey();  
            buffer.append(key);  
            buffer.append('=');  
            Object value = entry.getValue();  
            buffer.append(value);  
            if (it.hasNext())  
            {  
                buffer.append("&");  
            }  
        }  
        System.out.println(buffer.toString()); 
    }
}
