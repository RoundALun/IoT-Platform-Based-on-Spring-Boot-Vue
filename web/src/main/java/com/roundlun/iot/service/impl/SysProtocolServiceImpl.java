package com.roundlun.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysProtocol;
import com.roundlun.iot.mapper.SysProtocolMapper;
import com.roundlun.iot.service.ISysProtocolService;

/**
 * 协议Service业务层处理
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Service
public class SysProtocolServiceImpl implements ISysProtocolService {
    @Autowired
    private SysProtocolMapper sysProtocolMapper;

    /**
     * 查询协议
     *
     * @param protocolId 协议主键
     * @return 协议
     */
    @Override
    public SysProtocol selectSysProtocolByProtocolId(Long protocolId) {
        return sysProtocolMapper.selectSysProtocolByProtocolId(protocolId);
    }

    /**
     * 查询协议列表
     *
     * @param sysProtocol 协议
     * @return 协议
     */
    @Override
    public List<SysProtocol> selectSysProtocolList(SysProtocol sysProtocol) {
        return sysProtocolMapper.selectSysProtocolList(sysProtocol);
    }

    /**
     * 新增协议
     *
     * @param sysProtocol 协议
     * @return 结果
     */
    @Override
    public int insertSysProtocol(SysProtocol sysProtocol) {
        return sysProtocolMapper.insertSysProtocol(sysProtocol);
    }

    /**
     * 修改协议
     *
     * @param sysProtocol 协议
     * @return 结果
     */
    @Override
    public int updateSysProtocol(SysProtocol sysProtocol) {
        return sysProtocolMapper.updateSysProtocol(sysProtocol);
    }

    /**
     * 批量删除协议
     *
     * @param protocolIds 需要删除的协议主键
     * @return 结果
     */
    @Override
    public int deleteSysProtocolByProtocolIds(Long[] protocolIds) {
        return sysProtocolMapper.deleteSysProtocolByProtocolIds(protocolIds);
    }

    /**
     * 删除协议信息
     *
     * @param protocolId 协议主键
     * @return 结果
     */
    @Override
    public int deleteSysProtocolByProtocolId(Long protocolId) {
        return sysProtocolMapper.deleteSysProtocolByProtocolId(protocolId);
    }
}
