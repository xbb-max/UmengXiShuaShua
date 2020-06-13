package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class com_umeng_commonsdk_statistics_common_DeviceConfig extends MingMethodHook{

	public com_umeng_commonsdk_statistics_common_DeviceConfig(XposedConf conf) {
		super(conf);
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String mName = methodParam.method.getName();
		    Util.debug("com_umeng_commonsdk_statistics_common_DeviceConfig---call----"+mName);

	  }	
	

}
