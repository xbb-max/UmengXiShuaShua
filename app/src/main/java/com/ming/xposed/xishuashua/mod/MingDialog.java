package com.ming.xposed.xishuashua.mod;

import android.app.Dialog;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingDialog extends MingMethodHook {
	
	
	  public MingDialog(XposedConf conf)
	  {
		  super(conf);
	  }	  
		
		
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {	    
		    String mName = methodParam.method.getName();
		    Util.debug("mName::::"+mName);
		    if (mName.equals("show"))
		    {
		    	Dialog ad = ((Dialog)methodParam.thisObject);
		    	if(ad!=null)
		    	{
		    		ad.cancel();
		    	}
		    }
	  }
	
}
