package mqtt;

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