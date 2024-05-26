package com.roundlun.iot.mapper;

import java.util.List;

import com.roundlun.iot.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {
    public SysRole selectSysRoleByRoleId(Integer roleId);

    public SysRole selectSysRoleByRoleName (String roleName);

    public List<SysRole> selectSysRoleList(SysRole sysRole);

    public int insertSysRole(SysRole sysRole);

    public int updateSysRole(SysRole sysRole);

    public int deleteSysRoleByRoleId(Long roleId);

    public int deleteSysRoleByRoleIds(Integer[] roleIds);
}