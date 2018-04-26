package com.example.hardware;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBuildHardware();
        String imei = HardwareUtils.getImei(this);
        String userAgent = HardwareUtils.getSystemUserAgent();
        setContentView(R.layout.activity_main);
    }

    public void getBuildHardware(){
        String buildBoard = HardwareUtils.getBuildBoard();
        String buildBootloader = HardwareUtils.getBuildBootLoader();
        String buildBrand = HardwareUtils.getBuildBrand();
        String buildCpuApi = HardwareUtils.getbuildCpuApi();
        String buildCpuApi2 = HardwareUtils.getbuildCpuApi2();
        String buildDevice = HardwareUtils.getBuildDevice();
        String buildDisplay = HardwareUtils.getBuildDisplay();
        String buildFingerPrint = HardwareUtils.getBuildFingerPrint();
        String buildRadioVersion = HardwareUtils.getBuildRadioVersion();
        String buildSerial = HardwareUtils.getBuildSerial();
        String buildHardware = HardwareUtils.getBuildHardware();
        String buildHost = HardwareUtils.getBuildHost();
        String buildId = HardwareUtils.getBuildId();
        String buildManufacturer = HardwareUtils.getBuildManufacturer();
        String buildModel = HardwareUtils.getBuildModel();
        String buildProduct = HardwareUtils.getBuildProduct();
        String buildTags = HardwareUtils.getBuildTags();
        String buildTime = HardwareUtils.getBuildTime();
        String buildType = HardwareUtils.getBuildType();
        String buildUser = HardwareUtils.getBuildUser();
        String buildVersionBaseOS = HardwareUtils.getBuildVersionBaseOS();
        String buildVersionCodeName = HardwareUtils.getBuildVersionCodeName();
        String buildVersionIncremental = HardwareUtils.getBuildVersionIncremental();
        String buildVersionRelease = HardwareUtils.getBuildVersionRelease();
        String buildVersionSecurityPatch = HardwareUtils.getBuildVersionSecurityPatch();
        String buildVersionSDK = HardwareUtils.getBuildVersionSDK();

        String firmwareVersion = HardwareUtils.getFirmwareVersion();
    }

}
