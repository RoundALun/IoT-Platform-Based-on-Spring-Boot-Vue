package com.roundlun.iot.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.roundlun.iot.domain.entity.SysRoleMenu;
import com.roundlun.iot.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.entity.SysRole;
import com.roundlun.iot.mapper.SysRoleMapper;
import com.roundlun.iot.service.ISysRoleService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysRole> selectSysRoleList(SysRole sysRole) {
        return sysRoleMapper.selectSysRoleList(sysRole);
    }

    @Override
    @Transactional
    public SysRole selectSysRoleByRoleId(Integer roleId) {
        SysRole role = sysRoleMapper.selectSysRoleByRoleId(roleId);
        List<Integer> menuIdList = sysRoleMenuMapper.selectMenuIdListByRoleId(roleId);
        role.setMenuIdList(menuIdList);
        return role;
    }


    @Override
    public SysRole selectSysRoleByRoleName(String roleName) {
        return sysRoleMapper.selectSysRoleByRoleName(roleName);
    }

    @Override
    public int insertSysRole(SysRole sysRole) {
        sysRoleMapper.insertSysRole(sysRole);
        return addSysRoleMenu(sysRole);
    }

    @Override
    public int updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
        Integer[] roleIds = new Integer[]{sysRole.getRoleId()};
        deleteRoleMenu(roleIds);
        return addSysRoleMenu(sysRole);
    }

    @Override
    public int deleteSysRoleByRoleId(Long roleId) {
        return sysRoleMapper.deleteSysRoleByRoleId(roleId);
    }

    @Override
    public int deleteSysRoleByRoleIds(Integer[] roleIds) {
        sysRoleMenuMapper.deleteSysRoleMenusByRoleId(roleIds);
        return sysRoleMapper.deleteSysRoleByRoleIds(roleIds);
    }

    @Override
    @Transactional
    public int addSysRoleMenu(SysRole sysRole) {
        int rows = 1;
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        //写入role-menu
        if(sysRole.getMenuIdList() != null){
            for(Integer menuId : sysRole.getMenuIdList()){
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRoleMapper.selectSysRoleByRoleName(sysRole.getRoleName()).getRoleId());
                sysRoleMenu.setMenuId(menuId);
                list.add(sysRoleMenu);
            }
            if (list.size() > 0)
            {
                rows = sysRoleMenuMapper.insertSysRoleMenu(list);
            }
        }
        return rows;
    }

    @Override
    public int deleteRoleMenu(Integer[] roleId) {
        int rows = 1;
        rows = sysRoleMenuMapper.deleteSysRoleMenusByRoleId(roleId);
        return rows;
    }


}