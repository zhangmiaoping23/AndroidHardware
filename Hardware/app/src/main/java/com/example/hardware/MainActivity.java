package com.example.hardware;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.hardware.Util.AuthAccountUtils;
import com.example.hardware.Util.BatteryUtils;
import com.example.hardware.Util.BuildPropFileUtils;
import com.example.hardware.Util.CPUUtils;
import com.example.hardware.Util.ClassLoaderUtils;
import com.example.hardware.Util.DisplayUtils;
import com.example.hardware.Util.GoogleUtils;
import com.example.hardware.Util.HardwareUtils;
import com.example.hardware.Util.HuaweiHardwareUtils;
import com.example.hardware.Util.LgHardwareUtils;
import com.example.hardware.Util.NubiaHardwareUtils;
import com.example.hardware.Util.MobileNetworkUtils;
import com.example.hardware.Util.PackageUtils;
import com.example.hardware.Util.PhoneLocaleUtils;
import com.example.hardware.Util.Qiku360HardwareUtils;
import com.example.hardware.Util.SensorUtils;
import com.example.hardware.Util.SmartisanHardwareUtils;
import com.example.hardware.Util.StoreUtils;
import com.example.hardware.Util.OppoHardwareUtils;
import com.example.hardware.Util.RuntimeUtils;
import com.example.hardware.Util.SdcardUtils;
import com.example.hardware.Util.SignatureUtils;
import com.example.hardware.Util.SimCardUtils;
import com.example.hardware.Util.SystemFeatureUtils;
import com.example.hardware.Util.VivoHardwareUtils;
import com.example.hardware.Util.WifiUtils;
import com.example.hardware.Util.XiaomiHardwareUtils;
import com.example.hardware.rootdetect.RootDetectUtils;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String showInfo = "";
        try{
            ClassLoaderUtils.testLoaded(this);
            String path = this.getDatabasePath("accounts.db").getPath();
            //AuthAccountUtils.testGoogleAccount(this);

            BuildPropFileUtils.getStaticInstance();
            SignatureUtils.getSignatureHashCode(this);
            SignatureUtils.getSignaturesMd5(this,"com.xiaomi.market");

            showInfo += HardwareUtils.getInfo(this);

            showInfo += OppoHardwareUtils.getInfo(this);

            showInfo += VivoHardwareUtils.getInfo(this);

            showInfo += HuaweiHardwareUtils.getInfo(this);

            showInfo += XiaomiHardwareUtils.getInfo(this);

            showInfo += Qiku360HardwareUtils.getInfo(this);

            showInfo += SmartisanHardwareUtils.getInfo(this);

            showInfo += NubiaHardwareUtils.getInfo(this);

            showInfo += LgHardwareUtils.getInfo(this);

            showInfo += WifiUtils.getInfo(this);

            showInfo += MobileNetworkUtils.getInfo(this);

            showInfo += "\r\n";
            showInfo += SimCardUtils.getInfo(this);

            showInfo += "\r\n";
            showInfo += SdcardUtils.getInfo();

            showInfo += "\r\n";
            showInfo += DisplayUtils.getInfo(this);

            showInfo += "\r\n";
            showInfo += CPUUtils.getInfo();

            showInfo += "\r\n";
            showInfo += StoreUtils.getInfo();

            showInfo += "\r\n";
            showInfo += BatteryUtils.getInfo(this);

            showInfo += "\r\n";
            showInfo += SensorUtils.getInfo(this);

            showInfo += "\r\n";
            showInfo += PhoneLocaleUtils.getInfo(this);

            showInfo += "\r\n";
            showInfo += RuntimeUtils.getInfo();

            showInfo += "\r\n";
            showInfo += RootDetectUtils.getInfo(this);

            showInfo += GoogleUtils.getInfo(this);

            showInfo += PackageUtils.getInfo(this);

            showInfo += SystemFeatureUtils.getInfo(this);


        }catch (Exception e){
            e.printStackTrace();
        }

        TextView showTextView =   ((TextView) findViewById(R.id.tv_showinfo));
        showTextView.setText(showInfo);
        /**
         * 只有调用了该方法，TextView才能不依赖于ScrollView而实现滚动的效果。
         * 要在XML中设置TextView的textcolor，否则，当TextView被触摸时，会灰掉。
         */
        showTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

}
