package com.example.hardware.Util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by zhangmp on 2019/1/23.
 */

public class BuildPropFileUtils {
    public Properties properties;
    private static BuildPropFileUtils buildPropFileUtils = null;
    public BuildPropFileUtils(){
        FileInputStream fileInputStream = null;
        this.properties = new Properties();
        try {
            File rootDirectoryFile = Environment.getRootDirectory();
            // oppo /system/build.prop，apk有读权限
            // 未ROOT华为机器，无读权限
            fileInputStream = new FileInputStream(new File(rootDirectoryFile, "build.prop"));
            this.properties.load(fileInputStream);
        }
        catch(Throwable throwable) {

        }

        if(null != fileInputStream){
            try {
                fileInputStream.close();
            }
            catch(Exception exception) {
            }
        }

    }

    public String getProperty(String name, String defaultValue) {
        return this.properties.getProperty(name, defaultValue);
    }

    public static BuildPropFileUtils getStaticInstance(){
        if(null == buildPropFileUtils){
            buildPropFileUtils = new BuildPropFileUtils();
        }
        return buildPropFileUtils;
    }
}
