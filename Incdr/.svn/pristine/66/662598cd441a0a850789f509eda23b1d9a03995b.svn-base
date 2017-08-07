/**
 * 
 */
package com.inspur.incdr.base.test;

import java.io.IOException;

import org.junit.Test;

import com.inspur.incdr.base.client.InCdrbCVClient;
import com.inspur.incdr.base.client.InCdrbClient;
import com.inspur.incdr.base.conf.BasicInCdrbConfig;
import com.inspur.incdr.base.credential.BasicInCdrCredentials;
import com.inspur.incdr.base.modal.request.InCdrbNodeRequest;
import com.inspur.incdr.base.modal.request.InCdrbRequest;
import com.inspur.incdr.credential.InCdrCredentials;
import com.inspur.incdr.http.ResponseResult;
import com.inspur.incdr.inter.InCdrConfig;
import com.inspur.incdr.util.EncryptionUtil;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author zhangshuyi 2017年8月7日
 * @see
 * @since 1.0
 */
public class Tst1 {
    @Test
    public void test(){
        InCdrbRequest request = new InCdrbNodeRequest();
        request.setUsername("admin");
        request.setPassword(EncryptionUtil.base64("123456aA?"));
        request.setHost("172.23.0.122");
        request.setPort(81);
        request.setPath("/SearchSvc/CVWebService.svc/client");
        ResponseResult responseResult =null;
//        try {
            responseResult = InCdrbCVClient.doGet(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(responseResult);
    }
    @Test
    public void test2(){
        InCdrCredentials credentials = BasicInCdrCredentials.credential("admin", EncryptionUtil.base64("123456aA?"));
        InCdrConfig config =new BasicInCdrbConfig.Builder("172.23.0.122").build();
        InCdrbClient cdrb =new InCdrbClient(credentials,config);
        InCdrbNodeRequest request = new InCdrbNodeRequest();
        cdrb.addNode(request);
    }
}
