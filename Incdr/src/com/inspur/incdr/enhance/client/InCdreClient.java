/**
 * 
 */
package com.inspur.incdr.enhance.client;

import java.io.IOException;

import com.inspur.incdr.base.modal.request.InCdrbRequest;
import com.inspur.incdr.credential.InCdrCredentials;
import com.inspur.incdr.enhance.modal.InCdreNodeRequest;
import com.inspur.incdr.enhance.modal.request.InCdreRequest;
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
public class InCdreClient {
    private InCdrCredentials credentials;
    private InCdrConfig config ;
    /**
     * @param config
     * @param credentials
     */
    public InCdreClient( InCdrCredentials credentials,InCdrConfig config) {
        super();
        this.credentials = credentials;
        this.config = config;
    }
    public void addNode(InCdreNodeRequest request){
         request =  assemble(request);
//        request.setPort(81);
        request.setPath("/SearchSvc/CVWebService.svc/client");
        ResponseResult responseResult =null;
        try {
            responseResult = InCdreI2Client.doPost(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    private <X,Y extends InCdreRequest>X assemble (InCdreRequest request){
        request.setUsername(credentials.username());
        request.setPassword(EncryptionUtil.base64(credentials.password()));
        request.setHost(config.getEndpoint());
        request.setPort(config.getPort());
        return (X)request;
    }
   public static void main(String[] args) {
       
   }
}
