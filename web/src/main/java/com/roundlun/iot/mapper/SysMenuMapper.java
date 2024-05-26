package com.roundlun.iot.mapper;

import com.roundlun.iot.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    public List<SysMenu> selectSysMenuList(SysMenu sysMenu);

    public List<SysMenu> selectSysMenuListByUserId(Long userId);

//    public SysMenu selectSysMenuByMenuId(Integer menuId);


//    public int insertSysMenu(SysMenu sysMenu);
//
//    public int updateSysMenu(SysMenu sysMenu);
//
//    public int deleteSysMenuByMenuId(Long MenuId);
//
//    public int deleteSysMenuByMenuIds(Long[] MenuIds);



}
