#include <ArduinoMqttClient.h>
#include <WiFi.h>
#include <ArduinoJson.h>
//LED引脚定义
const int RedLED = 33;  // 16 corresponds to GPIO16
const int GreenLED = 32;  // 17 corresponds to GPIO17
const int BlueLED = 35;  // 2 corresponds to GPIO2
// setting PWM properties
const int freq = 5000;
const int RledChannel = 0;
const int GledChannel = 1;
const int BledChannel = 2;
const int resolution = 10;
bool ledStatus = false;
///////please enter your sensitive data in the Secret tab/arduino_secrets.h
char ssid[] = "wifissid";    // WiFi账号
char pass[] = "password";    // WiFi密码

WiFiClient wifiClient;
MqttClient mqttClient(wifiClient);

const char broker[] = "emqx.123roundlun.top";//阿里云broker.host
int        port     = 1883;
const char pub_topic[]  = "testtopic/2";//设备端发布消息主题
const char sub_topic[]  = "testtopic/1";//设备端订阅消息主题
int d = 333; //定义deviceSn
const long interval = 5000; //间隔5秒上传数据
unsigned long previousMillis = 0;

void setup() {
  //Initialize serial and wait for port to open:
  Serial.begin(115200);
  // configure LED PWM functionalitites
  ledcSetup(RledChannel, freq, resolution);
  ledcSetup(GledChannel, freq, resolution);
  ledcSetup(BledChannel, freq, resolution);
  // attach the channel to the GPIO to be controlled
  ledcAttachPin(RedLED, RledChannel);
  ledcAttachPin(GreenLED, GledChannel);
  ledcAttachPin(BlueLED, BledChannel);
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
  mqttClient.setId("led");//mqttClientID

  // You can provide a username and password for authentication
  mqttClient.setUsernamePassword("esp32", "123123");//mqttUserName & Password

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
    Pub_Status();
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
  
  while (mqttClient.available()) {
    char inChar = (char)mqttClient.read();
    Serial.print(inChar);
    inputString += inChar;
    
    
    
  }
  //控制led灯
    led_control(inputString);
  Serial.println();
}

void led_control(String inputString) {
  JsonDocument doc;
  DeserializationError error = deserializeJson(doc, inputString);
  if (error) {
    Serial.print("deserializeJson() failed: ");
    Serial.println(error.c_str());
    return;
  }
  int deviceSn = doc["deviceSn"];
  const char* propertyKey = doc["propertyKey"]; 
  bool propertyValue = doc["propertyValue"];
   Serial.println(propertyKey);
   if(propertyKey = "switcher"){
      if(propertyValue == true){
    inputString="led_on";
  }else if(propertyValue == false){
    inputString="led_off";
  }
   }

  if(inputString.startsWith("led_on")) {
  ledcWrite(RledChannel, 1023);  
  ledcWrite(GledChannel, 1023);  
  ledcWrite(BledChannel, 1023); 
    inputString="";
    ledStatus = true;
    Serial.print("led_on");
  } else if ( inputString.startsWith("led_off") ) {
    ledcWrite(RledChannel, 0);  
    ledcWrite(GledChannel, 0);  
    ledcWrite(BledChannel, 0); 
    inputString="";
    ledStatus = false;
    Serial.print("led_off");
  }
}

//发布引脚状态信息（灯是否亮）
void Pub_Status() {
  //{"light":1,"led":0}
  if(ledStatus){
    mqttClient.print("{\"deviceSn\":");
    mqttClient.print(d);
    mqttClient.print(',');
    mqttClient.print("\"switcher\":");
    mqttClient.print("true");
    mqttClient.print('}');
    Serial.print("{\"deviceSn\":");
    Serial.print(d);
    Serial.print(',');
    Serial.print("\"switcher\":");
    Serial.print("true");
    Serial.print('}');
  }else{
    mqttClient.print("{\"deviceSn\":");
    mqttClient.print(d);
    mqttClient.print(',');
    mqttClient.print("\"switcher\":");
    mqttClient.print("false");
    mqttClient.print('}');
    Serial.print("{\"deviceSn\":");
    Serial.print(d);
    Serial.print(',');
    Serial.print("\"switcher\":");
    Serial.print("false");
    Serial.print('}');
  }
}
