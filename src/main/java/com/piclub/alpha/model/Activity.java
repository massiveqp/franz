package com.piclub.alpha.model;

public class Activity {
    private String activityId;
    private String activityName;
    private Integer place;
    private Integer price;
    private String startTime;
    private String endTime;
    private Integer peopleLimit;
    private String memo;
    private String picUrl;
    private String createTime;
    private String updateTime;
    private Integer status;

    @Override
    public String toString() {
        return "Activity{" +
                "activityId='" + activityId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", place=" + place +
                ", price=" + price +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", peopleLimit=" + peopleLimit +
                ", memo='" + memo + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", status=" + status +
                '}';
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}