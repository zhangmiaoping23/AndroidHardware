package com.example.hardware.Util;

import android.content.Context;
import android.provider.Settings;

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
}
