package com.example.hardware.Util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;

import java.util.Iterator;
import java.util.List;

/**
 * https://www.cnblogs.com/tyjsjl/p/3695808.html
 #define SENSOR_TYPE_ACCELEROMETER       1 //加速度
 #define SENSOR_TYPE_MAGNETIC_FIELD      2 //磁场传感器
 #define SENSOR_TYPE_ORIENTATION         3 //方向传感器
 #define SENSOR_TYPE_GYROSCOPE           4 //陀螺仪
 #define SENSOR_TYPE_LIGHT               5 //光线传感器
 #define SENSOR_TYPE_PRESSURE            6 //压力
 #define SENSOR_TYPE_TEMPERATURE         7 //温度
 #define SENSOR_TYPE_PROXIMITY           8 //距离传感器
 #define SENSOR_TYPE_GRAVITY             9 //重力
 #define SENSOR_TYPE_LINEAR_ACCELERATION 10//线性加速度
 #define SENSOR_TYPE_ROTATION_VECTOR     11//旋转矢量
 #define SENSOR_TYPE_RELATIVE_HUMIDITY   12
 #define SENSOR_TYPE_AMBIENT_TEMPERATURE 13
 */

public class SensorUtils {
    public static String getSensorName(Context context,int sensorType) {
        String sensorName = "";
        try {
            SensorManager sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(sensorType);
            if(sensor != null){
                sensorName = sensor.getName();
            }
        }
        catch(Exception exception) {
          exception.toString();
        }
        return sensorName;
    }

    public static String getSensorVendor(Context context,int sensorType) {
        String sensorVendor = "";
        try {
            SensorManager sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(sensorType);
            if(null != sensor){
                sensorVendor = sensor.getVendor();
            }
        }
        catch(Exception exception) {
            exception.toString();
        }
        return sensorVendor;
    }

    public static boolean checkSensorNameIsContainBrand(Context context,int[] arrayCheckSensorTypes){
        boolean checkSensorNameIsContainBrandResult = false;
        try {
            SensorManager sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
            int index = 0;
            int length = arrayCheckSensorTypes.length;

            String sensorName = "";
            while(index < length) {
                List<Sensor> listSensors = sensorManager.getSensorList(arrayCheckSensorTypes[index]);
                Iterator iterator = listSensors.iterator();
                while(iterator.hasNext()) {
                    Sensor sensor = (Sensor) iterator.next();
                    String buildBrand = Build.BRAND;
                    sensorName = sensor.getName();
                    if(sensorName == null) {
                        continue;
                    }

                    if(buildBrand == null) {
                        continue;
                    }

                    if(!sensorName.toLowerCase().contains(buildBrand.toLowerCase())) {
                        continue;
                    }

                    checkSensorNameIsContainBrandResult = true;
                }

                ++index;
            }
        }
        catch(Exception exception) {
            exception.toString();
        }
        return checkSensorNameIsContainBrandResult;

    }
    public static String getInfo(Context context) {
        String logInfo = "";
        String sensorName = getSensorName(context,Sensor.TYPE_ACCELEROMETER);
        String sesorVedor = getSensorVendor(context,Sensor.TYPE_ACCELEROMETER);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_ACCELEROMETER(1) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_MAGNETIC_FIELD);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_MAGNETIC_FIELD);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_MAGNETIC_FIELD(2) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_ORIENTATION);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_ORIENTATION);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_ORIENTATION(3) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_GYROSCOPE);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_GYROSCOPE);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_GYROSCOPE(4) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_LIGHT);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_LIGHT);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_LIGHT(5) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_PRESSURE);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_PRESSURE);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_PRESSURE(6) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_TEMPERATURE);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_TEMPERATURE);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_TEMPERATURE(7) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_PROXIMITY);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_PROXIMITY);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_PROXIMITY(8) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_GRAVITY);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_GRAVITY);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_GRAVITY(9) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_LINEAR_ACCELERATION);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_LINEAR_ACCELERATION);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_ORIENTATION(10) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_ROTATION_VECTOR);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_ROTATION_VECTOR);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_ROTATION_VECTOR(11) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_RELATIVE_HUMIDITY);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_RELATIVE_HUMIDITY);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_RELATIVE_HUMIDITY(12) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));

        sensorName = getSensorName(context,Sensor.TYPE_AMBIENT_TEMPERATURE);
        sesorVedor = getSensorVendor(context,Sensor.TYPE_AMBIENT_TEMPERATURE);
        logInfo = LogUtils.record(logInfo, String.format("Sensor.TYPE_AMBIENT_TEMPERATURE(13) sensorName=%s ,sensorVedor=%s", sensorName,sesorVedor));
        //new int[]{1, 13, 9, 4, 5, 10, 2, 6, 8, 12, 11}
        int[] arrayCheckSensorTypes = {Sensor.TYPE_ACCELEROMETER,
                                        Sensor.TYPE_AMBIENT_TEMPERATURE,
                                        Sensor.TYPE_GRAVITY,
                                        Sensor.TYPE_GYROSCOPE,
                                        Sensor.TYPE_LIGHT,
                                        Sensor.TYPE_ORIENTATION,
                                        Sensor.TYPE_MAGNETIC_FIELD,
                                        Sensor.TYPE_PRESSURE,
                                        Sensor.TYPE_PROXIMITY,
                                        Sensor.TYPE_RELATIVE_HUMIDITY,
                                        Sensor.TYPE_ROTATION_VECTOR};
        boolean checkSensorNameIsContainBrandResult = checkSensorNameIsContainBrand(context,arrayCheckSensorTypes);
        logInfo = LogUtils.record(logInfo, String.format("checkSensorNameIsContainBrandResult=%b", checkSensorNameIsContainBrandResult));
        return logInfo;
    }
}
