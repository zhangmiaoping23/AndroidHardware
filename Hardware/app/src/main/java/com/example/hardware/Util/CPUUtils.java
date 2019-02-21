package com.example.hardware.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by zhangmp on 2019/1/23.
 */

public class CPUUtils {
    /*
    shell@R9:/ $ cat /proc/cpuinfo
    cat /proc/cpuinfo
    Processor	: AArch64 Processor rev 2 (aarch64)
    processor	: 0
    BogoMIPS	: 26.00
    Features	: fp asimd aes pmull sha1 sha2 crc32
    CPU implementer	: 0x41
    CPU architecture: AArch64
    CPU variant	: 0x0
    CPU part	: 0xd03
    CPU revision	: 2

    Hardware	: MT6755

     */
    public static String getCpuName(){
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        String cpuName = "";
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            bufferReader = new BufferedReader(fileReader);
            String line = bufferReader.readLine();
            if(line != null) {
                String[] processorArray = line.split(":\\s+", 2);
                if(processorArray.length >=  2){
                    cpuName = processorArray[1];
                }
            }
        }catch (Throwable throwable){

        }

        if(fileReader != null) {
            try {
                fileReader.close();
            }
            catch(IOException ioException) {
            }
        }

        if(bufferReader != null) {
            try {
                bufferReader.close();
            }
            catch(IOException ioException) {
            }
        }
        return cpuName;
    }

    /*
    root@R9:/sys/devices/system/cpu # ll
    ll
    drwxr-xr-x root     root              2019-01-23 14:06 cpu0
    drwxr-xr-x root     root              2019-01-23 14:06 cpu1
    drwxr-xr-x root     root              2019-01-23 14:06 cpu2
    drwxr-xr-x root     root              2019-01-23 14:06 cpu3
    drwxr-xr-x root     root              2019-01-23 14:06 cpu4
    drwxr-xr-x root     root              2019-01-23 14:06 cpu5
    drwxr-xr-x root     root              2019-01-23 14:06 cpu6
    drwxr-xr-x root     root              2019-01-23 14:06 cpu7
    drwxr-xr-x root     root              2019-01-23 09:55 cpufreq
    drwxr-xr-x root     root              2019-01-23 09:55 cpuidle
    drwxr-xr-x root     root              2019-01-23 09:55 cputopo
    -r--r--r-- root     root         4096 2019-01-23 09:55 kernel_max
    -r--r--r-- root     root         4096 2019-01-23 09:55 modalias
    -r--r--r-- root     root         4096 2019-01-23 09:55 offline
    -r--r--r-- root     root         4096 2019-01-23 09:55 online
    -r--r--r-- root     root         4096 2019-01-23 09:55 possible
    drwxr-xr-x root     root              2019-01-23 09:55 power
    -r--r--r-- root     root         4096 2019-01-23 09:55 present
    -rw-r--r-- root     root         4096 2019-01-23 09:55 uevent
     */
    public static int getCpuCoresNum(){
        int cpuCoresNum = 1;
        try {
            File[] fileArray = new File("/sys/devices/system/cpu/").listFiles(new CpuFileFilter());
            if(fileArray != null) {
                cpuCoresNum = fileArray.length;
            }
        }
        catch(Exception exception) {
        }

        return cpuCoresNum;
    }

    /*
    root@R9:/sys/devices/system/cpu/cpu0/cpufreq # cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq
    cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq                      <
    1950000
     */
    public static int getCpuMaxFreq(){
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        int maxFreq = 0;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            bufferReader = new BufferedReader(fileReader);
            String line = bufferReader.readLine();
            if(line != null) {
                maxFreq = Integer.parseInt(line.trim());
            }
        }catch (Throwable throwable){

        }

        if(fileReader != null) {
            try {
                fileReader.close();
            }
            catch(IOException ioException) {
            }
        }

        if(bufferReader != null) {
            try {
                bufferReader.close();
            }
            catch(IOException ioException) {
            }
        }
        return maxFreq;
    }

    /*
    root@R9:/sys/devices/system/cpu/cpu0/cpufreq # cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq
    cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq
    156000
     */
    public static int getCpuMinFreq(){
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        int minFreq = 0;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
            bufferReader = new BufferedReader(fileReader);
            String line = bufferReader.readLine();
            if(line != null) {
                minFreq = Integer.parseInt(line.trim());
            }
        }catch (Throwable throwable){

        }

        if(fileReader != null) {
            try {
                fileReader.close();
            }
            catch(IOException ioException) {
            }
        }

        if(bufferReader != null) {
            try {
                bufferReader.close();
            }
            catch(IOException ioException) {
            }
        }
        return minFreq;
    }

    public static void getInfo(){
        String cpuName = CPUUtils.getCpuName();
        LogUtils.i(String.format("cpuName=%s",cpuName));

        int cpuCoresNum = CPUUtils.getCpuCoresNum();
        LogUtils.i(String.format("cpuCoresNum=%d",cpuCoresNum));

        int cpuMaxFreq = CPUUtils.getCpuMaxFreq();
        LogUtils.i(String.format("cpuMaxFreq=%d",cpuMaxFreq));

        int cpuMinFreq = CPUUtils.getCpuMinFreq();
        LogUtils.i(String.format("cpuMinFreq=%d",cpuMinFreq));
    }
}

class CpuFileFilter implements FileFilter {
    CpuFileFilter() {
        super();
    }

    public boolean accept(File file) {
        boolean ret = Pattern.matches("cpu[0-9]", file.getName()) ? true : false;
        return ret;
    }
}