package com.hebin.lifetool.entity;

/**
 * 参数的Entity类
 */

public class DataEntity {

    private int page;
    private String phone;

    private String consname;
    private String type;

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
}
