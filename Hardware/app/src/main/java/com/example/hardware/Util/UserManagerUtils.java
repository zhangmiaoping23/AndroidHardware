package com.example.hardware.Util;

import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;

/**
 * Created by zhangmp on 2019/8/30.
 */

public class UserManagerUtils {
    public static void getUserInfo(Context context){
        UserManager userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
       // int myUserId = UserHandle.myUserId();

        //int userSerialNumber = userManager.getUserSerialNumber(myUserId);
        //if(userSerialNumber >= 0) {
            //androidCheckinRequest.setUserSerialNumber(userSerialNumber);
        //}
    }
    public static String getInfo(Context context){
        String logInfo = "";
        return logInfo;
    }
}
