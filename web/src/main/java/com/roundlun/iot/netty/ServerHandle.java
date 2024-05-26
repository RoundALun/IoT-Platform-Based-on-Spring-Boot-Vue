package com.roundlun.iot.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.roundlun.iot.domain.entity.SysDevice;
import com.roundlun.iot.service.ISysCollectionService;
import com.roundlun.iot.service.ISysDeviceService;
import com.roundlun.iot.service.ISysPropDataService;
import com.roundlun.iot.service.ISysPropertyService;
import com.roundlun.iot.utils.SpringContextUtils;

@Component
public class ServerHandle extends ChannelInboundHandlerAdapter {

    private boolean firstReceive = true;

    private Long deviceSn = null;

    private Long protocolId = null;

    private static final Logger log = LoggerFactory.getLogger(ServerHandle.class);

    public static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private ISysDeviceService deviceService = SpringContextUtils.getBean(ISysDeviceService.class);

    private ISysPropertyService propertyService = SpringContextUtils.getBean(ISysPropertyService.class);

    private ISysCollectionService collectionService = SpringContextUtils.getBean(ISysCollectionService.class);

    private ISysPropDataService propDateService = SpringContextUtils.getBean(ISysPropDataService.class);

    /**
     * 读取到客户端发来的消息
     *
     * @param ctx ChannelHandlerContext
     * @param msg msg
     * @throws Exception e
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String data = new String((byte[]) msg);
        JSONObject jsonObject = JSONObject.parseObject(data);
        if (this.firstReceive) {
            if (jsonObject.containsKey("deviceSn") && jsonObject.containsKey("devicePwd")) {
                Long deviceSn = jsonObject.getLong("deviceSn");
                SysDevice sysDevice = deviceService.selectSysDeviceByDeviceSn(deviceSn);
                if (sysDevice == null) {
                    ctx.close();
                    log.info("Device Auth Fail: " + jsonObject);
                }
                if (!sysDevice.getDevicePwd().equals(jsonObject.getString("devicePwd"))) {
                    ctx.close();
                    log.info("Device Auth Fail: " + jsonObject);
                }
                this.firstReceive = false;
                this.deviceSn = deviceSn;
                this.protocolId = sysDevice.getProtocolId();
                log.info("Device Auth Success: " + jsonObject);
            } else {
                ctx.close();
                log.info("Device Auth Fail: " + jsonObject);
            }
        } else {
            log.info("Receive Data: " + jsonObject);
            if (!propDateService.handleDeviceData(this.deviceSn, this.protocolId, jsonObject)) {
                ctx.close();
                log.info("Receive Data Fail: " + jsonObject);
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("HandlerAdded：" + ctx.channel().id().asShortText());
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("HandlerRemoved：" + ctx.channel().id().asShortText());
        clients.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}
