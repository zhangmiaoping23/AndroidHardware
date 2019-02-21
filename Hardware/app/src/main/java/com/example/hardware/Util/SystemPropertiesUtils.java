package com.example.hardware.Util;

import org.joor.Reflect;
import org.joor.ReflectException;

/**
 * Created by zhangmp on 2018/5/15.
 */

public class SystemPropertiesUtils {

    public static String get(String key, String defaultValue) {
        String value;
        try {
            value = Reflect.on("android.os.SystemProperties")
                    .call("get", key, defaultValue).get();
        } catch (ReflectException e) {
            e.printStackTrace();
            value = "";
        }
        return value;
    }
}
