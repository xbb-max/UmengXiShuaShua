package com.ming.xposed.xishuashua.mod.logic;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.ming.util.common.Util;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class InstallerReceiver extends BroadcastReceiver {

	Hashtable<String, String> installedApps;
	
	@Override
	public void onReceive(final Context context, Intent intent) {
		if(intent!=null)
		{
			
			   System.out.println("ming--------------------------InstallerReceiver------intent.getAction():"+intent.getAction());
			   System.out.println("ming--------------------------InstallerReceiver------packageName:"+intent.getDataString());
		   if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) 
		   {
	            String packageName = intent.getDataString();
	            int start = packageName.indexOf("package:");
	            if(start!=-1)
	            {
	            	packageName = packageName.substring(start+"package:".length());
	            }
			   
	            rest(context,"apk_installed",packageName);
//	            if(packageName.equals(pack))
	            {
         	
	            }
		   }
		   context.unregisterReceiver(this);
		   ((Activity)context).moveTaskToBack(true);
		}
	}
	
	public static void rest(final Context context, final String action, final String packageName)
	{
		new Thread() {
			@Override
			public void run() {
				
				Vector<String> paths = new Vector<String>();
				String pack = context.getPackageName();
				paths.addElement("/data/data/" + pack + "/cache/");
				paths.addElement("/data/data/" + pack + "/files/");
				paths.addElement("/data/data/" + pack + "/databases/");
				paths.addElement("/data/data/" + pack + "/shared_prefs/");
				paths.addElement("/data/data/" + pack + "/app_dex/");
				paths.addElement("/data/data/" + pack + "/hiapk_market/");
				paths.addElement("/data/data/" + pack + "/app_MyLibs/");
				paths.addElement("/data/data/" + pack + "/event/");
				paths.addElement("/data/data/" + pack + "/app_Plugins/");
				paths.addElement("/data/data/" + pack + "/cache/");
				
				
				
				File file = new File("/data/data/" + pack );
				File[] files = file.listFiles();
				if(files!=null)
				{
					for(File file2:files)
					{
						System.out.println("------------------------------------------------"+file2.getPath());
						if(!file2.getPath().endsWith("/lib"))
						{
	        				paths.addElement(file2.getPath());
						}
					}
				}
				

				for (String path : paths) {
					Util.delFolder(path);
				}
				
				
				
		    	Intent intent2 = new Intent();
	            intent2.setAction("com.ming.poyi");  
	            intent2.putExtra("action", action);  
	            intent2.putExtra("package", packageName);  
	            context.sendBroadcast(intent2);
	            
	            NotificationManager m_NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	            m_NotificationManager.cancelAll();
	            
			}
		}.start();	   		
	}
	
	private void loadInstalledApk(Context context)
	{
		installedApps.clear();
		List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
				continue;
			}
			installedApps.put(p.packageName, p.applicationInfo.loadLabel(context.getPackageManager()).toString());
		}			
	}

	private String pack;
	public void setPackage(String pack) {
		this.pack = pack;
	}		

}
