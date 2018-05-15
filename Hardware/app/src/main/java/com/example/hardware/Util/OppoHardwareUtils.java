package com.example.hardware.Util;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Method;

public class OppoHardwareUtils {
    public static void getHardwares(Context context){
        String buildBrand = HardwareUtils.getBuildBrand();
        buildBrand = buildBrand.toLowerCase();
        if(buildBrand.contains("oppo")){
            //oppo机型特有的属性
            String mobileRomVersion = OppoHardwareUtils.getMobileRomVersion();
            LogUtils.i(String.format("oppo mobileRomVersion=%s",mobileRomVersion));

            String romName = OppoHardwareUtils.getRomName();
            LogUtils.i(String.format("oppo romName(ro.build.display.id)=%s",romName));

            String colorImei = OppoHardwareUtils.reflectColorImei(context);
            LogUtils.i(String.format("oppo colorImei(higher than imei)=%s",colorImei));
        }
    }

    //此优先级高于HardwareUtils.getImei(Context context)
    public static String reflectColorImei(Context argContext) {
        String colorImei = null;
        try {
            Class colorOSTelephonyManagerClass = Class.forName("android.telephony.ColorOSTelephonyManager");
            Method getDefaultMethod = colorOSTelephonyManagerClass.getMethod("getDefault", Context.class);
            Object colorOSTelephonyManager = getDefaultMethod.invoke(colorOSTelephonyManagerClass, argContext);
            Method colorGetImeiMethod = colorOSTelephonyManagerClass.getMethod("colorGetImei", Integer.TYPE);
            colorImei =(String)colorGetImeiMethod.invoke(colorOSTelephonyManager, Integer.valueOf(0));
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
