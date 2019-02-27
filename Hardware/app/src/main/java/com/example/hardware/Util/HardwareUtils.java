package com.example.hardware.Util;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.TimeZone;

/**
 * Created by zhangmp on 2018/4/25.
 */

public class HardwareUtils {
    public static String getSystemUserAgent(){
        return System.getProperty("http.agent");
    }

    public static final int SETTINGS_SYSTEM = 1;
    public static final int SETTINGS_SECURE = 2;
    public static String getAndroidId(Context context,int type){
        String androidId = "";
        switch (type){
            case SETTINGS_SYSTEM:
            {
                androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);//af7a416984172e3a
            }
            break;
            case SETTINGS_SECURE:
            {
                androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);//af7a416984172e3a
            }
            break;
        }

        return androidId;
    }

    public static String getHostAddress() {
        String hostAddress = "";
        InetAddress inetAddress;
        try {
            Enumeration<NetworkInterface> enumrationNetworkInterface = NetworkInterface.getNetworkInterfaces();
            while(enumrationNetworkInterface.hasMoreElements()){
                NetworkInterface networkInterface = enumrationNetworkInterface.nextElement();
                Enumeration<InetAddress> enumrationInetAddress = networkInterface.getInetAddresses();
                while(enumrationInetAddress.hasMoreElements()) {
                    inetAddress = enumrationInetAddress.nextElement();
                    if (inetAddress.isLoopbackAddress()) {
                        continue;
                    }

                    /* networkInterface.getDisplayName() == eth1
                    首先得到的是IPV6格式 fe80::a00:27ff:fe1b:f5db%eth1%3 ,可以考虑用inetAddress.getAddress()返回的字节数组长度等于16过滤掉
                    然后才是IPV4的IP
                    */

                    /*
                    在往后面
                    networkInterface.getDisplayName() == wlan0
                    首先得到的是IPV6格式 fe80::a00:27ff:fe1b:f5db%eth1%3 ,可以考虑用inetAddress.getAddress()返回的字节数组长度等于16过滤掉
                    然后才是IPV4的IP
                    */
                    if(inetAddress.getAddress().length > 4){
                        continue;
                    }
                    hostAddress = inetAddress.getHostAddress().toString();
                    break;
                }
                if(!hostAddress.isEmpty()){
                    break;
                }
            }
        }
        catch(Exception exception) {

        }

        return hostAddress;
    }
    public static String getMacAddress(String hostAddress){
        String macAddress = "";
        try {
            InetAddress inetAddress = InetAddress.getByName(hostAddress);
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
            macAddress = HexUtils.toHexString(networkInterface.getHardwareAddress());
        }
        catch(Exception v1) {
            v1.printStackTrace();
        }

        return macAddress;
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

    public static String[] getBuildSupportAbis(){
        String[] ret;
        if(Build.VERSION.SDK_INT >= 21 && Build.SUPPORTED_ABIS.length > 0) {
            ret = Build.SUPPORTED_ABIS;
        }
        else if(!TextUtils.isEmpty(Build.CPU_ABI2)) {
            ret = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        else {
            ret = new String[]{Build.CPU_ABI};
        }

        return ret;
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
        String ret = "";
        if(Build.VERSION.SDK_INT >= 26) {
            try {
                //ret = Build.getSerial();
            }
            catch(SecurityException exception) {
                ret = "";
            }
        }

        if((null == ret) || (ret.isEmpty())){
            ret = Build.SERIAL;
        }

        return ret;
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

    public static String getImsi(Context context){
        return SimCardUtils.getSubscriberId(context);//460015050112949
    }

    public static String getSimSerialNumber(Context context) {
        return SimCardUtils.getSimSerialNumber(context);//89860117838006905255
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

    public static void getInfo(Context context){
        String imei = HardwareUtils.getImei(context);
        LogUtils.i(String.format("telephonyManagerImei=%s",imei));

        String imsi = HardwareUtils.getImsi(context);
        LogUtils.i(String.format("telephonyManagerImsi subscriberId=%s",imsi));

        String simSerialNumber = HardwareUtils.getSimSerialNumber(context);
        LogUtils.i(String.format("telephonyManager simSerialNumber=%s",simSerialNumber));

        String androidId = HardwareUtils.getAndroidId(context,HardwareUtils.SETTINGS_SYSTEM);
        LogUtils.i(String.format("Settings.System.androidId=%s",androidId));

        androidId = HardwareUtils.getAndroidId(context,HardwareUtils.SETTINGS_SECURE);
        LogUtils.i(String.format("Settings.Secure.androidId=%s",androidId));

        String timeZone = HardwareUtils.getTimeZone();
        LogUtils.i(String.format("timeZone=%s",androidId));

        String userAgent = HardwareUtils.getSystemUserAgent();
        LogUtils.i(String.format("userAgent=%s",userAgent));

        String hostAddress = HardwareUtils.getHostAddress();
        LogUtils.i(String.format("ip=%s",hostAddress));

        String macAddress = HardwareUtils.getMacAddress(hostAddress);
        LogUtils.i(String.format("macAddress=%s",macAddress));

    }
}
