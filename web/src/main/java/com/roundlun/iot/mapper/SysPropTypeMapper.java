package com.roundlun.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.roundlun.iot.domain.entity.SysPropType;

/**
 * 属性类型Mapper接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Mapper
public interface SysPropTypeMapper {
    /**
     * 查询属性类型
     *
     * @param propTypeId 属性类型主键
     * @return 属性类型
     */
    public SysPropType selectSysPropTypeByPropTypeId(Long propTypeId);

    /**
     * 查询属性类型列表
     *
     * @param sysPropType 属性类型
     * @return 属性类型集合
     */
    public List<SysPropType> selectSysPropTypeList(SysPropType sysPropType);

    /**
     * 新增属性类型
     *
     * @param sysPropType 属性类型
     * @return 结果
     */
    public int insertSysPropType(SysPropType sysPropType);

    /**
     * 修改属性类型
     *
     * @param sysPropType 属性类型
     * @return 结果
     */
    public int updateSysPropType(SysPropType sysPropType);

    /**
     * 删除属性类型
     *
     * @param propTypeId 属性类型主键
     * @return 结果
     */
    public int deleteSysPropTypeByPropTypeId(Long propTypeId);

    /**
     * 批量删除属性类型
     *
     * @param propTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPropTypeByPropTypeIds(Long[] propTypeIds);
}
