package com.example.hardware.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

/**
 * Created by zhangmp on 2018/5/15.
 */

public class SignatureUtils {
    public static int getSignatureHashCode(Context context){
        Signature signature = getSignature(context);
        int hashCode = signature.hashCode();
        //返回的结果例子：1572712583
        return hashCode;
    }
    public static Signature getSignature(Context argContext) {
        Signature signature = null;
        try {
            String packageName = argContext.getPackageName();
            PackageManager packageManager = argContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName,packageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            signature = signatures[0];
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return signature;
    }
}
