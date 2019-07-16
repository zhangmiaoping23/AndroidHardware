package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/6/13.
 */

public class Qiku360HardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("360")){
            logInfo = LogUtils.record(logInfo,"");

            String systemPropUIVersion = getSystemPropUIVersion();
            logInfo = LogUtils.record(logInfo,String.format("360 systemPropUIVersion=%s",systemPropUIVersion));

        }
        return  logInfo;
    }

    public static String getSystemPropUIVersion() {
        String systemPropUIVersion = "";
        systemPropUIVersion = SystemPropertiesUtils.getString("ro.build.uiversion", "");
        return systemPropUIVersion;
    }

}
