package com.hebin.lifetool.entity.constellation;


public class ConstellationmonthEntity {


    /**
     * date : 2016年09月
     * name : 白羊座
     * all : 对工作仍然是心心念念，不过水逆游走在上旬和中旬，误会和意外会比较多，这也是你工作压力最大的时段，而且木星上旬就离开工作宫，之前乐观估计的，现在真实的漏洞纷纷浮出水面，拆东墙补西墙，够你忙活的。所幸，上旬和中旬搭档都比较给力，下旬就要更多动用私人关系或潜规则了。

     * happyMagic :
     * health : 身体的小毛病多，尤其容易感染呼吸系统疾病。上旬和中旬为水逆期，加上火星在迁徙宫位，出行需要小心意外。

     * love : 工作忙得一团糟，爱情上当然就分身无术了，不过运气还不错，爱人体谅你，而且可以给到你切实的帮助。单身汪还有机会在共事关系中找到有缘人。不过这么和谐的局面，到了下旬，会因为你的私心被打破，相互折磨的时刻到了，进入地下情高发期。

     * money : 上旬和中旬，个人运气不佳，意外破财机会多，需依傍合作关系提升财运。下旬，利好偏财，有机会获得意外的馈赠，热衷参与一些以小博大的高风险高回报项目。

     * month : 9
     * work : 基本参考总运势。要补充提醒的是，月末火星入事业宫，出现你极端想要争取到的目标，激起事业的强大动力；这一点和冥王在事业宫逆行的结束时间叠合，很可能是你人生重要的新的事业起征点。建议你要非常重视接下来两个月所遇见的事业机会。
     * resultcode : 200
     * error_code : 0
     */

    private String date;
    private String name;
    private String all;
    private String happyMagic;
    private String health;
    private String love;
    private String money;
    private int month;
    private String work;
    private String resultcode;
    private int error_code;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getHappyMagic() {
        return happyMagic;
    }

    public void setHappyMagic(String happyMagic) {
        this.happyMagic = happyMagic;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
