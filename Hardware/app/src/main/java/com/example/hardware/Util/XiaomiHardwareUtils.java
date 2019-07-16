package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/6/13.
 */

public class XiaomiHardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("xiaomi")){
            logInfo = LogUtils.record(logInfo,"");

            String systemPropUIVersionCode = getSystemPropUIVersionCode();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropUIVersionCode=%s",systemPropUIVersionCode));

            String systemPropUIVersionName = getSystemPropUIVersionName();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropUIVersionName=%s",systemPropUIVersionName));

            String systemPropUIVersionCodeTime = getSystemPropUIVersionCodeTime();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropUIVersionCodeTime=%s",systemPropUIVersionCodeTime));

            String systemPropUIMcc = getSystemPropUIMcc();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropUIMcc=%s",systemPropUIMcc));

            String systemPropUIMnc = getSystemPropUIMnc();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropUIMnc=%s",systemPropUIMnc));

            String systemPropUIInternalStorage = getSystemPropUIInternalStorage();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropUIInternalStorage=%s",systemPropUIInternalStorage));
        }


        return  logInfo;
    }

    //miui版本号
    public static String getSystemPropUIVersionName(){
        String versionName = "";
        versionName = SystemPropertiesUtils.getString("ro.miui.ui.version.name","");
        return versionName;
    }

    public static String getSystemPropUIVersionCodeTime(){

        String versionCodeTime = "";
        versionCodeTime = SystemPropertiesUtils.getString("ro.miui.version.code_time","");
        return versionCodeTime;
    }

    //miui版本号
    public static String getSystemPropUIVersionCode() {
        String versionCode = "";
        versionCode = SystemPropertiesUtils.getString("ro.miui.ui.version.code", "");
        return versionCode;
    }

    public static String getSystemPropUIMcc(){
        String mcc = "";
        mcc = SystemPropertiesUtils.getString("ro.miui.mcc","");
        return mcc;
    }

    public static String getSystemPropUIMnc(){
        String mnc = "";
        mnc = SystemPropertiesUtils.getString("ro.miui.mnc","");
        return mnc;
    }

    public static String getSystemPropUIInternalStorage(){
        String versionCode = "";
        versionCode = SystemPropertiesUtils.getString("ro.miui.internal.storage","");
        return versionCode;
    }

}
