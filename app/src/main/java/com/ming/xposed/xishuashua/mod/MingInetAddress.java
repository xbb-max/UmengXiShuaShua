package com.ming.xposed.xishuashua.mod;

import de.robv.android.xposed.XC_MethodHook;

public class MingInetAddress extends MingMethodHook {

	public MingInetAddress(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
	}
	
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String mName = methodParam.method.getName();
			if(values!=null)
			{
//			    Util.debug("MingInetAddress-----o---"+mName+"---"+methodParam.getResult());
			    if (mName.equals("getHostAddress"))
				{
					methodParam.setResult(get("getHostAddress"));
			    }				
//			    Util.debug("MingInetAddress-----n---"+mName+"---"+methodParam.getResult());
			}
	  }	

}
