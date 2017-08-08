/**
 * 
 */
package com.inspur.incdr.json.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author zhangshuyi 2017年8月8日
 * @see
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
//XML文件中的根标识
@XmlRootElement(name = "App_CreateUserRequest")
public class CreateUserRequest {
    private User user ;

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CreateUserRequest [user=" + user + "]";
    }
    
    
    
}
