package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/8/21.
 */

public class ChangeBean {

    /**
     * status : 0
     * data : {"msg":"密码修改成功"}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 密码修改成功
         */

        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
