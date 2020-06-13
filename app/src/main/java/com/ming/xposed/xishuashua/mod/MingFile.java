package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingFile extends MingMethodHook {

	public MingFile(XposedConf conf) {
		super(conf);
		// TODO Auto-generated constructor stub
		  values = conf.getValues("Build");
	}
	
	
	  protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		  String mName = methodParam.method.getName();
		  if(values!=null)
		  {
//			    Util.debug("MingFile---cpa---o---"+mName+"---"+methodParam.args[0]+"---"+methodParam.getResult());
			    String path = (String)methodParam.args[0];
			    if(!Util.isNull(path))
			    {
			    	if(path.endsWith("/proc/meminfo"))
			    	{
			    		methodParam.args[0] = XposedConf.prop_Path+"/meminfo";
			    	}
			    }			  
//			    Util.debug("MingFileReader---cpa---n---"+mName+"---"+methodParam.args[0]+"---"+methodParam.getResult());
		  }
	  }

}
