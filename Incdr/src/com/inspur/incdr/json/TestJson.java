/**
 * 
 */
package com.inspur.incdr.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.inspur.incdr.json.pojo.CreateUserRequest;
import com.inspur.incdr.json.pojo.User;
import com.inspur.incdr.json.pojo.UserEntity;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author zhangshuyi 2017年8月8日
 * @see
 * @since 1.0
 */
public class TestJson {
    @Test
    public void test1(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("jdoe");
        Map map =new HashMap();
        map.put("userGroupName", "View All");
        User user =new User();
        user.setUserEntity(userEntity);
        user.setEnableUser("True");
        user.setAgePasswordDays("10");
        user.setEmail("jdoe@company.com");
        user.setPassword("UDl1NDU4OQ==");
        user.setFullName("Jane Doe");
        user.setDescription("backup admin user");
        user.setAssociatedUserGroups(map);
        CreateUserRequest cur = new CreateUserRequest();
        cur.setUser(user);
        
        String jsonString =JSON.toJSONString(cur);  
        System.out.println(jsonString);
        
        CreateUserRequest vo = JSON.parseObject(jsonString, CreateUserRequest.class);
        System.out.println(vo);
//        Assert.assertTrue(vo == cur.getUser().get);
    }
}
