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
            signature = getSignature(argContext,packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static Signature getSignature(Context argContext,String packageName) {
        Signature signature = null;
        try {
            PackageManager packageManager = argContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName,packageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            signature = signatures[0];
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return signature;
    }
    public static String getSignaturesMd5(Context context,String packageName) {
        String signaturesMd5 = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName,packageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            int length = signatures != null ? signatures.length : 0;
            if(length != 0) {
                String  signatureCharsString = packageInfo.signatures[0].toCharsString();
                signaturesMd5 = Md5Utils.getMD5(signatureCharsString).toLowerCase();
                for(int index = 1; index < length; ++index) {
                    signatureCharsString = packageInfo.signatures[index].toCharsString();
                    signaturesMd5 = signaturesMd5 + "," + Md5Utils.getMD5(signatureCharsString).toLowerCase();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return signaturesMd5;
    }
}
