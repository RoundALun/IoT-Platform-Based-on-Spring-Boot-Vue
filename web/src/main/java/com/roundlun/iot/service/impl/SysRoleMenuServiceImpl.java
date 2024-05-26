package com.roundlun.iot.service.impl;

import com.roundlun.iot.domain.entity.SysRoleMenu;
import com.roundlun.iot.mapper.SysRoleMenuMapper;
import com.roundlun.iot.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<Integer> selectMenuIdListByRoleId(Integer roldId) {
        return  sysRoleMenuMapper.selectMenuIdListByRoleId(roldId);
    }
}
