package com.example.hardware.Util;
import android.os.SystemProperties;

public class RuntimeUtils {
    public static final int RUNTIME_TYPE_UNKNOWN = 0;
    public static final int RUNTIME_TYPE_DALVIK = 1;
    public static final int RUNTIME_TYPE_ART = 2;

    public static int getRuntimeType() {
        String defaultValue = null;
        int ret = RUNTIME_TYPE_DALVIK;
        //例子：libart.so
        String tmpValue = SystemProperties.get("persist.sys.dalvik.vm.lib.2", defaultValue);
        if((tmpValue == null) || ("".equals(tmpValue))) {
            tmpValue = SystemProperties.get("persist.sys.dalvik.vm.lib", defaultValue);
        }

        if((tmpValue == null) || ("".equals(tmpValue))) {
            //例子：Dalvik为 1.6.0 art为2.1.0
            tmpValue = System.getProperty("java.vm.version");
            if((tmpValue != null) && (tmpValue.startsWith("1"))) {
                ret = RUNTIME_TYPE_DALVIK;
            }else if((tmpValue != null) && (tmpValue.startsWith("2"))) {
                ret = RUNTIME_TYPE_ART;
            }
            else{
                ret = RUNTIME_TYPE_UNKNOWN;
            }
        }
        else {
            if(tmpValue.startsWith("libdvm")){
                ret = RUNTIME_TYPE_DALVIK;
            }else if(tmpValue.startsWith("libart")) {
                ret = RUNTIME_TYPE_ART;
            }
            else {
               ret = RUNTIME_TYPE_UNKNOWN;
            }
        }

        return ret;
    }
    public static String getInfo() {
        String logInfo = "";
        int runtimeType = RuntimeUtils.getRuntimeType();
        logInfo = LogUtils.record(logInfo,String.format("runtimeType=%s",String.valueOf(runtimeType)));
        return logInfo;
    }
}