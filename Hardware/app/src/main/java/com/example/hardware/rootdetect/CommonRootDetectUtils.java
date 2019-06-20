package com.example.hardware.rootdetect;

import android.os.Build;

import com.example.hardware.Util.SystemPropertiesUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by zhangmp on 2019/6/20.
 */

public class CommonRootDetectUtils {
    public static boolean checkBuildTags() {
        boolean isRoot = false;
        String buildTags = Build.TAGS;
        if(buildTags != null && (buildTags.contains("test-keys"))) {
            isRoot = true;
        }

        return isRoot;
    }

    public static boolean checkSecureProperty() {
        boolean isRoot = false;
        //ro.debuggable的值应该是0，表示不可调试
        //ro.secure表示root权限，如果为0则表示启用root权限，1则相反
        //ro.adb.secure表示adb的root权限，同样0表示启用adb的root权限，1则相反。
        String secureProp = SystemPropertiesUtils.getString("ro.secure","");
        isRoot = "0".equals(secureProp);
        return isRoot;
    }

    public static boolean checkSu() {
        return checkForBinary("su");
    }

    public static boolean checkMagisk() {
        return checkForBinary("magisk");
    }

    private static ArrayList<String> defaultDirs = new ArrayList<String>();
    private static synchronized void syncInitSuDir(){
        if(defaultDirs.isEmpty()){
            defaultDirs.add("/sbin");
            defaultDirs.add("/vendor/bin");
            defaultDirs.add("/system/sbin");
            defaultDirs.add("/system/bin");
            defaultDirs.add("/system/xbin");
            defaultDirs.add("/system/bin/.ext");
            defaultDirs.add("/system/sd/xbin");
            defaultDirs.add("/system/usr/we-need-root");
            defaultDirs.add("/cache");
            defaultDirs.add("/data");
            defaultDirs.add("/dev");
            defaultDirs.add("/system/vendor/bin");
            defaultDirs.add("/vendor/xbin");
            defaultDirs.add("/system/vendor/xbin");
            defaultDirs.add("/product/bin");
            defaultDirs.add("/product/xbin");
            defaultDirs.add("/data/local/tmp");
            defaultDirs.add("/data/local/bin");
            defaultDirs.add("/data/local/xbin");
            defaultDirs.add("/data/local");
            defaultDirs.add("/system/bin/failsafe");
        }
    }

    private static synchronized boolean checkForBinary(String suFileName) {
       boolean isRoot = false;
        if(defaultDirs.isEmpty()){
            syncInitSuDir();
        }
        int size = defaultDirs.size();
        for(int index = 0; index < size; ++index) {
            if(new File(new StringBuilder().append(defaultDirs.get(index)).append(File.separator).append(suFileName).toString()).exists()) {
                isRoot = true;
            }
        }

        return isRoot;
    }
}
