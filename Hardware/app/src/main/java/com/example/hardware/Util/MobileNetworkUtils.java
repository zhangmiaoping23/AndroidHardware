package com.example.hardware.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.InetSocketAddress;

/**
 * Created by zhangmp on 2019/7/18.
 */

public class MobileNetworkUtils {
    public static boolean isWap() {
        boolean ret = !TextUtils.isEmpty(Proxy.getDefaultHost()) ? true : false;
        return ret;
    }

    public static boolean isMobileNetwork(Context context) {
        boolean ret =false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo) {
                int type = networkInfo.getType();
                if (ConnectivityManager.TYPE_MOBILE == type) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public static String getNetworkType(Context context){
        StringBuilder stringBuilder = new StringBuilder();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null != connectivityManager){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(null != networkInfo){
                int type = networkInfo.getType();
                 if(ConnectivityManager.TYPE_MOBILE == type)
                {
                    stringBuilder.append("typeName=");
                    String typeName = networkInfo.getTypeName();
                    stringBuilder.append(typeName);
                    stringBuilder.append(";");

                    stringBuilder.append("subTypeName=");
                    String subtypeName = networkInfo.getSubtypeName();
                    stringBuilder.append(subtypeName);
                    stringBuilder.append(";");

                    // TD-SCDMA   networkType is 17
                    int subtype = networkInfo.getSubtype();
                    stringBuilder.append("subType=");
                    stringBuilder.append(subtype);
                    stringBuilder.append("_");
                    switch (subtype) {
                        //1,2,4,7,11
                        //1移动2G，联通2G
                        //2 移动2G
                        //4,7,11 电信2G
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                            stringBuilder.append("2G");
                            break;

                        //3,5,6,8,9,10,12,14,15
                        //3,8 联通3G
                        //5,6,12,14 电信3G
                        //10,15 移动3G
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                        case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                        case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                            stringBuilder.append("3G") ;
                            break;
                        //13 移动4G,联通4G，电信4G
                        case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                            stringBuilder.append("4G");
                            break;
                        default:
                            // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                            if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000"))
                            {
                                stringBuilder.append("3G");
                            }
                            else
                            {
                                stringBuilder.append(subtypeName);
                            }
                            break;
                    }
                    stringBuilder.append(";");

                    stringBuilder.append("defaultHost=");
                    stringBuilder.append(Proxy.getDefaultHost());
                    stringBuilder.append(";");

                    stringBuilder.append("defaultPort=");
                    stringBuilder.append(Proxy.getDefaultPort());
                    stringBuilder.append(";");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String getInfo(Context context){
        String logInfo = "";
        boolean isMobileNetwork = isMobileNetwork(context);
        if(isMobileNetwork) {
            logInfo = LogUtils.record(logInfo, "");

            String networkType = getNetworkType(context);

            logInfo = LogUtils.record(logInfo,String.format("networkType=\"%s\"", networkType));

            boolean isWap = isWap();
            logInfo = LogUtils.record(logInfo,String.format("isWap=%s", isWap));
        }

        return logInfo;
    }
}
