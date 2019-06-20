package com.example.hardware.rootdetect;

import android.os.Build;

/**
 * Created by zhangmp on 2019/6/20.
 */

public class EmulatorDetectUtils {

    public static boolean checkEmulator() {
        boolean isEmulator = false;
        try {
            if(Build.HARDWARE.contains("goldfish")) {
                isEmulator = true;
            }
            else if(Build.FINGERPRINT.contains("generic")) {
                isEmulator = true;
            }
            else if(Build.MODEL.contains("google_sdk")) {
                isEmulator = true;
            }
            else if(Build.MODEL.contains("Emulator")) {
                isEmulator = true;
            }
            else if(Build.MODEL.contains("Android SDK built for x86")) {
                isEmulator = true;
            }
            else if(Build.MODEL.contains("Android SDK built for arm64")) {
                isEmulator = true;
            }
            else if("google_sdk".equals(Build.PRODUCT)) {
                isEmulator = true;
            }else{
                isEmulator = false;
            }

        }
        catch(Exception exception) {

        }
        return isEmulator;
    }
}
