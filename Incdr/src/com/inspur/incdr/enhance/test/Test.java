/**
 * 
 */
package com.inspur.incdr.enhance.test;

import com.inspur.incdr.base.credential.BasicInCdrCredentials;
import com.inspur.incdr.credential.InCdrCredentials;
import com.inspur.incdr.enhance.client.InCdreClient;
import com.inspur.incdr.enhance.conf.BasicInCdreConfig;
import com.inspur.incdr.enhance.modal.InCdreNodeRequest;
import com.inspur.incdr.inter.InCdrConfig;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author zhangshuyi 2017年8月7日
 * @see
 * @since 1.0
 */
public class Test {
    @org.junit.Test
   public void test(){
        InCdrCredentials credentials = BasicInCdrCredentials.credential("admin", "baadc39eb5055a2515fd371056eb6e4e");
        InCdrConfig config =new BasicInCdreConfig.Builder("172.23.63.12").port(58080).build();
        InCdreClient cdre =new InCdreClient(credentials,config);
        InCdreNodeRequest request = new InCdreNodeRequest();
        cdre.addNode(request);
   }
}
