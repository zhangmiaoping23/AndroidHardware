package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/6/13.
 */

public class NubiaHardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("nubia")){
            logInfo = LogUtils.record(logInfo,"");

            String systemPropRomInternalId = getSystemPropRomInternalId();
            logInfo = LogUtils.record(logInfo,String.format("nubia systemPropRomInternalId=%s",systemPropRomInternalId));

        }
        return  logInfo;
    }

    public static String getSystemPropRomInternalId() {
        String systemPropRomInternalId = "";
        systemPropRomInternalId = SystemPropertiesUtils.getString("ro.build.rom.internal.id", "");
        return systemPropRomInternalId;
    }

}
