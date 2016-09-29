package com.hebin.lifetool.entity.constellation;


public class ConstellationweekEntity {

    /**
     * date : 2016年09月25日-2016年10月01日
     * name : 狮子座
     * health : 健康：容易爆发急性疾病，有隐患在身的，建议随身备好常用药。 作者：马子晴
     * job : 求职：换环境的机会多，但你眷恋熟悉的环境，最终迈出多大的步子还要看你的选择。
     * love : 恋情：同居的季节，和喜欢的人宅在家里，过些很平淡的日常，就觉得幸福了。有逼婚大计的狮子，现在可以出手了。被虐的是单身汪，你会比任何时候，都更渴望稳定的情感关系，心甘情愿转入相亲频道。
     * money : 财运：更信赖那些之前给你带来收益的投资理财模式。
     * weekth : 40
     * work : 工作：火星本周入工作宫，在接下来的一个月里，对工作的重视会迅速提升，你自然会变得很用功，同时对工作的掌控欲也很强，一旦发现有人插手或拖延你的进度，脾气就会很大。
     * resultcode : 200
     * error_code : 0
     */

    private String date;
    private String name;
    private String health;
    private String job;
    private String love;
    private String money;
    private int weekth;
    private String work;
    private String resultcode;
    private int error_code;

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setWeekth(int weekth) {
        this.weekth = weekth;
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

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getJob() {
        return job;
    }

    public String getLove() {
        return love;
    }

    public String getMoney() {
        return money;
    }

    public int getWeekth() {
        return weekth;
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
