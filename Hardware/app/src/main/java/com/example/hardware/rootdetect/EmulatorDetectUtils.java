package com.example.hardware.rootdetect;

import android.os.Build;

import com.example.hardware.Util.SystemPropertiesUtils;

import java.io.File;
import java.util.ArrayList;

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

    public static boolean checkEmulatorProductName() {
        boolean isEmulator = false;
        if(emulatorProductNames.isEmpty()){
            syncInitEmulatorProductNames();
        }
        String productName = SystemPropertiesUtils.getString("ro.product.name","");
        int size = emulatorProductNames.size();
        for(int index = 0; index < size; ++index) {
            if(productName.contains(emulatorProductNames.get(index))) {
                isEmulator = true;
                break;
            }
        }

        return isEmulator;
    }

    private static ArrayList<String> emulatorProductNames = new ArrayList<String>();
    private static synchronized void syncInitEmulatorProductNames(){
        if(emulatorProductNames.isEmpty()){
            emulatorProductNames.add("ChangWan");
            emulatorProductNames.add("Droid4X");
            emulatorProductNames.add("nox");
            emulatorProductNames.add("ttVM_Hdragon");
            emulatorProductNames.add("sdk_google_phone_x86");
            emulatorProductNames.add("google_sdk");
        }
    }

    public static boolean checkEmulatorSystemProperties() {
        boolean isEmulator = false;
        if(emulatorSystemProperties.isEmpty()){
            syncInitEmulatorSystemProperties();
        }
        String value = "";
        String key = "";
        int intValue = 0;
        int size = emulatorSystemProperties.size();
        for(int index = 0; index < size; ++index) {
            key = emulatorSystemProperties.get(index);
            value = SystemPropertiesUtils.getString(key,"");
            if(!value.isEmpty() ){
                isEmulator = true;
                break;
            }

            intValue = SystemPropertiesUtils.getInt(key,-1);
            if(intValue >= 1){
                isEmulator = true;
                break;
            }
        }

        return isEmulator;
    }
    private static ArrayList<String> emulatorSystemProperties = new ArrayList<String>();
    private static synchronized void syncInitEmulatorSystemProperties(){
        if(emulatorSystemProperties.isEmpty()){
            emulatorSystemProperties.add("init.svc.vbox86-setup");
            emulatorSystemProperties.add("init.svc.droid4x");
            emulatorSystemProperties.add("init.svc.qemud");
            emulatorSystemProperties.add("init.svc.su_kpbs_daemon");
            emulatorSystemProperties.add("init.svc.noxd");
            emulatorSystemProperties.add("init.svc.ttVM_x86-setup");
            emulatorSystemProperties.add("init.svc.xxkmsg");
            emulatorSystemProperties.add("init.svc.microvirtd");
            emulatorSystemProperties.add("ro.kernel.android.qemud");
            emulatorSystemProperties.add("androVM.vbox_dpi");
            emulatorSystemProperties.add("androVM.vbox_graph_mode");
        }
    }

    public static synchronized boolean checkEmulatorFiles() {
        boolean isFind = false;
        if(emulatorFiles.isEmpty()){
            syncInitEmulatorFiles();
        }
        int size = emulatorFiles.size();
        for(int index = 0; index < size; ++index) {
            if(new File(emulatorFiles.get(index)).exists()) {
                isFind = true;
                break;
            }
        }

        return isFind;
    }

    private static ArrayList<String> emulatorFiles = new ArrayList<String>();
    private static synchronized void syncInitEmulatorFiles(){
        if(emulatorFiles.isEmpty()){
            emulatorFiles.add("/system/bin/qemu-props");
            emulatorFiles.add("/system/bin/androVM-prop");
            emulatorFiles.add("/system/bin/microvirt-prop");
            emulatorFiles.add("/system/lib/libdroid4x.so");
            emulatorFiles.add("/system/bin/windroyed");
            emulatorFiles.add("/system/bin/microvirtd");
            emulatorFiles.add("/system/bin/ttVM-prop");
            emulatorFiles.add("/system/bin/droid4x-prop");
            emulatorFiles.add("/data/.bluestacks.prop");
            emulatorFiles.add("/dev/socket/qemud");
        }
    }
}
