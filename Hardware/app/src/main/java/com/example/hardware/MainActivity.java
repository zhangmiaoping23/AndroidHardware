package com.example.hardware;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.hardware.Util.BuildPropFileUtils;
import com.example.hardware.Util.CPUUtils;
import com.example.hardware.Util.DisplayUtils;
import com.example.hardware.Util.HardwareUtils;
import com.example.hardware.Util.HuaweiHardwareUtils;
import com.example.hardware.Util.StoreUtils;
import com.example.hardware.Util.OppoHardwareUtils;
import com.example.hardware.Util.RuntimeUtils;
import com.example.hardware.Util.SdcardUtils;
import com.example.hardware.Util.SignatureUtils;
import com.example.hardware.Util.SimCardUtils;
import com.example.hardware.Util.VivoHardwareUtils;
import com.example.hardware.Util.WifiUtils;
import com.example.hardware.Util.XiaomiHardwareUtils;
import com.example.hardware.rootdetect.RootDetectUtils;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BuildPropFileUtils.getStaticInstance();
        SignatureUtils.getSignatureHashCode(this);

        String showInfo = "";
        showInfo += HardwareUtils.getInfo(this);

        showInfo += "\r\n";
        showInfo += OppoHardwareUtils.getInfo(this);

        showInfo += "\r\n";
        showInfo += VivoHardwareUtils.getInfo(this);

        showInfo += "\r\n";
        showInfo += HuaweiHardwareUtils.getInfo(this);

        showInfo += "\r\n";
        showInfo += XiaomiHardwareUtils.getInfo(this);

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
        showInfo += RuntimeUtils.getInfo();

        showInfo += "\r\n";
        showInfo += WifiUtils.getInfo(this);

        showInfo += "\r\n";
        showInfo += RootDetectUtils.getInfo(this);

        TextView showTextView =   ((TextView) findViewById(R.id.tv_showinfo));
        showTextView.setText(showInfo);
        /**
         * 只有调用了该方法，TextView才能不依赖于ScrollView而实现滚动的效果。
         * 要在XML中设置TextView的textcolor，否则，当TextView被触摸时，会灰掉。
         */
        showTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

}
