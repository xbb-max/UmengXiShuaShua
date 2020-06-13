package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingJSONObject extends MingMethodHook {

	
	public MingJSONObject(XposedConf conf) {
		super(conf);
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String mName = methodParam.method.getName();
		    if (mName.equals("toString"))
		    {
		    	String json = (String)methodParam.getResult();
		    	Util.debug("umeng-"+json);
		    }  
	  }	
	
}
