package com.inspur.incdr.base.test.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2016-06-14 18:34
 */
public class JAXBExcludeDemo {

    public static void main(String[] args) throws JAXBException {

        Student student = new Student();
        student.setId(1l);
        student.setName("Ricky");
        student.setAge(27);

        List<String> hobbies = new ArrayList<String>();
        hobbies.add("NBA");
        hobbies.add("电影");
        student.setHobbies(hobbies);

        String xml = JAXBUtils.marshal(student);
        System.out.println(xml);
    }
}