package com.example.hardware.Util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by zhangmp on 2018/8/3.
 */

public class DisplayUtils {
    public static String getScreenSize(Context context) {
        String screenSize = "";
        try {
            WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            if(Build.VERSION.SDK_INT > 13) {
                Point point = new Point();
                display.getSize(point);
                screenSize = point.x + "_" + point.y;
            }
            else {
                screenSize = display.getWidth() + "_" + display.getHeight();
            }
        }
        catch(Exception v0) {
        }

        return screenSize;
    }

    public static String getDensityDpi(Context context) {
        String densityDpi = "";
        DisplayMetrics displayMetrics = null;
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            if(display != null) {
                displayMetrics = new DisplayMetrics();
                display.getRealMetrics(displayMetrics);
                if(displayMetrics != null) {
                    densityDpi = String.valueOf(displayMetrics.densityDpi);
                }
            }
        }

        return densityDpi;
    }

    public static String getInfo(Context context){
        String logInfo = "";
        String screenSize = DisplayUtils.getScreenSize(context);
        logInfo = LogUtils.record(logInfo,String.format("screenSize=%s",screenSize));

        String densityDpi = DisplayUtils.getDensityDpi(context);
        logInfo = LogUtils.record(logInfo,String.format("densityDpi=%s",densityDpi));
        return logInfo;
    }
}
