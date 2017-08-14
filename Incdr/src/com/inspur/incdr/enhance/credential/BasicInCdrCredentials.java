/**
 * 
 */
package com.inspur.incdr.enhance.credential;

import com.inspur.incdr.credential.InCdrCredentials;


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
public class BasicInCdrCredentials implements InCdrCredentials {

    private final String uername;
    private final String password;

    /**
     * 
     */
    private BasicInCdrCredentials(String username, String password) {
     
        this.uername = username;
        this.password = password;

    }
    public static InCdrCredentials credential(String username, String password){
        if (username == null) {
            throw new IllegalArgumentException(
                    "The username key cannot be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException(
                    "The password key cannot be null.");
        }
        return new BasicInCdrCredentials(username,password);
    }
    /**
     * @see com.inspur.incdr.base.credential.InCdrCredentials#username()
     */
    @Override
    public String username() {
        return uername;
    }

    /**
     * @see com.inspur.incdr.base.credential.InCdrCredentials#password()
     */
    @Override
    public String password() {
        return password;
    }

}
