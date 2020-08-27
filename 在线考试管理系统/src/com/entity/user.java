package com.entity;

public class user {
    //用户信息类
    private Integer id;     //用户id,   auto_increment
    private String name;    //姓名
    private String password;//密码
    private String sex;     //性别
    private String area;    //籍贯
    private String belong; //老师/学生    根据注册赋值

    public user() {}         //无参构造

    public user(Integer id, String name, String password, String sex, String area, String belong) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.area = area;
        this.belong = belong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
