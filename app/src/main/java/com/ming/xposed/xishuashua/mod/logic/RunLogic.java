package com.ming.xposed.xishuashua.mod.logic;


import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ming.util.android.TimerHandler;
import com.ming.util.common.Util;

import java.util.Hashtable;

public class RunLogic implements Runnable {
	
	
	public static String appName;
	public static String appPack;
	public static boolean isCommit;
	public static boolean isOnlyRate;
	public static String account;
	public static String password;
	public static String say;
	
	
	
	public TimerHandler timer;
	public Context context;
	public MyLogic myLogic;
	public MyLogic defaultLogic;
	public Hashtable<String, MyLogic> logics;
	
	public RunLogic(Context context) {
		
		try
		{
			this.context = context;
			logics = new Hashtable<String, MyLogic>();
	        NotificationManager m_NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	        m_NotificationManager.cancelAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		timer = new TimerHandler(this, 3000);
	}
	
	public void setApp()
	{
	    if(myLogic!=null)
	    {
		    myLogic.setApp(RunLogic.appName, RunLogic.appPack);
		    myLogic.account = RunLogic.account;
		    myLogic.password = RunLogic.password;
		    myLogic.say = RunLogic.say;
		    myLogic.runLogic = this;
	    }
	    
	    
	    if(defaultLogic!=null)
	    {
	    	defaultLogic.setApp(RunLogic.appName, RunLogic.appPack);
	    	defaultLogic.account = RunLogic.account;
	    	defaultLogic.password = RunLogic.password;
	    	defaultLogic.say = RunLogic.say;
	    	defaultLogic.runLogic = this;
	    }		
	    
	    
	    
	}
	

	

	public void onPause() {
		if(timer!=null)
		{
			timer.stop();
		}
		
	}

	public void onResume() {
		
		
		if(timer!=null)
		{
			timer.start();
		}
	}
	
	public void showDialog(Dialog dialog) {
		if(myLogic!=null)
		{
			myLogic.showDialog(dialog);
		}
		if(defaultLogic!=null)
		{
			defaultLogic.showDialog(dialog);
		}
		
	}	

	public void onCreate() {
	}




	public void run() {
	    final String cn = ((Activity) context).getLocalClassName();
		Util.debug("cn------------------------::::---------------"+cn);
		
		try
		{
			if(myLogic!=null)
			{
				Util.debug("logic----myLogic::::"+myLogic+"------------------"+myLogic.whichSubPage);
				myLogic.run();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			if(defaultLogic!=null)
			{
				Util.debug("logic----defaultLogic::::"+defaultLogic+"------------------"+defaultLogic.whichSubPage);
				defaultLogic.run();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void loadLogic(Context context2) {
		// TODO Auto-generated method stub
		
	}



	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	public void start() {
	    if(myLogic!=null)
	    {
			timer.start();
	    }
	}

	public void stop() {
	    if(myLogic!=null)
	    {
			timer.stop();
	    }
	}

	public String getCode(Context context) {
		// TODO Auto-generated method stub
		return "ming";
	}

	public String getChannel(Context context) {
		// TODO Auto-generated method stub
		return "ming";
	}
	
	public static String getPartner(Context context, String pkgName)
    {
    	String partner = null;
    	try
    	{
        	ApplicationInfo appInfo = null;
    		Util.debug("getPartner--------pkgName----::"+pkgName);
        	try
        	{
        		appInfo = context.getPackageManager().getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}

        	if(appInfo!=null)
        	{
        		partner = appInfo.metaData.get("UMENG_APPKEY")+"";  
        		Util.debug("getPartner--------partner----::"+partner);
        	}    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return partner;
    }	
	
}
