package com.ming.xposed.xishuashua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.util.PoyiUtil;

public class MingReboot extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Util.debug("MingReboot-------------"+intent.getAction());
		  if(intent.getAction().equals("reboot")){
			  //
		    	if(Build.VERSION.SDK_INT==10)
		    	{
					  PoyiUtil.sendShell("reboot", 5000);
		    	}
		    	else
		    	{
					  PoyiUtil.sendShell("setprop ctl.restart surfaceflinger; setprop ctl.restart zygote", 5000);
		    	}			  
          }
		// TODO Auto-generated method stub
		
	}

}
