package com.example.hardware.rootdetect;
import android.content.Context;

import com.example.hardware.Util.LogUtils;

public class RootDetectUtils {
    public static String getInfo(Context context){
        String logInfo = "";

        boolean isRoot = CommonRootDetectUtils.checkSu();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkSuRet = %s",String.valueOf(isRoot)));

        isRoot = CommonRootDetectUtils.checkSecureProperty();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkSecurePropertyRet = %s",String.valueOf(isRoot)));

        isRoot = CommonRootDetectUtils.checkBuildTags();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkBuildTagsRet = %s",String.valueOf(isRoot)));

        isRoot = CommonRootDetectUtils.checkMagisk();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkMagiskRet = %s",String.valueOf(isRoot)));

        boolean isEmulator = EmulatorDetectUtils.checkEmulator();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkEmulatorRet = %s",String.valueOf(isEmulator)));

        isEmulator = EmulatorDetectUtils.checkEmulatorProductName();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkEmulatorProductNameRet = %s",String.valueOf(isEmulator)));

        isEmulator = EmulatorDetectUtils.checkEmulatorSystemProperties();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkEmulatorSystemPropertiesRet = %s",String.valueOf(isEmulator)));

        isEmulator = EmulatorDetectUtils.checkEmulatorFiles();
        logInfo = LogUtils.record(logInfo,String.format("RootDetect checkEmulatorFilesRet = %s",String.valueOf(isEmulator)));
        return logInfo;
    }
}

