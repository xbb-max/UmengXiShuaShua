package com.ming.xposed.xishuashua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.logic.ShuaLiang;

public class MingReceiver extends BroadcastReceiver {

    public void onReceive(final Context context, Intent intent) {
        String installResult = intent.getExtras().getString("xishuashua");
        Util.debug("MingReceiver---:"+installResult);
        if(!Util.isNull(installResult))
        {
        	if(installResult.indexOf("ok")!=-1)
        	{
                int type = intent.getExtras().getInt("type");
                String channel = Util.replaceAll(ShuaLiang.app.channel, "_reg", "", true);
                Util.debug("MingReceiver---ok");
        		ShuaLiang.netCount(channel);
        		
        		//ShuaLiang.app.channel
        	}
        	else if(installResult.indexOf("fail")!=-1)
        	{
        		ShuaLiang.resetTimer();
        	}
        	
        	
        }
    }  
}  