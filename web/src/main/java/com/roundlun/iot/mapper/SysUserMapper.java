package com.roundlun.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.roundlun.iot.domain.entity.SysUser;

/**
 * 用户Mapper接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Mapper
public interface SysUserMapper {
    /**
     * 查询用户
     *
     * @param userId 用户主键
     * @return 用户
     */
    public SysUser selectSysUserByUserId(Long userId);

    /**
     * 查询用户
     *
     * @param userName 用户名称
     * @return 用户
     */
    public SysUser selectSysUserByUserName(String userName);

    /**
     * 查询用户列表
     *
     * @param sysUser 用户
     * @return 用户集合
     */
    public List<SysUser> selectSysUserList(SysUser sysUser);

    /**
     * 新增用户
     *
     * @param sysUser 用户
     * @return 结果
     */
    public int insertSysUser(SysUser sysUser);

    /**
     * 修改用户
     *
     * @param sysUser 用户
     * @return 结果
     */
    public int updateSysUser(SysUser sysUser);

    /**
     * 删除用户
     *
     * @param userId 用户主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 批量删除用户
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);
}
