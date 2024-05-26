package com.roundlun.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.roundlun.iot.domain.entity.SysProperty;

/**
 * 属性Mapper接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Mapper
public interface SysPropertyMapper {
    /**
     * 查询属性
     *
     * @param propertyId 属性主键
     * @return 属性
     */
    public SysProperty selectSysPropertyByPropertyId(Long propertyId);

    /**
     * 查询属性列表
     *
     * @param sysProperty 属性
     * @return 属性集合
     */
    public List<SysProperty> selectSysPropertyList(SysProperty sysProperty);

    /**
     * 新增属性
     *
     * @param sysProperty 属性
     * @return 结果
     */
    public int insertSysProperty(SysProperty sysProperty);

    /**
     * 修改属性
     *
     * @param sysProperty 属性
     * @return 结果
     */
    public int updateSysProperty(SysProperty sysProperty);

    /**
     * 删除属性
     *
     * @param propertyId 属性主键
     * @return 结果
     */
    public int deleteSysPropertyByPropertyId(Long propertyId);

    /**
     * 批量删除属性
     *
     * @param propertyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPropertyByPropertyIds(Long[] propertyIds);
}
