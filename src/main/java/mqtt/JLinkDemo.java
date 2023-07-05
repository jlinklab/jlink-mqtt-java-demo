package mqtt;

public class JLinkDemo {
    public static void main(String[] args) throws InterruptedException  {
        //Initialization  JLinkClient
        JLinkClient jlinkClient=new JLinkClient("e0534f3240274897821a126be19b6d46",
                "0621ef206a1d4cafbe0c5545c3882ea8",
                "90f8bc17be2a425db6068c749dee4f5d",
                2);
        //Initialization JLinkDevice
        JLinkDevice jLinkDevice = new JLinkDevice("24349dceec6afb13", "admin", "1qa");
        // Set Password
        String token = Mqtt.getToken(jlinkClient, jLinkDevice);
        // Get Device Status
        Mqtt.queryStatus();
        //Login Device
        Mqtt.login();
        // Get Ability
        Mqtt.getAbility(jlinkClient,jLinkDevice);
        // KeepAlive
        Mqtt.keepAlive(jlinkClient,jLinkDevice);
        // Get Channel Title
        Mqtt.getConfig("ChannelTitle");
        // Get Alarm
        Mqtt.getConfig("Alarm.LocalAlarm");
        // NetWork Configuration
        Mqtt.getConfig("NetWork.NetCommon");
        // Location Configuration
        Mqtt.getConfig("General.Location");
        // Capture
        Mqtt.getCapture();
        //Low-power Device Wakeup
        Mqtt.wakeup();
        //Get LiveStream
        Mqtt.livestream();
        //Get Device Info
        Mqtt.getinfo();
        //Set Configuration
        Mqtt.setconfig();
        //Operate Device
        Mqtt.opdev();
        //Device UserManage
        Mqtt.usermanage();
    }
}
