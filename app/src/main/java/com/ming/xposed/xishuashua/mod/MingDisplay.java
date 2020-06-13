package com.ming.xposed.xishuashua.mod;


import android.util.DisplayMetrics;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MingDisplay extends MingMethodHook {

	public MingDisplay(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
	}
	
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String meName = methodParam.method.getName();
//		    Util.debug(meName+"--screen---old--"+methodParam.getResult());
		  if(values!=null)
		  {
			    Util.debug(meName+"--MingDisplay--"+methodParam.getResult());
			    Util.debug(meName+"--MingDisplay-w-"+values.get("getWidth"));
			    Util.debug(meName+"--MingDisplay-h-"+values.get("getHeight"));
			    if (meName.equals("getMetrics"))
			    {
			    	  DisplayMetrics dm = (DisplayMetrics)methodParam.args[0];
			    	  try 
			    	  {
			    		  dm.widthPixels = Util.getInt(values.get("getWidth"));
			    		  dm.heightPixels = Util.getInt(values.get("getHeight"));
			    		  
//				          Field widField = DisplayMetrics.class.getDeclaredField("noncompatWidthPixels");
//		                    widField.setAccessible(true);
//		                    widField.set(dm, ));
//			    		  
//		                    Field heightField = DisplayMetrics.class.getDeclaredField("noncompatHeightPixels");
//		                    heightField.setAccessible(true);
//		                    heightField.set(dm, Util.getInt(values.get("getHeight")));
			    	  }
			    	  catch (Exception e) {
			    		  e.printStackTrace();
						// TODO: handle exception
					}
			    }
			    else if (meName.equals("getWidth"))
			    {
				    methodParam.setResult(Util.getInt(values.get("getWidth")));
			    }  
			    else if (meName.equals("getHeight"))
			    {
				    methodParam.setResult(Util.getInt(values.get("getHeight")));
			    }  			    
			    
			    
		  }
		    Util.debug(meName+"--screen--new--"+methodParam.getResult());
	  }
	  
	  XC_LoadPackage.LoadPackageParam packParam;
		public void setPackParam(LoadPackageParam packParam) {
			this.packParam = packParam;
		}	
			  

}
