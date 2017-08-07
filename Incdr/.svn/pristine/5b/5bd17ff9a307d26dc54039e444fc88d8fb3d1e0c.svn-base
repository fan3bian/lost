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
public class BasicInCdrCredentialsOld {
    private final String accessKey;
    private final String secretKey;

    public BasicInCdrCredentialsOld(String accessKey, String secretKey) {
        if (accessKey == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        }
        if (secretKey == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        }

        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAWSAccessKeyId() {
        return accessKey;
    }

    public String getAWSSecretKey() {
        return secretKey;
    }
}
