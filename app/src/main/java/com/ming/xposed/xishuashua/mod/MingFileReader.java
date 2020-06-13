package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingFileReader extends MingMethodHook {

	public MingFileReader(XposedConf conf) {
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
			    Util.debug("MingFileReader---cpa---o---"+mName+"---"+methodParam.args[0]+"---"+methodParam.getResult());
			    String path = (String)methodParam.args[0];
			    if(!Util.isNull(path))
			    {
			    	if(path.endsWith("/cpufreq/scaling_cur_freq"))
			    	{
			    		methodParam.args[0] = XposedConf.prop_Path+"/scaling_cur_freq";
			    	}
			    	if(path.endsWith("/cpufreq/cpuinfo_max_freq"))
			    	{
			    		methodParam.args[0] = XposedConf.prop_Path+"/cpuinfo_max_freq";
			    	}
			    	if(path.endsWith("/cpufreq/cpuinfo_min_freq"))
			    	{
			    		methodParam.args[0] = XposedConf.prop_Path+"/cpuinfo_min_freq";
			    	}
			    	if(path.endsWith("/proc/cpuinfo"))
			    	{
			    		methodParam.args[0] = XposedConf.prop_Path+"/cpuinfo";
			    	}
			    }			  
			    Util.debug("MingFileReader---cpa---n---"+mName+"---"+methodParam.args[0]+"---"+methodParam.getResult());
		  }
	  }

}
