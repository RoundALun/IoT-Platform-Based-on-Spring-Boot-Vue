package com.roundlun.iot.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysDevice {
    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    private Long deviceId;

    /**
     * 设备SN
     */
    private Long deviceSn;

    /**
     * 设备密码
     */
    private String devicePwd;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 协议ID
     */
    private Long protocolId;

    /**
     * 协议
     */
    private SysProtocol protocol;

    public SysProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(SysProtocol protocol) {
        this.protocol = protocol;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceSn(Long deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Long getDeviceSn() {
        return deviceSn;
    }

    public void setDevicePwd(String devicePwd) {
        this.devicePwd = devicePwd;
    }

    public String getDevicePwd() {
        return devicePwd;
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deviceSn", getDeviceSn())
                .append("devicePwd", getDevicePwd())
                .append("userId", getUserId())
                .append("protocolId", getProtocolId())
                .toString();
    }
}
