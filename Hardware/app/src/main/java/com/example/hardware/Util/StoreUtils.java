package com.example.hardware.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zhangmp on 2019/1/23.
 */

public class StoreUtils {

    /*
    root@R9:/ # cat /proc/meminfo
    cat /proc/meminfo
    MemTotal:        3846568 kB
    MemFree:         1199228 kB
    Buffers:           94264 kB
    Cached:          1227144 kB
    SwapCached:         2968 kB
    Active:           840116 kB
    Inactive:        1259884 kB
    Active(anon):     542828 kB
    Inactive(anon):   236876 kB
    Active(file):     297288 kB
    Inactive(file):  1023008 kB
    Unevictable:         304 kB
    Mlocked:               0 kB
    SwapTotal:        524284 kB
    SwapFree:         444872 kB
    Dirty:                64 kB
    Writeback:             0 kB
    AnonPages:        776944 kB
    Mapped:           290384 kB
    Shmem:               808 kB
    Slab:             127420 kB
    SReclaimable:      56468 kB
    SUnreclaim:        70952 kB
    KernelStack:       41024 kB
    PageTables:        44120 kB
    NFS_Unstable:          0 kB
    Bounce:                0 kB
    WritebackTmp:          0 kB
    CommitLimit:     2447568 kB
    Committed_AS:   117648588 kB
    VmallocTotal:   251658176 kB
    VmallocUsed:      814584 kB
    VmallocChunk:   250703456 kB

     */
    public static long getRamTotalSizeInKB(){
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        long RamTotalSize = 0;
        try {
            fileReader = new FileReader("/proc/meminfo");
            bufferReader = new BufferedReader(fileReader,80);
            String line = bufferReader.readLine();
            if(line != null) {
                String totalSize = line.substring(line.indexOf(':') + 1, line.indexOf('k')).trim();
                RamTotalSize = Long.parseLong(totalSize);
            }
        }catch (Throwable throwable){

        }
        finally {
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
        }

        return RamTotalSize;
    }

    public static String getInfo() {
        String logInfo = "";
        String tmp = "";

        long ramTotalSizeInKB = StoreUtils.getRamTotalSizeInKB();
        tmp = String.format("ramTotalSizeInKB=%d \"KB\"",ramTotalSizeInKB);
        logInfo = LogUtils.record(logInfo,tmp);

        return logInfo;

    }
}
