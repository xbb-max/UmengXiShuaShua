package com.ming.xposed.xishuashua;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.bean.App;
import com.ming.xposed.xishuashua.mod.logic.RunLogic;
import com.ming.xposed.xishuashua.mod.umeng.Umeng_Logic;


import java.net.URI;

import de.robv.android.xposed.XC_MethodHook.MethodHookParam;

public class XiShuaShuaConf {
	public static String pkgName = "com.shinyv.cnr";
	public static String pkgClz = "com.shinyv.cnr.StartActivity_";
	public static String app_id = "ut";
	public static String appName = "YunTing-Umeng";
	
	
	public static int APP_CPA = 0;
	public static int appType = APP_CPA;
	
	
	public static boolean isCpaOK = false;
	public static boolean isNetCount = false;
	public static boolean isRegistOK = false;
	public static boolean isHuo = false;
	public static boolean isFail = false;
	public static String account;
	public static String password;
	public static int usedNum = 0;
	public static int usedType;
	
	
	public static String getVersion(Context context)
	{
		String v = "";
		try
		{
			PackageManager manager;
			PackageInfo info = null;
			manager = context.getPackageManager();
			info = manager.getPackageInfo(XiShuaShuaConf.pkgName, 0);
			v = info.versionName;
			
			Util.debug("info.versionName::"+info.versionName);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}		
	
	
	public static String getCode(Context context) {
		
		String code = "";
//    	try
//    	{
//            File file = new File(context.getPackageManager().getApplicationInfo(XiShuaShuaConf.pkgName, 0).sourceDir);  
//            if(file.exists())
//            {
//                byte[] data = Util.getZipData(file.getPath(), "res/raw/associate_tag.txt");
//                String config = new String(data);
//                code = config;
//            }
//    	}
//    	catch(Exception e)
//    	{
//    		e.printStackTrace();
//    	}		
		
		return code;
	}

	public static String getChannel(Context context) {
		// TODO Auto-generated method stub
		return "YT单行版";
	}	
		
	
	public static App loadApp(Context context)
	{
		App app = null;
		try
		{
//			String partner = getPartner(context);
//			RunLogic runlogic = getRunLogic(context);
			
			
//			if(runlogic!=null)
			{
				app = new App();
				
//				app.devices = "leshi";
				
				String version = getVersion(context);
				String code = getCode(context);
			    String channel = getChannel(context);
				String partner = code+"_"+channel;

				
				
				app.id = XiShuaShuaConf.app_id;
				if(appType== XiShuaShuaConf.APP_CPA)
				{
					app.name = XiShuaShuaConf.appName+code+"号("+channel+")";
					app.channel = XiShuaShuaConf.app_id+"_"+channel+"_"+code;
				}
				app.pack = XiShuaShuaConf.pkgName;
				app.cls = XiShuaShuaConf.pkgClz;
				app.imsi = "";
				app.type = "";
				app.shua_type = "";
				app.partner = partner;
				app.version = version;
				if(appType== XiShuaShuaConf.APP_CPA)
				{
					app.delayTime = 15;
				}
				
				Util.debug(app.channel+"------------");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return app;
	}	
	

	public static RunLogic getRunLogic(Context context) {
		return new Umeng_Logic(context);
	}
	
	public static void getAbstractHttpClientMonitor(MethodHookParam methodParams) {
		
//	    HttpUriRequest localHttpUriRequest = (HttpUriRequest)methodParams.args[0];
//	    URI localURI = localHttpUriRequest.getURI();
//		String url = localURI.toString();
//	  	if(url.indexOf("umeng")!=-1)
//  	  	{
//  	  	}
		
	}


	
}
