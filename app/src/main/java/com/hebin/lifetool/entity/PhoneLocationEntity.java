package com.hebin.lifetool.entity;

/**
 * 手机归属地
 */

public class PhoneLocationEntity {

    /**
     * resultcode : 200
     * reason : Return Successd!
     * result : {"province":"广东","city":"佛山","areacode":"0757","zip":"528000","company":"中国移动","card":"移动预付费卡"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultEntity result;
    private int error_code;

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public String getReason() {
        return reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public static class ResultEntity {
        /**
         * province : 广东
         * city : 佛山
         * areacode : 0757
         * zip : 528000
         * company : 中国移动
         * card : 移动预付费卡
         */

        private String province;
        private String city;
        private String areacode;
        private String zip;
        private String company;
        private String card;

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getAreacode() {
            return areacode;
        }

        public String getZip() {
            return zip;
        }

        public String getCompany() {
            return company;
        }

        public String getCard() {
            return card;
        }
    }
}
