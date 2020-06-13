package com.ming.xposed.xishuashua.mod;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingSettingsSystem extends MingMethodHook {

	public MingSettingsSystem(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		  if(values!=null)
		  {
			    String mName = methodParam.method.getName();
			    if (mName.equals("getString"))
			    {
			    	Util.debug("getString----"+methodParam.getResult());
			    	methodParam.setResult("");
			    }  
 
		  }
	  }
//      String str = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2)[1];
	
//	  10-18 22:09:55.970: INFO/System.out(888): minglog-MingFileReader---cpa---o---java.io.FileReader---/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq---null
//	  10-18 22:09:55.970: INFO/System.out(888): minglog-MingFileReader---cpa---n---java.io.FileReader---null
//	  10-18 22:09:56.000: INFO/System.out(888): minglog-MingFileReader---cpa---o---java.io.FileReader---/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq---null
//	  10-18 22:09:56.000: INFO/System.out(888): minglog-MingFileReader---cpa---n---java.io.FileReader---null
//	  10-18 22:09:56.000: INFO/System.out(888): minglog-MingFileReader---cpa---o---java.io.FileReader---/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq---null
//	  10-18 22:09:56.000: INFO/System.out(888): minglog-MingFileReader---cpa---n---java.io.FileReader---null
//	  10-18 22:09:56.000: INFO/System.out(888): minglog-MingFileReader---cpa---o---java.io.FileReader---/proc/cpuinfo---null
//	  10-18 22:09:56.000: INFO/System.out(888): minglog-MingFileReader---cpa---n---java.io.FileReader---null
	

}
