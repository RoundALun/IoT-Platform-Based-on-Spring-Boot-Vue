package com.roundlun.iot.service;

import java.util.List;

import com.roundlun.iot.domain.entity.SysUser;

/**
 * 用户Service接口
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
public interface ISysUserService {
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
     * 批量删除用户
     *
     * @param userIds 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);

    /**
     * 删除用户信息
     *
     * @param userId 用户主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);
}
