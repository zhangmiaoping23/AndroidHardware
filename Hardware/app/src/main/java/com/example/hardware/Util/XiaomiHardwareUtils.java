package com.example.hardware.Util;

import android.content.Context;

import org.joor.Reflect;

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

            String systemPropCarrierName = getSystemPropCarrierName();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropCarrierName=%s",systemPropCarrierName));

            String systemPropRegion = getSystemPropRegion();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi systemPropRegion=%s",systemPropRegion));

            String oaid = getOaid(context);
            logInfo = LogUtils.record(logInfo,String.format("xiaomi oaid=%s",oaid));
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

    /**
     * 判断当前机型是否是定制机，用于小米手机
     *
     * @return
     */
    public static String getSystemPropCarrierName() {
        String customize = SystemPropertiesUtils.getString("ro.carrier.name", "unknown");
        if (customize != null) {
            return customize;
        }
        return null;
    }

    public static String getSystemPropRegion() {
        String customize = SystemPropertiesUtils.getString("ro.miui.region", "CN");
        if (customize != null) {
            return customize;
        }
        return null;
    }

    //  Object v0 = Za_ReflectUtils.b_invokeObject(Za_ReflectUtils.a_findClass("com.android.id.IdentifierManager"), null, "getOAID", "(Landroid/content/Context;)Ljava/lang/String;", new Object[]{b_AppGlobals.b_getApplication()});
    public static String getOaid(Context context){
        String oaid = "";
        try{
            oaid = Reflect.on("com.android.id.IdentifierManager").call("getOAID",context).get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return oaid;
    }
}
