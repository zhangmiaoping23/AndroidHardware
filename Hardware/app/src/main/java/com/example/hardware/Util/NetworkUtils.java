package com.example.hardware.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.regex.Pattern;

/**
 * Created by zhangmp on 2019/7/18.
 */

public class NetworkUtils {
    public static boolean IsAirModeOn(Context context) {
        boolean ret = true;
        if(Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 1) {
            ret = false;
        }

        return ret;
    }

    public static String getEthernetMacAddress(Context context){
        String ret = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            //wifi,此处返回null
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
            if (null != networkInfo) {
                String extraInfo = networkInfo.getExtraInfo();
                Pattern macAddrPattern = Pattern.compile("^([0-9a-fA-F][0-9a-fA-F][:-]?){5}[0-9a-fA-F][0-9a-fA-F]$");
                if(extraInfo != null && (macAddrPattern.matcher(extraInfo).matches())) {
                    ret = extraInfo;
                }
            }
        }
        return ret;
    }

    public static String getRoming(Context context){
        String ret = "";
        String roamingPrefix = "";
        String romingPostfix = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            //wifi,此处返回null
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            //在wifi网络上，此处有值
            // [type: MOBILE[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: (none), roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]

            //在wifi网络与移动网络同时开启的情况,此处有值
            // [type: MOBILE[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: (none), roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]

            //单独开移动网络的情况，此处有值
            // [type: MOBILE[CDMA - eHRPD], state: CONNECTED/CONNECTED, reason: connected, extra: ctnet, roaming: false, failover: false, isAvailable: true, isConnectedToProvisioningNetwork: false]

            if(networkInfo == null) {
                roamingPrefix = "unknown";
            }
            else if(networkInfo.getType() == 0) {
                roamingPrefix = "mobile";
            }
            else {
                roamingPrefix = "notmobile";
            }
        }

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager == null) {
            romingPostfix = "unknown";
        }
        else if(telephonyManager.isNetworkRoaming()) {
            romingPostfix = "roaming";
        }
        else {
            romingPostfix = "notroaming";
        }
        ret = roamingPrefix + "-" + romingPostfix;
        return ret;
    }

    public static String getInfo(Context context) {
        String logInfo = "";
        String ethernetMacAddress = NetworkUtils.getEthernetMacAddress(context);
        logInfo = LogUtils.record(logInfo, String.format("ethernetMacAddress=%s", ethernetMacAddress));

        String roaming = NetworkUtils.getRoming(context);
        logInfo = LogUtils.record(logInfo,String.format("roaming=%s", roaming));

        return logInfo;
    }
}
