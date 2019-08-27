package com.example.hardware.Util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by zhangmp on 2019/8/26.
 */

public class GoogleUtils {
    public static String getGsfId(Context context) {
        String ret = null;
        try {

            //adb shell 'sqlite3 /data/data/com.google.android.gsf/databases/gservices.db "select * from main where name = \"android_id\";"'
            Uri gservicesUri = Uri.parse("content://com.google.android.gsf.gservices");
            Cursor cursor = context.getContentResolver().query(gservicesUri, null, null, new String[]{"android_id"}, null);
            if(cursor == null) {
                ret ="Not found";
            }else{
                if(!cursor.moveToFirst() || cursor.getColumnCount() < 2) {
                    cursor.close();
                    ret ="Not found";
                }else {
                    Long id = Long.parseLong(cursor.getString(1));
                    String gsfId = Long.toHexString(id);
                    ret = gsfId;
                    cursor.close();
                }
            }
        }
        catch(SecurityException securityException) {
            securityException.printStackTrace();
            ret = null;
        }
        catch(Exception exception) {
            exception.printStackTrace();
            ret = null;
        }

        return ret;
    }

    public static String getInfo(Context context){
        String logInfo = "";
        String gsfId = GoogleUtils.getGsfId(context);
        if((gsfId != null) && !gsfId.equals("Not found")){
            logInfo = LogUtils.record(logInfo,"");

            logInfo = LogUtils.record(logInfo, String.format("gsfId=%s", gsfId));
        }

        return logInfo;
    }
}
