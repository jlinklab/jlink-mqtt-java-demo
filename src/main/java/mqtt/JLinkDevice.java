package mqtt;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * device Object
 */
public class JLinkDevice {
    /**
     * devise serial number
     */
    private String mDeviceSn;

    private JLinkClient mJLinkClient = null;

    /**
     * device UserName
     */
    private String mDeviceUser;

    /**
     * device Password
     */
    private String mDevicePass;

    public JLinkDevice(String mDeviceSn, String mDeviceUser, String mDevicePass) {
        this.mDeviceSn = mDeviceSn;
        this.mDeviceUser = mDeviceUser;
        this.mDevicePass = mDevicePass;
    }

    public String getmDeviceSn() {
        return mDeviceSn;
    }

    public void setmDeviceSn(String mDeviceSn) {
        this.mDeviceSn = mDeviceSn;
    }

    public JLinkClient getmJLinkClient() {
        return mJLinkClient;
    }

    public void setmJLinkClient(JLinkClient mJLinkClient) {
        this.mJLinkClient = mJLinkClient;
    }

    public String getmDeviceUser() {
        return mDeviceUser;
    }

    public void setmDeviceUser(String mDeviceUser) {
        this.mDeviceUser = mDeviceUser;
    }

    public String getmDevicePass() {
        return mDevicePass;
    }

    public void setmDevicePass(String mDevicePass) {
        this.mDevicePass = mDevicePass;
    }
}
