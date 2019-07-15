package com.example.hardware.Util;

import android.content.Context;

/**
 * Created by zhangmp on 2019/6/13.
 */

public class VivoHardwareUtils {
    public static String getInfo(Context context){
        String logInfo = "";
        String manufacturer = HardwareUtils.getBuildManufacturer();
        manufacturer = manufacturer.toLowerCase();
        if(manufacturer.contains("vivo")){

            String systemPropVivoHardwareVersion = getSystemPropVivoHardwareVersion();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoHardwareVersion=%s",systemPropVivoHardwareVersion));

            String systemPropVivoProductModel = getSystemPropVivoProductModel();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoProductModel=%s",systemPropVivoProductModel));

            String systemPropVivoProductReleaseModel = getSystemPropVivoProductReleaseModel();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoProductReleaseModel=%s",systemPropVivoProductReleaseModel));

            String systemPropVivoProductReleaseName = getSystemPropVivoProductReleaseName();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoProductReleaseName=%s",systemPropVivoProductReleaseName));

            String systemPropVivoProductVersion = getSystemPropVivoProductVersion();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoProductVersion=%s",systemPropVivoProductVersion));

            String systemPropBuildVersionBBK = getSystemPropBuildVersionBBK();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropBuildVersionBBK=%s",systemPropBuildVersionBBK));

            String systemPropHardwareBBK = getSystemPropHardwareBBK();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropHardwareBBK=%s",systemPropHardwareBBK));

            String systemPropProductModelBBK = getSystemPropProductModelBBK();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropProductModelBBK=%s",systemPropProductModelBBK));

            String systemPropVivoOsVersion = getSystemPropVivoOsVersion();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoOsVersion=%s",systemPropVivoOsVersion));

            String systemPropVivoOsName = getSystemPropVivoOsName();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoOsName=%s",systemPropVivoOsName));

            String systemPropVivoBuildDisplayId = getSystemPropVivoBuildDisplayId();
            logInfo = LogUtils.record(logInfo,String.format("vivo systemPropVivoBuildDisplayId=%s",systemPropVivoBuildDisplayId));


        }
        return  logInfo;
    }


    public static String getSystemPropBuildVersionBBK() {
        String buildVersionBBK = "";
        buildVersionBBK = SystemPropertiesUtils.getString("ro.build.version.bbk", "");
        return buildVersionBBK;
    }

    public static String getSystemPropHardwareBBK() {
        String hardwareBBK = "";
        hardwareBBK = SystemPropertiesUtils.getString("ro.hardware.bbk", "");
        return hardwareBBK;
    }

    public static String getSystemPropProductModelBBK() {
        String productModelBBK = "";
        productModelBBK = SystemPropertiesUtils.getString("ro.product.model.bbk", "");
        return productModelBBK;
    }

    public static String getSystemPropVivoProductModel() {
        String productModel = "";
        productModel = SystemPropertiesUtils.getString("ro.vivo.product.model", "");
        return productModel;
    }

    public static String getSystemPropVivoProductReleaseName() {
        String productReleaseName = "";
        productReleaseName = SystemPropertiesUtils.getString("ro.vivo.product.release.name", "");
        return productReleaseName;
    }

    public static String getSystemPropVivoProductReleaseModel() {
        String productReleaseModel = "";
        productReleaseModel = SystemPropertiesUtils.getString("ro.vivo.product.release.model", "");
        return productReleaseModel;
    }


    public static String getSystemPropVivoProductVersion() {
        String productVersion = "";
        productVersion = SystemPropertiesUtils.getString("ro.vivo.product.version", "");
        return productVersion;
    }

    public static String getSystemPropVivoHardwareVersion() {
        String hardwareVersion = "";
        hardwareVersion = SystemPropertiesUtils.getString("ro.vivo.hardware.version", "");
        return hardwareVersion;
    }


    public static String getSystemPropVivoBuildDisplayId(){
        String buildDisplayId = "";
        buildDisplayId = SystemPropertiesUtils.getString("ro.vivo.os.build.display.id","");
        return buildDisplayId;
    }

    public static String getSystemPropVivoOsName() {
        String osName = "";
        osName = SystemPropertiesUtils.getString("ro.vivo.os.name", "");
        return osName;
    }


    public static String getSystemPropVivoOsVersion() {
        String osVersion = "";
        osVersion = SystemPropertiesUtils.getString("ro.vivo.os.version", "");
        return osVersion;
    }

}
