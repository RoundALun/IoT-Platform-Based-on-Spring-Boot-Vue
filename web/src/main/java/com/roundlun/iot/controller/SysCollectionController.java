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
import com.roundlun.iot.domain.entity.SysCollection;
import com.roundlun.iot.page.TableDataInfo;
import com.roundlun.iot.service.ISysCollectionService;

/**
 * 采集Controller
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/collection")
public class SysCollectionController extends BaseController {
    @Autowired
    private ISysCollectionService sysCollectionService;

    /**
     * 查询采集列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysCollection sysCollection) {
        startPage();
        List<SysCollection> list = sysCollectionService.selectSysCollectionList(sysCollection);
        return getDataTable(list);
    }

    /**
     * 获取采集详细信息
     */
    @GetMapping(value = "/{collectionId}")
    public AjaxResult getInfo(@PathVariable("collectionId") Long collectionId) {
        return AjaxResult.success(sysCollectionService.selectSysCollectionByCollectionId(collectionId));
    }

    /**
     * 新增采集
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysCollection sysCollection) {
        return toAjax(sysCollectionService.insertSysCollection(sysCollection));
    }

    /**
     * 修改采集
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysCollection sysCollection) {
        return toAjax(sysCollectionService.updateSysCollection(sysCollection));
    }

    /**
     * 删除采集
     */
    @DeleteMapping("/{collectionIds}")
    public AjaxResult remove(@PathVariable Long[] collectionIds) {
        return toAjax(sysCollectionService.deleteSysCollectionByCollectionIds(collectionIds));
    }
}
