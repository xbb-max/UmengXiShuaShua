package com.ming.xposed.xishuashua.mod.logic;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.util.common.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Vector;

public class MyLogic {

	public Random rand;
	public String appName;
	public String appPack;
//	public int whichPage;
	public int whichSubPage;
	public int clickNum = 0;
	public String account;
	public String password;
	public String say;
	public View dialogView;
	public Context context;
	public RunLogic runLogic;
	
	public MyLogic(Context context) {
		this.context = context;
		rand = new Random();
	}
	
	public int getRand(int size)
	{
		int which = Math.abs(rand.nextInt()%size);
		return which;
	}		
	
	
	public void registerInstallReceivers(Context context, String pack) {
		InstallerReceiver installerReceiver = new InstallerReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        installerReceiver.setPackage(pack);
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
//        filter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        context.registerReceiver(installerReceiver, filter);
//		        filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//		        registerReceiver(mCloseSystemDialogsReceiver, filter);
	}	

	public static View getWindowView(Context context)
	{
		View view = null;
    	if(context instanceof Activity)
    	{
			view = ((Activity)context).getWindow().getDecorView();
    	}
    	return view;
	}  	
	
	
	public static View getRootView(Context context)
	{
		View view = null;
    	if(context instanceof Activity)
    	{
			view = ((Activity)context).getWindow().getDecorView().getRootView();
    	}
    	return view;
	}  	
	
	public static int getStatusHeight(Context context){
        int statusHeight = 0;
    	if(context instanceof Activity)
    	{
    		Activity activity = ((Activity)context);
            Rect localRect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
            statusHeight = localRect.top;
            if (0 == statusHeight){
                Class<?> localClass;
                try {
                    localClass = Class.forName("com.android.internal.R$dimen");
                    Object localObject = localClass.newInstance();
                    int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                    statusHeight = activity.getResources().getDimensionPixelSize(i5);
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
    	}
		
        return statusHeight;
    }	
	
	
	public void clickView(View view, float x, float y)
	{
		Util.debug("click---:"+x+"-"+y);
		
		long downTime = SystemClock.uptimeMillis();
		final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
				MotionEvent.ACTION_DOWN, x, y, 0);
		downTime += 600;
		final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
				MotionEvent.ACTION_UP, x, y, 0);
		
		view.dispatchTouchEvent(downEvent);
   	    view.dispatchTouchEvent(upEvent);   
	}
	
	public void clickView(View view, View cv)
	{
	    XY xy = XY.getXY(cv);
    	float x = xy.x+6;
   	 	float y = xy.y+6;
   	    clickView(view,x,y);
	}
	
	
	public Vector<View> loadViews(View view)
	{
		Vector<View> vs = new Vector<View>();
		loadViews(view,vs);
		return vs;
	}
	
	
	public void loadViews(View view, Vector<View> vs)
	{
//		System.out.println("-------------------------------view:"+view);
//		System.out.println("-------------------------------view-id:"+view.getId());
//		vs.addElement(view);
		if(view instanceof ViewGroup)
		{
			ViewGroup vg = (ViewGroup)view;
			int size = vg.getChildCount();
			for(int i=0;i<size;i++)
			{
				View v2 = vg.getChildAt(i);
				if(vs!=null)
				{
					vs.addElement(v2);
				}
				loadViews(v2,vs);
			}
		}
	}
	
	public View findViewByName(Vector<View> vs, String name, boolean isshow)
	{
		View rview = null;
		for(View view:vs)
		{
			if(view instanceof TextView)
			{
				TextView tv = (TextView)view;
				String s = tv.getText().toString().trim();
				if(s.equals(name))
				{
					Toast.makeText(context, tv.getText()+"----visiable:"+tv.isShown(), 500).show();
					if(tv.isShown()==isshow)
					{
						rview = tv;
						break;
					}
				}
			}
		}	
		
		return rview;
	}

	public View findViewByNameMohu(Vector<View> vs, String name, boolean isshow)
	{
		View rview = null;
		for(View view:vs)
		{
			if(view instanceof TextView)
			{
				TextView tv = (TextView)view;
				if(tv.getText().toString().indexOf(name)!=-1)
				{
//					Toast.makeText(context, tv.getText()+"----visiable:"+tv.isShown(), 500).show();
					if(tv.isShown()==isshow)
					{
						rview = tv;
						break;
					}
				}
			}
		}	
		
		return rview;
	}	
	
	
//	public View findViewById(View view,int id)
//	{
//		View view2 = null;
//		System.out.println("-------------------------------view:"+view);
//		System.out.println("-------------------------------view-id:"+view.getId());
//		if(view instanceof ViewGroup)
//		{
//			ViewGroup vg = (ViewGroup)view;
//			int size = vg.getChildCount();
//			for(int i=0;i<size;i++)
//			{
//				View v2 = vg.getChildAt(i);
//				findViewById(v2,id);
//			}
//		}
//		else
//		{
//			view2 = 			
//		}
//		return view2;
//	}	
//	
	
	public OnItemClickListener getAbsListViewOnItemClickListener(View view)
	{
		Object cl = null;
        Class cls = AdapterView.class;
        Field filed;
		try {
			filed = cls.getDeclaredField("mOnItemClickListener");
			filed.setAccessible(true);  
        	cl = filed.get(view);
		} catch (Exception e) {
		} 		
		
		return (OnItemClickListener)cl;
	}


