package com.example.hardware.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import org.joor.Reflect;

import java.util.Map;

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

            String coreMiuiLevel = getMiuiLevel(context,"com.miui.core");
            logInfo = LogUtils.record(logInfo,String.format("xiaomi com.miui.core miuiLevel=%s",coreMiuiLevel));

            String systemMiuiLevel = getMiuiLevel(context,"com.miui.system");
            logInfo = LogUtils.record(logInfo,String.format("xiaomi com.miui.system miuiLevel=%s",systemMiuiLevel));

            String romMiuiLevel = getMiuiLevel(context,"com.miui.rom");
            logInfo = LogUtils.record(logInfo,String.format("xiaomi com.miui.rom miuiLevel=%s",romMiuiLevel));

            String romLevel = getRomLevel(coreMiuiLevel,systemMiuiLevel,romMiuiLevel);
            logInfo = LogUtils.record(logInfo,String.format("xiaomi romLevel=%s",romLevel));

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

    public static String getRomLevel(String coreMiuiLevel,String systemMiuiLevel,String romMiuiLevel){
        //"com.miui.core", "com.miui.system", "com.miui.rom"
        //res\xml\miui_manifest.xml  <manifest miui:name="" miui:level="9" xmlns:miui="http://schemas.android.com/apk/res/miui" />
        //com.miui.core 	/system/app/miui/miui.apk					MIUI SDK
        //com.miui.system	/system/app/miuisystem.apk
        //com.miui.rom		/system/framework/framework-ext-res.apk
        String romLevel = "";
        if(romLevel.isEmpty()){
            romLevel += coreMiuiLevel;
        }else{
            romLevel += "," + coreMiuiLevel;
        }

        if(romLevel.isEmpty()){
            romLevel += systemMiuiLevel;
        }else{
            romLevel += "," + systemMiuiLevel;
        }

        if(romLevel.isEmpty()){
            romLevel += romMiuiLevel;
        }else{
            romLevel += "," + romMiuiLevel;
        }
        return romLevel;
    }
    public static String getMiuiLevel(Context context,String packageName){
        String miuiLevelRet = "";
        try{
            do{
                Class ManifestParserClass = Class.forName("miui.core.ManifestParser");
                if(null == ManifestParserClass) {
                    break;
                }
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);
                if(null == packageInfo){
                    break;
                }

                if(packageInfo.applicationInfo.sourceDir.isEmpty()){
                    break;
                }

                AssetManager assetManager = Reflect.on("android.content.res.AssetManager").create().get();
                if(assetManager == null){
                    break;
                }
                Resources resources =  context.createPackageContext(packageName, 0).getResources();
                if(null == resources){
                    break;
                }


                /*
                //ManifestParser.createFromResources(resources, packageInfo.packageName, packageInfo.applicationInfo.metaData).parse(null).getLevel();
                Object manifestParser = Reflect.on("miui.core.ManifestParser").call("createFromResources",resources, packageInfo.packageName, packageInfo.applicationInfo.metaData).get();
                Map map = null;
                //public miui.core.Manifest miui.core.ManifestParser.parse(java.util.Map)
                Object  manifest = Reflect.on(manifestParser).call("parse",map).get();
                Integer miuiLevel = Reflect.on(manifest).call("getLevel").get();
                */

                //parse直接传null会发生异常
                //Integer miuiLevel = Reflect.on("miui.core.ManifestParser").call("createFromResources",resources, packageInfo.packageName, packageInfo.applicationInfo.metaData).call("parse",null).call("getLevel").get();
                Map map = null;
                Integer miuiLevel = Reflect.on("miui.core.ManifestParser").call("createFromResources",resources, packageInfo.packageName, packageInfo.applicationInfo.metaData).call("parse",map).call("getLevel").get();
                miuiLevelRet = String.valueOf(miuiLevel);
            }while(false);
        }catch (Throwable throwable){
            throwable.toString();
        }
        return miuiLevelRet;
    }
}
