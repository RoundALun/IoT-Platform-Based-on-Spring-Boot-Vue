package com.roundlun.iot.mapper;
import java.util.List;

import com.roundlun.iot.domain.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface SysRoleMenuMapper {
//    public List<SysRoleMenu> selectSysRoleMenus(SysRoleMenu sysRoleMenu);
    public int insertSysRoleMenu(List<SysRoleMenu> sysRoleMenu);

    public List<Integer> selectMenuIdListByRoleId(Integer roleId);

//    public int updateSysRoleMenu(SysRoleMenu sysRoleMenu);
//    public int deleteSysRoleMenu(SysRoleMenu sysRoleMenu);
    public int deleteSysRoleMenusByRoleId(Integer[] roleIds);


}
