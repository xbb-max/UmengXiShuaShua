package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingTelephonyManager extends MingMethodHook {
	
	  public MingTelephonyManager(XposedConf conf)
	  {
		  super(conf);
		  values = conf.getValues("TelephonyManager");
	  }
	  
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		  if(values!=null)
		  {
			    String mName = methodParam.method.getName();
			    if (mName.equals("getDeviceId"))
			    {
			    	methodParam.setResult(get("imei"));
			    }
			    if (mName.equals("getSimSerialNumber"))
			    {
			    	methodParam.setResult(get("getSimSerialNumber"));
			    }
			    if (mName.equals("getSubscriberId"))
			    {
			    	methodParam.setResult(get("imsi"));
			    }
			    if (mName.equals("getLine1Number"))
			    {
			    	methodParam.setResult(get("getLine1Number"));
			    }
			    if (mName.equals("getSimCountryIso"))
			    {
			    	methodParam.setResult(get("getSimCountryIso"));
			    }
			    if (mName.equals("getSimOperator"))
			    {
			    	methodParam.setResult(get("getSimOperator"));
			    }
			    if (mName.equals("getSimOperatorName"))
			    {
			    	methodParam.setResult(get("getSimOperatorName"));
			    }
			    if (mName.equals("getNetworkCountryIso"))
			    {
			    	methodParam.setResult(get("getNetworkCountryIso"));
			    }
			    if (mName.equals("getNetworkOperator"))
			    {
			    	methodParam.setResult(get("getNetworkOperator"));
			    }
			    if (mName.equals("getNetworkOperatorName"))
			    {
			    	methodParam.setResult(get("getNetworkOperatorName"));
			    }
			    if (mName.equals("getNetworkType"))
			    {
			    	methodParam.setResult(Util.getInt(get("getNetworkType")));
			    }
			    if (mName.equals("getPhoneType"))
			    {
			    	methodParam.setResult(Util.getInt(get("getPhoneType")));
			    }
			    if (mName.equals("hasIccCard"))
			    {
			    	methodParam.setResult(Util.getBoolean(get("hasIccCard")));
			    }
			    if (mName.equals("getSimState"))
			    {
			    	methodParam.setResult(Util.getInt(get("getSimState")));
			    }
			    if (mName.equals("getDeviceSoftwareVersion"))
			    {
			    	methodParam.setResult(get("getDeviceSoftwareVersion"));
			    }
			    if (mName.equals("isNetworkRoaming"))
			    {
			    	methodParam.setResult(Util.getBoolean(get("isNetworkRoaming")));
			    }
			    if (mName.equals("getCallState"))
			    {
			    	methodParam.setResult(Util.getInt(get("getCallState")));
			    }			  
		  }
	  }

}
