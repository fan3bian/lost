/**
 * 
 */
package com.inspur.incdr.base;

/**
 * simple introduction
 * 
 * <p>
 * detailed comment
 * 
 * @author zhangshuyi 2017年8月2日
 * @see
 * @since 1.0
 */
public class InCdreConfig {

    private String accessKey;
    private String secretaKey;
    private String endpoint;
    private Integer port;

    /**
     * @return the accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * @return the secretaKey
     */
    public String getSecretaKey() {
        return secretaKey;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }
    public InCdreConfig builder(){
        
        return this;
    }

    /**
     * @param accessKey the accessKey to set
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * @param secretaKey the secretaKey to set
     */
    public void setSecretaKey(String secretaKey) {
        this.secretaKey = secretaKey;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }
    
}
