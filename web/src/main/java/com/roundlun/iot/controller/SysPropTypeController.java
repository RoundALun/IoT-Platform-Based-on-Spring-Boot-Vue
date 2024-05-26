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
import com.roundlun.iot.domain.entity.SysPropType;
import com.roundlun.iot.page.TableDataInfo;
import com.roundlun.iot.service.ISysPropTypeService;

/**
 * 属性类型Controller
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/prop_type")
public class SysPropTypeController extends BaseController {
    @Autowired
    private ISysPropTypeService sysPropTypeService;

    /**
     * 查询属性类型列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysPropType sysPropType) {
        startPage();
        List<SysPropType> list = sysPropTypeService.selectSysPropTypeList(sysPropType);
        return getDataTable(list);
    }

    /**
     * 获取属性类型详细信息
     */
    @GetMapping(value = "/{propTypeId}")
    public AjaxResult getInfo(@PathVariable("propTypeId") Long propTypeId) {
        return AjaxResult.success(sysPropTypeService.selectSysPropTypeByPropTypeId(propTypeId));
    }

    /**
     * 新增属性类型
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysPropType sysPropType) {
        return toAjax(sysPropTypeService.insertSysPropType(sysPropType));
    }

    /**
     * 修改属性类型
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysPropType sysPropType) {
        return toAjax(sysPropTypeService.updateSysPropType(sysPropType));
    }

    /**
     * 删除属性类型
     */
    @DeleteMapping("/{propTypeIds}")
    public AjaxResult remove(@PathVariable Long[] propTypeIds) {
        return toAjax(sysPropTypeService.deleteSysPropTypeByPropTypeIds(propTypeIds));
    }
}
