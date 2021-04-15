package com.example.hardware.Util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;

/**
 * Created by zhangmp on 2020/3/24.
 */

public class BatteryUtils {

    public static int getBatteryLevel(Context context){
        int batteryLevel = -1;
        try {
            if(Build.VERSION.SDK_INT < 21) {
                batteryLevel = -1;
            }else{
                //电池容量，单位微安时
                BatteryManager batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
                int chargeCounter = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
                batteryLevel  = chargeCounter / 1000;
            }
        }
        catch(Exception exception) {
        }

        return batteryLevel;
    }

    /**
     *  实时获取电量
     */
    public static int getBatteryRemainingCapacityByReceiver(Context context){
        int level = 0;
        Intent batteryInfoIntent = context.getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        level = batteryInfoIntent.getIntExtra("level",0);
        int batterySum = batteryInfoIntent.getIntExtra("scale", 100);
        int percentBattery= 100 *  level / batterySum;
        return percentBattery;
    }

    public static int getBatteryStatusByReceiver(Context context){
        int status = -1;
        Intent batteryInfoIntent = context.getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        status = batteryInfoIntent.getIntExtra("status",-1);
        return status;
    }

    public static int getBatteryTemperatureByReceiver(Context context){
        int temperature = -1;
        Intent batteryInfoIntent = context.getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        temperature = batteryInfoIntent.getIntExtra("temperature",-1);
        //正常大于等于100，测试返回250
        return temperature;
    }

    public static int getBatteryVoltageByReceiver(Context context){
        int voltage = -1;
        Intent batteryInfoIntent = context.getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        voltage = batteryInfoIntent.getIntExtra("voltage",-1);

        //正常大于500，测试返回4283
        return voltage;
    }


    public static boolean isBatteryPresentByReceiver(Context context){
        boolean present = true;
        Intent batteryInfoIntent = context.getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        present = batteryInfoIntent.getBooleanExtra("present",true);
        return present;
    }

    public static String getBatteryStatusDescription(int status){
        String ret = "BATTERY_STATUS_UNKNOWN";
        switch (status){
            case BatteryManager.BATTERY_STATUS_UNKNOWN:{
                ret = "BATTERY_STATUS_UNKNOWN";
            }
            break;

            case BatteryManager.BATTERY_STATUS_CHARGING:{
                ret = "BATTERY_STATUS_CHARGING";
            }
            break;

            case BatteryManager.BATTERY_STATUS_DISCHARGING:{
                ret = "BATTERY_STATUS_DISCHARGING";
            }
            break;

            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:{
                ret = "BATTERY_STATUS_NOT_CHARGING";
            }
            break;

            case BatteryManager.BATTERY_STATUS_FULL:{
                ret = "BATTERY_STATUS_FULL";
            }
        }
        return ret;
    }
    public static int getBatteryRemainingCapacity(Context context){
        int batteryRemainingCapacity = 1;
        if(Build.VERSION.SDK_INT >= 21){
            BatteryManager batteryManager = (BatteryManager)context.getSystemService(Context.BATTERY_SERVICE);
            //电池剩余容量，作为总容量的百分比
            batteryRemainingCapacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        }
        return batteryRemainingCapacity;
    }

    public static int getBatteryStatus(Context context){
        int batteryStatus = BatteryManager.BATTERY_STATUS_UNKNOWN;
        if(Build.VERSION.SDK_INT > 26){
            BatteryManager batteryManager = (BatteryManager)context.getSystemService(Context.BATTERY_SERVICE);
            batteryStatus = batteryManager.getIntProperty(6);
        }
        return batteryStatus;
    }

    public static String getInfo(Context context) {
        String logInfo = "";
        int batteryLevel = getBatteryLevel(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryLevel=%s", batteryLevel));

        int batteryRemainingCapacityByReceiver = getBatteryRemainingCapacityByReceiver(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryRemainingCapacityByReceiver=%s", batteryRemainingCapacityByReceiver));

        int batteryRemainingCapacity = getBatteryRemainingCapacity(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryRemainingCapacity=%s", batteryRemainingCapacity));

        int batteryStatusByReceiver = getBatteryStatusByReceiver(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryStatusByReceiver=%s", getBatteryStatusDescription(batteryStatusByReceiver)));

        int batteryStatus = getBatteryStatus(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryStatus=%s", getBatteryStatusDescription(batteryStatus)));

        int batteryTemperatureByReceiver = getBatteryTemperatureByReceiver(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryTemperatureByReceiver=%s", batteryTemperatureByReceiver));

        int batteryVoltageByReceiver = getBatteryVoltageByReceiver(context);
        logInfo = LogUtils.record(logInfo, String.format("batteryVoltageByReceiver=%s", batteryVoltageByReceiver));

        boolean isBatteryPresentByReceiver = isBatteryPresentByReceiver(context);
        logInfo = LogUtils.record(logInfo, String.format("isBatteryPresentByReceiver=%s", isBatteryPresentByReceiver));
        return logInfo;
    }
}
