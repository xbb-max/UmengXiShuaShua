package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import java.net.URL;

import de.robv.android.xposed.XC_MethodHook;

public class MingURL extends XC_MethodHook{

	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParams)
	    throws Throwable
	  {
		    URL url = (URL)methodParams.thisObject;
			Util.debug("ming------------http-----------:"+url.toString());
	  }	
	
	
}
