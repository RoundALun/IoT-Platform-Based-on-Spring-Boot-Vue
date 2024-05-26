package com.roundlun.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.roundlun.iot.domain.entity.SysCollection;
import com.roundlun.iot.domain.model.QueryData;

/**
 * 采集Mapper接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Mapper
public interface SysCollectionMapper {
    /**
     * 查询采集
     *
     * @param collectionId 采集主键
     * @return 采集
     */
    public SysCollection selectSysCollectionByCollectionId(Long collectionId);

    /**
     * 查询采集列表
     *
     * @param sysCollection 采集
     * @return 采集集合
     */
    public List<SysCollection> selectSysCollectionList(SysCollection sysCollection);

    /**
     * 查询采集列表
     *
     * @param queryData 历史采集
     * @return 采集集合
     */
    public List<SysCollection> selectHistoryDataList(QueryData queryData);

    /**
     * 新增采集
     *
     * @param sysCollection 采集
     * @return 结果
     */
    public int insertSysCollection(SysCollection sysCollection);

    /**
     * 修改采集
     *
     * @param sysCollection 采集
     * @return 结果
     */
    public int updateSysCollection(SysCollection sysCollection);

    /**
     * 删除采集
     *
     * @param collectionId 采集主键
     * @return 结果
     */
    public int deleteSysCollectionByCollectionId(Long collectionId);

    /**
     * 批量删除采集
     *
     * @param collectionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCollectionByCollectionIds(Long[] collectionIds);
}
