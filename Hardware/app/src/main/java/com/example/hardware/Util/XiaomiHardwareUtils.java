package com.example.hardware.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import org.joor.Reflect;

import java.util.ArrayList;
import java.util.List;
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

            List<String> imeiList = getImeiListFromSystemProp();    //getImeiListFromMiui() > getImeiListAboveLollipop(context),getImeiListBelowLollipop(context) > getImeiListFromSystemProp()
            logInfo = LogUtils.record(logInfo,String.format("xiaomi imeiListFromSystemProp=%s",imeiList.toString()));

            String aaid = getAaid(context);
            logInfo = LogUtils.record(logInfo,String.format("xiaomi aaid=%s",aaid));

            boolean isAlphaBuild = isAlphaBuild();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isAlphaBuild=%b",isAlphaBuild));

            boolean isCtaBuild = isCtaBuild();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isCtaBuild=%b",isCtaBuild));

            boolean isCtsBuild = isCtsBuild();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isCtsBuild=%b",isCtsBuild));

            boolean isDevelopmentVersion = isDevelopmentVersion();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isDevelopmentVersion=%b",isDevelopmentVersion));

            boolean isInternationalBuild = isInternationalBuild();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isInternationalBuild=%b",isInternationalBuild));

            boolean isStableVersion = isStableVersion();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isStableVersion=%b",isStableVersion));

            boolean isTablet = isTablet();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi isTablet=%b",isTablet));

            String romBuildCode = getRomBuildCode();
            logInfo = LogUtils.record(logInfo,String.format("xiaomi romBuildCode=%s",romBuildCode));

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

    public static boolean isAlphaBuild() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_ALPHA_BUILD").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static boolean isCtaBuild() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_CTA_BUILD").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static boolean isCtsBuild() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_CTS_BUILD").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static boolean isDevelopmentVersion() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_DEVELOPMENT_VERSION").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static boolean isInternationalBuild() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_INTERNATIONAL_BUILD").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static boolean isStableVersion() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_STABLE_VERSION").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static boolean isTablet() {
        boolean ret = false;
        try{
            Reflect.on("miui.os.Build").field("IS_TABLET").get();
        }catch (Throwable throwable){
            throwable.toString();
        }
        return ret;
    }

    public static String getRomBuildCode(){
        String romBuildCode = "";
        if(isAlphaBuild()){
            romBuildCode = "A";
        }else if(isDevelopmentVersion()){
            romBuildCode = "D";
        }else if(isStableVersion()){
            romBuildCode = "S";
        }
        return romBuildCode;
    }

    private static List<String> getImeiListFromSystemProp() {
        ArrayList arrayList = new ArrayList();
        String imei1 = SystemPropertiesUtils.getString("ro.ril.miui.imei0",""); //[ro.ril.miui.imei0]: [865411023509766] //[ro.ril.miui.imei]: [865411023509766]
        if(TextUtils.isEmpty(imei1)) {
            imei1 = SystemPropertiesUtils.getString("ro.ril.oem.imei","");
        }

        if(TextUtils.isEmpty(imei1)) {
            imei1 = SystemPropertiesUtils.getString("persist.radio.imei","");
        }

        if(isLegalImei(imei1)) {
            arrayList.add(imei1);
        }

        String imei2 = "";
        if(isMultisim()) {
            imei2 = SystemPropertiesUtils.getString("ro.ril.miui.imei1","");
            if(TextUtils.isEmpty(imei2)) {
                imei2 = SystemPropertiesUtils.getString("ro.ril.oem.imei2","");
            }

            if(TextUtils.isEmpty(imei2)) {
                imei2 = SystemPropertiesUtils.getString("persist.radio.imei2","");
            }

            if(isLegalImei(imei2)) {
                arrayList.add(imei2);
            }
        }
        return arrayList;
    }

    private static boolean isLegalImei(String imei) {
        boolean isLegal = imei == null || imei.length() != 15 || (imei.matches("^0*$")) ? false : true;
        return isLegal;
    }

    private static boolean isMultisim() {
        if("dsds".equals(SystemPropertiesUtils.getString("persist.radio.multisim.config",""))) {
            return true;
        }

        boolean ret = false;
        String buildDevice = Build.DEVICE;
        if("lcsh92_wet_jb9".equals(buildDevice)
                || "lcsh92_wet_tdd".equals(buildDevice)
                || "HM2013022".equals(buildDevice)
                || "HM2013023".equals(buildDevice)
                || "armani".equals(buildDevice)
                || "HM2014011".equals(buildDevice)
                || "HM2014012".equals(buildDevice)) {
           ret = true;
        }

        return ret;
    }

    public static String getAaid(Context context){
        String aaid = "";
        try{
            aaid = context.getContentResolver().getType(Uri.parse("content://com.miui.analytics.server.AnalyticsProvider/aaid"));
        }catch (Exception e){

        }

        if(aaid.isEmpty()){
            aaid = (String)Reflect.on("android.provider.MiuiSettings$Ad").call("getAaid",context.getContentResolver()).get();
        }
        return aaid;
    }
}
