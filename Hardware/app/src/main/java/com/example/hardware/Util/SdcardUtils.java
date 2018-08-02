package com.example.hardware.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by zhangmp on 2018/8/2.
 */

public class SdcardUtils {
    public static String getSdcardName(){
        String sdcardName = "";
        String filePath = getSdcardDevicePath() + "name";
        try {
            FileReader fileReader = new FileReader(filePath); // 厂商
            sdcardName = new BufferedReader((Reader) fileReader).readLine();
        } catch (Exception e) {

        }
        return sdcardName;
    }

    public static String getSdcardCid(){
        String sdcardCid = "1234567890";
        String filePath = getSdcardDevicePath() + "cid";
        try {
            FileReader fileReader = new FileReader(filePath); // SD Card ID
            sdcardCid = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {
            try {
                filePath = "/sys/ufs/ufsid";
                FileReader fileReader = new FileReader(filePath);
                sdcardCid = new BufferedReader((Reader) fileReader).readLine().trim();
            } catch (Exception ex) {

            }

        }
        return sdcardCid;
    }

    public static String getSdcardCsd(){
        String sdcardCsd = "";
        String filePath = getSdcardDevicePath() + "csd";
        try {
            FileReader fileReader = new FileReader(filePath);
            sdcardCsd = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardCsd;
    }

    public static String getSdcardFwrev(){
        String sdcardFwrev = "";
        String filePath = getSdcardDevicePath() + "fwrev";
        try {
            FileReader fileReader = new FileReader(filePath);// 固件编号
            sdcardFwrev = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardFwrev;
    }


    public static String getSdcardHwrev(){
        String sdcardHwrev = "";
        String filePath = getSdcardDevicePath() + "hwrev";
        try {
            FileReader fileReader = new FileReader(filePath);// 硬件版本
            sdcardHwrev = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardHwrev;
    }

    public static String getSdcardManfid(){
        String sdcardManfid = "";
        String filePath = getSdcardDevicePath() + "manfid";
        try {
            FileReader fileReader = new FileReader(filePath);//manufacture 制造
            sdcardManfid = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardManfid;
    }

    public static String getSdcardOemid(){
        String sdcardOemid = "";
        String filePath = getSdcardDevicePath() + "oemid";
        try {
            FileReader fileReader = new FileReader(filePath);// 原始设备制造商
            sdcardOemid = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardOemid;
    }

    public static String getSdcardScr(){
        String sdcardScr = "";
        String filePath = getSdcardDevicePath() + "scr";
        try {
            FileReader fileReader = new FileReader(filePath);
            sdcardScr = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardScr;
    }

    public static String getSdcardSerial(){
        String sdcardSerial = "";
        String filePath = getSdcardDevicePath() + "serial";
        try {
            FileReader fileReader = new FileReader(filePath);// 串号/序列号
            sdcardSerial = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardSerial;
    }

    public static String getSdcardDate(){
        String sdcardDate = "";
        String filePath = getSdcardDevicePath() + "date";
        try {
            FileReader fileReader = new FileReader(filePath);// 生产日期
            sdcardDate = new BufferedReader((Reader) fileReader).readLine().trim();
        } catch (Exception e) {

        }
        return sdcardDate;
    }

    public static String getSdcardDevicePath(){
        if(sdcardDevicePath.isEmpty()){
            initSdcardDevicePath();
        }
        return sdcardDevicePath;
    }
    private static void initSdcardDevicePath(){
        String devicePath = "";
        Object localOb;
        try {
            localOb = new FileReader("/sys/block/mmcblk0/device/type");
            localOb = new BufferedReader((Reader) localOb).readLine()
                    .toLowerCase().contentEquals("sd");
            if (localOb != null) {
                devicePath = "/sys/block/mmcblk0/device/";
            }
        } catch (Exception e) {

        }
        try {
            localOb = new FileReader("/sys/block/mmcblk1/device/type");
            localOb = new BufferedReader((Reader) localOb).readLine()
                    .toLowerCase().contentEquals("sd");
            if (localOb != null) {
                devicePath = "/sys/block/mmcblk1/device/";
            }
        } catch (Exception e) {

        }
        try {

            localOb = new FileReader("/sys/block/mmcblk2/device/type");
            localOb = new BufferedReader((Reader) localOb).readLine()
                    .toLowerCase().contentEquals("sd");
            if (localOb != null) {
                devicePath = "/sys/block/mmcblk2/device/";
            }
        } catch (Exception e) {
        }
        sdcardDevicePath = devicePath;
    }



    public static String sdcardDevicePath = "";
}
