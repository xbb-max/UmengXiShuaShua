package com.ming.util.android;

import android.os.Handler;
import android.os.Message;

public class TimerHandler extends Handler
{
	
	private Runnable runnable;
	private long delayMillis;
	private boolean isRuning;


	public TimerHandler(Runnable run, long de)
	{
		this.runnable = run;
		this.delayMillis = de;
	}

	public void setDelay(long mm)
	{
		this.delayMillis = mm;
	}

	public void handleMessage(Message msg)
	{
//		Util.debug("000000000000000000000000000000000000000000----------------");
		runnable.run();
		sleep();
	}


	public void stop()
	{
		this.removeMessages(0);
		isRuning = false;
	}

	public void sleep()
	{
		this.removeMessages(0);
		if(isRuning)
		{
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}

	public void start()
	{
		isRuning = true;
		sleep();
	}	

}
