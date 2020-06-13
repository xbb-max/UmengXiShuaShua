package com.ming.xposed.xishuashua.mod;


import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingSystem extends MingMethodHook{

	public MingSystem(XposedConf conf) {
		super(conf);
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String mName = methodParam.method.getName();
		    if (mName.equals("currentTimeMillis"))
			{
//			    Util.debug("System.currentTimeMillis----before----"+methodParam.getResult());
		    	long currentTimeMillis = (Long)methodParam.getResult()+XposedConf.timeDelay;
//			    Util.debug("System.currentTimeMillis----after----"+currentTimeMillis);
		    	methodParam.setResult(currentTimeMillis);
		    }				
		    
		    
		    
	  }	
}
