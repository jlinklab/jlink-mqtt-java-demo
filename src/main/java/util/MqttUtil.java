package util;

import mqtt.OnMessageCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.TimeUnit;

/**
 * @author yxm
 * @description: mqtt
 * @date 2021/12/28 15:30
 */
public class MqttUtil {
    public static void mqtt(String pubTopic, String subTopic, String content, String password) throws InterruptedException {
        //0: at most once, 1: at least once, 2: only once
        int qos = 2;
        String broker = "ssl://mqtt.bcloud365.net:8000";
        String clientId = "e0534f3240274897821a126be19b6d46";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            // MQTT Connection option
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("username");
            connOpts.setPassword(password.toCharArray());
            // remember state across restarts and reconnects
            connOpts.setCleanSession(true);
            // Set callback
            client.setCallback(new OnMessageCallback());
            // Establish connection
            client.connect(connOpts);
            // Subscribe
            client.subscribe(subTopic, qos);
            // Parameters required for message publishing
            MqttMessage message = new MqttMessage();
            message.setPayload(content.getBytes());
            message.setQos(qos);
            client.publish(pubTopic, message);
            TimeUnit.SECONDS.sleep(3);
            client.disconnect();
            client.close();
        } catch (MqttException me) {
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
