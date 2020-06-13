package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import java.util.HashMap;

import de.robv.android.xposed.XC_MethodHook;

public class MingBDLocation extends MingMethodHook {

	public MingBDLocation(XposedConf conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParams)
	    throws Throwable
	  {
		  Object obj = methodParams.args[0];
		  int paramInt = ((Integer)methodParams.args[1]).intValue();
		  HashMap paramHashMap = (HashMap)methodParams.args[2];
		  Util.debug("paramInt---"+paramInt);
		  Util.debug("paramHashMap---"+paramHashMap.size());
		  java.util.Iterator it = paramHashMap.entrySet().iterator();
		  while (it.hasNext())
		  {
			  java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
	        String key = (String)entry.getKey();
	        String value = (String)entry.getValue();
			  Util.debug(key+"---"+value);
		}		  
	  }		

}
