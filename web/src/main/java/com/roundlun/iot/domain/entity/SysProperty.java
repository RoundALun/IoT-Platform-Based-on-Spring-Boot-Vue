package com.roundlun.iot.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class SysProperty {
    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    private Long propertyId;

    /**
     * 协议ID
     */
    private Long protocolId;

    /**
     * 属性类型ID
     */
    private Long propTypeId;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性标识
     */
    private String propertyKey;

    /**
     * 单位
     */
    private String unit;

    /**
     * 属性类型
     */
    private SysPropType propType;

    public SysPropType getPropType() {
        return propType;
    }

    public void setPropType(SysPropType propType) {
        this.propType = propType;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setPropTypeId(Long propTypeId) {
        this.propTypeId = propTypeId;
    }

    public Long getPropTypeId() {
        return propTypeId;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("propertyId", getPropertyId())
                .append("protocolId", getProtocolId())
                .append("propTypeId", getPropTypeId())
                .append("propertyName", getPropertyName())
                .append("propertyKey", getPropertyKey())
                .append("unit", getUnit())
                .toString();
    }
}
