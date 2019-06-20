package com.example.hardware.rootdetect;

import com.example.hardware.Util.LogUtils;

/*
import android.securitydiagnose.HwSecurityDiagnoseManager.StpExtraStatusInfo;
import android.securitydiagnose.HwSecurityDiagnoseManager;
import com.huawei.android.os.BuildEx.VERSION;
import com.huawei.secure.android.common.util.LogsUtil;
*/
public class HuaweiEmui10RootDetectUtils {
    private static final int CATEGORY_ROOT = 8;
    private static final int RESULT_RISK = 1;
    private static final String TAG = "Emui10RootDetect";

    public static boolean isRoot() {
        boolean isRoot = false;
        /*
        try {
            if(BuildEx.VERSION.EMUI_SDK_INT >= 21) {
                if(1 == HwSecurityDiagnoseManager.getInstance().getStpStatusByCategory(8, false, false, new HwSecurityDiagnoseManager.StpExtraStatusInfo())) {
                    isRoot = true;
                }
            }
        }
        catch(Throwable throwable) {
        }
        */
        return isRoot;
    }
}

