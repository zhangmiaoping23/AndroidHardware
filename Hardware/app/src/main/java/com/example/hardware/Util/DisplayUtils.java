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

    public static int getDensityDpi(Context context) {
        int densityDpi = 0;
        try {
            WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            densityDpi = displayMetrics.densityDpi;
        }
        catch(Exception v0) {
        }

        return densityDpi;
    }
}
