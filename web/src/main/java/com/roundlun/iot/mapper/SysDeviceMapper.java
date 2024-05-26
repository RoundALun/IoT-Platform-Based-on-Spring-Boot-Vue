package com.roundlun.iot.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.roundlun.iot.domain.entity.SysDevice;

import java.util.List;

/**
 * 设备Mapper接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Mapper
public interface SysDeviceMapper {
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
     * 删除设备
     *
     * @param deviceId 设备主键
     * @return 结果
     */
    public int deleteSysDeviceByDeviceId(Long deviceId);

    /**
     * 批量删除设备
     *
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds);
}
