package com.ming.xposed.xishuashua.logic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ming.util.android.AUtil;
import com.ming.util.android.RunningListener;
import com.ming.util.android.TimerThread;
import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.XiShuaShuaConf;
import com.ming.xposed.xishuashua.bean.App;
import com.ming.xposed.xishuashua.bean.RemoteData;
import com.ming.xposed.xishuashua.conf.MingConf;
import com.ming.xposed.xishuashua.conf.RemoteConf;
import com.ming.xposed.xishuashua.conf.TimeConf;
import com.ming.xposed.xishuashua.mod.XposedConf;
import com.ming.xposed.xishuashua.util.Base64;
import com.ming.xposed.xishuashua.util.PoyiUtil;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Random;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ShuaLiang implements Runnable {

	private static final String APPKEY ="umeng app key" ;
	private static final boolean OPEN_PROXY =true;
	Context context;

	public String filePath;
	static MingConf mingConf;
	static RemoteConf remoteConf;
	public static App app;

	Random rand;

	private static TimerThread timer;
	NotificationManager nm;

	public ShuaLiang(Context context_, App app_) {
		this.context = context_;
		filePath = getSDPath();//getCacheRootPath(context);
		mingConf = new MingConf(context);
		remoteConf = new RemoteConf(context);
		rand = new Random();

		ShuaLiang.app = app_;
		nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		timer = new TimerThread(this,20);
		timer.setRuningListener(new RunningListener(){

			public void run(final int time) {

				Util.debug("time-----:"+time);


			}});



		shua = this;
	}

	private Handler handler;
	public void showTips(final String tips, final int t) {
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


    int downloading_notification_id = 0;
    Notification notification;
    public void showRuning(){

    	try
    	{
    		downloading_notification_id = (int) System.currentTimeMillis();


    		String title = app.name;
            PendingIntent contentIntent = null;

    		Intent pendIntent = new Intent();
        	contentIntent = PendingIntent.getActivity(context, 0, pendIntent, PendingIntent.FLAG_CANCEL_CURRENT);



        	notification=new Notification(android.R.drawable.ic_dialog_info, title, System.currentTimeMillis());
//        	notification.flags|=Notification.FLAG_ONGOING_EVENT;
        	notification.tickerText = title;
//        	notification.flags = Notification.FLAG_ONGOING_EVENT;
//        	contentView.setTextViewText(R.id.notificationTitle, app.getName());
//        	contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
//        	notification.contentView = contentView;

        	PendingIntent pt = contentIntent;
//        	notification.setLatestEventInfo(context,title,"Running",pt);
        	notification.contentIntent = pt;
//


        	nm.notify(downloading_notification_id, notification);

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }


	boolean isStart;
	public void start() {
		showTips("启动中...",5);
		new Thread(new Runnable(){

			public void run() {

				timer.start();

			}}).start();

	}

	public void run() {
		System.gc();
		Util.debug("shualiang-----runing");
		shualiang();
	}






	protected static ShuaLiang shua;

	private boolean init() {

		return true;
	}


	public void forceStop(String name)
	{
//		PoyiUtil.forceStop(context,name);
//		PoyiUtil.sendShell("am force-stop " + name + " \n",5000);
//		try
//		{
//			Intent intent = new Intent();
//			intent.setClassName("com.topjohnwu.magisk","a.s");
//			intent.setAction("stop");
//			intent.putExtra("packageName",name);
//			context.startActivity(intent);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}

	private int shuaCount;
	private int startNum;
	public void restart()
	{
		try
		{
//			forceStop(app.pack);

			long l = 0;
			if (startNum % 2 == 0)
			{
			Util.debug("new--------------------------------------------------------");
//				remoteConf.loadConf();
//                showTips("this.filePath "+this.filePath,5);
			PoyiUtil.sendShell("ipclient 830e2bea02fe476282241dc31ac49459 1",5000);
			int ret = remoteConf.loadNetConf();
			showTips("loadNetConf ret "+ret,5);
			if (ret <0)
			{
				return;
			}
			String confProp = null;
			if(remoteConf.bind_status==2||remoteConf.bind_status==3)
			{
				Util.delFile(this.filePath + "/mingconf.prop");
				PoyiUtil.clearApplicationUserData(context,app.pack);
				Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/.dciduPlus/.dcidConfig");
				Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/.dciduPlus/");

				Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/.DataStorage/ContextData.xml");
				Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/.DataStorage/");

				Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/.UTSystemConfig/Global/Alvin2.xml");
				Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/.UTSystemConfig/");

				Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/data/.push_deviceid");
				Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/data/");
				Util.delFolder(Environment.getExternalStorageDirectory().getPath());

				confProp = remoteConf.deviceConf;
				//showTips("remoteConf.deviceConf "+remoteConf.deviceConf,5);
				if(confProp!=null)
				{
					Util.debug("wirte mingconf.prop file ---"+confProp +" length="+confProp.length());
					writeFileData(this.context,this.filePath + "/mingconf.prop", confProp);
					XposedConf xposedConf = new XposedConf();
					xposedConf.load();
					Hashtable<String, String> values = xposedConf.getValues("Build");
					Hashtable<String, String> telValues = xposedConf.getValues("TelephonyManager");
					Hashtable<String, String>  wifiValues = xposedConf.getValues("WifiInfo");
					PoyiUtil.sendShell(" setphone -a " + values.get("android_id") +
									" -m " +  values.get("MANUFACTURER") +
									" -s " + "OWKPELYSDZBGFPFU" +
									" -i" + telValues.get("imei")+
									" -w " + wifiValues.get("getMacAddress") +
									" -n " + wifiValues.get("getSSID")+
									" -b " + values.get("BRAND") +
									" -o " + values.get("MODEL") +
									" -e" + telValues.get("imsi") +
									" -p " + values.get("PRODUCT")
							,5000);
//                        writeFileData(this.context,this.filePath + "/bei.prop", confProp);
				}
			}
			//Util.debug("confProp---"+confProp);
			Util.debug("bind_status---"+remoteConf.bind_status);
			if(remoteConf.bind_status==2)
			{
				String conf = new String(Base64.encode(remoteConf.deviceConf.getBytes()));
				//Util.debug("conf---"+conf);
				remoteConf.uploadConf(URLEncoder.encode(URLEncoder.encode(conf,"utf-8"),"utf-8"),2);
				shuaCount++;
			}
			if(remoteConf.bind_status==3)
			{
				String conf = new String(Base64.encode(remoteConf.deviceConf.getBytes()));
				//Util.debug("conf---"+conf);
				remoteConf.uploadConf(URLEncoder.encode(URLEncoder.encode(conf,"utf-8"),"utf-8"),4);
			}
			//Util.debug("shuaCount------:"+ shuaCount );
			long time = TimeConf.getTime();
			l =  -1* time;

		}
			else
			{
				Util.debug("upload--------------------------------------------------------");

			}


			String timeProp = "<" + l + ">";
			writeFileData(this.context,this.filePath + "/mingtime.prop", timeProp);
//			Util.debug(this.filePath + "/mingtime.prop");
			startNum++;
			if (startNum >100 ) {
				startNum = 0;
			}
			startApp();

//
//
//
////			String confProp = mingConf.makeConf();
//			num = remoteData.num;
//			String confProp = remoteData.device;
//			Util.debug("startAppTwice:::::"+startAppTwice);
//			Util.debug("startApp:::::" + num + ":::::" + startNum);
//			String eventProp = remoteData.event;
//			long time = TimeConf.getTime();
//			long l = -100000L * (num - 1 - startNum % num);
//			if (startNum % num == 0) {
//				l = l - time;
//			}
//			String timeProp = "<" + l + ">";
//
//			if(remoteConf.)
//
//
//			if (startNum % 2 == 0)
//			{
//				clearUserData();
//				Util.debug("conf-------"+confProp);
//				String ipProp = remoteData.proxy;
//				writeFileData(this.context,this.filePath + "/mingconf.prop", confProp.getBytes("utf-8"));
//				writeFileData(this.context,this.filePath + "/mingtime.prop", timeProp.getBytes("utf-8"));
//				if (!TextUtils.isEmpty(ipProp)) {
//					writeFileData(this.context, this.filePath + "/mingip.prop", ipProp.getBytes("utf-8"));
//				}
//				if (!TextUtils.isEmpty(eventProp)) {
//					writeFileData(this.context, this.filePath + "/mingevent.prop", eventProp.getBytes("utf-8"));
//				}
//			}
//			else
//			{
//				writeFileData(this.context,this.filePath + "/mingtime.prop", timeProp.getBytes("utf-8"));
//				writeFileData(this.context, this.filePath + "/mingevent.prop", "".getBytes("utf-8"));
//			}

		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}


	private void startApp( )
	{
		try
		{
			String cls = AUtil.getAppMain(context, app.pack);
			Util.debug("app.pack---"+app.pack);
			Util.debug("cls---"+cls);

			Intent localIntent = new Intent();
			if(Util.isNull(cls))
			{
				localIntent.setClassName(app.pack, app.cls);
			}
			else
			{
				localIntent.setClassName(app.pack, cls);
			}
//			localIntent.putExtra("appkey", APPKEY);
//			localIntent.putExtra("channel", channel);
//			localIntent.putExtra("switch", isOpen);
//			localIntent.putExtra("time", time);
			localIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
			this.context.startActivity(localIntent);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void shualiang() {
		try {
//			boolean success = init();
//
//			if (!success) {
//				Thread.sleep(1 * 1000L);
//				return;
//			}
//			Thread.sleep(1*1000);


			restart();

        }
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void clearUserData() {
		try {
			Intent intent = new Intent();
			intent.setAction(XiShuaShuaConf.pkgName);
			intent.putExtra("action", "finish");
			context.sendBroadcast(intent);
            Thread.sleep(1*1000);
            if (startNum == 0) {
				Thread.sleep(1*1000);
			}


			Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/.dciduPlus/.dcidConfig");
			Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/.dciduPlus/");

			Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/.DataStorage/ContextData.xml");
			Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/.DataStorage/");

			Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/.UTSystemConfig/Global/Alvin2.xml");
			Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/.UTSystemConfig/");

			Util.delFile(Environment.getExternalStorageDirectory().getPath()+"/data/.push_deviceid");
			Util.delFolder(Environment.getExternalStorageDirectory().getPath()+"/data/");
			Util.delFolder(Environment.getExternalStorageDirectory().getPath());
			


		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	

	private static void netCount(final MingConf mingConf2, final String channel) {
		new Thread(new Runnable() {

			public void run() {
				
				try
				{
					String url = "";
					byte[] data = Util.getHttpData(url, false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			
			}
		}).start();
	}
	
	public static void netCount(final String channel) {

		netCount(mingConf,channel);
	}

	public static String getSDPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
		if(sdCardExist)
		{
			sdDir = Environment.getExternalStorageDirectory();//获取跟目录
		}
		return sdDir.toString();
	}

	public static String getCacheRootPath(Context paramContext) {

		Util.debug("-------------"+paramContext.getFilesDir());
		
		String str = paramContext.getFilesDir().getPath();
		if (str.endsWith("/"))
			str = str.substring(0, str.length() - 1);
		Object localObject = str;
		
		return ((String) localObject);
	}

	public static void writeFileData(Context context, String paramString, String paramArrayOfByte) {
//		if (AUtil.checkSDCARD()) {
//			Util.writeFileData(paramString, paramArrayOfByte);
//			return;
//		}
		File localFile = new File(paramString);
		//Util.debug("delete "+localFile.getName()+" file :"+new File("/data/data/com.ming.xposed.xishuashua/files/"+localFile.getName()).delete()+"------------");
        AUtil.WriteFileData(paramString,paramArrayOfByte);
        //AUtil.writeAppFileData(context, localFile.getName(),
//				paramArrayOfByte);
        Util.debug("wirte mingconf.prop file size ---"+localFile.length()
        +" filePath "+paramString);
	}

	public void stop() {
		timer.pause();
		this.clearUserData();
		// TODO Auto-generated method stub
		
	}

	
	public static void resetTimer() {
		timer.reset();
	}	
		

}
