package com.roundlun.iot.mqtt;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    public void sendToMqtt(String data);
}
