package com.ming.xposed.xishuashua.mod;

import de.robv.android.xposed.XC_MethodHook;

public class MingWifiInfo extends MingMethodHook {
	
	
	  public MingWifiInfo(XposedConf conf)
	  {
		  super(conf);
		  values = conf.getValues("WifiInfo");
	  }	  
		
		
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {	    
		  if(values!=null)
		  {
			    String mName = methodParam.method.getName();
			    if (mName.equals("getMacAddress"))
			    {
			    	methodParam.setResult(get("getMacAddress"));
			    }
			    if (mName.equals("getSSID"))
			    {
			    	methodParam.setResult(get("getSSID"));
			    }
			    if (mName.equals("getBSSID"))
			    {
			    	methodParam.setResult(get("getBSSID"));
			    }
		  }
	  }
	
}
