package com.ming.xposed.xishuashua.mod;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ming.util.common.Util;

import de.robv.android.xposed.XC_MethodHook;

public class MingNetworkInfo extends MingMethodHook {

	public MingNetworkInfo(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		  if(values!=null)
		  {
			    String mName = methodParam.method.getName();
//			    Util.debug("MingNetworkInfo----o---"+mName+"---"+methodParam.getResult());
			    
			    if (mName.equals("getTypeName"))
			    {
			    	if(Util.getBoolean(values.get("isUsedMobileNetwork")))
			    	{
				    	String type = "MOBILE";
				    	methodParam.setResult(type);
			    	}
			    }  

			    if (mName.equals("getState"))
			    {
			    	if(Util.getBoolean(values.get("isUsedMobileNetwork")))
			    	{
			    		NetworkInfo networkInfo = (NetworkInfo)methodParam.thisObject;
			    		int type = networkInfo.getType();
//					    Util.debug("MingNetworkInfo-----type---"+type);
			    		if(type== ConnectivityManager.TYPE_MOBILE)
			    		{
					    	NetworkInfo.State state = NetworkInfo.State.CONNECTED;
					    	methodParam.setResult(state);
			    		}
			    		else
			    		{
					    	NetworkInfo.State state = NetworkInfo.State.DISCONNECTED;
					    	methodParam.setResult(state);
			    		}
			    		
			    	}
			    }
			  if (mName.equals("getSubtypeName")) {
				  if (Util.getBoolean(values.get("isUsedMobileNetwork"))) {
					  switch (Util.getRandom(3)) {
						  case 0:
							  methodParam.setResult("HSPA");
							  break;
						  case 1:
							  methodParam.setResult("GPRS");
							  break;
						  case 2:
							  methodParam.setResult("LTE");
							  break;
					  }
				  }
			  }

//			    Util.debug("MingNetworkInfo-----n---"+mName+"---"+methodParam.getResult());
		  }
		  
	  }
		
	

}
