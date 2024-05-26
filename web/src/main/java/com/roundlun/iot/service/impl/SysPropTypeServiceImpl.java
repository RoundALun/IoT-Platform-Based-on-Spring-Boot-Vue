package com.roundlun.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysPropType;
import com.roundlun.iot.mapper.SysPropTypeMapper;
import com.roundlun.iot.service.ISysPropTypeService;

/**
 * 属性类型Service业务层处理
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Service
public class SysPropTypeServiceImpl implements ISysPropTypeService {
    @Autowired
    private SysPropTypeMapper sysPropTypeMapper;

    /**
     * 查询属性类型
     *
     * @param propTypeId 属性类型主键
     * @return 属性类型
     */
    @Override
    public SysPropType selectSysPropTypeByPropTypeId(Long propTypeId) {
        return sysPropTypeMapper.selectSysPropTypeByPropTypeId(propTypeId);
    }

    /**
     * 查询属性类型列表
     *
     * @param sysPropType 属性类型
     * @return 属性类型
     */
    @Override
    public List<SysPropType> selectSysPropTypeList(SysPropType sysPropType) {
        return sysPropTypeMapper.selectSysPropTypeList(sysPropType);
    }

    /**
     * 新增属性类型
     *
     * @param sysPropType 属性类型
     * @return 结果
     */
    @Override
    public int insertSysPropType(SysPropType sysPropType) {
        return sysPropTypeMapper.insertSysPropType(sysPropType);
    }

    /**
     * 修改属性类型
     *
     * @param sysPropType 属性类型
     * @return 结果
     */
    @Override
    public int updateSysPropType(SysPropType sysPropType) {
        return sysPropTypeMapper.updateSysPropType(sysPropType);
    }

    /**
     * 批量删除属性类型
     *
     * @param propTypeIds 需要删除的属性类型主键
     * @return 结果
     */
    @Override
    public int deleteSysPropTypeByPropTypeIds(Long[] propTypeIds) {
        return sysPropTypeMapper.deleteSysPropTypeByPropTypeIds(propTypeIds);
    }

    /**
     * 删除属性类型信息
     *
     * @param propTypeId 属性类型主键
     * @return 结果
     */
    @Override
    public int deleteSysPropTypeByPropTypeId(Long propTypeId) {
        return sysPropTypeMapper.deleteSysPropTypeByPropTypeId(propTypeId);
    }
}
