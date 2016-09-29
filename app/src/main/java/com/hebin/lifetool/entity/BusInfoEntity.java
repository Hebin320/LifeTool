package com.hebin.lifetool.entity;


import java.util.List;

public class BusInfoEntity {

    /**
     * reason : 查询成功
     * result : {"title":"佛山市长途汽车站_时刻表","list":[{"name":"佛山市鸿运汽车站","tel":"0757-82281287","adds":"佛山市汾江中路5号"},{"name":"佛山市粤运汽车客运站","tel":"0757-82838873","adds":"佛山市禅城区文昌路佛山火车站广场西侧"},{"name":"佛山市大沥汽车客运站","tel":"0757-81181111/0757-81188976","adds":"佛山市南海区大沥镇禅炭路沥西路段"},{"name":"佛山市汽车客运站","tel":"0757-82281287/0757-82232940","adds":"佛山市禅城区汾江中路6号"},{"name":"佛山市南海区西樵汽车客运站","tel":"0757-86826815","adds":"佛山市南海区西樵镇樵金南路189号"}]}
     * error_code : 0
     */

    private String reason;
    private ResultEntity result;
    private int error_code;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
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
         * title : 佛山市长途汽车站_时刻表
         * list : [{"name":"佛山市鸿运汽车站","tel":"0757-82281287","adds":"佛山市汾江中路5号"},{"name":"佛山市粤运汽车客运站","tel":"0757-82838873","adds":"佛山市禅城区文昌路佛山火车站广场西侧"},{"name":"佛山市大沥汽车客运站","tel":"0757-81181111/0757-81188976","adds":"佛山市南海区大沥镇禅炭路沥西路段"},{"name":"佛山市汽车客运站","tel":"0757-82281287/0757-82232940","adds":"佛山市禅城区汾江中路6号"},{"name":"佛山市南海区西樵汽车客运站","tel":"0757-86826815","adds":"佛山市南海区西樵镇樵金南路189号"}]
         */

        private String title;
        private List<ListEntity> list;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getTitle() {
            return title;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            /**
             * name : 佛山市鸿运汽车站
             * tel : 0757-82281287
             * adds : 佛山市汾江中路5号
             */

            private String name;
            private String tel;
            private String adds;

            public void setName(String name) {
                this.name = name;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public void setAdds(String adds) {
                this.adds = adds;
            }

            public String getName() {
                return name;
            }

            public String getTel() {
                return tel;
            }

            public String getAdds() {
                return adds;
            }
        }
    }
}
