package com.roundlun.iot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysDevice;
import com.roundlun.iot.mapper.SysDeviceMapper;
import com.roundlun.iot.service.ISysDeviceService;

import java.util.List;

/**
 * 设备Service业务层处理
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Service
public class SysDeviceServiceImpl implements ISysDeviceService {
    @Autowired
    private SysDeviceMapper sysDeviceMapper;

    /**
     * 查询设备
     *
     * @param deviceId 设备主键
     * @return 设备
     */
    @Override
    public SysDevice selectSysDeviceByDeviceId(Long deviceId) {
        return sysDeviceMapper.selectSysDeviceByDeviceId(deviceId);
    }

    /**
     * 查询设备
     *
     * @param deviceSn 设备SN
     * @return 设备
     */
    @Override
    public SysDevice selectSysDeviceByDeviceSn(Long deviceSn) {
        return sysDeviceMapper.selectSysDeviceByDeviceSn(deviceSn);
    }

    /**
     * 查询设备列表
     *
     * @param sysDevice 设备
     * @return 设备
     */
    @Override
    public List<SysDevice> selectSysDeviceList(SysDevice sysDevice) {
        return sysDeviceMapper.selectSysDeviceList(sysDevice);
    }

    /**
     * 新增设备
     *
     * @param sysDevice 设备
     * @return 结果
     */
    @Override
    public int insertSysDevice(SysDevice sysDevice) {
        return sysDeviceMapper.insertSysDevice(sysDevice);
    }

    /**
     * 修改设备
     *
     * @param sysDevice 设备
     * @return 结果
     */
    @Override
    public int updateSysDevice(SysDevice sysDevice) {
        return sysDeviceMapper.updateSysDevice(sysDevice);
    }

    /**
     * 批量删除设备
     *
     * @param deviceIds 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds) {
        return sysDeviceMapper.deleteSysDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除设备信息
     *
     * @param deviceId 设备主键
     * @return 结果
     */
    @Override
    public int deleteSysDeviceByDeviceId(Long deviceId) {
        return sysDeviceMapper.deleteSysDeviceByDeviceId(deviceId);
    }
}
