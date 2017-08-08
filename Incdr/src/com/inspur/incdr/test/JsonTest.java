/**
 * 
 */
package com.inspur.incdr.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

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
public class JsonTest {
    @Test
    public void test() {
        Map map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        String mapJson =JSON.toJSONString(map);
        System.out.println(mapJson);
        
    }
}
