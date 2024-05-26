package com.roundlun.iot.service;

import com.roundlun.iot.domain.entity.RouterVo;
import com.roundlun.iot.domain.entity.SysMenu;

import java.util.List;

public interface ISysMenuService {
    public List<SysMenu> selectSysMenuList(SysMenu sysMenu);

    public List<SysMenu> selectSysMenuListByUserId(Long userId);

    public List<RouterVo> buildMenus(List<SysMenu> menus);

//    public SysMenu selectSysMenuListByMenuId(Integer menuId);
//
//    public SysMenu selectSysMenuListByName(String name);

//    public int insertSysMenu(SysMenu sysMenu);
//
//    public int updateSysMenu(SysMenu sysMenu);
//
//    public  int deleteSysMenu(SysMenu sysMenu);



}


