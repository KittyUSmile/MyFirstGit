package com.entity;

public class province {
    //省份信息类
    private Integer id;//省份ID
    private String name;//省份名称
    private String abbr;//省份简称
    private String mainCity;//省会

    public province(Integer id, String name, String abbr, String mainCity) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.mainCity = mainCity;
    }

    public province() {
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

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getMainCity() {
        return mainCity;
    }

    public void setMainCity(String mainCity) {
        this.mainCity = mainCity;
    }
}
