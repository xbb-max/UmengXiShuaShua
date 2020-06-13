package com.ming.xposed.xishuashua.util;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ming.util.android.AUtil;
import com.ming.xposed.xishuashua.MingReboot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyPowerOn {
	
    public static String setRestart(Context context, int restarttime)
    {
    	String s = "";
//    	if(Build.VERSION.SDK_INT==10)
//    	{
//        	try
//        	{
//        		Calendar c = Calendar.getInstance();
//        		c.setTimeInMillis(System.currentTimeMillis());
//        		int hour = c.get(Calendar.HOUR_OF_DAY);
//        		int minutes = c.get(Calendar.MINUTE);
//        		
////        		int restarttime = 5;
//        		
//    			int h_ = hour;
//    			int m_ = minutes+restarttime;
//    			if(m_>=60)
//    			{
//    				h_++;
//    				m_ = m_-60;
//    			}
//    			if(h_>=24)
//    			{
//    				h_ = h_-24;
//    			} 
//    			
//    			s = MyPowerOn.setPoweron(context,h_,m_);
//    			
//    			
//        		
//        	}
//        	catch(Exception e)
//        	{
//        		e.printStackTrace();
//        	}    		
//    	}
//    	else
    	{
            Intent intent =new Intent(context, MingReboot.class);
            intent.setAction("reboot");
            PendingIntent sender= PendingIntent.getBroadcast(context, 0, intent, 0);
            
            Calendar calendar= Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.MINUTE, restarttime);
            
            SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
            s = sFormat.format(calendar.getTimeInMillis());

//            sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String cTime = sFormat.format(System.currentTimeMillis());
//            this.setTitle(cTime+"-"+rebootTime);

    	    AUtil.showTips(context, "手机将于 "+s+" 重启");

            AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    	}

    	return s;
    }


    public static String setPoweron(Context context, int hour, int minutes)
    {
		StringBuffer sb = new StringBuffer();
		try
		{
			updateDB(context,2,"off",""+hour,""+minutes);

			int h_ = hour;
			int m_ = minutes+2;
			if(m_>=60)
			{
				h_++;
				m_ = m_-60;
			}
			if(h_>=24)
			{
				h_ = h_-24;
			}

			updateDB(context,1,"on",""+h_,""+m_);

			sb.append("手机将于 "+hour+":"+minutes+" 关机, "+h_+":"+m_+" 开机");


			Toast.makeText(context,sb.toString(), 6000).show();




//	        System.out.println("localAlarm.id---"+localAlarm.id);
//	        System.out.println("localAlarm.hour---"+localAlarm.hour);
//	        System.out.println("localAlarm.minutes---"+localAlarm.minutes);
//	        System.out.println("localAlarm.label---"+localAlarm.label);
//	        System.out.println("localAlarm.time---"+localAlarm.time);
//	        System.out.println("localAlarm.alert---"+localAlarm.alert);
//	        System.out.println("localAlarm.enabled---"+localAlarm.enabled);
//	        System.out.println("localAlarm.silent---"+localAlarm.silent);
//	        System.out.println("localAlarm.vibrate---"+localAlarm.vibrate);
//	        System.out.println("localAlarm.daysOfWeek---"+localAlarm.daysOfWeek);


//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.id---2
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.hour---9
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.minutes---0
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.label---off
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.time---1423443600000
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.alert---null
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.enabled---true
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.silent---true
//	        02-09 02:05:50.740: INFO/System.out(4240): localAlarm.vibrate---false


//	        Alarms.setNextAlert(context);

//	        Alarms.enableAlert(context, localAlarm, localAlarm.time);


//	        System.out.println("calculateAlarm---"+calculateAlarm(localAlarm));

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
    }

    public static void updateDB(Context context, int id, String message, String hour, String minutes)
    {
    	try
    	{
       	 	ContentResolver cr = context.getContentResolver();
            Uri CONTENT_URI = Uri.parse("content://com.android.settings/timerpower");
            ContentValues localContentValues = new ContentValues(2);
            localContentValues.put("hour",hour);
            localContentValues.put("minutes",minutes);
	        localContentValues.put("enabled","1");
	        localContentValues.put("daysofweek","127");
	        localContentValues.put("alarmtime","0");
	        localContentValues.put("vibrate","0");
	        localContentValues.put("message",message);
 	        localContentValues.put("alert","silent");
    	
       	    cr.update(ContentUris.withAppendedId(CONTENT_URI, id), localContentValues,null, null);
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}    	
    	
    }
    
    
    
    
	
	

}
