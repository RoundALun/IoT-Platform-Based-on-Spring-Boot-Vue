package com.roundlun.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysProperty;
import com.roundlun.iot.mapper.SysPropertyMapper;
import com.roundlun.iot.service.ISysPropertyService;

/**
 * 属性Service业务层处理
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Service
public class SysPropertyServiceImpl implements ISysPropertyService {
    @Autowired
    private SysPropertyMapper sysPropertyMapper;

    /**
     * 查询属性
     *
     * @param propertyId 属性主键
     * @return 属性
     */
    @Override
    public SysProperty selectSysPropertyByPropertyId(Long propertyId) {
        return sysPropertyMapper.selectSysPropertyByPropertyId(propertyId);
    }

    /**
     * 查询属性列表
     *
     * @param sysProperty 属性
     * @return 属性
     */
    @Override
    public List<SysProperty> selectSysPropertyList(SysProperty sysProperty) {
        return sysPropertyMapper.selectSysPropertyList(sysProperty);
    }

    /**
     * 新增属性
     *
     * @param sysProperty 属性
     * @return 结果
     */
    @Override
    public int insertSysProperty(SysProperty sysProperty) {
        return sysPropertyMapper.insertSysProperty(sysProperty);
    }

    /**
     * 修改属性
     *
     * @param sysProperty 属性
     * @return 结果
     */
    @Override
    public int updateSysProperty(SysProperty sysProperty) {
        return sysPropertyMapper.updateSysProperty(sysProperty);
    }

    /**
     * 批量删除属性
     *
     * @param propertyIds 需要删除的属性主键
     * @return 结果
     */
    @Override
    public int deleteSysPropertyByPropertyIds(Long[] propertyIds) {
        return sysPropertyMapper.deleteSysPropertyByPropertyIds(propertyIds);
    }

    /**
     * 删除属性信息
     *
     * @param propertyId 属性主键
     * @return 结果
     */
    @Override
    public int deleteSysPropertyByPropertyId(Long propertyId) {
        return sysPropertyMapper.deleteSysPropertyByPropertyId(propertyId);
    }
}
