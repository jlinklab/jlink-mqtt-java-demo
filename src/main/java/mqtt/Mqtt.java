package mqtt;

import util.CommonUtil;
import util.MqttUtil;

/**
 * @author yxm
 * @description: Test Run
 * @date 2021/12/28 16:31
 */
public class Mqtt {

    private static String password;

    public static void main(String[] args) throws InterruptedException {
        // Set Password
        setPassword();
        // Get Device Status
//        queryStatus();
        //Login Device
        login();
        // Get Ability
//        getAbility();
        // KeepAlive
//        keepAlive();
        // Get Channel Title
//        getConfig("ChannelTitle");
        // Get Alarm
//        getConfig("Alarm.LocalAlarm");
        // NetWork Configuration
//        getConfig("NetWork.NetCommon");
        // Location Configuration
//        getConfig("General.Location");
        // Capture
//        getCapture();
        //Low-power Device Wakeup
//        wakeup();
        //Get LiveStream
//        livestream();
        //Get Device Info
//        getinfo();
        //Set Configuration
//        setconfig();
        //Operate Device
//        opdev();
        //Device UserManage
//        usermanage();

    }

    private static void setPassword() {
        password = CommonUtil.getPassword();
    }

    private static void queryStatus() throws InterruptedException {
        String pubTopic = "v1/status/query/" + password;
        String subTopic = pubTopic + "/result";
        String content = "";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void login() throws InterruptedException {
        String pubTopic = "v1/device/login/" + password;
        String subTopic = pubTopic + "/result";
        String content = "{\n" +
                "\t\"EncryptType\": \"MD5\",\n" +
                "\t\"LoginType\": \"DVRIP-Web\",\n" +
                "\t\"PassWord\": \"1qa\",\n" +
                "\t\"UserName\": \"admin\",\n" +
                "\t\"Name\": \"generalinfo\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void wakeup() throws InterruptedException {
        String pubTopic = "v1/device/wakeup/" + password;
        String subTopic = pubTopic;
        String content = "";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void livestream() throws InterruptedException {
        String pubTopic = "v1/device/livestream/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "    \"mediaType\": \"hls\",\n" +
                "    \"channel\": \"0\",\n" +
                "    \"stream\": \"1\",\n" +
                "    \"protocol\": \"ts\",\n" +
                "    \"username\": \"username\",\n" +
                "    \"devPwd\": \"password\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void getinfo() throws InterruptedException {
        String pubTopic = "v1/device/getinfo/" + password;
        String subTopic = pubTopic;
        String content = "";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void setconfig() throws InterruptedException {
        String pubTopic = "v1/device/setconfig/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "    \"Name\": \"hls\",\n" +
                "    \"ChannelTitle\": [\n" +
                "        \"cha\"\n" +
                "    ]\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void opdev() throws InterruptedException {
        String pubTopic = "v1/device/opdev/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "    \"Name\": \"hls\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void usermanage() throws InterruptedException {
        String pubTopic = "v1/device/usermanage/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "    \"Name\": \"hls\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    public static void getAbility() throws InterruptedException {
        String password = CommonUtil.getPassword();
        String pubTopic = "v1/device/getability/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "    \"Name\":\"SystemFunction\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    public static void keepAlive() throws InterruptedException {
        String password = CommonUtil.getPassword();
        String pubTopic = "v1/device/keepalive/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "        \"Name\":\"KeepAlive\"\n" +
                "    }";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void getConfig(String name) throws InterruptedException {
        String pubTopic = "v1/device/getconfig/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "        \"Name\":\"" + name + "\"\n" +
                "    }";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    private static void getCapture() throws InterruptedException {
        String pubTopic = "v1/device/capture/" + password;
        String subTopic = pubTopic;
        String content = "{\n" +
                "    \"Name\":\"OPSNAP\",\n" +
                "    \"OPSNAP\":{\n" +
                "        \"Channel\":0\n" +
                "    }\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }
}
