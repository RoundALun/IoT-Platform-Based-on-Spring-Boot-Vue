#include <ArduinoMqttClient.h>
#include <WiFi.h>
#include "DHT.h"

#define DHTTYPE DHT11
#define DHTPIN 32
DHT dht(DHTPIN, DHTTYPE); //温湿度传感器引脚定义

char ssid[] = "wifissid";    // WiFi账号
char pass[] = "password";    // WiFi密码

WiFiClient wifiClient;
MqttClient mqttClient(wifiClient);

const char broker[] = "emqx.123roundlun.top";//mqtt物联网平台
int port = 1883;
const char pub_topic[] = "testtopic/2";//设备端发布消息主题
const char sub_topic[] = "testtopic/1";//设备端订阅消息主题
int d = 123;
const long interval = 5000; //间隔5秒上传温湿度数据
unsigned long previousMillis = 0;

void setup() {
  //Initialize serial and wait for port to open:
  Serial.begin(115200);
  dht.begin();
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  // attempt to connect to WiFi network:
  Serial.print("Attempting to connect to WPA SSID: ");
  Serial.println(ssid);
  while (WiFi.begin(ssid, pass) != WL_CONNECTED) {
    // failed, retry
    Serial.print(".");
    delay(5000);
  }
  Serial.println("You're connected to the network");
  Serial.println("连接成功,IP为：");
  Serial.println(WiFi.localIP()); //连接成功后打印ip
  WiFi.setAutoReconnect(true);// 设置当断开连接的时候自动重连
  WiFi.persistent(true);// 该方法设置将WiFi参数保存于Flash

  Serial.println();

  // You can provide a unique client ID, if not set the library uses Arduino-millis()
  // Each client must have a unique client ID
  mqttClient.setId("esp32");//mqttClientID

  // You can provide a username and password for authentication
  mqttClient.setUsernamePassword("esp32dht", "123123");//mqttUserName & Password

  Serial.print("Attempting to connect to the MQTT broker: ");
  Serial.println(broker);

  if (!mqttClient.connect(broker, port)) {
    Serial.print("MQTT connection failed! Error code = ");
    Serial.println(mqttClient.connectError());

    while (1);
  }

  Serial.println("You're connected to the MQTT broker!");
  Serial.println();

  // set the message receive callback
  mqttClient.onMessage(onMqttMessage);

  Serial.print("Subscribing to topic: ");
  Serial.println(sub_topic);
  Serial.println();

  // subscribe to a topic
  mqttClient.subscribe(sub_topic);

  // topics can be unsubscribed using:
  // mqttClient.unsubscribe(topic);

  Serial.print("Waiting for messages on topic: ");
  Serial.println(sub_topic);
  Serial.println();
}

void loop() {
  mqttClient.poll();
  // to avoid having delays in loop, we'll use the strategy from BlinkWithoutDelay
  // see: File -> Examples -> 02.Digital -> BlinkWithoutDelay for more info
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    // save the last time a message was sent
    previousMillis = currentMillis;

    // send message, the Print interface can be used to set the message contents
    mqttClient.beginMessage(pub_topic);

    //发布温湿度信息
    Pub_DHT();
    mqttClient.endMessage();

    Serial.println();

  }
}

//回调函数
void onMqttMessage(int messageSize) {
  String inputString="";
  // we received a message, print out the topic and contents
  Serial.println("Received a message with topic '");
  Serial.print(mqttClient.messageTopic());
  Serial.print("', length ");
  Serial.print(messageSize);
  Serial.println(" bytes:");
  Serial.println();
}

void Pub_DHT() {
  Serial.print("Sending message to topic: ");
  Serial.println(pub_topic);
  float t = dht.readTemperature();
  float h = dht.readHumidity();
  mqttClient.print("{\"deviceSn\":");
  mqttClient.print(d);
  mqttClient.print(',');
  mqttClient.print("\"tem\":");
  mqttClient.print(t);
  mqttClient.print(',');
  mqttClient.print("\"hum\":");
  mqttClient.print(h);
  mqttClient.print('}');

  Serial.print("{\"temperature\":");
  Serial.print(t);
  Serial.print(',');
  Serial.print("\"humidity\":");
  Serial.print(h);
  Serial.print('}');
  Serial.println();
}
