# jlink-mqtt-java-demo
> org.eclipse.paho.client.mqttv3                  version 1.2.1
> hutool-all                                      version 5.8.19
> bcprov-jdk16                                    version 1.46

# SDK Methods
1. Obtain the TOKEN to obtain the token value for authentication
2. Connect to MQTT server: the developer's uuid should be used as the ClientID, username can be specified by itself, and the token value obtained in the first step is used as the password
3. Specify a subscription Topic
4. Specify a publishing Topic and publish it with a payload
5. Subscribe to Topic to obtain payload

#Example

## implement MqttCallback.class
> By implementing the MqttCallback interface, you can define methods that will be called by the MQTT client library when certain events occur.


```java
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class OnMessageCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        // After the connection is lost, it is usually reconnected in this room
        System.out.println("The connection is down and needs to be reconnected");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // The messages that we get after subscribe are executed into this
        System.out.println("Receive message subject:" + topic);
        System.out.println("Receive message Qos:" + message.getQos());
        System.out.println("Receive message content:" + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Called when the publishing message is complete
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
```
## Initialization JLinkClient

> uuid,appKey,appSecret,movecard Obtained from (open.jftech.com).

```
JLinkClient jClient = new JLinkClient(uuid,appKey,appSecret,movecard);
```

## Initialization JLinkDevice

> Sn is the device serial number
> devUsername indicates the device login username
> devPassword indicates the device login password

```
JLinkDevice jDevice = new JLinkDevice(jClient, sn, devUsername, devPassword);
```

## Get Device Token
>Firstly, it is necessary to obtain the device token, as all subsequent operations depend on obtaining the device token.
```
   String token = Mqtt.getToken(jlinkClient, jLinkDevice);
```
## Get Device Status
```
Mqtt.queryStatus();
```

## Login Device
```
Mqtt.login();
```

## Get Ability
```
Mqtt.getAbility(jlinkClient,jLinkDevice);
```

## KeepAlive
```
Mqtt.keepAlive(jlinkClient,jLinkDevice);
```

## Get Channel Title
```
Mqtt.getConfig("ChannelTitle");
```

## Get Alarm
```
Mqtt.getConfig("Alarm.LocalAlarm");
```
## NetWork Configuration
```
Mqtt.getConfig("NetWork.NetCommon");
``` 

## Location Configuration
```
Mqtt.getConfig("General.Location");
```

## Capture
```
Mqtt.getCapture();
```

## Low-power Device Wakeup
```
Mqtt.wakeup();
```

## Get LiveStream
```
Mqtt.livestream();
```

## Get Device Info
```
Mqtt.getinfo();
```

## Set Configuration
```
Mqtt.setconfig();
```

## Operate Device
```
Mqtt.opdev();
```

## Device UserManage
```
Mqtt.usermanage();
```







