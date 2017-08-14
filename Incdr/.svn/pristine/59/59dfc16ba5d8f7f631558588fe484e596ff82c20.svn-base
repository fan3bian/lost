/**
 * 
 */
package com.inspur.incdr.enhance.conf;

import com.inspur.incdr.inter.InCdrConfig;

/**
 * simple introduction
 * 
 * <p>
 * detailed comment
 * 
 * @author zhangshuyi 2017年8月4日
 * @see
 * @since 1.0
 */
public class BasicInCdreConfig implements InCdrConfig{
    private final String endpoint;
    private final String path;
    private final int port;

    public static class Builder {
        private final String endpoint;
        private String path;
        private int port = 58080;

        public Builder(String endpiont) {
            this.endpoint = endpiont;
        }
        public Builder port(int port){
            this.port =port;
            return this;
        }
        public Builder path(int port){
            this.port =port;
            return this;
        }
        public BasicInCdreConfig build(){
            return new BasicInCdreConfig(this);
        }
    }

    private  BasicInCdreConfig(Builder builder){
        endpoint = builder.endpoint;
        path = builder.path;
        port = builder.port;
        
    }
    /**
     * @see com.inspur.incdr.inter.InCdrConfig#getEndpoint()
     */

    /**
     * @see com.com.inspur.incdr.inter.InCdrConfig#getEndpoint()
     */
    @Override
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @see com.com.inspur.incdr.inter.InCdrConfig#getScheme()
     */
    @Override
    public String getScheme() {
        return path;
    }

    /**
     * @see com.com.inspur.incdr.inter.InCdrConfig#getPort()
     */
    @Override
    public Integer getPort() {
        return port;
    }

}
