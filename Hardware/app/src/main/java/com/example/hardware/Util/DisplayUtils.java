package com.example.hardware.Util;

import android.content.Context;
import android.content.res.Resources;
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

    public static DisplayMetrics getWindowManagerDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = null;
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            if(display != null) {
                displayMetrics = new DisplayMetrics();
                display.getRealMetrics(displayMetrics);
            }
        }

        return displayMetrics;
    }

    public static DisplayMetrics getResourcesDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = null;
        Resources resources = context.getResources();
        resources.getConfiguration();
        if(null != resources){
            displayMetrics = resources.getDisplayMetrics();
        }
       return displayMetrics;
    }


    public static String getInfo(Context context){
        String logInfo = "";
        String screenSize = DisplayUtils.getScreenSize(context);
        logInfo = LogUtils.record(logInfo,String.format("screenSize=%s",screenSize));

        DisplayMetrics displayMetrics = DisplayUtils.getResourcesDisplayMetrics(context);
        if(null != displayMetrics){
            logInfo = LogUtils.record(logInfo,String.format("resourcesDisplayMetrics=%s",displayMetrics.toString()));
        }

        displayMetrics = DisplayUtils.getWindowManagerDisplayMetrics(context);
        if(null != displayMetrics){
            logInfo = LogUtils.record(logInfo,String.format("windowManagerDisplayMetrics=%s",displayMetrics.toString()));
        }
        return logInfo;
    }
}
