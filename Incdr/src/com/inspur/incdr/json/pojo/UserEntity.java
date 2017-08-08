/**
 * 
 */
package com.inspur.incdr.json.pojo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author zhangshuyi 2017年8月8日
 * @see
 * @since 1.0
 */
public class UserEntity {
    private String userName;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserEntity [userName=" + userName + "]";
    }
    
}
