package com.roundlun.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.roundlun.iot.domain.AjaxResult;
import com.roundlun.iot.domain.entity.SysUser;
import com.roundlun.iot.service.ISysUserService;
import com.roundlun.iot.utils.BaseUserInfo;
import com.roundlun.iot.utils.MD5Utils;
import com.roundlun.iot.utils.TokenUtils;

@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody SysUser sysUser) {
        String password = sysUser.getPassword();
        //在数据库中查找用户
        sysUser = sysUserService.selectSysUserByUserName(sysUser.getUserName());
        if (sysUser == null) {
            //数据库中没有该用户名返回
            return AjaxResult.error("用户名不存在");
        }
        if (!sysUser.getPassword().equals(MD5Utils.getMD5Str(password))) {
            //密码不正确返回
            return AjaxResult.error("密码错误");
        }
        sysUser.setPassword("");
        //创建token并返回
        return AjaxResult.success(tokenUtils.createToken(sysUser));
    }

    @PostMapping("/logout")
    public AjaxResult logout() {
        tokenUtils.delLoginUser(getLoginUser().getUuid());
        return AjaxResult.success(BaseUserInfo.get());
    }

    @PostMapping("/getInfo")
    public AjaxResult getInfo() {
        return AjaxResult.success(getLoginUser());
    }

}
