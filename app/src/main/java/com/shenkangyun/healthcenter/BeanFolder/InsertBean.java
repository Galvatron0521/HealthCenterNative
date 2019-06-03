package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/11/27.
 */

public class InsertBean {

    /**
     * status : 0
     * data : {"message":"保存成功!"}
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
         * message : 保存成功!
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
