package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/8/28.
 */

public class ClassLoaderUtils {
    public static void testLoaded(Context context){
        ClassLoader classLoader = context.getClass().getClassLoader();
        try {
            Class class1 = classLoader.loadClass("com.example.hardware.Util.ClassLoaderUtils");
            class1 = classLoader.loadClass("com.example.hardware.Util.ClassLoaderUtils");
            class1 = classLoader.loadClass("java.lang.String");
            class1 = classLoader.loadClass("java.lang.String");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
