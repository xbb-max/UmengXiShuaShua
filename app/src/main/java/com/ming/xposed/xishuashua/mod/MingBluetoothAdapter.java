package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingBluetoothAdapter extends MingMethodHook{

	public MingBluetoothAdapter(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String meName = methodParam.method.getName();
		    Util.debug("MingBluetoothAdapter--"+meName);
		  if(values!=null)
		  {
			    Util.debug(meName+"--bluetooth--"+methodParam.getResult());
			    if (meName.equals("getDefaultAdapter"))
			    {
				  
			    }  
			    else if (meName.equals("getAddress"))
			    {
				    methodParam.setResult(get("getBluetoothMac"));
			    }  
		  }
	  }	

}
