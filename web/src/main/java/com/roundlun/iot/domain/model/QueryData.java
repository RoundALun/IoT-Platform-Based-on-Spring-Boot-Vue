package com.roundlun.iot.domain.model;

import java.util.Date;

public class QueryData {

    private static final long serialVersionUID = 1L;

    private Long deviceSn;

    private String propertyKey;

    private Date beginTime;

    private Date endTime;

    public Long getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(Long deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
