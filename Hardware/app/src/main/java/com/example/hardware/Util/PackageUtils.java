package com.example.hardware.Util;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by zhangmp on 2019/9/3.
 */

public class PackageUtils {
    public static ArrayList<String> getSystemSharedLibraryNames(Context context){
        ArrayList<String> ret = new ArrayList<String>();
        PackageManager packageManager = context.getPackageManager();
        String[] systemSharedLibraryNames = packageManager.getSystemSharedLibraryNames();
        if(systemSharedLibraryNames != null) {
            Arrays.sort(systemSharedLibraryNames);
            int length = systemSharedLibraryNames.length;
            for(int index = 0; index < length; ++index) {
                ret.add(systemSharedLibraryNames[index]);
            }
        }
        return ret;
    }

    public static ArrayList<String> getSystemAvailableFeatures(Context context){
        ArrayList<String> ret = new ArrayList<String>();
        PackageManager packageManager = context.getPackageManager();
        FeatureInfo[] feautreInfoArray = packageManager.getSystemAvailableFeatures();
        if(feautreInfoArray != null) {
            int length = feautreInfoArray.length;
            String name = "";
            for(int index = 0; index < length;index++){
                name = feautreInfoArray[index].name;
                if(!TextUtils.isEmpty(name)) {
                    ret.add(name);
                }
            }
            Collections.sort(ret);
        }
        return ret;
    }

    public static String getInfo(Context context){
        String logInfo = "";
        logInfo = LogUtils.record(logInfo,"");
        logInfo = LogUtils.record(logInfo,"SystemSharedLibraryNames:");
        ArrayList<String> systemSharedLibraryNames = getSystemSharedLibraryNames(context);
        int size = systemSharedLibraryNames.size();
        for(int index = 0; index < size; ++index) {
            logInfo = LogUtils.record(logInfo,systemSharedLibraryNames.get(index));
        }

        logInfo = LogUtils.record(logInfo,"");
        logInfo = LogUtils.record(logInfo,"systemAvailableFeatures:");
        ArrayList<String> systemAvailableFeatures = getSystemAvailableFeatures(context);
        size = systemAvailableFeatures.size();
        for(int index = 0; index < size; ++index) {
            logInfo = LogUtils.record(logInfo,systemAvailableFeatures.get(index));
        }
        return logInfo;
    }
}
