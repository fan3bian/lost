package com.inspur.incdr.base.test.jaxb;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 收货地址
 *
 * @author Ricky Fung
 * @create 2016-06-13 18:28
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    private String province;
    private String city;
    private String district;
    private String street;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}