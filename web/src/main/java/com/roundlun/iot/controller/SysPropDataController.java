package com.roundlun.iot.controller;


import com.roundlun.iot.mqtt.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.roundlun.iot.domain.AjaxResult;
import com.roundlun.iot.domain.entity.SysCollection;
import com.roundlun.iot.domain.entity.SysDevice;
import com.roundlun.iot.domain.model.QueryData;
import com.roundlun.iot.redis.RedisCache;
import com.roundlun.iot.service.ISysCollectionService;
import com.roundlun.iot.service.ISysDeviceService;
import com.roundlun.iot.service.ISysPropDataService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/propData")
public class SysPropDataController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysPropDataService sysPropDataService;

    @Autowired
    private ISysDeviceService sysDeviceService;

    @Autowired
    private ISysCollectionService sysCollectionService;
    @Autowired
    private MqttGateway mqttGateway;

    @GetMapping("/list/{deviceSn}")
    public AjaxResult list(@PathVariable("deviceSn") Long deviceSn) {

        return AjaxResult.success(sysPropDataService.list(deviceSn));
    }

    @PostMapping("/history")
    public AjaxResult list(@RequestBody QueryData queryData) {
        List<SysCollection> collections = new ArrayList<>();
        SysDevice sysDevice = sysDeviceService.selectSysDeviceByDeviceSn(queryData.getDeviceSn());
        if (sysDevice == null) {
            return AjaxResult.success(collections);
        }
        collections = sysCollectionService.selectHistoryDataList(queryData);
        return AjaxResult.success(collections);
    }
    @PutMapping("/put")
    public AjaxResult put(@RequestBody String input){
        try {
            mqttGateway.sendToMqtt(input);
            return AjaxResult.success("Message sent to MQTT topic");
        }catch (Exception e){
            return AjaxResult.error("Failed to send message");
        }

    }
}
