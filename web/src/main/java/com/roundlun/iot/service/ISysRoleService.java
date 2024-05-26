package com.roundlun.iot.service;
import java.util.List;

import com.roundlun.iot.domain.entity.SysRole;
import org.springframework.transaction.annotation.Transactional;

public interface ISysRoleService {
    public List<SysRole> selectSysRoleList(SysRole sysRole);

    @Transactional
    SysRole selectSysRoleByRoleId(Integer roleId);

    public SysRole selectSysRoleByRoleName(String roleName);
    public int insertSysRole(SysRole sysRole);
    public int updateSysRole(SysRole sysRole);
    public int deleteSysRoleByRoleId(Long roleId);
    public int deleteSysRoleByRoleIds(Integer[] roleIds);

    @Transactional
    public int addSysRoleMenu(SysRole sysRole);

    @Transactional
    public int deleteRoleMenu(Integer[] roleId);

}
