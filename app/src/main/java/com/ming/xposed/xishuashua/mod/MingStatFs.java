package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingStatFs extends MingMethodHook{

	public MingStatFs(XposedConf conf) {
		super(conf);
	    values = conf.getValues("Build");
	}
	
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		  String mName = methodParam.method.getName();
		  if(values!=null)
		  {
//			    Util.debug("MingStatFs-----o---"+mName+"---"+methodParam.getResult());
			    if (mName.equals("getBlockCount"))
			    {
			    	int t = Util.getInt(get("getTotalInternalMemorySize"));
			    	methodParam.setResult(t);
			    }  
			    if (mName.equals("getAvailableBlocks"))
			    {
			    	int a = Util.getInt(get("getAvailableInternalMemorySize"));
			    	methodParam.setResult(a);
			    }
//			    Util.debug("MingStatFs-----n---"+mName+"---"+methodParam.getResult());
		  }
	  }		
	

}
