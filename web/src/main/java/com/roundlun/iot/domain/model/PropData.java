package com.roundlun.iot.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PropData {

    private static final long serialVersionUID = 1L;

    /**
     * 属性标识
     */
    private String propertyKey;

    /**
     * 属性数值
     */
    private Object propertyValue;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 采集时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}
