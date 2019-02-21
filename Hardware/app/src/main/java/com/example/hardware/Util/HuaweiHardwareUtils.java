package com.example.hardware.Util;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HuaweiHardwareUtils {
    public static void getHardwares(Context context){
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("huawei")){
            int emuiSdkInt = getEmuiSdkInt();
            LogUtils.i(String.format("huawei emuiSdkInt=%s",String.valueOf(emuiSdkInt)));

            String custVersion = getCustCVersion();
            LogUtils.i(String.format("huawei custVersion=%s",custVersion));

            String emuiVersionFromSystemProperties = getEmuiVersionFromSystemProperties();
            LogUtils.i(String.format("huawei emuiVersionFromSystemProperties=%s",emuiVersionFromSystemProperties));

            String emuiVersionFromBuildEx = getEmuiVersionFromBuildEx();
            LogUtils.i(String.format("huawei emuiVersionFromBuildEx=%s",emuiVersionFromBuildEx));

            int emuiApiLevel = getEmuiApiLevel();
            LogUtils.i(String.format("huawei emuiApiLevel=%d",emuiApiLevel));

            String udid = getUDID();
            LogUtils.i(String.format("huawei udid=%s",udid));
        }
    }

    private static final HashMap<Integer,String> hashmapApiLevelToName = new HashMap<Integer,String>();
    static {
        hashmapApiLevelToName.put(Integer.valueOf(1), "1.0");
        hashmapApiLevelToName.put(Integer.valueOf(2), "1.5");
        hashmapApiLevelToName.put(Integer.valueOf(3), "1.6");
        hashmapApiLevelToName.put(Integer.valueOf(4), "2.0");
        hashmapApiLevelToName.put(Integer.valueOf(5), "2.0");
        hashmapApiLevelToName.put(Integer.valueOf(6), "2.3");
        hashmapApiLevelToName.put(Integer.valueOf(7), "3.0");
        hashmapApiLevelToName.put(Integer.valueOf(8), "3.0.5");
        hashmapApiLevelToName.put(Integer.valueOf(8), "3.1");
        hashmapApiLevelToName.put(Integer.valueOf(9), "4.0");
        hashmapApiLevelToName.put(Integer.valueOf(10), "4.1");
        hashmapApiLevelToName.put(Integer.valueOf(11), "5.0");
        hashmapApiLevelToName.put(Integer.valueOf(12), "5.1");
        hashmapApiLevelToName.put(Integer.valueOf(13), "5.1");
        hashmapApiLevelToName.put(Integer.valueOf(14), "8.0");
        hashmapApiLevelToName.put(Integer.valueOf(15), "8.1");
    }
    public static int getEmuiApiLevel(){
        int emuiApiLevel = SystemProperties.getInt("ro.build.hw_emui_api_level", 0);
        if(emuiApiLevel == 0) {
            emuiApiLevel = getEmuiApiLevelFromMap();
        }
        return emuiApiLevel;
    }

    public static int getEmuiSdkInt(){
        int emuiSdkInt = 0;
        try {
            Class reflectClass = Class.forName("com.huawei.android.os.BuildEx$VERSION");
            Field field = reflectClass.getField("EMUI_SDK_INT");
            Object value = field.get(null);
            emuiSdkInt = (int)value;
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return emuiSdkInt;
    }

    private static int getEmuiApiLevelFromMap() {
        int emuiApiLevel = 0;
        String versionEmui = SystemProperties.get("ro.build.version.emui", "");             // EmotionUI_8.0.0
        String emuiVersionName = "";
        if(!TextUtils.isEmpty(versionEmui)) {
            String[] splitArray = versionEmui.split("_");
            if(splitArray.length == 2) {
                emuiVersionName = splitArray[1];
            }
        }

        if(!TextUtils.isEmpty(emuiVersionName)) {
            Iterator iterator = hashmapApiLevelToName.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (emuiVersionName.startsWith((String) entry.getValue())) {
                    emuiApiLevel = (int)entry.getKey();
                    break;
                }
            }
        }

        return emuiApiLevel;
    }

    public static String getEmuiVersionFromBuildEx(){
        String emuiVersion = "";
        try {
            Class reflectClass = Class.forName("com.huawei.android.os.BuildEx");
            Field field = reflectClass.getField("EMUI_VERSION");
            Object value = field.get(null);
            emuiVersion = (String)value;
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return emuiVersion;
    }

    public static String getCustCVersion() {
        return SystemPropertiesUtils.get("ro.product.CustCVersion", "");
    }

    public static String getEmuiVersionFromSystemProperties(){
        String emuiVersion = "";
        emuiVersion = SystemPropertiesUtils.get("ro.build.version.emui","");
        return emuiVersion;
    }

    public static String getUDID(){
        String udid = null;
        try {
            Class reflectClass = Class.forName("com.huawei.android.os.BuildEx");
            Method reflectMethod = reflectClass.getMethod("getUDID");
            Object object = reflectMethod.invoke(null, new Object[0]);
            udid = (String)object;
        } catch(InvocationTargetException exception) {
            udid = "";
        } catch(NoSuchMethodException exception) {
            udid = null;
        }
        catch(ClassNotFoundException exception) {
            udid = null;
        }catch(Exception exception) {
            udid = "";
        }
        return udid;
    }

}
