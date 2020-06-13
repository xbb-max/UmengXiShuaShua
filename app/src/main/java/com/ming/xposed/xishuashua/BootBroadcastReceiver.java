package com.ming.xposed.xishuashua;


import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ming.util.common.Util;

public class BootBroadcastReceiver extends BroadcastReceiver {
    static final String action_boot="android.intent.action.BOOT_COMPLETED";


	@Override
	public void onReceive(Context context, Intent intent) {
//		try
//		{
//			KeyguardManager km = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
//			KeyguardLock kk = km.newKeyguardLock("");
//			kk.disableKeyguard();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		Util.debug("boot-:"+intent);


        if (intent.getAction().equals(action_boot)){

            Intent ootStartIntent=new Intent(context, Main.class);
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ootStartIntent); 
        }
	}
 
}