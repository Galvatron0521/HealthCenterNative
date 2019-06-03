package com.shenkangyun.healthcenter.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/11/16.
 */

public class QuestionBean {
    private String id;
    private String diseasesID;
    private String moduleCode;
    private String fieldName;
    private String fieldControlName;
    private String displayName;
    private Object suffixName;
    private String fieldType;
    private int isNewline;
    private int isHaveChild;
    private int fieldRequired;
    private Object createUser;
    private long createTime;
    private Object updateUser;
    private Object updateTime;
    private int delFlag;
    private Object remark;
    private String scores;
    private Object lowerLimit;
    private Object upperLimit;
    private List<AddEPDSBean.DataBean.FieldListBean.ChildListBeanX> childList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiseasesID() {
        return diseasesID;
    }

    public void setDiseasesID(String diseasesID) {
        this.diseasesID = diseasesID;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldControlName() {
        return fieldControlName;
    }

    public void setFieldControlName(String fieldControlName) {
        this.fieldControlName = fieldControlName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Object getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(Object suffixName) {
        this.suffixName = suffixName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getIsNewline() {
        return isNewline;
    }

    public void setIsNewline(int isNewline) {
        this.isNewline = isNewline;
    }

    public int getIsHaveChild() {
        return isHaveChild;
    }

    public void setIsHaveChild(int isHaveChild) {
        this.isHaveChild = isHaveChild;
    }

    public int getFieldRequired() {
        return fieldRequired;
    }

    public void setFieldRequired(int fieldRequired) {
        this.fieldRequired = fieldRequired;
    }

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
        this.createUser = createUser;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Object updateUser) {
        this.updateUser = updateUser;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public Object getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Object lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Object getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Object upperLimit) {
        this.upperLimit = upperLimit;
    }

    public List<AddEPDSBean.DataBean.FieldListBean.ChildListBeanX> getChildList() {
        return childList;
    }

    public void setChildList(AddEPDSBean.DataBean.FieldListBean.ChildListBeanX childList) {
        this.childList = (List<AddEPDSBean.DataBean.FieldListBean.ChildListBeanX>) childList;
    }
}