	public int getImageViewResID(View view)
	{
		Object cl = null;
        Class cls = ImageView.class;
//	      Field[] fs = cls.getDeclaredFields();
//	      for(Field f:fs)
//	      {
//	      	System.out.println("--------------"+f.getName());
//	        if(f.equals("mOnChildClickListener"))
//	        {
//				f.setAccessible(true);
////	        	cl = f.get(view);
//	        }
//	      }


        Field filed;
		try {

			filed = cls.getDeclaredField("mResource");
	      	System.out.println("-------------filed-"+filed);
			filed.setAccessible(true);
	      	System.out.println("-------------view-"+view);
        	cl = filed.get(view);
	      	System.out.println("-------------cl-"+cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ((Integer)cl).intValue();
	}

	public OnChildClickListener getOnChildClickListener(View view)
	{
		Object cl = null;
        Class cls = ExpandableListView.class;
	      Field[] fs = cls.getDeclaredFields();
	      for(Field f:fs)
	      {
	      	System.out.println("--------------"+f.getName());
	        if(f.equals("mOnChildClickListener"))
	        {
				f.setAccessible(true);
//	        	cl = f.get(view);
	        }
	      }


        Field filed;
		try {

			filed = cls.getDeclaredField("mOnChildClickListener");
	      	System.out.println("-------------filed-"+filed);
			filed.setAccessible(true);
	      	System.out.println("-------------view-"+view);
        	cl = filed.get(view);
	      	System.out.println("-------------cl-"+cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (OnChildClickListener)cl;
	}		
	
	
	public Object getField(Class cls, String name, Object view)
	{
		Object cl = null;
        Field filed;
		try {
			filed = cls.getDeclaredField(name);
			filed.setAccessible(true);  
        	cl = filed.get(view);
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		return cl;		
	}	
	
	
	public OnClickListener getOnClickListener(View view)
	{
		OnClickListener cl = (OnClickListener)getListener(view,"mOnClickListener");
		
		
//        Class cls = View.class;//.getSuperclass();//Class.forName("android.app.ActivityManager");
//    	System.out.println("--------------"+cls);
//
////        Field[] fs = cls.getFields();
////        for(Field f:fs)
////        {
////        	System.out.println("--------------"+f.getName());
////        }
//        
//        
//        Field filed;
//		try {
//			filed = cls.getDeclaredField("mOnClickListener");
//			filed.setAccessible(true);  
//        	System.out.println("---ss----------"+filed+"---"+filed.get(view));
//        	cl = (OnClickListener)filed.get(view);
//		} catch (Exception e) {
//		} 		
//		
//		
		
		
		return cl;
	}
	
	
	public Object getListener(View view, String s)
	{
		Object cl = null;
        Class cls = View.class;
        Field filed;
		try {
			filed = cls.getDeclaredField(s);
			filed.setAccessible(true);  
        	cl = filed.get(view);
		} catch (Exception e) {
		} 		
		
		return cl;
	}


	public void run() {
		// TODO Auto-generated method stub
		
	}


	public void showDialog(Dialog dialog) {
		// TODO Auto-generated method stub
		
	}


	public void setApp(String string, String string2) {
		this.appName = string;
		this.appPack = string2;
	}	
	
	
	
	
	public Object getField(Class zclass, Object obj, String name)
	{
		Object ro = null;
		try
		{
//			System.out
//					.println("zclass::-----------------------------:"
//							+ zclass);
//			Field[] fields = zclass.getDeclaredFields();
//			for (Field field : fields) {
//				System.out
//						.println("field::-----------------------------oooooooooooo:"
//								+ field+"----value:::"+ field.get(obj));
//			}

			Field field = zclass.getDeclaredField(name);
			field.setAccessible(true);
			ro = field.get(obj);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ro;
	}
	
	public Object callMethod(Class zclass, Object obj, String name, Class[] params, Object[] values)
	{
		Object ro = null;
		try
		{
//			System.out
//					.println("zclass::-----------------------------:"
//							+ zclass);
//			Field[] fields = zclass.getDeclaredFields();
//			for (Field field : fields) {
//				System.out
//						.println("field::-----------------------------oooooooooooo:"
//								+ field+"----value:::"+ field.get(obj));
//			}

			Method method = zclass.getDeclaredMethod(name, params);
			method.setAccessible(true);
			ro = method.invoke(obj, values);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ro;
	}	
	
	public void finish(Context context) {
		try
		{
			((Activity)context).finish();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}	

	public Method getMethod(Class zclass, String name,
                            Class[] classes) {
		Method method = null;
		try
		{
			method = zclass.getDeclaredMethod(name, classes);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return method;
	}
	
	public int timeOutStep;
	public boolean timeOut(int i) {
		timeOutStep++;
		System.out.println("timeOut---------------:"+timeOutStep+"---------------------total:"+i);
		if(timeOutStep>=i)
		{
			timeOutStep = 0;
			return true;
		}
		return false;
	}
	
	public void moveClick(Context context, final View view, final float sx, final float sy, final float ex, final float ey, final float second, final int dir)
	{
		((Activity)context).runOnUiThread(new Runnable(){

			public void run() {
				
				long downTime = SystemClock.uptimeMillis();
				long eventTime = SystemClock.uptimeMillis();
				view.dispatchTouchEvent(MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, sx, sy, 0));
				if(dir==0)
				{
					float step = (sy-ey)/second;
					for(int i=1;i<=second;i++)
					{
						view.dispatchTouchEvent(MotionEvent.obtain(downTime,  (long)(eventTime+(i*1000)), MotionEvent.ACTION_MOVE, sx, sy-i*step, 0));
					}
					
				}				
				else if(dir==1)
				{
					
				}
				else if(dir==2)
				{
				}
				else if(dir==3)
				{
					
				}
				
				view.dispatchTouchEvent(MotionEvent.obtain(downTime, (long)(eventTime+(second*1000)), MotionEvent.ACTION_UP, ex, ey, 0));
				
			}});		

	}

	public void stopRuning() {
		runLogic.stop();
	}
	
}
