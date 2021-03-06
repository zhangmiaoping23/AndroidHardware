package com.example.hardware.Util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;


import static org.joor.Reflect.on;

public class OppoHardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String buildBrand = HardwareUtils.getBuildBrand();
        buildBrand = buildBrand.toLowerCase();
        if(buildBrand.contains("oppo")){
            logInfo = LogUtils.record(logInfo,"");

            //oppo机型特有的属性
            String mobileRomVersion = OppoHardwareUtils.getMobileRomVersion();
            logInfo = LogUtils.record(logInfo,String.format("oppo mobileRomVersion=%s",mobileRomVersion));

            String subBrand = OppoHardwareUtils.getSubBrand();
            logInfo = LogUtils.record(logInfo,String.format("oppo subBrand=%s",subBrand));

            String colorOSVersion = OppoHardwareUtils.getColorOSVersion();
            logInfo = LogUtils.record(logInfo,String.format("oppo colorOSVersion=%s",colorOSVersion));

            boolean isColorOsV2 = OppoHardwareUtils.isColorOsV2();
            logInfo = LogUtils.record(logInfo,String.format("oppo isColorOsV2=%b",isColorOsV2));

            boolean isColorOsV3 = OppoHardwareUtils.isColorOsV3();
            logInfo = LogUtils.record(logInfo,String.format("oppo isColorOsV3=%b",isColorOsV3));

            String romName = OppoHardwareUtils.getRomName();
            logInfo = LogUtils.record(logInfo,String.format("oppo romName(ro.build.display.id)=%s",romName));

            String colorImei = OppoHardwareUtils.reflectColorImei(context);
            logInfo = LogUtils.record(logInfo,String.format("oppo colorImei(higher than imei)=%s",colorImei));

            String insVer = getInsVer(context);
            logInfo = LogUtils.record(logInfo,String.format("oppo insVer=%s",insVer));

            String locale = getLocale();
            logInfo = LogUtils.record(logInfo,String.format("oppo locale=%s",locale));
        }
        return logInfo;
    }
    //
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

    public static String getSubBrand() {
        String subBrand = "";
        try {
            subBrand = SystemPropertiesUtils.getString("ro.product.brand.sub", "");
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return subBrand;
    }

    public static String getColorOSVersion(){
        //静态方法
        int colorOSVersion = on("com.color.os.ColorBuild").call("getColorOSVERSION").get();
        String ret = String.valueOf(colorOSVersion);
        return ret;
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

    public static boolean isColorOsV2() {
        boolean ret = false;
        String mobileRomVersion = getMobileRomVersion();
        if(!TextUtils.isEmpty(((CharSequence)mobileRomVersion)) && ((mobileRomVersion.startsWith("v2")) || (mobileRomVersion.startsWith("V2")))) {
            ret = true;
        }

        return ret;
    }

    public static boolean isColorOsV3() {
        boolean ret = false;
        String mobileRomVersion = getMobileRomVersion();  // V3.0.0
        if(!TextUtils.isEmpty(((CharSequence)mobileRomVersion)) && mobileRomVersion.length() >= 2) {
            char first = mobileRomVersion.charAt(0);
            char second = mobileRomVersion.charAt(1);
            if(first != 'v' && first != 'V') {  // 118=v / 86=V
                return ret;
            }

            if(second != '3'  && second != '4' && second != '5') {  // 51=0x33=3 / 52=0x34=4 / 53=0x35=5 /
                return ret;
            }

            ret = true;
        }

        return ret;
    }

    public static String getLocale() {
        String oppoRegion =  "";
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            oppoRegion = (String)systemPropertiesClass.getMethod("get", String.class, String.class).invoke(systemPropertiesClass, "persist.sys.oppo.region", "");           // "CN"
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        String ret = PhoneLocaleUtils.getLocaleDefaultLanuage() + "-" + PhoneLocaleUtils.getLocaleDefaultCountry();
        if(!TextUtils.isEmpty(((CharSequence)oppoRegion))) {
            ret = ret + ";" + oppoRegion;
        }

        return ret;
    }

    public static String getInsVer(Context argContext) {
        String ret = "-1";
        int failResult = -1;
        if(isPackageInstantPlatformInstalled(argContext, "com.nearme.instant.platform")) {
            int platformVersion = getPlatformVersion(argContext);                   //1000
            int platformApiLevel = getPlatformApiLevel(argContext);                 //10100
            int platformBizVersion = getPlatformBizVersion(argContext);             //1
            if(failResult != platformVersion && failResult != platformApiLevel && failResult != platformBizVersion) {
                StringBuilder stringBuilder = new StringBuilder().append(platformApiLevel).append("/").append(platformVersion).append("/").append(platformBizVersion);
                try {
                    ret = URLEncoder.encode(stringBuilder.toString(), "UTF-8");
                }
                catch(UnsupportedEncodingException exception) {

                }

                return ret;
            }

            ret = "-1";
        }
        else {
            ret = "-1";
        }

        return ret;
    }

    private static boolean isPackageInstantPlatformInstalled(Context argContext, String packageName) {
        boolean ret = false;
        PackageManager packageManager = argContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.nearme.instant.platform", packageManager.GET_META_DATA);
            if(applicationInfo == null) {
                return ret;
            }

            if(!applicationInfo.packageName.equals(packageName)) {
                return ret;
            }
        }
        catch(Exception exception) {
            return ret;
        }

        return true;
    }

    private static int getPlatformVersion(Context argContext) {
        int ret = -1;
        PackageManager packageManager = argContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.nearme.instant.platform", packageManager.GET_META_DATA);
            if(applicationInfo == null) {
                return ret;
            }

            Object objectPlatformVersion = applicationInfo.metaData.get("platformVersion");
            if(objectPlatformVersion == null) {
                return ret;
            }

            if(!(objectPlatformVersion instanceof Integer)) {
                return ret;
            }

            int platformVersion = ((Integer)objectPlatformVersion).intValue();
            return platformVersion;
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }

    private static int getPlatformApiLevel(Context argContext) {
        int ret = -1;
        PackageManager packageManager = argContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.nearme.instant.platform", packageManager.GET_META_DATA);
            if(applicationInfo == null) {
                return ret;
            }

            Object objectApiLevel = applicationInfo.metaData.get("api_level");
            if(objectApiLevel == null) {
                return ret;
            }

            if(!(objectApiLevel instanceof Integer)) {
                return ret;
            }

            int apiLevel = ((Integer)objectApiLevel).intValue();
            return apiLevel;
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }

    private static int getPlatformBizVersion(Context argContext) {
        int ret = -1;
        PackageManager packageManager = argContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.nearme.instant.platform", packageManager.GET_META_DATA);
            if(applicationInfo == null) {
                return ret;
            }

            Object objectBizVersion = applicationInfo.metaData.get("biz_version");
            if(objectBizVersion == null) {
                return ret;
            }

            if(!(objectBizVersion instanceof Integer)) {
                return ret;
            }

            int bizVersion = ((Integer)objectBizVersion).intValue();
            return bizVersion;
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }
}
