package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/6/13.
 */

public class SmartisanHardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("smartisan")){
            logInfo = LogUtils.record(logInfo,"");

            String systemPropSmartisanVersion = getSystemPropSmartisanVersion();
            logInfo = LogUtils.record(logInfo,String.format("smartisan systemPropUIVersion=%s",systemPropSmartisanVersion));

        }
        return  logInfo;
    }

    public static String getSystemPropSmartisanVersion() {
        String systemPropSmartisanVersion = "";
        systemPropSmartisanVersion = SystemPropertiesUtils.getString("ro.smartisan.version", "");
        return systemPropSmartisanVersion;
    }

}
