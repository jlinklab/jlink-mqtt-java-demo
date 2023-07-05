# jlink-mqtt-java-demo
> org.eclipse.paho.client.mqttv3                  version 1.2.0
> hutool-all                                      version 5.7.7
> bcprov-jdk16                                    version 1.46

# SDK Methods
1. Obtain the TOKEN to obtain the token value for authentication
2. Connect to MQTT server: the developer's uuid should be used as the ClientID, username can be specified by itself, and the token value obtained in the first step is used as the password
3. Specify a subscription Topic
4. Specify a publishing Topic and publish it with a payload
5. Subscribe to Topic to obtain payload
