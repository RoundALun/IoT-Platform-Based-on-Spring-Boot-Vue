package com.roundlun.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.roundlun.iot.domain.entity.SysProtocol;

/**
 * 协议Mapper接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Mapper
public interface SysProtocolMapper {
    /**
     * 查询协议
     *
     * @param protocolId 协议主键
     * @return 协议
     */
    public SysProtocol selectSysProtocolByProtocolId(Long protocolId);

    /**
     * 查询协议列表
     *
     * @param sysProtocol 协议
     * @return 协议集合
     */
    public List<SysProtocol> selectSysProtocolList(SysProtocol sysProtocol);

    /**
     * 新增协议
     *
     * @param sysProtocol 协议
     * @return 结果
     */
    public int insertSysProtocol(SysProtocol sysProtocol);

    /**
     * 修改协议
     *
     * @param sysProtocol 协议
     * @return 结果
     */
    public int updateSysProtocol(SysProtocol sysProtocol);

    /**
     * 删除协议
     *
     * @param protocolId 协议主键
     * @return 结果
     */
    public int deleteSysProtocolByProtocolId(Long protocolId);

    /**
     * 批量删除协议
     *
     * @param protocolIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysProtocolByProtocolIds(Long[] protocolIds);
}
