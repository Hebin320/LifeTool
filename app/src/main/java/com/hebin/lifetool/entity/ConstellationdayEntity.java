package com.hebin.lifetool.entity;

/**
 * Created by Administrator on 2016/9/28.
 */

public class ConstellationdayEntity {

    /**
     * date : 20160928
     * name : 狮子座
     * datetime : 2016年09月28日
     * all : 20%
     * color : 白色
     * health : 73%
     * love : 40%
     * money : 20%
     * number : 8
     * QFriend : 处女座
     * summary : 你可能积极思考着自己的财富状态，容易有一些悲观的想法出现，但是物质光靠精打细算很难有所突破，反而使自己的生活兴致降低。
     * work : 20%
     * resultcode : 200
     * error_code : 0
     */

    private int date;
    private String name;
    private String datetime;
    private String all;
    private String color;
    private String health;
    private String love;
    private String money;
    private int number;
    private String QFriend;
    private String summary;
    private String work;
    private String resultcode;
    private int error_code;

    public void setDate(int date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getAll() {
        return all;
    }

    public String getColor() {
        return color;
    }

    public String getHealth() {
        return health;
    }

    public String getLove() {
        return love;
    }

    public String getMoney() {
        return money;
    }

    public int getNumber() {
        return number;
    }

    public String getQFriend() {
        return QFriend;
    }

    public String getSummary() {
        return summary;
    }

    public String getWork() {
        return work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public int getError_code() {
        return error_code;
    }
}
