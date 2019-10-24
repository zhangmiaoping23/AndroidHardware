package com.example.hardware.Util;
import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class PhoneLocaleUtils {
    public static String getLocaleDefaultLanuage(){
        String language = Locale.getDefault().getLanguage();
        return language;
    }

    public static String getLocaleDefaultCountry(){
        String country = Locale.getDefault().getCountry();
        return country;
    }

    public static String getConfigurationLocaleLanuage(Context context){
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return language;
    }

    public static String getConfigurationLocaleCountry(Context context){
        String country = context.getResources().getConfiguration().locale.getCountry();
        return country;
    }
    public static String getInfo(Context context) {
        String logInfo = "";
        String localeDefaultLanuage = PhoneLocaleUtils.getLocaleDefaultLanuage();
        logInfo = LogUtils.record(logInfo, String.format("localeDefaultLanuage=%s", localeDefaultLanuage));

        String localeDefaultCountry = PhoneLocaleUtils.getLocaleDefaultCountry();
        logInfo = LogUtils.record(logInfo, String.format("localeDefaultCountry=%s", localeDefaultCountry));

        //一般是在ComponentCallbacks使用
        String configurationLocaleLanuage = PhoneLocaleUtils.getConfigurationLocaleLanuage(context);
        logInfo = LogUtils.record(logInfo, String.format("configurationLocaleLanuage=%s", configurationLocaleLanuage));

        String configurationLocaleCountry = PhoneLocaleUtils.getConfigurationLocaleCountry(context);
        logInfo = LogUtils.record(logInfo, String.format("configurationLocaleCountry=%s", configurationLocaleCountry));


        return logInfo;
    }
}