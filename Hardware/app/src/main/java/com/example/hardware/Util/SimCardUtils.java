package com.example.hardware.Util;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInput;
import java.io.Reader;

/**
 * Created by zhangmp on 2018/5/31.
 */

public class SimCardUtils {
    public static String getOperator(Context argContext) {
        String networkOperatorName;
        try {
            TelephonyManager telephonyManager = (TelephonyManager)argContext.getSystemService(Context.TELEPHONY_SERVICE);
            networkOperatorName = telephonyManager.getNetworkOperatorName();
        }
        catch(Exception exception) {
            networkOperatorName = "";
        }

        return networkOperatorName;
    }

    public static String getSimSerialNumber(Context context) {
        String simSerialNumber = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simSerialNumber = telephonyManager.getSimSerialNumber();
        }
        return simSerialNumber;
    }
}
