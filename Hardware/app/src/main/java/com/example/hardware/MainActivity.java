package com.example.hardware;

import android.app.Activity;
import android.os.Bundle;

import com.example.hardware.Util.LogUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBuildHardware();
        String imei = HardwareUtils.getImei(this);
        LogUtils.i(String.format("imei=%s",imei));
        String userAgent = HardwareUtils.getSystemUserAgent();
        LogUtils.i(String.format("userAgent=%s",userAgent));
        setContentView(R.layout.activity_main);
    }

    public void getBuildHardware(){
        String buildBoard = HardwareUtils.getBuildBoard();
        LogUtils.i(String.format("buildBoard=%s",buildBoard));

        String buildBootloader = HardwareUtils.getBuildBootLoader();
        LogUtils.i(String.format("buildBootloader=%s",buildBootloader));

        String buildBrand = HardwareUtils.getBuildBrand();
        LogUtils.i(String.format("buildBrand=%s",buildBrand));

        String buildCpuApi = HardwareUtils.getbuildCpuApi();
        LogUtils.i(String.format("buildCpuApi=%s",buildCpuApi));

        String buildCpuApi2 = HardwareUtils.getbuildCpuApi2();
        LogUtils.i(String.format("buildCpuApi2=%s",buildCpuApi2));

        String buildDevice = HardwareUtils.getBuildDevice();
        LogUtils.i(String.format("buildDevice=%s",buildDevice));

        String buildDisplay = HardwareUtils.getBuildDisplay();
        LogUtils.i(String.format("buildDisplay=%s",buildDisplay));

        String buildFingerPrint = HardwareUtils.getBuildFingerPrint();
        LogUtils.i(String.format("buildFingerPrint=%s",buildFingerPrint));

        String buildRadioVersion = HardwareUtils.getBuildRadioVersion();
        LogUtils.i(String.format("buildRadioVersion=%s",buildRadioVersion));

        String buildSerial = HardwareUtils.getBuildSerial();
        LogUtils.i(String.format("buildSerial=%s",buildSerial));

        String buildHardware = HardwareUtils.getBuildHardware();
        LogUtils.i(String.format("buildHardware=%s",buildHardware));

        String buildHost = HardwareUtils.getBuildHost();
        LogUtils.i(String.format("buildHost=%s",buildHost));

        String buildId = HardwareUtils.getBuildId();
        LogUtils.i(String.format("buildId=%s",buildId));

        String buildManufacturer = HardwareUtils.getBuildManufacturer();
        LogUtils.i(String.format("buildManufacturer=%s",buildManufacturer));

        String buildModel = HardwareUtils.getBuildModel();
        LogUtils.i(String.format("buildModel=%s",buildModel));

        String buildProduct = HardwareUtils.getBuildProduct();
        LogUtils.i(String.format("buildProduct=%s",buildProduct));

        String buildTags = HardwareUtils.getBuildTags();
        LogUtils.i(String.format("buildTags=%s",buildTags));

        String buildTime = HardwareUtils.getBuildTime();
        LogUtils.i(String.format("buildTime=%s",buildTime));

        String buildType = HardwareUtils.getBuildType();
        LogUtils.i(String.format("buildType=%s",buildType));

        String buildUser = HardwareUtils.getBuildUser();
        LogUtils.i(String.format("buildUser=%s",buildUser));

        String buildVersionBaseOS = HardwareUtils.getBuildVersionBaseOS();
        LogUtils.i(String.format("buildVersionBaseOS=%s",buildVersionBaseOS));

        String buildVersionCodeName = HardwareUtils.getBuildVersionCodeName();
        LogUtils.i(String.format("buildVersionCodeName=%s",buildVersionCodeName));

        String buildVersionIncremental = HardwareUtils.getBuildVersionIncremental();
        LogUtils.i(String.format("buildVersionIncremental=%s",buildVersionIncremental));

        String buildVersionRelease = HardwareUtils.getBuildVersionRelease();
        LogUtils.i(String.format("buildVersionRelease=%s",buildVersionRelease));

        String buildVersionSecurityPatch = HardwareUtils.getBuildVersionSecurityPatch();
        LogUtils.i(String.format("buildVersionSecurityPatch=%s",buildVersionSecurityPatch));

        String buildVersionSDK = HardwareUtils.getBuildVersionSDK();
        LogUtils.i(String.format("buildVersionSDK=%s",buildVersionSDK));

        String firmwareVersion = HardwareUtils.getFirmwareVersion();
        LogUtils.i(String.format("firmwareVersion=%s",firmwareVersion));

    }

}
