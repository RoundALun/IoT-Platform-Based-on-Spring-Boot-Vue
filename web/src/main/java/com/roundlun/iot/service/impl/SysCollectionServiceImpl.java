package com.roundlun.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysCollection;
import com.roundlun.iot.domain.model.QueryData;
import com.roundlun.iot.mapper.SysCollectionMapper;
import com.roundlun.iot.service.ISysCollectionService;

/**
 * 采集Service业务层处理
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Service
public class SysCollectionServiceImpl implements ISysCollectionService {
    @Autowired
    private SysCollectionMapper sysCollectionMapper;

    /**
     * 查询采集
     *
     * @param collectionId 采集主键
     * @return 采集
     */
    @Override
    public SysCollection selectSysCollectionByCollectionId(Long collectionId) {
        return sysCollectionMapper.selectSysCollectionByCollectionId(collectionId);
    }

    /**
     * 查询采集列表
     *
     * @param sysCollection 采集
     * @return 采集
     */
    @Override
    public List<SysCollection> selectSysCollectionList(SysCollection sysCollection) {
        return sysCollectionMapper.selectSysCollectionList(sysCollection);
    }

    /**
     * 查询采集列表
     *
     * @param queryData 历史采集
     * @return 采集集合
     */
    @Override
    public List<SysCollection> selectHistoryDataList(QueryData queryData) {
        return sysCollectionMapper.selectHistoryDataList(queryData);
    }

    /**
     * 新增采集
     *
     * @param sysCollection 采集
     * @return 结果
     */
    @Override
    public int insertSysCollection(SysCollection sysCollection) {
        return sysCollectionMapper.insertSysCollection(sysCollection);
    }

    /**
     * 修改采集
     *
     * @param sysCollection 采集
     * @return 结果
     */
    @Override
    public int updateSysCollection(SysCollection sysCollection) {
        return sysCollectionMapper.updateSysCollection(sysCollection);
    }

    /**
     * 批量删除采集
     *
     * @param collectionIds 需要删除的采集主键
     * @return 结果
     */
    @Override
    public int deleteSysCollectionByCollectionIds(Long[] collectionIds) {
        return sysCollectionMapper.deleteSysCollectionByCollectionIds(collectionIds);
    }

    /**
     * 删除采集信息
     *
     * @param collectionId 采集主键
     * @return 结果
     */
    @Override
    public int deleteSysCollectionByCollectionId(Long collectionId) {
        return sysCollectionMapper.deleteSysCollectionByCollectionId(collectionId);
    }
}
