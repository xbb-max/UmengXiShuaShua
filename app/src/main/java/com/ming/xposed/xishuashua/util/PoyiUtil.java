package com.ming.xposed.xishuashua.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.ming.util.android.AUtil;
import com.ming.util.common.Util;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.execution.CommandCapture;
import com.stericson.RootTools.execution.Shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

public class PoyiUtil {
	
	public static void killProcess(Context context, String pack)
	{
        String ss = PoyiUtil.do_exec("ps");
        System.out.println(ss);
        String[] pids = PoyiUtil.findPID(ss, pack);
        for(String pid:pids)
        {
//            System.out.println("su killall -HUP "+pack);
        	String s = PoyiUtil.do_exec("killall -HUP "+pack);
            System.out.println(s);
            android.os.Process.sendSignal(Util.getInt(pid), 3); 
        }
        
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
//        am.forceStopPackage(pack);
//        am.killBackgroundProcesses(pack);
        
        
        ss = PoyiUtil.do_exec("ps");
        Util.debug(ss);
	}
	

	public static String[] findPID(String lines, String pack)
	{
        Vector<String> stringbuffer = new Vector<String>();
		int start = lines.indexOf(pack);
		if(start!=-1)
		{
			while(start!=-1)
			{
				int last_end = lines.lastIndexOf('\n', start);
				String line = lines.substring(last_end+1, start+pack.length());
		        Util.debug(line);
		        String[] ss = getSlipts(line," ");
    			stringbuffer.addElement(ss[1]);
//		        
//		        for(String s:ss)
//		        {
//		        	Util.debug("--------------:"+s);
//		        }
				start = lines.indexOf(pack, start+pack.length());
			}
			
		}
		
        String[] ss  = new String[stringbuffer.size()];
        stringbuffer.copyInto(ss);
		return ss;
	}
	
	private static String[] getSlipts(String line, String tag)
	{
		StringBuffer sb = new StringBuffer();
        Vector<String> stringbuffer = new Vector<String>();
	    for(int i=0;i<line.length();i++)
	    {
	    	String chr = line.charAt(i)+"";
	    	if(chr.equals(tag))
	    	{
	    		if(sb.length()>0)
	    		{
	    			stringbuffer.addElement(sb.toString());
	    			sb.delete(0, sb.length());
	    		}
	    	}
	    	else
	    	{
	    		sb.append(chr);
	    	}
	    }
		
		if(sb.length()>0)
		{
			stringbuffer.addElement(sb.toString());
			sb.delete(0, sb.length());
		}
	    
        String[] ss  = new String[stringbuffer.size()];
        stringbuffer.copyInto(ss);
		return ss;
	}
	
	
    public static String do_exec(String cmd) {
    	String s = "\n";
    	try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(
								new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				s += line + "\n";				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return s; 		
    }
	
	public static void forceStop(Context context, String yourpkgname)
	{
	      try
	      {
				if (RootTools.isAccessGiven()) {
					RootTools.killProcess(yourpkgname);
				}
	          
	          Util.debug("-----------forceStop");
	          
	      }
	      catch(Exception e)
	      {
	      	e.printStackTrace();
	      }   		
	}		
	
	public static void installApp(Context context, Uri yourpkgname, Runnable run)
	{
	      try
	      {
//	    	  PackageManager pm = context.getPackageManager();  
//	    	  PackageInstallObserver ob = new PackageInstallObserver();
//	    	  ob.setRun(run);
//	    	  pm.installPackage(yourpkgname, ob ,PackageManager.INSTALL_REPLACE_EXISTING,"");
//	          Method forceStopPackage = am.getClass().getDeclaredMethod("clearApplicationUserData",String.class, android.content.pm.IPackageDataObserver.class); 
//	          forceStopPackage.setAccessible(true); 
//	          forceStopPackage.invoke(am, yourpkgname,new ClearCacheObserver()); 
	      }
	      catch(Exception e)
	      {
	      	e.printStackTrace();
	      }   		
	} 	
	
	public static void backupApp(Context context, String packname)
	{
	      try
	      {
	    	  PackageManager pm = context.getPackageManager();
//	    	  ApplicationInfo appInfo =  pm.getApplicationInfo(packname,PackageManager.GET_META_DATA);
	    	  List<ApplicationInfo> appInfos = pm.getInstalledApplications(PackageManager.GET_META_DATA);
	    	  for(ApplicationInfo appInfo:appInfos)
	    	  {
    	    	  Util.debug("appInfo.packageName----:"+appInfo.packageName);
	    		  if(appInfo.packageName.equals(packname))
	    		  {
	    	    	  Util.debug("appInfo.sourceDir----:"+appInfo.sourceDir);
	    	    	  byte[] data = Util.readFileData(appInfo.sourceDir);
	    	    	  AUtil.writeAppFileData(context, packname+".apk", data);
	    	    	  data = null;
	    	    	  break;
	    		  }
	    	  }
	    	  
	      }
	      catch(Exception e)
	      {
	      	e.printStackTrace();
	      }		
	}
	
	
	
	public static void uninstallApp(Context context, String yourpkgname, Runnable run)
	{
	      try
	      {
				if (RootTools.isAccessGiven()) {
					PoyiUtil.sendShell("pm uninstall "+yourpkgname,5000);
					if(run!=null)
					{
						run.run();
					}
				}
	      }
	      catch(Exception e)
	      {
	      	e.printStackTrace();
	      }   		
	}    
    
    
	public static void clearApplicationUserData(Context context, String yourpkgname)
	{
	      try
	      {
				if (RootTools.isAccessGiven()) 
				{
					PoyiUtil.sendShell("pm clear "+yourpkgname,5000);
//                    PoyiUtil.sendShell("ipclient 830e2bea02fe476282241dc31ac49459 1",5000);
					PoyiUtil.sendShell("pm grant com.ming.xposed.xishuashua android.permission.WRITE_EXTERNAL_STORAGE",5000);
					PoyiUtil.sendShell("pm grant com.ming.xposed.xishuashua android.permission.READ_EXTERNAL_STORAGE",5000);

					PoyiUtil.sendShell("pm grant com.shinyv.cnr android.permission.WRITE_EXTERNAL_STORAGE",5000);
					PoyiUtil.sendShell("pm grant com.shinyv.cnr android.permission.READ_EXTERNAL_STORAGE",5000);

				}	    	  
	      }
	      catch(Exception e)
	      {
	      	e.printStackTrace();
;	      }
	}	
	

    public static List<String> sendShell(String cmds, int tag)
    {
    	List<String> list = null;
		try 
		{
	    	CommandCapture command = new CommandCapture(0, cmds);
	    	RootTools.getShell(true).add(command).waitForFinish();
			Thread.sleep(668);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
    }	






}






