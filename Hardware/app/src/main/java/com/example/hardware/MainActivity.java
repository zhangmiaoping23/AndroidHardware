package com.example.hardware;

import android.app.Activity;
import android.graphics.Path;
import android.os.Bundle;

import com.example.hardware.Util.HardwareUtils;
import com.example.hardware.Util.LogUtils;
import com.example.hardware.Util.OppoHardwareUtils;
import com.example.hardware.Util.RuntimeUtils;
import com.example.hardware.Util.SignatureUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignatureUtils.getSignatureHashCode(this);
        int runtimeType = RuntimeUtils.getRuntimeType();
        LogUtils.i(String.format("runtimeType=%s",String.valueOf(runtimeType)));
        getBuildHardware();
        String imei = HardwareUtils.getImei(this);
        LogUtils.i(String.format("telephonyManagerImei=%s",imei));
        String userAgent = HardwareUtils.getSystemUserAgent();
        LogUtils.i(String.format("userAgent=%s",userAgent));

        OppoHardwareUtils.getHardwares(this);
        setContentView(R.layout.activity_main);
    }
    /*
    例子一：
        I/AndroidHardware: buildBoard=full_oppo6755_15111
        I/AndroidHardware: buildBootloader=unknown
        I/AndroidHardware: buildBrand=OPPO
        I/AndroidHardware: buildCpuApi=arm64-v8a
        I/AndroidHardware: buildCpuApi2=
        I/AndroidHardware: SupportAbi0=arm64-v8a
        I/AndroidHardware: SupportAbi1=armeabi-v7a
        I/AndroidHardware: SupportAbi2=armeabi
        I/AndroidHardware: buildDevice=R9
        I/AndroidHardware: buildDisplay=R9m_11_A.13_160314
        I/AndroidHardware: buildFingerPrint=OPPO/R9m/R9:5.1/LMY47I/1449641681:user/release-keys
        I/AndroidHardware: buildRadioVersion=MOLY.LR11.W1539.MD.MP.V9.P36.T36, 2016/03/12 23:22
        I/AndroidHardware: buildSerial=IRFMW8CMLFFASGGY
        I/AndroidHardware: buildHardware=mt6755
        I/AndroidHardware: buildHost=ubuntu-121-109
        I/AndroidHardware: buildId=LMY47I
        I/AndroidHardware: buildManufacturer=OPPO
        I/AndroidHardware: buildModel=OPPO R9m
        I/AndroidHardware: buildProduct=R9m
        I/AndroidHardware: buildTags=dev-keys
        I/AndroidHardware: buildTime=1457895750000
        I/AndroidHardware: buildType=user
        I/AndroidHardware: buildUser=root
        I/AndroidHardware: buildVersionBaseOS=OPPO/R9m/R9:5.1/LMY47I/1449641681:user/release-keys
        I/AndroidHardware: buildVersionCodeName=REL
        I/AndroidHardware: buildVersionIncremental=1457895608
        I/AndroidHardware: buildVersionRelease=5.1
        I/AndroidHardware: buildVersionSecurityPatch=2016-01-01
        I/AndroidHardware: buildVersionSDK=22
        I/AndroidHardware: firmwareVersion=5.1
     */
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

        String[] supportedApis = HardwareUtils.getBuildSupportAbis();
        for(int index = 0; index < supportedApis.length;index ++){
            LogUtils.i(String.format("SupportAbi%d=%s",index,supportedApis[index]));
        }


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
