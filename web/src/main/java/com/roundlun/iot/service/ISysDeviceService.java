package com.roundlun.iot.service;

import com.roundlun.iot.domain.entity.SysDevice;

import java.util.List;

/**
 * 设备Service接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
public interface ISysDeviceService {
    /**
     * 查询设备
     *
     * @param deviceId 设备主键
     * @return 设备
     */
    public SysDevice selectSysDeviceByDeviceId(Long deviceId);

    /**
     * 查询设备
     *
     * @param deviceSn 设备SN
     * @return 设备
     */
    public SysDevice selectSysDeviceByDeviceSn(Long deviceSn);

    /**
     * 查询设备列表
     *
     * @param sysDevice 设备
     * @return 设备集合
     */
    public List<SysDevice> selectSysDeviceList(SysDevice sysDevice);

    /**
     * 新增设备
     *
     * @param sysDevice 设备
     * @return 结果
     */
    public int insertSysDevice(SysDevice sysDevice);

    /**
     * 修改设备
     *
     * @param sysDevice 设备
     * @return 结果
     */
    public int updateSysDevice(SysDevice sysDevice);

    /**
     * 批量删除设备
     *
     * @param deviceIds 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备信息
     *
     * @param deviceId 设备主键
     * @return 结果
     */
    public int deleteSysDeviceByDeviceId(Long deviceId);
}
