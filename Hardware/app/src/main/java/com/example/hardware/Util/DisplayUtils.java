package com.example.hardware.Util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.lang.reflect.Method;

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

    public static void getScreenFromDisplayMetrics(Context context){
        int orientation = context.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            currentDeviceWidth = context.getResources().getDisplayMetrics().widthPixels;
            currentDeviceHeight = context.getResources().getDisplayMetrics().heightPixels;
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            currentDeviceWidth = context.getResources().getDisplayMetrics().heightPixels;
            currentDeviceHeight = context.getResources().getDisplayMetrics().widthPixels;
        }
        else {
            int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
            currentDeviceWidth = Math.min(widthPixels, heightPixels);
            currentDeviceHeight = Math.max(widthPixels, heightPixels);
        }

        currentDensity = context.getResources().getDisplayMetrics().density;
        int maxPixels = currentDeviceWidth > currentDeviceHeight ? currentDeviceWidth : currentDeviceHeight;
        IS_HIGH_QUALITY_DEVICE = true;
        if(maxPixels < 800 || currentDensity <= 1f) {
            IS_HIGH_QUALITY_DEVICE = false;
        }

        IS_SURPORT_MUTITOUCH_GESTURER = true;
        if(getSdkInt() < 7 || !isSupportMutitouchGesturer()) {
            IS_SURPORT_MUTITOUCH_GESTURER = false;
        }
    }

    public static String getConfigurationInfo(Context context){
        ConfigurationInfo configurationInfo = ((ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE)).getDeviceConfigurationInfo();
        int reqTouchScreen = configurationInfo.reqTouchScreen;
        int reqKeyboardType = configurationInfo.reqKeyboardType;
        int reqNavigation = configurationInfo.reqNavigation;
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        int reqInputFeatures = configurationInfo.reqInputFeatures;
        String info = configurationInfo.toString();
        return info;
    }
    private static int getSdkInt() {
        int ret = 0;
        try {
            ret = Integer.valueOf(Build.VERSION.SDK).intValue();
        }
        catch(NumberFormatException exception) {
        }

        return ret;
    }

    private static boolean isSupportMutitouchGesturer() {
        boolean ret = true;
        Method[] methods = MotionEvent.class.getDeclaredMethods();
        int length = methods.length;
        int index = 0;
        int getPointerIdIsFind = 0;
        int getPointerCountIsFind = 0;
        while(index < length) {
            Method method = methods[index];
            if(method.getName().equals("getPointerCount")) {
                getPointerCountIsFind = 1;
            }

            if(method.getName().equals("getPointerId")) {
                getPointerIdIsFind = 1;
            }

            ++index;
        }

        if(getSdkInt() < 7 && (getPointerCountIsFind == 0 || getPointerIdIsFind == 0)) {
            ret = false;
        }

        return ret;
    }


    public static String getInfo(Context context){
        getScreenFromDisplayMetrics(context);

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

        String configurationInfo = DisplayUtils.getConfigurationInfo(context);
        if(null != configurationInfo){
            logInfo = LogUtils.record(logInfo,String.format("configurationInfo=%s",configurationInfo));
        }
        return logInfo;
    }

    public static float currentDensity = 0;
    public static int currentDeviceWidth = 0;
    public static int currentDeviceHeight = 0;
    public static boolean IS_HIGH_QUALITY_DEVICE = true;
    public static boolean IS_SURPORT_MUTITOUCH_GESTURER = true;

}
