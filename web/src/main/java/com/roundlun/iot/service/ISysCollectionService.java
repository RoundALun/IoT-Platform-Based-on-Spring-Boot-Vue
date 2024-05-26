package com.roundlun.iot.service;

import com.roundlun.iot.domain.entity.SysCollection;
import com.roundlun.iot.domain.model.QueryData;

import java.util.List;

/**
 * 采集Service接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
public interface ISysCollectionService {
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
     * 批量删除采集
     *
     * @param collectionIds 需要删除的采集主键集合
     * @return 结果
     */
    public int deleteSysCollectionByCollectionIds(Long[] collectionIds);

    /**
     * 删除采集信息
     *
     * @param collectionId 采集主键
     * @return 结果
     */
    public int deleteSysCollectionByCollectionId(Long collectionId);
}
