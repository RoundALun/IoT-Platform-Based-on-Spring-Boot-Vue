package com.roundlun.iot.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class SysPropType {
    private static final long serialVersionUID = 1L;

    /**
     * 属性类型ID
     */
    private Long propTypeId;

    /**
     * 属性类型名称
     */
    private String propTypeName;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPropTypeId(Long propTypeId) {
        this.propTypeId = propTypeId;
    }

    public Long getPropTypeId() {
        return propTypeId;
    }

    public void setPropTypeName(String propTypeName) {
        this.propTypeName = propTypeName;
    }

    public String getPropTypeName() {
        return propTypeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("propTypeId", getPropTypeId())
                .append("propTypeName", getPropTypeName())
                .append("remark", getRemark())
                .toString();
    }
}
