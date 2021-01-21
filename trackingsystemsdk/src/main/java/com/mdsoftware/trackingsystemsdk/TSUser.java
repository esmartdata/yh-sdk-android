package com.mdsoftware.trackingsystemsdk;

public class TSUser {

    public static String guid;
    public String real_name;
    public String nick_name;
    public String age;
    public String birthday;
    public String gender;
    public String account;
    public String country;
    public String province;
    public String city;

    public static String getGuid() {
        return guid;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getAccount() {
        return account;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
