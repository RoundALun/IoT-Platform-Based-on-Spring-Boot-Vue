package com.roundlun.iot.controller;

import com.roundlun.iot.domain.AjaxResult;
import com.roundlun.iot.domain.entity.RouterVo;
import com.roundlun.iot.domain.entity.SysMenu;
import com.roundlun.iot.page.TableDataInfo;
import com.roundlun.iot.service.ISysMenuService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController{
    @Autowired
    private ISysMenuService sysMenuService;
    @GetMapping("/all")
    public AjaxResult list(SysMenu sysMenu){
        List<SysMenu> menuList = sysMenuService.selectSysMenuList(sysMenu);
        return AjaxResult.success(menuList);
    }

    @GetMapping("/getRouter")
    public AjaxResult getRouter(SysMenu sysMenu){
        List<SysMenu> userMenuList = sysMenuService.selectSysMenuListByUserId(getUserId());
        List<RouterVo> router =  sysMenuService.buildMenus(userMenuList);
        return AjaxResult.success(router);
    }
}
