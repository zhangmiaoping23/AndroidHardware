package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2020/3/19.
 */

public class SystemFeatureUtils {
    public static boolean hasHardwareTelephonySystemFeature(Context context){
        boolean hasHardwareTelephonySystemFeature = false;
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            //"Device can make phone calls"
            hasHardwareTelephonySystemFeature = true;
        }
        return hasHardwareTelephonySystemFeature;
    }

    public static boolean hasHardwareTouchScreenSystemFeature(Context context){
        boolean hasHardwareTouchScreenSystemFeature = false;
        if (context.getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            //"Device has a touch screen."
            hasHardwareTouchScreenSystemFeature = true;
        }
        return hasHardwareTouchScreenSystemFeature;
    }

    public static String getInfo(Context context) {
        //重点关注的硬件特性
        String logInfo = "";
        boolean hasHardwareTelephonySystemFeature = hasHardwareTelephonySystemFeature(context);
        logInfo = LogUtils.record(logInfo, String.format("hasHardwareTelephonySystemFeature=%b", hasHardwareTelephonySystemFeature));

        boolean hasHardwareTouchScreenSystemFeature = hasHardwareTouchScreenSystemFeature(context);
        logInfo = LogUtils.record(logInfo, String.format("hasHardwareTouchScreenSystemFeature=%b", hasHardwareTouchScreenSystemFeature));
        return logInfo;
    }
}
