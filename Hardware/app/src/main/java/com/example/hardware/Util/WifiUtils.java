package com.example.hardware.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by zhangmp on 2018/8/3.
 */

public class WifiUtils {

    public static String getConnectWifiIP(Context context){
        String ip = "";
        try {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            //SSID: TP-LINK_114, BSSID: f4:83:cd:93:eb:17, MAC: c8:f2:30:56:d9:3e, Supplicant state: COMPLETED, RSSI: -41, Link speed: 65Mbps, Frequency: 2437MHz, Net ID: 5, Metered hint: false, score: 79
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo != null) {
                ip = intToIp(wifiInfo.getIpAddress());
            }
        }
        catch(Exception v1) {

        }

        return ip;
    }

    public static String intToIp(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    public static String getConnectWifiMacAddress(Context context){
        String macAddress = "";
        try {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo != null && !TextUtils.isEmpty(wifiInfo.getMacAddress())) {
                //在Android 6.0系统上，获取的就有问题，返回的是“02:00:00:00:00:00”
                //问题分析 :
                //原来谷歌官方为了给用户更多的数据保护，从这个6.0版本开始， Android 移除了通过 WiFi 和蓝牙 API 来在应用程序中可编程的访问本地硬件标示符。
                //现在 WifiInfo.getMacAddress() 和 BluetoothAdapter.getAddress() 方法都将返回 02:00:00:00:00:00
                //HardwareUtils.getMacAddressFromReflectNetworkInterface()采用这问题就是返回正确的
                //if(!wifiInfo.getMacAddress().equals("02:00:00:00:00:00")){
                    macAddress = wifiInfo.getMacAddress();
                //}else{
                    //macAddress = HardwareUtils.getMacAddressFromReflectNetworkInterface();
                //}

            }
        }
        catch(Exception exception) {

        }

        return macAddress;
    }

    public static String getConnectWifiBSSID(Context context){
        String bssid = "";
        try {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo != null) {
                bssid = wifiInfo.getBSSID();
            }
        }
        catch(Exception v1) {

        }

        return bssid;
    }

    public static String getConnectWifiSSID(Context context){
        String ssid = "";
        try {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo != null) {
                ssid = wifiInfo.getSSID();
            }
        }
        catch(Exception v1) {

        }

        return ssid;
    }

    public static boolean isWifiNetwork(Context context) {
        boolean ret = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo) {
                int type = networkInfo.getType();
                if (ConnectivityManager.TYPE_WIFI == type) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public static String getInfo(Context context) {
        String logInfo = "";
        boolean isWifiNetwork = isWifiNetwork(context);
        if(isWifiNetwork){
            logInfo = LogUtils.record(logInfo,"");

            String connectWifiIP = WifiUtils.getConnectWifiIP(context);
            logInfo = LogUtils.record(logInfo,String.format("connectWifiIP=%s", connectWifiIP));

            //getMacAddress()是本机无线网卡的MAC地址
            String connectWifiMacAddress = WifiUtils.getConnectWifiMacAddress(context);
            logInfo = LogUtils.record(logInfo,String.format("connectWifiMacAddress=%s", connectWifiMacAddress));

            String macAddressFromReflectNetworkInterfaceLower = HardwareUtils.getMacAddressFromReflectNetworkInterface(":").toLowerCase();
            logInfo = LogUtils.record(logInfo,String.format("macAddressFromReflectNetworkInterfaceLower=%s", macAddressFromReflectNetworkInterfaceLower));

            //getBSSID()是路由器WIFI的MAC地址
            String connectWifiBSSID = WifiUtils.getConnectWifiBSSID(context);
            logInfo = LogUtils.record(logInfo,String.format("connectWifiBSSID=%s", connectWifiBSSID));

            //Service Set Identifier
            String connectWifiSSID = WifiUtils.getConnectWifiSSID(context);
            logInfo = LogUtils.record(logInfo,String.format("connectWifiSSID=%s", connectWifiSSID));

            String proxyDefaultHost = WifiUtils.getProxyDefaultHost();
            logInfo = LogUtils.record(logInfo,String.format("proxyDefaultHost=%s", proxyDefaultHost));

            int proxyDefaultPort = WifiUtils.getProxyDefaultPort();
            logInfo = LogUtils.record(logInfo,String.format("proxyDefaultPort=%d", proxyDefaultPort));

            String proxyHost = WifiUtils.getProxyHost(context);
            logInfo = LogUtils.record(logInfo,String.format("proxyHost=%s", proxyHost));

            int proxyPort = WifiUtils.getProxyPort(context);
            logInfo = LogUtils.record(logInfo,String.format("proxyPort=%d", proxyPort));

            String systemProxyHost = WifiUtils.getSystemProxyHost();
            logInfo = LogUtils.record(logInfo,String.format("systemProxyHost=%s", systemProxyHost));

            int systemProxyPort = WifiUtils.getSystemProxyPort();
            logInfo = LogUtils.record(logInfo,String.format("systemProxyPort=%d", systemProxyPort));
        }

        return logInfo;
    }

    public static String getProxyDefaultHost(){
        return Proxy.getDefaultHost();
    }

    public static int getProxyDefaultPort(){
        return Proxy.getDefaultPort();
    }

    public static String getProxyHost(Context context){
        return Proxy.getHost(context);
    }

    public static int getProxyPort(Context context){
        return Proxy.getPort(context);
    }

    public static String getSystemProxyHost(){
        return System.getProperty("http.proxyHost");
    }

    public static int getSystemProxyPort(){
        int port = -1;
        String proxyPort = System.getProperty("http.proxyPort");
        if(proxyPort != null){
            if(false == proxyPort.isEmpty()){
                port = Integer.parseInt(proxyPort);
            }
        }

        return  port;
    }
}
