package com.example.hardware.Util;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.internal.util.HexDump;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;

/**
 * Created by zhangmp on 2018/4/25.
 */

public class HardwareUtils {
    public static String getSystemUserAgent(){
        return System.getProperty("http.agent");
    }

    public static String getWebkitUserAgent(Context context){
        String userAgent = "";
        WebView webView = new WebView(context);
        WebSettings webSettings = webView.getSettings();
        userAgent = webSettings.getUserAgentString();
        return userAgent;
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

    public static String getMacAddressFromReflectNetworkInterface(String splitString) {
        String ret = "";
        Object hardwareAddress;
        NetworkInterface networkInterface;
        byte[] bytesHardwareAddress;
        try {
            Enumeration<NetworkInterface> enumrationNetworkInterface = NetworkInterface.getNetworkInterfaces();
            if(enumrationNetworkInterface != null) {
                while(enumrationNetworkInterface.hasMoreElements()){
                    networkInterface = enumrationNetworkInterface.nextElement();
                    if(!(networkInterface.getName().equalsIgnoreCase("wlan0"))) {
                        continue;
                    }
                    Method getHardwareAddressMethod = Class.forName("java.net.NetworkInterface").getMethod("getHardwareAddress");
                    hardwareAddress = getHardwareAddressMethod.invoke(networkInterface);
                    if((hardwareAddress instanceof byte[])) {
                        bytesHardwareAddress = (byte[])hardwareAddress;
                        StringBuffer stringBuffer = new StringBuffer();
                        int index;
                        for(index = 0; index < bytesHardwareAddress.length - 1; ++index) {
                            stringBuffer.append(HexDump.toHexString(bytesHardwareAddress[index]).toUpperCase());
                            stringBuffer.append(splitString);
                        }

                        stringBuffer.append(HexDump.toHexString(bytesHardwareAddress[bytesHardwareAddress.length - 1]).toUpperCase());
                        ret = stringBuffer.toString();
                        break;
                    }
                }
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }


    public static String getBuildBoard(){
        String buildBoard = Build.BOARD;
        return buildBoard;
    }

    public static String getSystemPropBoardPlatform(){
        String ret = SystemPropertiesUtils.getString("ro.board.platform","");
        return ret;
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
        else{
            ret = getSupportAbis(Build.CPU_ABI,Build.CPU_ABI2);
        }
        return ret;
    }
    private static String[] getSupportAbis(String cpuAbi,String cpuAbi2){
        String[] ret;
        if(!TextUtils.isEmpty(cpuAbi2)) {
            if (!"unknown".equals(cpuAbi2)) {
                ret = new String[]{cpuAbi, cpuAbi2};
            }else{
                ret = new String[]{cpuAbi};
            }
        }
        else{
            ret = new String[]{cpuAbi};
        }

        return ret;
    }

    public static String getProductCpuAbilistFromProp(){
        String ret = SystemPropertiesUtils.getString("ro.product.cpu.abilist","");
        return ret;
    }

    public static String[] getProductCpuAbisFromProp(){
        String[] ret;
        String cpuAbi = SystemPropertiesUtils.getString("ro.product.cpu.abi","");
        String cpuAbi2 = SystemPropertiesUtils.getString("ro.product.cpu.abi2","");
        ret = getSupportAbis(cpuAbi,cpuAbi2);
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

    public static String getSystemPropDisplay(){
        String systemPropDisplay = SystemPropertiesUtils.getString("ro.build.display.id","");
        return systemPropDisplay;
    }

    public static String getBuildFingerPrint(){
        String buildFingerPrint = Build.FINGERPRINT;
        return buildFingerPrint;
    }

    public static String getSystemPropFingerPrint(){
        String fingerPrint = SystemPropertiesUtils.getString("ro.build.fingerprint","");
        return fingerPrint;
    }

    public static String getBuildRadioVersion(){
        String buildRadioVersion = "";
        try {
            //buildRadioVersion = Build.getRadioVersion();
            Class buildClass = Class.forName("android.os.Build");
            buildRadioVersion = (String)buildClass.getMethod("getRadioVersion").invoke(buildClass);
        }
        catch(Throwable throwable) {
            buildRadioVersion = Build.RADIO;
        }
        return buildRadioVersion;
    }

    public static String getSystemPropGsmVersionBaseband(){
        String gsmVersionBaseband = SystemPropertiesUtils.getString("gsm.version.baseband","");
        return gsmVersionBaseband;

    }

    public static String getBuildSerial(Context context){
        String ret = "";
        if(Build.VERSION.SDK_INT >= 26) {
            boolean hasPermission = PermissionUtils.hasPermission(context,"android.permission.READ_PHONE_STATE");
            if(hasPermission){
                try {
                    String buildClassName = "android.os.Build";
                    Method getSerialMethod = Class.forName(buildClassName).getMethod("getSerial");
                    if(getSerialMethod != null) {
                        getSerialMethod.setAccessible(true);
                        ret = (String) getSerialMethod.invoke(null);
                    }
                }
                catch(Exception exception) {
                    ret = "";
                }
            }
        }else{
            //Android8.0(26),Android9.0(28)此接口也正常
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

    public static String getSystemPropManufacturer(){
         String manufacturer = SystemPropertiesUtils.getString("ro.product.manufacturer","");
        return manufacturer;
    }

    public static String getBuildModel(){
        String buildModel =Build.MODEL;
        return buildModel;
    }

    public static String getBuildProduct(){
        String buildProduct = Build.PRODUCT;
        return buildProduct;
    }

    public static String getSystemPropProductName(){
        String systemPropProductName = SystemPropertiesUtils.getString("ro.product.name","");
        return systemPropProductName;
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
        String deviceId = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            //deviceId = Build.VERSION.SDK_INT >= 21 ? telephonyManager.getImei(0) : telephonyManager.getDeviceId();
            deviceId = telephonyManager.getDeviceId();
            //ESN_PATTERN           Pattern.compile("^([0-9a-fA-F]{8})$"),且不以"80"开头，"80"开头的是伪造的
            //DEVICE_ID_PATTERN     Pattern.compile("^(([0-9]{15})|([0-9a-fA-F]{14}))$")
        }
        return deviceId;
    }

    public static String getImsi(Context context){
        return SimCardUtils.getImsi(context);//460015050112949
    }

    public static String getSimSerialNumber(Context context) {
        return SimCardUtils.getSimSerialNumber(context);//89860117838006905255
    }

    public static String getTimeZoneId(){
        String timeZoneId = TimeZone.getDefault().getID();
        return timeZoneId;
    }

    public static String getTimeZoneByCalendar(){
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ZZZZ");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
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
    public static String getInfo(Context context){
        String logInfo = "";
        logInfo = getBuildInfo(context);
        String imei = HardwareUtils.getImei(context);
        logInfo = LogUtils.record(logInfo,String.format("telephonyManagerImei=%s",imei));

        String imsi = HardwareUtils.getImsi(context);
        logInfo = LogUtils.record(logInfo,String.format("telephonyManagerImsi subscriberId=%s",imsi));

        String simSerialNumber = HardwareUtils.getSimSerialNumber(context);
        logInfo = LogUtils.record(logInfo,String.format("telephonyManager simSerialNumber=%s",simSerialNumber));

        String androidId = HardwareUtils.getAndroidId(context,HardwareUtils.SETTINGS_SYSTEM);
        logInfo = LogUtils.record(logInfo,String.format("Settings.System.androidId=%s",androidId));

        androidId = HardwareUtils.getAndroidId(context,HardwareUtils.SETTINGS_SECURE);
        logInfo = LogUtils.record(logInfo,String.format("Settings.Secure.androidId=%s",androidId));

        String timeZoneId = HardwareUtils.getTimeZoneId();
        logInfo = LogUtils.record(logInfo,String.format("timeZoneId=%s",timeZoneId));

        String timeZoneByCalendar = HardwareUtils.getTimeZoneByCalendar();
        logInfo = LogUtils.record(logInfo,String.format("timeZoneByCalendar=%s",timeZoneByCalendar));

        String userAgent = HardwareUtils.getSystemUserAgent();
        logInfo = LogUtils.record(logInfo,String.format("userAgent=%s",userAgent));

        String webkitUserAgent = HardwareUtils.getWebkitUserAgent(context);
        logInfo = LogUtils.record(logInfo,String.format("webkitUserAgent=%s",webkitUserAgent));

        String hostAddress = HardwareUtils.getHostAddress();
        logInfo = LogUtils.record(logInfo,String.format("ip=%s",hostAddress));

        String macAddressFromNetworkInterface = HardwareUtils.getMacAddress(hostAddress);
        logInfo = LogUtils.record(logInfo,String.format("macAddressFromNetworkInterface=%s",macAddressFromNetworkInterface));

        String macAddressFromReflectNetworkInterface = HardwareUtils.getMacAddressFromReflectNetworkInterface("-");
        logInfo = LogUtils.record(logInfo,String.format("macAddressFromReflectNetworkInterface=%s",macAddressFromReflectNetworkInterface));
        return logInfo;
    }

    /*
    例子一：
        I/AndroidHardware: buildBoard=full_oppo6755_15111
        I/AndroidHardware: buildBootloader=unknown
        I/AndroidHardware: buildBrand=OPPO
        I/AndroidHardware: buildCpuApi=arm64-v8a
        I/AndroidHardware: buildCpuApi2=
        I/AndroidHardware: SupportAbi0=arm64-v8a
        I/AndroidHardware: SupportAbi1=armeabi-v7a
        I/AndroidHardware: SupportAbi2=armeabi
        I/AndroidHardware: buildDevice=R9
        I/AndroidHardware: buildDisplay=R9m_11_A.13_160314
        I/AndroidHardware: buildFingerPrint=OPPO/R9m/R9:5.1/LMY47I/1449641681:user/release-keys
        I/AndroidHardware: buildRadioVersion=MOLY.LR11.W1539.MD.MP.V9.P36.T36, 2016/03/12 23:22
        I/AndroidHardware: buildSerial=IRFMW8CMLFFASGGY
        I/AndroidHardware: buildHardware=mt6755
        I/AndroidHardware: buildHost=ubuntu-121-109
        I/AndroidHardware: buildId=LMY47I
        I/AndroidHardware: buildManufacturer=OPPO
        I/AndroidHardware: buildModel=OPPO R9m
        I/AndroidHardware: buildProduct=R9m
        I/AndroidHardware: buildTags=dev-keys
        I/AndroidHardware: buildTime=1457895750000
        I/AndroidHardware: buildType=user
        I/AndroidHardware: buildUser=root
        I/AndroidHardware: buildVersionBaseOS=OPPO/R9m/R9:5.1/LMY47I/1449641681:user/release-keys
        I/AndroidHardware: buildVersionCodeName=REL
        I/AndroidHardware: buildVersionIncremental=1457895608
        I/AndroidHardware: buildVersionRelease=5.1
        I/AndroidHardware: buildVersionSecurityPatch=2016-01-01
        I/AndroidHardware: buildVersionSDK=22
        I/AndroidHardware: firmwareVersion=5.1
     */
    public static String  getBuildInfo(Context context){
        String logInfo = "";
        String buildBoard = HardwareUtils.getBuildBoard();
        logInfo = LogUtils.record(logInfo,String.format("buildBoard=%s",buildBoard));

        String systemPropBoardPlatform = HardwareUtils.getSystemPropBoardPlatform();
        logInfo = LogUtils.record(logInfo,String.format("systemPropBoardPlatform=%s",systemPropBoardPlatform));

        String buildBootloader = HardwareUtils.getBuildBootLoader();
        logInfo = LogUtils.record(logInfo,String.format("buildBootloader=%s",buildBootloader));

        String buildBrand = HardwareUtils.getBuildBrand();
        logInfo = LogUtils.record(logInfo,String.format("buildBrand=%s",buildBrand));

        String buildCpuApi = HardwareUtils.getbuildCpuApi();
        logInfo = LogUtils.record(logInfo,String.format("buildCpuApi=%s",buildCpuApi));

        String buildCpuApi2 = HardwareUtils.getbuildCpuApi2();
        logInfo = LogUtils.record(logInfo,String.format("buildCpuApi2=%s",buildCpuApi2));

        String productCpuAbilist = HardwareUtils.getProductCpuAbilistFromProp();
        logInfo = LogUtils.record(logInfo,String.format("productCpuAbilist=%s",productCpuAbilist));

        String[] productCpuAbisFromProp = HardwareUtils.getProductCpuAbisFromProp();
        for(int index = 0; index < productCpuAbisFromProp.length;index ++){
            logInfo = LogUtils.record(logInfo,String.format("productCpuAbisFromProp%d=%s",index,productCpuAbisFromProp[index]));
        }

        String[] supportedApis = HardwareUtils.getBuildSupportAbis();
        for(int index = 0; index < supportedApis.length;index ++){
            logInfo = LogUtils.record(logInfo,String.format("SupportAbi%d=%s",index,supportedApis[index]));
        }

        String buildDevice = HardwareUtils.getBuildDevice();
        logInfo = LogUtils.record(logInfo,String.format("buildDevice=%s",buildDevice));

        String buildDisplay = HardwareUtils.getBuildDisplay();
        logInfo = LogUtils.record(logInfo,String.format("buildDisplay=%s",buildDisplay));

        String systemPropDisplay = HardwareUtils.getSystemPropDisplay();
        logInfo = LogUtils.record(logInfo,String.format("systemPropDisplay=%s",systemPropDisplay));

        String buildFingerPrint = HardwareUtils.getBuildFingerPrint();
        logInfo = LogUtils.record(logInfo,String.format("buildFingerPrint=%s",buildFingerPrint));

        String systemPropFingerPrint = HardwareUtils.getSystemPropFingerPrint();
        logInfo = LogUtils.record(logInfo,String.format("systemPropFingerPrint=%s",systemPropFingerPrint));

        String buildRadioVersion = HardwareUtils.getBuildRadioVersion();
        logInfo = LogUtils.record(logInfo,String.format("buildRadioVersion=%s",buildRadioVersion));

        String systemPropGsmVersionBaseband = HardwareUtils.getSystemPropGsmVersionBaseband();
        logInfo = LogUtils.record(logInfo,String.format("systemPropGsmVersionBaseband=%s",systemPropGsmVersionBaseband));


        String buildSerial = HardwareUtils.getBuildSerial(context);
        logInfo = LogUtils.record(logInfo,String.format("buildSerial=%s",buildSerial));

        String buildHardware = HardwareUtils.getBuildHardware();
        logInfo = LogUtils.record(logInfo,String.format("buildHardware=%s",buildHardware));

        String buildHost = HardwareUtils.getBuildHost();
        logInfo = LogUtils.record(logInfo,String.format("buildHost=%s",buildHost));

        String buildId = HardwareUtils.getBuildId();
        logInfo = LogUtils.record(logInfo,String.format("buildId=%s",buildId));

        String buildManufacturer = HardwareUtils.getBuildManufacturer();
        logInfo = LogUtils.record(logInfo,String.format("buildManufacturer=%s",buildManufacturer));

        String systemPropManufacturer = HardwareUtils.getSystemPropManufacturer();
        logInfo = LogUtils.record(logInfo,String.format("systemPropManufacturer=%s",systemPropManufacturer));

        String buildModel = HardwareUtils.getBuildModel();
        logInfo = LogUtils.record(logInfo,String.format("buildModel=%s",buildModel));

        String buildProduct = HardwareUtils.getBuildProduct();
        logInfo = LogUtils.record(logInfo,String.format("buildProduct=%s",buildProduct));

        String systemPropProductName = HardwareUtils.getSystemPropProductName();
        logInfo = LogUtils.record(logInfo,String.format("systemPropProductName=%s",systemPropProductName));

        String buildTags = HardwareUtils.getBuildTags();
        logInfo = LogUtils.record(logInfo,String.format("buildTags=%s",buildTags));

        String buildTime = HardwareUtils.getBuildTime();
        logInfo = LogUtils.record(logInfo,String.format("buildTime=%s",buildTime));

        String buildType = HardwareUtils.getBuildType();
        logInfo = LogUtils.record(logInfo,String.format("buildType=%s",buildType));

        String buildUser = HardwareUtils.getBuildUser();
        logInfo = LogUtils.record(logInfo,String.format("buildUser=%s",buildUser));

        String buildVersionBaseOS = HardwareUtils.getBuildVersionBaseOS();
        logInfo = LogUtils.record(logInfo,String.format("buildVersionBaseOS=%s",buildVersionBaseOS));

        String buildVersionCodeName = HardwareUtils.getBuildVersionCodeName();
        logInfo = LogUtils.record(logInfo,String.format("buildVersionCodeName=%s",buildVersionCodeName));

        String buildVersionIncremental = HardwareUtils.getBuildVersionIncremental();
        logInfo = LogUtils.record(logInfo,String.format("buildVersionIncremental=%s",buildVersionIncremental));

        String buildVersionRelease = HardwareUtils.getBuildVersionRelease();
        logInfo = LogUtils.record(logInfo,String.format("buildVersionRelease=%s",buildVersionRelease));

        String buildVersionSecurityPatch = HardwareUtils.getBuildVersionSecurityPatch();
        logInfo = LogUtils.record(logInfo,String.format("buildVersionSecurityPatch=%s",buildVersionSecurityPatch));

        String buildVersionSDK = HardwareUtils.getBuildVersionSDK();
        logInfo = LogUtils.record(logInfo,String.format("buildVersionSDK=%s",buildVersionSDK));

        String firmwareVersion = HardwareUtils.getFirmwareVersion();
        logInfo = LogUtils.record(logInfo,String.format("firmwareVersion=%s",firmwareVersion));
        return logInfo;
    }

}
