package mqtt;

import util.CommonUtil;
import util.MqttUtil;

/**
 * @author yxm
 * @description: Test Run
 * @date 2021/12/28 16:31
 */
public class Mqtt {

    private static String token;

    public static void main(String[] args) throws InterruptedException {

        JLinkClient jlinkClient=new JLinkClient("e0534f3240274897821a126be19b6d46",
                "0621ef206a1d4cafbe0c5545c3882ea8",
                "90f8bc17be2a425db6068c749dee4f5d",
                2);
        JLinkDevice jLinkDevice = new JLinkDevice("24349dceec6afb13", "admin", "1qa");

        // Set Password
        getToken(jlinkClient,jLinkDevice);
        // Get Device Status
        queryStatus();
        //Login Device
        login();
        // Get Ability
        getAbility(jlinkClient,jLinkDevice);
        // KeepAlive
        keepAlive(jlinkClient,jLinkDevice);
        // Get Channel Title
        getConfig("ChannelTitle");
        // Get Alarm
        getConfig("Alarm.LocalAlarm");
        // NetWork Configuration
        getConfig("NetWork.NetCommon");
        // Location Configuration
        getConfig("General.Location");
        // Capture
        getCapture();
        //Low-power Device Wakeup
        wakeup();
        //Get LiveStream
        livestream();
        //Get Device Info
        getinfo();
        //Set Configuration
        setconfig();
        //Operate Device
        opdev();
        //Device UserManage
        usermanage();

    }

    public static String getToken(JLinkClient client,JLinkDevice device) {
        token = CommonUtil.getToken(client, device);
        return token;
    }

    public static String createSubTopic(String pubTopic){
        return pubTopic+"/result";
    }

    public static void queryStatus() throws InterruptedException {
        String pubTopic = "v1/status/query/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void login() throws InterruptedException {
        String pubTopic = "v1/device/login/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "\t\"EncryptType\": \"MD5\",\n" +
                "\t\"LoginType\": \"DVRIP-Web\",\n" +
                "\t\"PassWord\": \"\",\n" +
                "\t\"UserName\": \"admin\",\n" +
                "\t\"Name\": \"generalinfo\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void wakeup() throws InterruptedException {
        String pubTopic = "v1/device/wakeup/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void livestream() throws InterruptedException {
        String pubTopic = "v1/device/livestream/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "    \"mediaType\": \"hls\",\n" +
                "    \"channel\": \"0\",\n" +
                "    \"stream\": \"1\",\n" +
                "    \"protocol\": \"ts\",\n" +
                "    \"username\": \"username\",\n" +
                "    \"devPwd\": \"password\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void getinfo() throws InterruptedException {
        String pubTopic = "v1/device/getinfo/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void setconfig() throws InterruptedException {
        String pubTopic = "v1/device/setconfig/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "    \"Name\": \"hls\",\n" +
                "    \"ChannelTitle\": [\n" +
                "        \"cha\"\n" +
                "    ]\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void opdev() throws InterruptedException {
        String pubTopic = "v1/device/opdev/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "    \"Name\": \"hls\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void usermanage() throws InterruptedException {
        String pubTopic = "v1/device/usermanage/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "    \"Name\": \"hls\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void getAbility(JLinkClient client,JLinkDevice device) throws InterruptedException {
        String password = getToken(client, device);
        String pubTopic = "v1/device/getability/" + password;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "    \"Name\":\"SystemFunction\"\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    public static void keepAlive(JLinkClient client,JLinkDevice device) throws InterruptedException {
        String password = getToken(client,device);
        String pubTopic = "v1/device/keepalive/" + password;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "        \"Name\":\"KeepAlive\"\n" +
                "    }";
        MqttUtil.mqtt(pubTopic, subTopic, content, password);
    }

    public static void getConfig(String name) throws InterruptedException {
        String pubTopic = "v1/device/getconfig/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "        \"Name\":\"" + name + "\"\n" +
                "    }";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }

    public static void getCapture() throws InterruptedException {
        String pubTopic = "v1/device/capture/" + token;
        String subTopic = createSubTopic(pubTopic);
        String content = "{\n" +
                "    \"Name\":\"OPSNAP\",\n" +
                "    \"OPSNAP\":{\n" +
                "        \"Channel\":0\n" +
                "    }\n" +
                "}";
        MqttUtil.mqtt(pubTopic, subTopic, content, token);
    }
}
