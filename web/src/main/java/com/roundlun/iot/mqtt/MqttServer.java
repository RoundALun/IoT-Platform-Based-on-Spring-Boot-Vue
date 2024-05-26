package com.roundlun.iot.mqtt;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import com.roundlun.iot.domain.entity.SysDevice;
import com.roundlun.iot.service.ISysCollectionService;
import com.roundlun.iot.service.ISysDeviceService;
import com.roundlun.iot.service.ISysPropDataService;
import com.roundlun.iot.service.ISysPropertyService;
import com.roundlun.iot.utils.SpringContextUtils;

@Configuration
@IntegrationComponentScan
public class MqttServer {

    //此处填DNS解析后对应的ip地址
    private String hostUrl = "tcp://127.0.0.1:1883";
    //剩下部分填写其余对应的信息即可
    private String username = "test";
    private String password = "test";
    private String clientId = "web";
    private String recvTopic = "testtopic/2";

    private String pubTopic = "testtopic/1";

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(60);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory(), recvTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * When Mqtt Input Channel
     */


    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {


        return new MessageHandler() {

            private ISysDeviceService deviceService = SpringContextUtils.getBean(ISysDeviceService.class);

            private ISysPropertyService propertyService = SpringContextUtils.getBean(ISysPropertyService.class);

            private ISysCollectionService collectionService = SpringContextUtils.getBean(ISysCollectionService.class);

            private ISysPropDataService propDateService = SpringContextUtils.getBean(ISysPropDataService.class);

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                //此处添加处理方法
                //传入的消息是用message.getPayload().toString()得到
                //传入的topic名字用message.getHeaders().get("mqtt_receivedTopic").toString()得到

//                System.out.println(msg);
                try {
                    String msg = message.getPayload().toString();
                    String data = new String(msg);
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    Long deviceSn = jsonObject.getLong("deviceSn");
                    SysDevice sysDevice = deviceService.selectSysDeviceByDeviceSn(deviceSn);
                    Long protocolId = sysDevice.getProtocolId();

                    System.out.println("Received Data: " + jsonObject);
                    if (!propDateService.handleDeviceData(deviceSn, protocolId, jsonObject)) {
                        System.out.println("Prop Data Fail: " + jsonObject);
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("webOut", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(pubTopic);
        messageHandler.setConverter(new DefaultPahoMessageConverter());
        return messageHandler;
    }

    @Bean
    public IntegrationFlow mqttOutboundFlow() {
        return IntegrationFlows.from(mqttOutboundChannel())
                .handle(mqttOutbound())
                .get();
    }
}
