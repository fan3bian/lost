/**
 * 
 */
package com.inspur.incdr.base.client;

import java.io.IOException;

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
public class InCdrbClient {
    private InCdrCredentials credentials;
    private InCdrConfig config ;
    /**
     * @param config
     * @param credentials
     */
    public InCdrbClient( InCdrCredentials credentials,InCdrConfig config) {
        super();
        this.credentials = credentials;
        this.config = config;
    }
    public void addNode(InCdrbNodeRequest request){
         request =  assemble(request);
//        request.setPort(81);
        request.setPath("/SearchSvc/CVWebService.svc/client");
        ResponseResult responseResult =null;
        responseResult = InCdrbCVClient.doGet(request);
//        try {
//            responseResult = InCdrbCVClient.doPost(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @SuppressWarnings("unchecked")
    private <X,Y extends InCdrbRequest>X assemble (InCdrbRequest request){
        request.setUsername(credentials.username());
        request.setPassword(EncryptionUtil.base64(credentials.password()));
        request.setHost(config.getEndpoint());
        request.setPort(config.getPort());
        return (X)request;
    }
   public static void main(String[] args) {
       
   }
}
