package com.example.hardware.Util;

/**
 * Created by zhangmp on 2018/8/2.
 */

public class HexUtils {
    public static String toHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer(bytes.length);
        int length = bytes.length;
        for(int index = 0; index < length; ++index) {
            String byteHexString = Integer.toHexString(bytes[index] & 255);
            stringBuffer = byteHexString.length() == 1 ? stringBuffer.append("0").append(byteHexString) : stringBuffer.append(byteHexString);
        }

        return String.valueOf(stringBuffer);
    }
}
