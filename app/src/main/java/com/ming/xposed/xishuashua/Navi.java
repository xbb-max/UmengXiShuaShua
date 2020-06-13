package com.ming.xposed.xishuashua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.WindowManager;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.bean.App;
import com.ming.xposed.xishuashua.ui.NaviInfo;

public class Navi extends Activity {

	
	NaviInfo naviInfo;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        try
        {
            PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
            @SuppressLint("InvalidWakeLockTag")
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
            wakeLock.acquire();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        App app = XiShuaShuaConf.loadApp(this);
        naviInfo = new NaviInfo(this,app);
        Util.debug("naviInfo---"+naviInfo);
//		this.setTitle(app.name);
		
        setContentView(naviInfo);
        
    }	
    
}
