package com.roundlun.iot.mapper;

import com.roundlun.iot.domain.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    public int insertSysUserRole(List<SysUserRole> sysUserRoles);
    public List<Integer> selectRoleIdByUserId(Long userId);

    public int deleteSysUserRolesByUserId(Long[] userIds);

}
