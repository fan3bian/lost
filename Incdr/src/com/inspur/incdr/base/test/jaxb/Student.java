package com.inspur.incdr.base.test.jaxb;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2016-06-14 18:35
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Student {
    private long id;
    private String name;

    @XmlTransient
    private int age;

    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    private List<String> hobbies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
