package com.roundlun.iot.controller;

import java.util.List;

import com.roundlun.iot.domain.entity.SysUser;
import com.roundlun.iot.service.ISysUserService;
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
import com.roundlun.iot.domain.entity.SysProtocol;
import com.roundlun.iot.page.TableDataInfo;
import com.roundlun.iot.service.ISysProtocolService;

/**
 * 协议Controller
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/protocol")
public class SysProtocolController extends BaseController {
    @Autowired
    private ISysProtocolService sysProtocolService;
    /**
     * 查询协议列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysProtocol sysProtocol) {
        if (!getLoginUser().getParentId().equals(0L)) {
            sysProtocol.setUserId(getUserId());
        }
        startPage();
        List<SysProtocol> list = sysProtocolService.selectSysProtocolList(sysProtocol);
        return getDataTable(list);
    }

    /**
     * 获取协议详细信息
     */
    @GetMapping(value = "/{protocolId}")
    public AjaxResult getInfo(@PathVariable("protocolId") Long protocolId) {
        return AjaxResult.success(sysProtocolService.selectSysProtocolByProtocolId(protocolId));
    }

    /**
     * 新增协议
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysProtocol sysProtocol) {
        sysProtocol.setUserId(getUserId());
        return toAjax(sysProtocolService.insertSysProtocol(sysProtocol));
    }

    /**
     * 修改协议
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysProtocol sysProtocol) {
        return toAjax(sysProtocolService.updateSysProtocol(sysProtocol));
    }

    /**
     * 删除协议
     */
    @DeleteMapping("/{protocolIds}")
    public AjaxResult remove(@PathVariable Long[] protocolIds) {
        return toAjax(sysProtocolService.deleteSysProtocolByProtocolIds(protocolIds));
    }
}
