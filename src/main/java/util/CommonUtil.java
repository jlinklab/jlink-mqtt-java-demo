package util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import mqtt.JLinkClient;
import mqtt.JLinkDevice;

import java.nio.charset.StandardCharsets;

/**
 * @author yxm
 * @description: password
 * @date 2021/12/28 15:31
 */
public class CommonUtil {


    public static String getToken(JLinkClient client, JLinkDevice device) {

        String url = "https://pub-token.xmeye.net/reqToken";
        String SEVICE = "MQTT";
        String tm = getTimMillis();
        String sign = getEncryptStr(client.getUuid(), client.getAppKey(), client.getAppSecret(), tm, client.getMoveCard());
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("sn", device.getmDeviceSn());
        jsonObject.putOnce("service", SEVICE);
        jsonObject.putOnce("uuid", client.getUuid());
        jsonObject.putOnce("appkey", client.getAppKey());
        jsonObject.putOnce("tm", tm);
        jsonObject.putOnce("sign", sign);
        jsonObject.putOnce("user", device.getmDeviceUser());
        jsonObject.putOnce("pass", device.getmDevicePass());
        String password = HttpRequest.post(url).header(Header.CONTENT_TYPE, "application/json")
                .body(jsonObject.toString()).execute().body();
        return JSONUtil.parseObj(password).getStr("token");
    }

    private static String getEncryptStr(String uuid, String appKey, String appSecret, String timeMillis, int movedCard) {
        String encryptStr = uuid + appKey + appSecret + timeMillis;
        byte[] encryptByte = encryptStr.getBytes(StandardCharsets.ISO_8859_1);
        byte[] changeByte = change(encryptStr, movedCard);
        byte[] mergeByte = mergeByte(encryptByte, changeByte);
        return SecureUtil.md5().digestHex(mergeByte);
    }

    /**
     * SimpleShift
     */
    private static byte[] change(String encryptStr, int moveCard) {
        byte[] encryptByte = encryptStr.getBytes(StandardCharsets.ISO_8859_1);
        int encryptLength = encryptByte.length;
        byte temp;
        for (int i = 0; i < encryptLength; i++) {
            temp = ((i % moveCard) > ((encryptLength - i) % moveCard)) ? encryptByte[i] : encryptByte[encryptLength - (i + 1)];
            encryptByte[i] = encryptByte[encryptLength - (i + 1)];
            encryptByte[encryptLength - (i + 1)] = temp;
        }
        return encryptByte;
    }

    /**
     * merge
     *
     * @param encryptByte
     * @param changeByte
     * @return
     */
    private static byte[] mergeByte(byte[] encryptByte, byte[] changeByte) {
        int encryptLength = encryptByte.length;
        int encryptLength2 = encryptLength * 2;
        byte[] temp = new byte[encryptLength2];
        for (int i = 0; i < encryptByte.length; i++) {
            temp[i] = encryptByte[i];
            temp[encryptLength2 - 1 - i] = changeByte[i];
        }
        return temp;
    }

    private static long timMillis;//timeStamp
    private static long counter = 0L;//counter

    /**
     * accessToCounter
     *
     * @return
     */
    private static synchronized String getCounter() {
        ++counter;
        if (counter < 10L)
            return "000000" + String.valueOf(counter);
        else if (counter < 100L)
            return "00000" + String.valueOf(counter);
        else if (counter < 1000L)
            return "0000" + String.valueOf(counter);
        else if (counter < 10000L)
            return "000" + String.valueOf(counter);
        else if (counter < 100000L)
            return "00" + String.valueOf(counter);
        else if (counter < 1000000L)
            return "0" + String.valueOf(counter);
        else if (counter < 10000000L)
            return String.valueOf(counter);
        else {
            counter = 1L;
            return "000000" + String.valueOf(counter);
        }
    }

    /**
     * Gets the combined timestamp
     *
     * @return
     */
    private static String getTimMillis() {
        timMillis = System.currentTimeMillis();
        return getCounter() + String.valueOf(timMillis);
    }
}
