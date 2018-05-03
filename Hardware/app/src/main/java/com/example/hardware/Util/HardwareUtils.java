package com.example.hardware.Util;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.TimeZone;

/**
 * Created by zhangmp on 2018/4/25.
 */

public class HardwareUtils {
    public static String getSystemUserAgent(){
        return System.getProperty("http.agent");
    }

    public static String getBuildBoard(){
        String buildBoard = Build.BOARD;
        return buildBoard;
    }

    public static String getBuildBootLoader(){
        String buildBootloader = Build.BOOTLOADER;
        return buildBootloader;
    }

    public static String getBuildBrand(){
        String buildBrand = Build.BRAND;
        return buildBrand;
    }

    public static String getbuildCpuApi(){
        String buildCpuApi = Build.CPU_ABI;
        return buildCpuApi;
    }

    public static String getbuildCpuApi2(){
        String buildCpuApi2 = Build.CPU_ABI2;
        return buildCpuApi2;
    }

    public static String getBuildDevice(){
        String buildDevice = Build.DEVICE;
        return buildDevice;
    }

    public static String getBuildDisplay(){
        String buildDisplay =Build.DISPLAY;
        return buildDisplay;
    }

    public static String getBuildFingerPrint(){
        String buildFingerPrint = Build.FINGERPRINT;
        return buildFingerPrint;
    }

    public static String getBuildRadioVersion(){
        String buildRadioVersion = Build.getRadioVersion();
        return buildRadioVersion;
    }

    public static String getBuildSerial(){
        String buildSerial = "";
        /*
        try{
            //此方法在compileSDKVersion=26才引入
            buildSerial = Build.getSerial();
        }catch (NoSuchMethodError exception){
            buildSerial = Build.SERIAL;
        }
        */
        buildSerial = Build.SERIAL;
        return buildSerial;
    }

    public static String getBuildHardware(){
        String buildHardware = Build.HARDWARE;
        return buildHardware;
    }

    public static String getBuildHost(){
        String buildHost = Build.HOST;
        return buildHost;
    }

    public static String getBuildId(){
        String buildId = Build.ID;
        return buildId;
    }

    public static String getBuildManufacturer(){
        String buildManufacturer = Build.MANUFACTURER;
        return buildManufacturer;
    }

    public static String getBuildModel(){
        String buildModel =Build.MODEL;
        return buildModel;
    }
    public static String getBuildProduct(){
        String buildProduct = Build.PRODUCT;
        return buildProduct;
    }

    public static String getBuildTags(){
        String buildTags = Build.TAGS;
        return buildTags;
    }

    public static String getBuildTime(){
        long time = Build.TIME;
        String buildTime = String.valueOf(time);
        return buildTime;
    }

    public static String getBuildType(){
        String buildType = Build.TYPE;
        return buildType;
    }

    public static String getBuildUser(){
        String buildUser = Build.USER;
        return buildUser;
    }

    public static String getBuildVersionBaseOS(){
        String buildVersionBaseOS = "";
        try{
            buildVersionBaseOS = Build.VERSION.BASE_OS;
        }catch (java.lang.NoSuchFieldError error){
            buildVersionBaseOS = "";
        }
        return buildVersionBaseOS;
    }

    public static String getBuildVersionCodeName(){
        String buildVersionCodeName = Build.VERSION.CODENAME;
        return buildVersionCodeName;
    }

    public static String getBuildVersionIncremental(){
        String buildVersionIncremental = Build.VERSION.INCREMENTAL;
        return buildVersionIncremental;
    }

    public static String getBuildVersionRelease(){
        String buildVersionRelease = Build.VERSION.RELEASE;
        return buildVersionRelease;
    }

    public static String getBuildVersionSecurityPatch(){
        String buildVersionSecurityPatch = "";
        try{
            buildVersionSecurityPatch = Build.VERSION.SECURITY_PATCH;;
        }catch (java.lang.NoSuchFieldError error){
            buildVersionSecurityPatch = "";
        }
        return buildVersionSecurityPatch;
    }

    public static String getBuildVersionSDK(){
        String buildVersionSDK = Build.VERSION.SDK;
        return buildVersionSDK;
    }

    public static String getFirmwareVersion(){
        String firmwareVersion =getBuildVersionRelease().trim();
        return firmwareVersion;
    }

    public static String getImei(Context context){
        return getDeviceId(context);
    }

    public static String getDeviceId(Context context) {
        String deviceId = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            deviceId = telephonyManager.getDeviceId();
        }
        return deviceId;
    }
    public static String getDensityDpi(Context context) {
        String densityDpi = "";
        DisplayMetrics displayMetrics = null;
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            if(display != null) {
                displayMetrics = new DisplayMetrics();
                display.getRealMetrics(displayMetrics);
                if(displayMetrics != null) {
                    densityDpi = String.valueOf(displayMetrics.densityDpi);
                }
            }
        }

        return densityDpi;
    }

    public static String getTimeZone(){
        String timeZone = TimeZone.getDefault().getID();
        return timeZone;
    }

    /*
    private static String getValueFromBuildPropFile() {
        String[] splitArrays;
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        int twoValue = 2;
        String nullStr = null;
        String buildPropPath = "/system/build.prop";
        try {
            fileReader = new FileReader(buildPropPath);
            bufferedReader = new BufferedReader(((Reader)fileReader), 2048);
            do {
                String cmdline = = bufferedReader.readLine();
                if(cmdline != null) {
                    splitArrays = buildPropPath.split("=", 2);
                    if(splitArrays.length != twoValue) {
                        continue;
                    }

                    if(splitArrays[0].equals("ro.product.cpu.abilist")) {
                        buildPropPath = splitArrays[1];
                    }
                    else {
                        if(!splitArrays[0].equals("ro.product.cpu.abi")) {
                            continue;
                        }

                        break;
                    }
                }
            }
            while(true);
        }catch (Exception exception){

        }
        finally {
            try{
                if(null != bufferedReader){
                    bufferedReader.close();
                }

                if(null != fileReader){
                    fileReader.close();
                }
            }catch (Exception exception){

            }
        }

        return nullStr;
    }
    */
}
