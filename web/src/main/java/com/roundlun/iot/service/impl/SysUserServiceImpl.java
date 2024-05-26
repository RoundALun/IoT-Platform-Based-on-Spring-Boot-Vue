package com.roundlun.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.roundlun.iot.domain.entity.SysUserRole;
import com.roundlun.iot.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysUser;
import com.roundlun.iot.mapper.SysUserMapper;
import com.roundlun.iot.service.ISysUserService;
import com.roundlun.iot.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户Service业务层处理
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询用户
     *
     * @param userId 用户主键
     * @return 用户
     */
    @Override
    @Transactional
    public SysUser selectSysUserByUserId(Long userId) {
        SysUser sysUser = sysUserMapper.selectSysUserByUserId(userId);
        sysUser.setRoleIdList(sysUserRoleMapper.selectRoleIdByUserId(userId));
        return sysUser;
    }

    /**
     * 查询用户
     *
     * @param userName 用户名称
     * @return 用户
     */
    public SysUser selectSysUserByUserName(String userName) {
        return sysUserMapper.selectSysUserByUserName(userName);
    }

    /**
     * 查询用户列表
     *
     * @param sysUser 用户
     * @return 用户
     */
    @Override
    public List<SysUser> selectSysUserList(SysUser sysUser) {
        return sysUserMapper.selectSysUserList(sysUser);
    }

    /**
     * 新增用户
     *
     * @param sysUser 用户
     * @return 结果
     */
    @Override
    public int insertSysUser(SysUser sysUser) {
        sysUser.setPassword(MD5Utils.getMD5Str(sysUser.getPassword()));
        int r = sysUserMapper.insertSysUser(sysUser);
        if(r > 0 ){
            addSysUserRole(sysUser);
            return r;
        }else{
            return r;
        }
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户
     * @return 结果
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        sysUser.setPassword(MD5Utils.getMD5Str(sysUser.getPassword()));
        Long[] userIds = new Long[]{sysUser.getUserId()};
        deleteSysUserRoles(userIds);
        addSysUserRole(sysUser);
        return sysUserMapper.updateSysUser(sysUser);
    }

    /**
     * 批量删除用户
     *
     * @param userIds 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserIds(Long[] userIds) {
        deleteSysUserRoles(userIds);
        return sysUserMapper.deleteSysUserByUserIds(userIds);
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserId(Long userId) {
        Long[] userIds = new Long[]{userId};
        deleteSysUserRoles(userIds);
        return sysUserMapper.deleteSysUserByUserId(userId);
    }

    @Transactional
    public int addSysUserRole(SysUser sysUser){
        int row = 1;
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        if(sysUser.getUserId() != null){
            for(Integer roleId : sysUser.getRoleIdList()){
                Long userId = sysUserMapper.selectSysUserByUserName(sysUser.getUserName()).getUserId();
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                list.add(sysUserRole);
            }

            if (list.size() > 0)
            {
                row = sysUserRoleMapper.insertSysUserRole(list);
            }
        }
        return row;
    }

    public int deleteSysUserRoles(Long[] userIds){
        int row=1;
        row = sysUserRoleMapper.deleteSysUserRolesByUserId(userIds);
        return row;
    }
}
