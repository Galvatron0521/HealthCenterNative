package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/7/7.
 */

public class RegisterBean {

    /**
     * status : -1
     * data : {"message":"手机号和用户名已注册!"}
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
         * message : 手机号和用户名已注册!
         */

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
