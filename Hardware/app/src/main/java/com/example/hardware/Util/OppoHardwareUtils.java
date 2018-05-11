package com.example.hardware.Util;

import android.content.Context;
import android.text.TextUtils;

public class OppoHardwareUtils {
    //此优先级高于HardwareUtils.getImei(Context context)
    public static String reflectColorImei(Context argContext) {
        String colorImei = null;
        try {
            Class colorOSTelephonyManagerClass = Class.forName("android.telephony.ColorOSTelephonyManager");
            colorImei =(String)colorOSTelephonyManagerClass.getMethod("colorGetImei", Integer.TYPE).invoke(colorOSTelephonyManagerClass.getMethod("getDefault", Context.class).invoke(colorOSTelephonyManagerClass, argContext), Integer.valueOf(0));
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        return colorImei;
    }

    public static String getMobileRomVersion() {
        String mobileRomVersion = "";
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            mobileRomVersion = (String)systemPropertiesClass.getMethod("get", String.class, String.class).invoke(systemPropertiesClass, "ro.build.version.opporom", "0");  // "V3.0.0"
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return mobileRomVersion;
    }

    public static String getRomName() {
        String romName = "";
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            romName = (String)systemPropertiesClass.getMethod("get", String.class, String.class).invoke(systemPropertiesClass, "ro.build.display.id", "");
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return romName;
    }
}
