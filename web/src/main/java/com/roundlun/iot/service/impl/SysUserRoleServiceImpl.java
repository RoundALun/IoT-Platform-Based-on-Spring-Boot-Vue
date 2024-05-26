package com.roundlun.iot.service.impl;

import com.roundlun.iot.mapper.SysUserRoleMapper;
import com.roundlun.iot.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysUserRoleServiceImpl implements ISysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public List<Integer> selectRoleIdByUserId(Long userId) {
        return sysUserRoleMapper.selectRoleIdByUserId(userId);
    }
}
