package com.roundlun.iot.domain.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class SysCollection {
    private static final long serialVersionUID = 1L;

    /**
     * 采集ID
     */
    private Long collectionId;

    /**
     * 设备SN
     */
    private Long deviceSn;

    /**
     * 采集数据
     */
    private String value;

    /**
     * 采集时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setDeviceSn(Long deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Long getDeviceSn() {
        return deviceSn;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public SysCollection(Long deviceSn, String value, Date collectionTime) {
        this.deviceSn = deviceSn;
        this.value = value;
        this.collectionTime = collectionTime;
    }

    public SysCollection() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("collectionId", getCollectionId())
                .append("deviceSn", getDeviceSn())
                .append("value", getValue())
                .append("collectionTime", getCollectionTime())
                .toString();
    }
}
