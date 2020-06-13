package com.ming.xposed.xishuashua.mod.umeng;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.XiShuaShuaConf;
import com.ming.xposed.xishuashua.mod.logic.MyLogic;

public class Default extends MyLogic {
	public Default(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public void run()
	{
		try
		{
			Util.debug("MingXposedMod.isNetCount:"+XiShuaShuaConf.isNetCount);
			Util.debug("MingXposedMod.isCpaOK:"+XiShuaShuaConf.isCpaOK);
			if(XiShuaShuaConf.isNetCount)
			{
		    	Intent intent = new Intent();
		    	intent.setAction("com.ming.xposed.xishuashua");
		    	if(XiShuaShuaConf.isRegistOK)
		    	{
			    	intent.putExtra("xishuashua", "regist");
		    	}
		    	else
		    	{
			    	intent.putExtra("xishuashua", "ok");
		    	}
		    	intent.putExtra("type", XiShuaShuaConf.appType);
		    	context.sendBroadcast(intent);
		    	XiShuaShuaConf.isNetCount = false;
			}
			//08-05 15:59:41.753: I/System.out(1165): minglog-current-----:27.189.201.223


			if(XiShuaShuaConf.isFail)
			{
		    	Intent intent = new Intent();
		    	intent.setAction("com.ming.xposed.xishuashua");  
		    	intent.putExtra("xishuashua", "fail"); 
		    	context.sendBroadcast(intent);
				whichSubPage = 1;
				XiShuaShuaConf.isFail = false;
			}
			
			
//			if(XiShuaShuaConf.isCpaOK)
			{
//				if(XiShuaShuaConf.appType==XiShuaShuaConf.APP_CPA)
				{
					if(clickNum%2==0)
					{
						 View view = getRootView(context);
				    	 float x = 130;
				    	 float y = 410;
						Util.debug("click-----"+x+":"+y);
				    	 this.clickView(view, x, y);
					}
					 clickNum++;					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
