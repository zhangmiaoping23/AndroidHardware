package com.example.hardware.Util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * Created by zhangmp on 2018/5/31.
 */

public class SimCardUtils {
    /**
     * 无sim卡也有值:中国电信
     * @param argContext
     * @return
     */
    public static String getNetworkOperatorName(Context argContext) {
        String networkOperatorName;
        try {
            TelephonyManager telephonyManager = (TelephonyManager)argContext.getSystemService(Context.TELEPHONY_SERVICE);
            networkOperatorName = telephonyManager.getNetworkOperatorName();
        }
        catch(Exception exception) {
            networkOperatorName = "";
        }

        return networkOperatorName;
    }

    public static int getPhoneType(Context context) {
        int  phoneType = TelephonyManager.PHONE_TYPE_NONE;
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            phoneType = telephonyManager.getPhoneType();
        }
        return phoneType;
    }

    public static String getPhoneTypeDescription(int networkType){
        String phoneTypeDescription = "";
        switch (networkType){
            case TelephonyManager.PHONE_TYPE_NONE:{
                /** No phone radio. */
                phoneTypeDescription = "PHONE_TYPE_NONE";
            }
            break;

            case TelephonyManager.PHONE_TYPE_GSM:{
                /** Phone radio is GSM. */
                phoneTypeDescription = "PHONE_TYPE_GSM";
            }
            break;

            case TelephonyManager.PHONE_TYPE_CDMA:{
                /** Phone radio is CDMA. */
                phoneTypeDescription = "PHONE_TYPE_CDMA";
            }
            break;

            case TelephonyManager.PHONE_TYPE_SIP:{
                /** Phone is via SIP. */
                phoneTypeDescription = "PHONE_TYPE_SIP";
            }
            break;
        }
        return phoneTypeDescription;
    }
    /**
     * 无sim卡 line1Number=null
     * @param context
     * @return
     */
    public static String getLine1Number(Context context) {
        String line1Number = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            line1Number = telephonyManager.getLine1Number();
        }
        return line1Number;
    }

    /**
     * 无sim卡为空串
     * @param argContext
     * @return
     */
    public static String getSimOperatorName(Context argContext) {
        String simOperatorName;
        try {
            TelephonyManager telephonyManager = (TelephonyManager)argContext.getSystemService(Context.TELEPHONY_SERVICE);
            simOperatorName = telephonyManager.getSimOperatorName();
        }
        catch(Exception exception) {
            simOperatorName = "";
        }

        return simOperatorName;
    }

    /**
     * 无sim卡也有值:China Net
     * @param argContext
     * @return
     */
    public static String getCarrierName(Context argContext) {
        String networkOperatorName = getNetworkOperatorName(argContext).toLowerCase();
        String ret = "none";
        if((networkOperatorName.equals("中国移动")) || (networkOperatorName.equals("china mobile")) || (networkOperatorName.equals("chinamobile"))) {
            ret = "China Mobile";
        }
        else if(networkOperatorName.equals("中国联通") || networkOperatorName.equals("china unicom") || networkOperatorName.equals("chinaunicom")) {
            ret = "China Unicom";
        }
        else if(networkOperatorName.equals("中国电信") || networkOperatorName.equals("china net") || networkOperatorName.equals("chinanet")) {
            ret = "China Net";
        }else{

        }

        return ret;
    }

    public static String getICCIDBySubscriptionManager(Context context){
        String iccid = "";
        //sdk level不小于22使用
        if(Build.VERSION.SDK_INT >= 22){
            //int index = DEFAULT_SLOT_INDEX;
            int index = 0;
            SubscriptionInfo info = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(index);
            if (info != null) {
                iccid = info.getIccId();
            }
        }
        return iccid;
    }

    public static String getICCID(Context context){
        return getSimSerialNumber(context);
    }

    public static String getReflectICCID(Context context){
        String reflectICCID = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Method getSubscriberInfoMethod = telephonyManager.getClass().getDeclaredMethod("getSubscriberInfo");
            getSubscriberInfoMethod.setAccessible(true);
            Object objectTelephonyManager = getSubscriberInfoMethod.invoke(telephonyManager);
            Method getPhoneMethod = objectTelephonyManager.getClass().getDeclaredMethod("getPhone",int.class);
            getPhoneMethod.setAccessible(true);
            Object objectPhone = getPhoneMethod.invoke(objectTelephonyManager,0);
            Method getFullIccSerialNumberMethod = objectPhone.getClass().getMethod("getFullIccSerialNumber");
            reflectICCID = (String) getFullIccSerialNumberMethod.invoke(objectPhone);

        } catch (Throwable e) {
        }
        return reflectICCID;
    }

    /*
     * ICCID
     * 无sim卡，simSerialNumber=null
     */
    public static String getSimSerialNumber(Context context) {
        String simSerialNumber = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simSerialNumber = telephonyManager.getSimSerialNumber();
        }
        return simSerialNumber;
    }

    public static String getImsi(Context context){
        return getSubscriberId(context);
    }

    /*
     * 无sim卡为""
     */
    public static String getSimCountryIso(Context context) {
        String  simCountryIso = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simCountryIso = telephonyManager.getSimCountryIso();
        }
        return simCountryIso;
    }

    public static int getNetworkType(Context context) {
        int  networkType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            networkType = telephonyManager.getNetworkType();
        }
        return networkType;
    }

    public static int getDataNetworkType(Context context) {
        int  networkType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
        if(Build.VERSION.SDK_INT >= 24){
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if(null != telephonyManager){
                networkType = telephonyManager.getDataNetworkType();
            }
        }

        return networkType;
    }

    public static int getVoiceNetworkType(Context context) {
        int  networkType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
        if(Build.VERSION.SDK_INT >= 24){
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if(null != telephonyManager){
                networkType = telephonyManager.getVoiceNetworkType();
            }
        }
        return networkType;
    }


    public static String getNetworkTypeDescription(int networkType){
        String networkTypeDescription = "";
        switch (networkType){
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:{
                networkTypeDescription = "NETWORK_TYPE_UNKNOWN";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_GPRS:{
                /** Current network is GPRS */
                networkTypeDescription = "NETWORK_TYPE_GPRS";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_EDGE:{
                /** Current network is EDGE */
                networkTypeDescription = "NETWORK_TYPE_EDGE";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_UMTS:{
                /** Current network is UMTS */
                networkTypeDescription = "NETWORK_TYPE_UMTS";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_CDMA:{
                /** Current network is CDMA: Either IS95A or IS95B*/
                networkTypeDescription = "NETWORK_TYPE_CDMA";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_EVDO_0:{
                /** Current network is EVDO revision 0*/
                networkTypeDescription = "NETWORK_TYPE_EVDO_0";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_EVDO_A:{
                /** Current network is EVDO revision A*/
                networkTypeDescription = "NETWORK_TYPE_EVDO_A";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_1xRTT:{
                /** Current network is 1xRTT*/
                networkTypeDescription = "NETWORK_TYPE_1xRTT";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_HSDPA:{
                /** Current network is HSDPA */
                networkTypeDescription = "NETWORK_TYPE_HSDPA";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_HSUPA:{
                /** Current network is HSUPA */
                networkTypeDescription = "NETWORK_TYPE_HSUPA";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_HSPA:{
                /** Current network is HSPA */
                networkTypeDescription = "NETWORK_TYPE_HSPA";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_IDEN:{
                /** Current network is iDen */
                networkTypeDescription = "NETWORK_TYPE_IDEN";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_EVDO_B:{
                /** Current network is EVDO revision B*/
                networkTypeDescription = "NETWORK_TYPE_EVDO_B";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_LTE:{
                /** Current network is LTE */
                networkTypeDescription = "NETWORK_TYPE_LTE";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_EHRPD:{
                /** Current network is eHRPD */
                networkTypeDescription = "NETWORK_TYPE_EHRPD";
            }
            break;

            case TelephonyManager.NETWORK_TYPE_HSPAP:{
                /** Current network is HSPA+ */
                networkTypeDescription = "NETWORK_TYPE_HSPAP";
            }
            break;

            case 16:{
                /** Current network is GSM {@hide} */
                networkTypeDescription = "NETWORK_TYPE_GSM";
            }
            break;

            case 17:{
                /** Current network is TD_SCDMA {@hide} */
                networkTypeDescription = "NETWORK_TYPE_TD_SCDMA";
            }
            break;

            case 18:{
                /** Current network is IWLAN {@hide} */
                networkTypeDescription = "NETWORK_TYPE_IWLAN";
            }
            break;

        }
        return networkTypeDescription;
    }

    public static String getNetworkCountryIso(Context context) {
        String  networkCountryIso = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            networkCountryIso = telephonyManager.getNetworkCountryIso();
        }
        return networkCountryIso;
    }

    /*
     * 无sim卡，subscriberId=null
     */
    public static String getSubscriberId(Context context){
        /*
        simOperator = MCC + MNC
        IMSI = MCC + MNC + MSIN
        国际移动用户识别码（IMSI：International Mobile Subscriber Identification Number）是区别移动用户的标志，储存在SIM卡中，可用于区别移动用户的有效信息。
        其总长度不超过15位，同样使用0~9的数字。其中MCC是移动用户所属国家代号，占3位数字，中国的MCC规定为460；MNC是移动网号码，由两位或者三位数字组成，中国移动
        的移动网络编码（MNC）为00.用于识别移动用户所归属的移动通信网；MSIN是移动用户识别码，用以识别某一移动通信网中的移动用户。
         */
        String subscriberId = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            subscriberId = telephonyManager.getSubscriberId();
        }
        return subscriberId;
    }

    /*
     *无sim卡也有值:46003
     */
    public static String getNetworkOperator(Context context) {
        String  networkOperator = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            networkOperator = telephonyManager.getNetworkOperator();
        }
        return networkOperator;
    }

    /*
    *无sim卡,返回空串
    */
    public static String getSimOperator(Context context) {
        String  simOperator = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simOperator = telephonyManager.getSimOperator();
        }
        return simOperator;
    }

    public static String getMCC(String operator){
        String mcc = "";
        if(!operator.isEmpty()){
            mcc = operator.substring(0,3);
        }
        return mcc;
    }

    public static String getMNC(String operator){
        String mnc = "";
        if(!operator.isEmpty()){
            mnc = operator.substring(3);
        }
        return mnc;
    }

    public static int getSimState(Context context){
        int simState = TelephonyManager.SIM_STATE_UNKNOWN;
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simState = telephonyManager.getSimState();
        }
        return simState;
    }

    public static String getSimStateDescription(int simState){
        String simStateDescription = "";
        switch (simState){
            case TelephonyManager.SIM_STATE_UNKNOWN:{
                simStateDescription = "SIM_STATE_UNKNOWN";
            }
            break;

            case TelephonyManager.SIM_STATE_ABSENT:{
                /** SIM card state: no SIM card is available in the device */
                simStateDescription = "SIM_STATE_ABSENT";
            }
            break;

            case TelephonyManager.SIM_STATE_PIN_REQUIRED:{
                /** SIM card state: Locked: requires the user's SIM PIN to unlock */
                simStateDescription = "SIM_STATE_PIN_REQUIRED";
            }
            break;

            case TelephonyManager.SIM_STATE_PUK_REQUIRED:{
                /** SIM card state: Locked: requires the user's SIM PUK to unlock */
                simStateDescription = "SIM_STATE_PUK_REQUIRED";
            }
            break;

            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:{
                /** SIM card state: Locked: requires a network PIN to unlock */
                simStateDescription = "SIM_STATE_NETWORK_LOCKED";
            }
            break;

            case TelephonyManager.SIM_STATE_READY:{
                /** SIM card state: Ready */
                simStateDescription = "SIM_STATE_READY";
            }
            break;

            case 6:{
                /** SIM card state: SIM Card is NOT READY*/
                simStateDescription = "SIM_STATE_NOT_READY";
            }
            break;

            case 7:{
                /** SIM card state: SIM Card Error, permanently disabled*/
                simStateDescription = "SIM_STATE_PERM_DISABLED";
            }
            break;

            case 8:{
                /** SIM card state: , present but faulty*/
                simStateDescription = "SIM_STATE_CARD_IO_ERROR";
            }
            break;
        }
        return simStateDescription;
    }

    /*
     * 无sim卡,ResoucesConfiguration.mcc=0 ResoucesConfiguration.mnc=0
     */
    public static Configuration getResoucesConfiguration(Context context){
        Configuration configuration = null;
        Resources resources = context.getResources();
        if(null != resources){
            configuration = resources.getConfiguration();
        }
        return configuration;
    }

    public static String getInfo(Context context){
        String logInfo = "";

        //network部分和sim卡存在无关
        int networkType = SimCardUtils.getNetworkType(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils networkType=%d networkTypeDescription=%s",networkType,SimCardUtils.getNetworkTypeDescription(networkType)));

        int dataNetworkType = SimCardUtils.getDataNetworkType(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils dataNetworkType=%d dataNetworkTypeDescription=%s",dataNetworkType,SimCardUtils.getNetworkTypeDescription(dataNetworkType)));

        int voiceNetworkType = SimCardUtils.getVoiceNetworkType(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils voiceNetworkType=%d voiceNetworkTypeDescription=%s",voiceNetworkType,SimCardUtils.getNetworkTypeDescription(voiceNetworkType)));

        String networkCountryIso = SimCardUtils.getNetworkCountryIso(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils networkCountryIso=%s",networkCountryIso));

        String networkOperatorName = SimCardUtils.getNetworkOperatorName(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils networkOperatorName=%s",networkOperatorName));

        String carrierName = SimCardUtils.getCarrierName(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils carrierName=%s",carrierName));


        int phoneType = SimCardUtils.getPhoneType(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils phoneType=%d phoneTypeDescription=%s",phoneType,SimCardUtils.getPhoneTypeDescription(phoneType)));

        String simCountryIso = SimCardUtils.getSimCountryIso(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils simCountryIso=%s",simCountryIso));

        String simOperatorName = SimCardUtils.getSimOperatorName(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils simOperatorName=%s",simOperatorName));

        String simSerialNumber_ICCID = SimCardUtils.getICCID(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils simSerialNumber_ICCID=%s",simSerialNumber_ICCID));

        String reflectICCID = SimCardUtils.getReflectICCID(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils reflectICCID=%s",reflectICCID));
        String iccidBySubscriptionManager = SimCardUtils.getICCIDBySubscriptionManager(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils iccidBySubscriptionManager=%s",iccidBySubscriptionManager));

        int simState = SimCardUtils.getSimState(context);
        String simStateDescription = SimCardUtils.getSimStateDescription(simState);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils simState= %d simStateDescription=%s",simState,simStateDescription));

        logInfo = getMccAndMnc(logInfo,context);

        String line1Number = SimCardUtils.getLine1Number(context);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils line1Number=%s",line1Number));

        /*
        华为8.0无SIM卡,WIFI网络
        SimCardUtils networkType=7 networkTypeDescription=NETWORK_TYPE_1xRTT
        SimCardUtils dataNetworkType=7 dataNetworkTypeDescription=NETWORK_TYPE_1xRTT
        SimCardUtils voiceNetworkType=7 voiceNetworkTypeDescription=NETWORK_TYPE_1xRTT
        SimCardUtils networkCountryIso=cn
        SimCardUtils networkOperatorName=中国电信
        SimCardUtils carrierName=China Net
        SimCardUtils phoneType=2 phoneTypeDescription=PHONE_TYPE_CDMA
        SimCardUtils simCountryIso=
        SimCardUtils simOperatorName=
        SimCardUtils simSerialNumber_ICCID=null
        SimCardUtils simState= 1 simStateDescription=SIM_STATE_ABSENT
        SimCardUtils networkOperator=46003 mcc=460 mnc=03
        SimCardUtils simOperator= mcc= mnc=
        SimCardUtils subscriberId_imsi=null
        SimCardUtils ResoucesConfiguration.mcc=0 ResoucesConfiguration.mnc=0
        SimCardUtils line1Number=null
         */

        /*
        OPPO R9m8.0 有电信SIM卡,WIFI网络
        SimCardUtils networkType=13 networkTypeDescription=NETWORK_TYPE_LTE
        SimCardUtils dataNetworkType=0 dataNetworkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils voiceNetworkType=0 voiceNetworkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils networkCountryIso=cn
        SimCardUtils networkOperatorName=中国电信
        SimCardUtils carrierName=China Net
        SimCardUtils phoneType=2 phoneTypeDescription=PHONE_TYPE_CDMA
        SimCardUtils simCountryIso=cn
        SimCardUtils simOperatorName=中国电信
        SimCardUtils simSerialNumber_ICCID=89860317245927627265
        SimCardUtils simState= 5 simStateDescription=SIM_STATE_READY
        SimCardUtils networkOperator=46003 mcc=460 mnc=03
        SimCardUtils simOperator=46003 mcc=460 mnc=03
        SimCardUtils subscriberId_imsi=460036960147295 mcc=460 mnc=03
        SimCardUtils ResoucesConfiguration.mcc=460 ResoucesConfiguration.mnc=3
        SimCardUtils line1Number=

         */

        /*
        Oppo R9m 有电信SIM卡,WIFI网络
        SimCardUtils networkType=14 networkTypeDescription=NETWORK_TYPE_EHRPD
        SimCardUtils dataNetworkType=0 dataNetworkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils voiceNetworkType=0 voiceNetworkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils networkCountryIso=cn
        SimCardUtils networkOperatorName=中国电信
        SimCardUtils carrierName=China Net
        SimCardUtils phoneType=2 phoneTypeDescription=PHONE_TYPE_CDMA
        SimCardUtils simCountryIso=cn
        SimCardUtils simOperatorName=中国电信
        SimCardUtils simSerialNumber_ICCID=89860318245922746218
        SimCardUtils simState= 5 simStateDescription=SIM_STATE_READY
        SimCardUtils networkOperator=46003 mcc=460 mnc=03
        SimCardUtils simOperator=46003 mcc=460 mnc=03
        SimCardUtils subscriberId_imsi=460036960204851 mcc=460 mnc=03
        SimCardUtils ResoucesConfiguration.mcc=460 ResoucesConfiguration.mnc=3
        SimCardUtils line1Number=

         */
         /*
         XiaoMi9的Android9,双卡：主卡 移动   副卡 联通
        SimCardUtils networkType=13 networkTypeDescription=NETWORK_TYPE_LTE
        SimCardUtils dataNetworkType=13 dataNetworkTypeDescription=NETWORK_TYPE_LTE
        SimCardUtils voiceNetworkType=13 voiceNetworkTypeDescription=NETWORK_TYPE_LTE
        SimCardUtils networkCountryIso=cn
        SimCardUtils networkOperatorName=中国移动
        SimCardUtils carrierName=China Mobile
        SimCardUtils phoneType=1 phoneTypeDescription=PHONE_TYPE_GSM
        SimCardUtils simCountryIso=cn
        SimCardUtils simOperatorName=中国移动
        SimCardUtils simSerialNumber_ICCID=898600E51314F2071232
        SimCardUtils simState= 5 simStateDescription=SIM_STATE_READY
        SimCardUtils networkOperator=46000 mcc=460 mnc=00
        SimCardUtils simOperator=46001 mcc=460 mnc=01
        SimCardUtils subscriberId_imsi=460027592317732 mcc=460 mnc=02
        SimCardUtils ResoucesConfiguration.mcc=460 ResoucesConfiguration.mnc=2
        SimCardUtils line1Number=

          */

        /*
        LGE Nexus 5 Android4.4.4 无SIM卡
        SimCardUtils networkType=0 networkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils dataNetworkType=0 dataNetworkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils voiceNetworkType=0 voiceNetworkTypeDescription=NETWORK_TYPE_UNKNOWN
        SimCardUtils networkCountryIso=
        SimCardUtils networkOperatorName=
        SimCardUtils carrierName=none
        SimCardUtils phoneType=1 phoneTypeDescription=PHONE_TYPE_GSM
        SimCardUtils simCountryIso=
        SimCardUtils simOperatorName=
        SimCardUtils simSerialNumber_ICCID=null
        SimCardUtils simState= 1 simStateDescription=SIM_STATE_ABSENT
        SimCardUtils networkOperator= mcc= mnc=
        SimCardUtils simOperator= mcc= mnc=
        SimCardUtils subscriberId_imsi=null
        SimCardUtils ResoucesConfiguration.mcc=0 ResoucesConfiguration.mnc=0
        SimCardUtils line1Number=null

         */

        /*

         */
        return logInfo;
    }

    public static String getMccAndMnc(String logInfo,Context context){
        String networkOperator = SimCardUtils.getNetworkOperator(context);
        String mcc = SimCardUtils.getMCC(networkOperator);
        String mnc = SimCardUtils.getMNC(networkOperator);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils networkOperator=%s mcc=%s mnc=%s",networkOperator,mcc,mnc));

        String simOperator = SimCardUtils.getSimOperator(context);
        String simMcc = SimCardUtils.getMCC(simOperator);
        String simMnc = SimCardUtils.getMNC(simOperator);
        logInfo = LogUtils.record(logInfo,String.format("SimCardUtils simOperator=%s mcc=%s mnc=%s",simOperator,simMcc,simMnc));

        String subscriberId = SimCardUtils.getSubscriberId(context);
        if(subscriberId == null){
            logInfo = LogUtils.record(logInfo,"SimCardUtils subscriberId_imsi=null ");
        }else{
            if(subscriberId.length() >= 6){
                logInfo = LogUtils.record(logInfo,String.format("SimCardUtils subscriberId_imsi=%s mcc=%s mnc=%s",subscriberId,subscriberId.substring(0,3),subscriberId.substring(3,5)));
            }else{
                logInfo = LogUtils.record(logInfo,String.format("SimCardUtils subscriberId_imsi=%s",subscriberId));
            }

        }

        Configuration configuration = SimCardUtils.getResoucesConfiguration(context);
        if(null == configuration){
            logInfo = LogUtils.record(logInfo,"SimCardUtils ResoucesConfiguration=null");
        }else{
            logInfo = LogUtils.record(logInfo,String.format("SimCardUtils ResoucesConfiguration.mcc=%d ResoucesConfiguration.mnc=%d",configuration.mcc,configuration.mnc));
        }

        return logInfo;
    }

}
