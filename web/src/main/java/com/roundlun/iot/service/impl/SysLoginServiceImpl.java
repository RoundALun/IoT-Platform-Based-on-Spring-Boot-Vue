package com.roundlun.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roundlun.iot.domain.model.LoginUser;
import com.roundlun.iot.mapper.SysUserMapper;
import com.roundlun.iot.service.ISysLoginService;

@Service
public class SysLoginServiceImpl implements ISysLoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String login(LoginUser loginUser) {

        return null;
    }
}
