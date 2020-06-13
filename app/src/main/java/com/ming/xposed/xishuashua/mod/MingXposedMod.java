package com.ming.xposed.xishuashua.mod;


import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.XiShuaShuaConf;
import com.ming.xposed.xishuashua.mod.logic.RunLogic;
import com.ming.xposed.xishuashua.util.PoyiUtil;


import java.net.URL;
import java.util.Hashtable;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.ming.xposed.xishuashua.mod.XposedConf.prop_Path;


public class MingXposedMod implements IXposedHookLoadPackage
{

	RunLogic runLogic = null;
	private XposedConf xposedConf;
	
	
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam packParam)
	    throws Throwable
    {
		  String pack = packParam.packageName;
    	  Util.debug("-----xposed---8:"+pack);
    	  
    	  
		  if(pack.equals(XiShuaShuaConf.pkgName))
		  {
	    	  Util.debug("ming-----xposed---ok----------------");
	    	  XiShuaShuaConf.isCpaOK = false;
	    	  XiShuaShuaConf.isNetCount = false;
			  xposedConf = new XposedConf();
			  xposedConf.load();
			  
			  
			  hackSystem(packParam);
			  hackRuntime(packParam);
			  hackFile(packParam);
			  hackFileReader(packParam);
			  hackStatFs(packParam);
			  hackBuild();
			  hackDisplay(packParam);
			  hackSettingsSecure(packParam);
			  hackSettingsSystem(packParam);
			  hackTelephonyManager(packParam);
			  hackInetAddress(packParam);
	       	  hackNetworkInfo(packParam);
			  hackWifiInfo(packParam);
			  hackBluetoothAdapter(packParam);
			  hackMingSensorManager(packParam);
			  
	       	  hackAbstractHttpClient(packParam);
	       	  hackURL(packParam);
			  
		      hackJSONObject(packParam);
			  
			  
		   //	  hack_com_umeng_commonsdk_statistics_common_DeviceConfig(packParam);
	       	  

   	          
   	          
   	          
			  XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class,new XC_MethodHook(){
		    	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParm)
		    	  {
		    		  xposedConf.setProxy();
		    		  Activity context = (Activity)methodParm.thisObject;
		    		  String actName = context.getLocalClassName();
			    	  Util.debug("ming-----xposed---"+XiShuaShuaConf.pkgName+"----------------onCreate:"+actName);
//					  String data = Util.readFileSdcard(prop_Path+"/mingconf.prop");
//					  showTips(context,"xposedconf="+data
//					  +" ming-----prop_Path---"+prop_Path +"----------------onCreate:"+actName,5);
			    	  if(runLogic==null)
			    	  {
			    		  runLogic = XiShuaShuaConf.getRunLogic(context); 
			    	  }
			    	  runLogic.loadLogic(context);
		    		  runLogic.onCreate();
		    	  }});
				  
				  XposedHelpers.findAndHookMethod(Activity.class, "onResume",new XC_MethodHook(){
			    	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParm)
			    	  {
			    		  Activity context = (Activity)methodParm.thisObject;
			    		  String actName = context.getLocalClassName();
				    	  Util.debug("ming-----xposed---"+XiShuaShuaConf.pkgName+"----------------onResume:"+actName);
				    	  if(runLogic!=null)
				    	  {
					    	  runLogic.loadLogic(context);
				    		  runLogic.onResume();
				    	  }
			    	  }});
				  
				  XposedHelpers.findAndHookMethod(Activity.class, "onPause",new XC_MethodHook(){
			    	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParm)
			    	  {
			    		  Activity context = (Activity)methodParm.thisObject;
			    		  String actName = context.getLocalClassName();
				    	  Util.debug("ming-----xposed---"+XiShuaShuaConf.pkgName+"----------------onPause:"+actName);
				    	  if(runLogic!=null)
				    	  {
					    	  runLogic.loadLogic(context);
				    		  runLogic.onPause();
				    	  }
			    	  }});	    	 
		  }
    }
	private Handler handler;

	public void showTips(final Context context,final String tips, final int t) {
		if (handler == null) {
			handler = new Handler(Looper.getMainLooper());
		}

		handler.post(new Runnable() {
			public void run() {
				try {
					Toast.makeText(context, tips,t).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
    private MingSystem mingSystem;
	private void hackSystem(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingSystem = new MingSystem(xposedConf);
		try
		{
			ClassLoader classLoader = packParam.classLoader;
//			Util.debug("Hook-cpa-------------------System");
			XposedHelpers.findAndHookMethod("java.lang.System",packParam.classLoader,"currentTimeMillis",new Object[]{mingSystem});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}




    private MingRuntime runntime;
	private void hackRuntime(XC_LoadPackage.LoadPackageParam packParam)
	{
		if(runntime==null)
		{
			runntime = new MingRuntime(xposedConf);
		}
		try
		{
			ClassLoader classLoader = packParam.classLoader;
			Class clz = classLoader.loadClass("java.lang.Runtime");
			Util.debug("Hook-cpa-------------------Runtime:"+clz);
			if(clz!=null)
			{
				XposedHelpers.findAndHookMethod(clz,"exec", String.class,runntime);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


    private MingFile mingFile;
	private void hackFile(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingFile = new MingFile(xposedConf);
		try
		{
			ClassLoader classLoader = packParam.classLoader;
			Class clz = classLoader.loadClass("java.io.File");
			Util.debug("Hook-cpa-------------------File:"+clz);
			if(clz!=null)
			{
				XposedHelpers.findAndHookConstructor(clz, String.class,mingFile);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    private MingFileReader mingFileReader;
	private void hackFileReader(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingFileReader = new MingFileReader(xposedConf);
		try
		{
			ClassLoader classLoader = packParam.classLoader;
			Class clz = classLoader.loadClass("java.io.FileReader");
			Util.debug("Hook-cpa-------------------FileReader:"+clz);
			if(clz!=null)
			{
				XposedHelpers.findAndHookConstructor(clz, String.class,mingFileReader);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    private MingStatFs mingStatFs;
	private void hackStatFs(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingStatFs = new MingStatFs(xposedConf);
		try
		{
			ClassLoader classLoader = packParam.classLoader;
			Class clz = classLoader.loadClass("android.os.StatFs");
			Util.debug("Hook-cpa-------------------StatFs:"+clz);
			if(clz!=null)
			{
				XposedHelpers.findAndHookMethod(clz,"getBlockCount",mingStatFs);
				XposedHelpers.findAndHookMethod(clz,"getAvailableBlocks",mingStatFs);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void hackBuild()
	{
		Hashtable<String, String> values = xposedConf.getValues("Build");
		if(values!=null)
		{
	        XposedHelpers.setStaticObjectField(Build.class, "ID", values.get("ID"));
	        XposedHelpers.setStaticObjectField(Build.class, "BOARD", values.get("BOARD"));
	        XposedHelpers.setStaticObjectField(Build.class, "BOOTLOADER", values.get("BOOTLOADER"));
	        XposedHelpers.setStaticObjectField(Build.class, "BRAND", values.get("BRAND"));
	        XposedHelpers.setStaticObjectField(Build.class, "CPU_ABI", values.get("CPU_ABI"));
	        XposedHelpers.setStaticObjectField(Build.class, "CPU_ABI2", values.get("CPU_ABI2"));
	        XposedHelpers.setStaticObjectField(Build.class, "DEVICE", values.get("DEVICE"));
	        XposedHelpers.setStaticObjectField(Build.class, "DISPLAY", values.get("DISPLAY"));
	        XposedHelpers.setStaticObjectField(Build.class, "FINGERPRINT", values.get("FINGERPRINT"));
	        XposedHelpers.setStaticObjectField(Build.class, "HARDWARE", values.get("HARDWARE"));
	        XposedHelpers.setStaticObjectField(Build.class, "HOST", values.get("HOST"));
	        XposedHelpers.setStaticObjectField(Build.class, "MANUFACTURER", values.get("MANUFACTURER"));
	        XposedHelpers.setStaticObjectField(Build.class, "MODEL", values.get("MODEL"));
	        XposedHelpers.setStaticObjectField(Build.class, "PRODUCT", values.get("PRODUCT"));
	        XposedHelpers.setStaticObjectField(Build.class, "RADIO", values.get("RADIO"));
	        XposedHelpers.setStaticObjectField(Build.class, "TAGS", values.get("TAGS"));
	        XposedHelpers.setStaticLongField(Build.class, "TIME", Util.getLong((String)values.get("TIME")));
	        XposedHelpers.setStaticObjectField(Build.class, "TYPE", values.get("TYPE"));
	        XposedHelpers.setStaticObjectField(Build.class, "USER", values.get("USER"));
	        XposedHelpers.setStaticObjectField(Build.VERSION.class, "CODENAME", values.get("VERSION_CODENAME"));
	        XposedHelpers.setStaticObjectField(Build.VERSION.class, "INCREMENTAL", values.get("VERSION_INCREMENTAL"));
	        XposedHelpers.setStaticObjectField(Build.VERSION.class, "RELEASE", values.get("VERSION_RELEASE"));
	        XposedHelpers.setStaticObjectField(Build.VERSION.class, "SDK", values.get("VERSION_SDK"));
	      //  XposedHelpers.setStaticIntField(Build.VERSION.class, "SDK_INT", Util.getInt((String)values.get("VERSION_SDK_INT")));

		}
	}


    private MingDisplay mingDisplay;
	private void hackDisplay(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingDisplay = new MingDisplay(xposedConf);
	    String clsName = Display.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getMetrics",new Object[]{DisplayMetrics.class,mingDisplay});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getWidth",new Object[]{mingDisplay});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getHeight",new Object[]{mingDisplay});
	}


    private MingSettingsSecure mingSettingsSecure;
	private void hackSettingsSecure(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingSettingsSecure = new MingSettingsSecure(xposedConf);
		XposedHelpers.findAndHookMethod(android.provider.Settings.Secure.class,"getString", ContentResolver.class, String.class,mingSettingsSecure);
	}

    private MingTelephonyManager mingTelephonyManager;
	private void hackTelephonyManager(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingTelephonyManager = new MingTelephonyManager(xposedConf);
		String clsName = TelephonyManager.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getDeviceId",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSimSerialNumber",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSubscriberId",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getLine1Number",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSimCountryIso",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSimOperator",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSimOperatorName",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getNetworkCountryIso",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getNetworkOperator",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getNetworkOperatorName",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getNetworkType",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getPhoneType",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"hasIccCard",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSimState",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getDeviceSoftwareVersion",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"isNetworkRoaming",new Object[]{mingTelephonyManager});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getCallState",new Object[]{mingTelephonyManager});
	}


    private MingInetAddress mingInetAddress;
	private void hackInetAddress(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingInetAddress = new MingInetAddress(xposedConf);
		try
		{
			ClassLoader classLoader = packParam.classLoader;
			Class clz = classLoader.loadClass("java.net.InetAddress");
			Util.debug("Hook-------------------InetAddress:"+clz);
			if(clz!=null)
			{
				XposedHelpers.findAndHookMethod(clz,"getHostAddress",mingInetAddress);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    private MingNetworkInfo mingNetworkInfo;
	private void hackNetworkInfo(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingNetworkInfo = new MingNetworkInfo(xposedConf);
		try
		{
			ClassLoader classLoader = packParam.classLoader;
			Class clz = classLoader.loadClass("android.net.NetworkInfo");
			Util.debug("Hook-cpa-------------------NetworkInfo:"+clz);
			if(clz!=null)
			{
				XposedHelpers.findAndHookMethod(clz,"getTypeName",mingNetworkInfo);
				XposedHelpers.findAndHookMethod(clz,"getState",mingNetworkInfo);
				XposedHelpers.findAndHookMethod(clz,"getSubtypeName",mingNetworkInfo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


    private MingWifiInfo mingWifiInfo;
	private void hackWifiInfo(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingWifiInfo = new MingWifiInfo(xposedConf);
		String clsName = WifiInfo.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getMacAddress",new Object[]{mingWifiInfo});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getSSID",new Object[]{mingWifiInfo});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getBSSID",new Object[]{mingWifiInfo});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getIpAddress",new Object[]{mingWifiInfo});
	}

	private MingBluetoothAdapter mingBluetoothAdapter;
	private void hackBluetoothAdapter(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingBluetoothAdapter = new MingBluetoothAdapter(xposedConf);
		String clsName = BluetoothAdapter.class.getName();
//		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getDefaultAdapter",new Object[]{mingBluetoothAdapter});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"getAddress",new Object[]{mingBluetoothAdapter});
	}

    private MingSensorManager mingSensorManager;
	private void hackMingSensorManager(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingSensorManager = new MingSensorManager(xposedConf);
		mingSensorManager.setPackParam(packParam);
		XposedHelpers.findAndHookMethod(SensorManager.class,"getSensorList",int.class,mingSensorManager);
	}


    private com_umeng_commonsdk_statistics_common_DeviceConfig mycom_umeng_commonsdk_statistics_common_DeviceConfig;
	private void hack_com_umeng_commonsdk_statistics_common_DeviceConfig(XC_LoadPackage.LoadPackageParam packParam)
	{
		mycom_umeng_commonsdk_statistics_common_DeviceConfig = new com_umeng_commonsdk_statistics_common_DeviceConfig(xposedConf);
		String clsName = "com.umeng.commonsdk.statistics.common.DeviceConfig";
		Util.debug("hook---"+clsName);
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"a",new Object[]{long.class,mycom_umeng_commonsdk_statistics_common_DeviceConfig});
	}  	
	
	
    private MingJSONObject mingJSONObject; 	  
	private void hackJSONObject(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingJSONObject = new MingJSONObject(xposedConf);
		String clsName = org.json.JSONObject.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"toString",new Object[]{mingJSONObject});
	}  	
	
	
	
	
	
    private MingSettingsSystem mingSettingsSystem; 	  
	private void hackSettingsSystem(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingSettingsSystem = new MingSettingsSystem(xposedConf);
		XposedHelpers.findAndHookMethod(android.provider.Settings.System.class,"getString", ContentResolver.class, String.class,mingSettingsSystem);
	}  	
	
	
	
    private MingWebViewClient mingWebViewClient; 	  
	private void hackWebView(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingWebViewClient = new MingWebViewClient();
		String clsName = WebViewClient.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"onPageFinished",new Object[]{WebView.class, String.class,mingWebViewClient});
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"onPageStarted",new Object[]{WebView.class, String.class, Bitmap.class,mingWebViewClient});
	}	
	
	
    private MingBDLocation mingBDLocation; 	  
    boolean isCpaHookOK = false;
	private void hackonComplete(XC_LoadPackage.LoadPackageParam packParam)
	{
		if(mingBDLocation==null)
		{
			mingBDLocation = new MingBDLocation(xposedConf);
		}
//		if(!isCpaHookOK)
//		{
//			try {
//				ClassLoader classLoader = packParam.classLoader;
//				Class clz = classLoader.loadClass("com.plateno.botao.ui.member.LoginActivity");
//				Util.debug("hook-BDLocation-------------------:"+clz);
//				if(clz!=null)
//				{
//					Class Platform_clz = classLoader.loadClass("cn.sharesdk.framework.Platform");
//					XposedHelpers.findAndHookMethod(clz,"onComplete",new Object[]{Platform_clz,int.class,HashMap.class,mingTelephonyManager});
//					isCpaHookOK = true;
//				}
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}

	} 	
	
	
//	  08-21 03:52:52.753: DEBUG/dalvikvm(227): VFY: unable to resolve static method 965: Landroid/support/multidex/PreMultiDexExtractor;.load (Landroid/content/Context;Landroid/content/pm/ApplicationInfo;Ljava/io/File;Z)Ljava/util/List;
//
//	  08-21 03:52:52.753: DEBUG/dalvikvm(227): ming------------http-----------:http://cc.m.jd.com/client.action?functionId=navigation&uuid=861440136819672-97D6C346151&clientVersion=4.3.0&build=20348&client=android&d_brand=Xiaomi&d_model=xiaomi3yidon&osVersion=4.3.3&screen=800*480&partner=dr006&networkType=wifi&sign=6GqhndsUCkRWhZ7ygUnUYQ&sv=1&st=1440136856740

	  
	  
	

	  
	
	
	
    private MingDialog mingAlertDialog; 	  
	private void hackDialog(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingAlertDialog = new MingDialog(xposedConf);
		String clsName = Dialog.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"show",new Object[]{mingAlertDialog});
	}		
	
	  
    private MingAbstractHttpClient mingAbstractHttpClient; 	  
	private void hackAbstractHttpClient(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingAbstractHttpClient = new MingAbstractHttpClient();
//	    String clsName = AbstractHttpClient.class.getName();
//		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"execute",new Object[]{HttpUriRequest.class,mingAbstractHttpClient});
	}	  

	  
	  	
	
    private MingURL mingUrl; 	  
    private MingURLConnection mingURLConnection;
	private void hackURL(XC_LoadPackage.LoadPackageParam packParam)
	{
		mingUrl = new MingURL();
	    String clsName = URL.class.getName();
		XposedHelpers.findAndHookMethod(clsName,packParam.classLoader,"openConnection",new Object[]{mingUrl});
		mingURLConnection = new MingURLConnection();
		
		
//		if(Build.VERSION.SDK_INT==10)
//		{
//			XposedHelpers.findAndHookMethod("org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnectionImpl",packParam.classLoader,"getInputStream",new Object[]{mingURLConnection});
//		}
//		else
//		{
//			XposedHelpers.findAndHookMethod("libcore.net.http.HttpURLConnectionImpl",packParam.classLoader,"getInputStream",new Object[]{mingURLConnection});
//		}
		
	}	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}