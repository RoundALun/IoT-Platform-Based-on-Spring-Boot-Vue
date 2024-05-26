package com.roundlun.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.roundlun.iot.domain.AjaxResult;
import com.roundlun.iot.domain.entity.SysRole;
import com.roundlun.iot.page.TableDataInfo;
import com.roundlun.iot.service.ISysRoleService;


@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController{
    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/list")
    public TableDataInfo list(SysRole sysRole){
        if (sysRole == null) {
            sysRole = new SysRole();
        }
        startPage();
        List<SysRole> list = sysRoleService.selectSysRoleList(sysRole);
        return getDataTable(list);
    }
    @GetMapping("/all")
    public AjaxResult getAllRole(SysRole sysRole){

        List<SysRole> list = sysRoleService.selectSysRoleList(sysRole);
        return AjaxResult.success(list);
    }

    @GetMapping("/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Integer roleId){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", sysRoleService.selectSysRoleByRoleId(roleId));
        return ajax;
    }
    @PostMapping
    public AjaxResult add(@RequestBody SysRole sysRole){
        return toAjax(sysRoleService.insertSysRole(sysRole));
    }
    @PutMapping
    public AjaxResult edit(@RequestBody SysRole sysRole){
        return toAjax(sysRoleService.updateSysRole(sysRole));
    }
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Integer[] roleIds){
        return toAjax(sysRoleService.deleteSysRoleByRoleIds(roleIds));
    }

}