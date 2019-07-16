package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/6/13.
 */

public class LgHardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("lge")){
            logInfo = LogUtils.record(logInfo,"");

            String systemProplgUiVersion = getSystemPropLgUiVersion();
            logInfo = LogUtils.record(logInfo,String.format("Lg systemProplgUiVersion=%s",systemProplgUiVersion));

        }
        return  logInfo;
    }

    public static String getSystemPropLgUiVersion() {
        String systemProplgUiVersion = "";
        systemProplgUiVersion = SystemPropertiesUtils.getString("ro.lge.lguiversion", "");
        return systemProplgUiVersion;
    }

}
