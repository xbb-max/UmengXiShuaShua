package com.ming.util.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.ming.util.common.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class AUtil {

	
	public static int getDeviceWidth(Context paramContext)
	  {
	    return getDeviceWidth((Activity)paramContext);
	  }
	  
	  

	public static int getDeviceHeight(Context paramContext)
	  {
	    return getDeviceHeight((Activity)paramContext);
	  }	
		
	public static int getDeviceHeight(Activity paramActivity)
	  {
	    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
	    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
	    return localDisplayMetrics.heightPixels;
	  }
	  
	public static int getDeviceWidth(Activity paramActivity)
	  {
	    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
	    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
	    return localDisplayMetrics.widthPixels;
	  }
	
	
	
	
	private static Handler handler;
	public static void showTips(final Context context, final String tips) {
		if (handler == null) {
			handler = new Handler(Looper.getMainLooper());
		}

		handler.post(new Runnable() {
			public void run() {
				try {
					Toast.makeText(context, tips, 60).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}		  
	
	  public static byte[] readAppFileData(Context paramContext, String paramString)
	  {
		  byte[] data = null;
	    try
	    {
	      FileInputStream localFileOutputStream = paramContext.openFileInput(paramString);
	      data = Util.inputStreamToBytes(localFileOutputStream);
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	      return data;
	  }		
	
	
	public static String getAppMain(Context context, String className)
	{
		String isMain = "";
		try
		{
			Intent intent = new Intent(Intent.ACTION_MAIN, null);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);

			List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
			
			for (int i = 0; i < list.size(); i++) 
			{
				ActivityInfo act = list.get(i).activityInfo;
//				System.out.println("act.name-------------------------:"+act.name+"----------package:"+act.packageName);
				if(act.packageName.indexOf(className)!=-1)
				{
					isMain = act.name;
					break;
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return isMain;
		
	}	
	
	
	  private static int id_index = 4660;
	  public static int getId()
	  {
	    id_index = 1 + id_index;
	    return id_index;
	  }
	
	
	
	public static boolean checkSDCARD() {
		try {
			boolean bool = Environment.getExternalStorageState().equals(
					"mounted");
			return bool;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return false;
	}
	
	  public static byte[] getAssetsData(Context paramContext, String paramString)
	  {
	    String str = paramString;
	    try
	    {
	      if (paramString.startsWith("assets:\\"))
	        str = paramString.substring(9);
	      byte[] arrayOfByte = Util.inputStreamToBytes(paramContext.getAssets().open(str));
	      return arrayOfByte;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return null;
	  }	
	
	
	  public static boolean writeAppFileData(Context paramContext, String paramString, byte[] paramArrayOfByte)
	  {
	    try
	    {
	      FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString, Context.MODE_WORLD_READABLE );
	      localFileOutputStream.write(paramArrayOfByte);
	      localFileOutputStream.close();
	      return true;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return false;
	  }

	public static void WriteFileData(String filePath,String message)
	{
		String mPath = filePath;//
		try{
			FileOutputStream fout = new FileOutputStream(mPath,false);
			byte [] bytes = message.getBytes();
			fout.write(bytes);
			fout.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
