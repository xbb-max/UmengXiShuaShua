package com.ming.xposed.xishuashua.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ming.util.android.AUtil;
import com.ming.util.android.RunningListener;
import com.ming.util.android.TimerThread;
import com.ming.xposed.xishuashua.bean.App;
import com.ming.xposed.xishuashua.util.MyService;

public class NaviInfo extends RelativeLayout implements Runnable {

	private TextView timerInfo;
	private TimerThread timer;
	static NaviInfo myNavi;

	public NaviInfo(final Context context, App app) {
		super(context);
		this.app = app;
		timer = new TimerThread(this,1);
		myNavi = this; 
		
		
		timer.setRuningListener(new RunningListener(){

			public void run(final int time) {
				
				if(context instanceof Activity)
				{
					((Activity)context).runOnUiThread(new Runnable(){

						public void run() {
							
							timerInfo.setText("自启动:"+((time*100/10))+"%");
						}});
				}
				
				// TODO Auto-generated method stub
				
			}});			
		
		
		setup(context);
		
		
	}

		
	private void setup(final Context context){
		
		timerInfo = new TextView(context);
		
		
	    LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

	    View top = makeTop(context);
	    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	    top.setLayoutParams(params);
	    this.addView(top);


		shuaBtn = new Button(context);
		shuaBtn.setText("启动刷量");
		shuaBtn.setId(AUtil.getId());
	    params = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	    shuaBtn.setLayoutParams(params);
	    this.addView(shuaBtn);

	    shuaBtn.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {

				startService(context);
				timer.pause();

			}});


	    View bottom = makeBottom(context);
	    params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	    params.addRule(RelativeLayout.ABOVE,shuaBtn.getId());
	    bottom.setLayoutParams(params);
	    this.addView(bottom);
	}
	
	
	
	public void startService(Context context) {

		Intent intent = new Intent();
		intent.putExtra(MyService.KAct, MyService.KActStartNavi);
		intent.setClass(context, MyService.class);
		context.startService(intent);
	}

	public static void stopService(Context context) {
		Intent intent = new Intent();
		intent.setClass(context, MyService.class);
		context.stopService(intent);
	} 	
	

	
	App app;
	
	private View makeTop(Context context)
	{
	    LinearLayout top = new LinearLayout(context);
	    
	    top.setOrientation(LinearLayout.VERTICAL);
	    
	    return top;
	}
	
	  private Button shuaBtn;
	
		private View makeBottom(final Context context)
		{
		    LinearLayout bottom = new LinearLayout(context);
		    
		    bottom.setOrientation(LinearLayout.HORIZONTAL);
		    
		    
			bottom.addView(timerInfo);
			
		    return bottom;
		}
		
		boolean isRunning;
		public void run() {
			Context context = getContext();
			if(isRunning)
			{
				startService(context);
				timer.pause();
			}
			else
			{
				isRunning = true;
			}
		}
		
}
