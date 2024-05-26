package com.roundlun.iot.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class SysProtocol {
    private static final long serialVersionUID = 1L;

    /**
     * 协议ID
     */
    private Long protocolId;

    /**
     * 协议名称
     */
    private String protocolName;

    /**
     * 用户ID
     */
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolName() {
        return protocolName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("protocolId", getProtocolId())
                .append("protocolName", getProtocolName())
                .toString();
    }
}
