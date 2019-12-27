package com.example.hardware.Util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;

/**
 * Created by zhangmp on 2019/12/11.
 */

public class PermissionUtils {
    public static boolean hasPermission(Context context,String queryPermission){
        boolean ret = context.checkPermission(queryPermission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED ? true : false;
        return ret;
    }
}
