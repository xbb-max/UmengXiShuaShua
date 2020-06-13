package com.ming.xposed.xishuashua.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.ming.xposed.xishuashua.Main;
import com.ming.xposed.xishuashua.R;
import com.ming.xposed.xishuashua.XiShuaShuaConf;
import com.ming.xposed.xishuashua.bean.App;
import com.ming.xposed.xishuashua.logic.ShuaLiang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {
	// 定义个一个Tag标签
	private static final String TAG = "MyService";

	public static final String KAct = "act";

	public static final String KActStartNavi = "startnavi";

	// 这里定义吧一个Binder类，用在onBind()有方法里，这样Activity那边可以获取到
	private MyBinder mBinder = new MyBinder();

	public ShuaLiang sl;
//	private MingReceiver msr;

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		showNotification();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg1 = START_STICKY;
		return super.onStartCommand(arg0, arg1, arg2);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "lzf start onStart~~~");
		super.onStart(intent, startId);

		if (intent != null) {
			Bundle b = intent.getExtras();
			if (b != null) {
				String act = b.getString(KAct);
				Log.i(TAG, "lzf act: " + act);

				if (act.equals(KActStartNavi)) {
//					byte[] data = AUtil.readAppFileData(this, "appIndex.txt");
//					String txt = new String(data);
//					final int index = Util.getInt(Util.getTagString(txt));

					new Thread(new Runnable() {

						public void run() {
							App app = XiShuaShuaConf.loadApp(MyService.this);
							if (sl == null) {
								sl = new ShuaLiang(MyService.this, app);
							}
							sl.start();
						}
					}).start();

				}

			}
		}

	}

//	public void startApp() {
//
//		// 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
//		PackageInfo packageinfo = null;
//		Context context = this;
//		try {
//			String packagename = sl.app.downloadAppPack;
//			packageinfo = context.getPackageManager().getPackageInfo(
//					packagename, 0);
//			if (packageinfo == null) {
//				return;
//			}
//
//			// 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
//			Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
//			resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//			resolveIntent.setPackage(packageinfo.packageName);
//
//			// 通过getPackageManager()的queryIntentActivities方法遍历
//			List<ResolveInfo> resolveinfoList = context.getPackageManager()
//					.queryIntentActivities(resolveIntent, 0);
//
//			ResolveInfo resolveinfo = resolveinfoList.iterator().next();
//			if (resolveinfo != null) {
//				// packagename = 参数packname
//				String packageName = resolveinfo.activityInfo.packageName;
//				// 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
//				String className = resolveinfo.activityInfo.name;
//				// LAUNCHER Intent
//				Intent intent = new Intent(Intent.ACTION_MAIN);
//				intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//				// 设置ComponentName参数1:packagename参数2:MainActivity路径
//				ComponentName cn = new ComponentName(packageName, className);
//
//				intent.setComponent(cn);
//				context.startActivity(intent);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public static int initApps(Context context,int index) {
//		
//		if (index != -1) {
//			if (Resource.apps == null) {
//				Resource.init();
//				byte[] data = AUtil.getAssetsData(context, "apps.xml");
//				String xml;
//				try {
//					xml = new String(data, "utf-8");
//	                new AppList(context).load(xml,Resource.apps);
////					AppList.load(xml, Resource.apps);
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return index;
//	}

	@Override
	public void onDestroy() {
//		if (sl != null) {
//			new Thread() {
//				public void run() {
//					sl.stop();
//				}
//			}.start();
//		}
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	public class MyBinder extends Binder {
		public MyService getService() {
			return MyService.this;
		}
	}

	private String getTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date(time);
		String t1 = format.format(d1);
		return t1;
	}



	private void showNotification() {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		if (mNotificationManager == null)
			return;
		int sysVersion = Integer.parseInt(VERSION.SDK);
		if (sysVersion <= 14) {
			String text = getString(R.string.app_name);

			Notification notification = new Notification(R.drawable.icon, text,
					System.currentTimeMillis());

			notification.flags = notification.flags
					| Notification.FLAG_NO_CLEAR
					| Notification.FLAG_ONGOING_EVENT;
			Intent intent = new Intent(this, Main.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 1,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);

			// Set the info for the views that show in the notification panel.
//			notification.setLatestEventInfo(this, getText(R.string.app_name),
//					text, contentIntent);

			// Send the notification.
			// We use a layout id because it is a unique number. We use it later
			// to
			// cancel.

			startForeground(R.string.app_name, notification);
		}
	}
}