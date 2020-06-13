package com.ming.xposed.xishuashua.mod;

import java.util.Hashtable;

import de.robv.android.xposed.XC_MethodHook;

public class MingMethodHook extends XC_MethodHook{

	  public Hashtable<String, String> values;
	  public XposedConf conf;
	  
	  public MingMethodHook(XposedConf conf)
	  {
		  this.conf = conf;
	  }
	  
	  public String get(String name)
	  {
		  return values.get(name);
	  }
	
	
	
}
