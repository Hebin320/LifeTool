package com.hebin.lifetool.entity;

/**
 * 参数的Entity类
 */

public class DataEntity {


    private int page;
    private String phone;
    private String consname;
    private String type;
    private String station;
    private String time;
    private String month;
    private String day;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public void setConsname(String consname) {
        this.consname = consname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConsname() {
        return consname;
    }

    public String getType() {
        return type;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStation() {
        return station;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }
}
