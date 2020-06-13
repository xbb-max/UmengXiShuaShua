package com.ming.xposed.xishuashua.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ming.util.android.AUtil;
import com.ming.util.android.RunningListener;
import com.ming.util.android.TimerThread;
import com.ming.util.common.Element;
import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.Navi;
import com.ming.xposed.xishuashua.R;
import com.ming.xposed.xishuashua.XiShuaShuaConf;
import com.ming.xposed.xishuashua.bean.App;
import com.ming.xposed.xishuashua.util.PoyiUtil;

import java.util.List;
import java.util.Vector;

public class AppList extends RelativeLayout implements Runnable {

	
	private ListView listView;
	private BasicAdapter adapter;
	private TextView timerInfo;
	private TimerThread timer;
	
//	private String partner;

	private Vector<App> apps;
	
	
	
	
	public AppList(final Context context) {
		super(context);
//		partner = XiShuaShuaConf.getPartner(context);
//		AUtil.showTips(context, "partner:"+partner);
		apps = new Vector<App>();
		timer = new TimerThread(this,1);
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
		
		loadApp(context,apps);
		this.refresh();
	} 
	
	
	
	
	private void setup(Context context){
	
		listView = new ListView(context);
		timerInfo = new TextView(context);
//		apps = Resource.apps;
	    adapter = new BasicAdapter(context);
	    adapter.setData(apps);
	    
	    listView.setAdapter(adapter);
	    
	    
	    listView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
				
				final App app = (App)adapter.getItem(arg2);
				app.setIndex(arg2);
				final int index = arg2;
		        AlertDialog localAlertDialog = new AlertDialog.Builder(getContext()).create();
		        localAlertDialog.setTitle("操作选择");
		        localAlertDialog.setMessage("已选择 "+app.name+" 进行操作...");
		        localAlertDialog.setButton("启动导航", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						if(apps.size()>0)
						{
							startNavi();
						}
						timer.pause();

						
					}
				});
		        localAlertDialog.setButton3("填加到桌面", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						
						makeShortcut(app,index);
						
					}
				});		        
		        localAlertDialog.setButton2("取消", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});				        

		        localAlertDialog.show();
			}});
	    
	    LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
	    listView.setLayoutParams(params);
	    this.addView(listView);


	    params = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	    timerInfo.setLayoutParams(params);
	    this.addView(timerInfo);


	}

	private void makeShortcut(App app, int index)
	{
		try
		{
	        Intent intent=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");//action
	        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, app.name);
	        intent.putExtra("duplicate", false);
	        Parcelable icon = Intent.ShortcutIconResource.fromContext(getContext(), R.drawable.icon);
	        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

	        Intent intent2 = new Intent(Intent.ACTION_MAIN);
	        intent2.setClass(getContext(), Navi.class);
			Bundle bundle = new Bundle();
			bundle.putInt("index", index);
			intent2.putExtras(bundle);
	        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,intent2);
	        getContext().sendBroadcast(intent); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void startNavi()
	{
		try
		{
			Intent intent = new Intent();
			intent.setClass(getContext(), Navi.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			getContext().startActivity(intent);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void loadApp(Context context, Vector<App> apps)
	{
		App app = XiShuaShuaConf.loadApp(context);
		if(app!=null)
		{
			apps.addElement(app);
		}
	}
	
	


	
	
	public void load(String xml, Vector<App> apps)
	{
		try
		{
			Element el = Element.parse(xml);
			parse(el,apps);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	private void parse(Element node, Vector<App> apps)
	{
		String tag = node.getTag();
		if(tag.equals("app"))
		{
			App app = App.parse(node);
//			Util.debug("-"+partner+"-"+partner.length());
//			Util.debug("="+app.partner+"="+app.partner.length());
//			Util.debug("app.partner.indexOf(partner)"+app.partner.indexOf(partner)+"=");
//			Util.debug("app.partner.indexOf(partner)"+app.partner.equals(partner)+"=");
//			if(partner.equals(app.partner))
			{
				if(XiShuaShuaConf.getVersion(getContext()).equals(app.version))
				{
					apps.addElement(app);
				}
			}
		}
		
		Vector<Element> nodes = node.getChildren();
		for(Element el:nodes)
		{
			parse(el,apps);
		}
	}	
	

	
	
	
	  class BasicAdapter extends BaseAdapter
	  {
	    Vector<App> beans;
	    Context context;

	    BasicAdapter(Context paramContext)
	    {
	      this.context = paramContext;
	    }

	    public View getAppView(Context paramContext, App app)
	    {
	      LinearLayout localLinearLayout = new LinearLayout(paramContext);
	      localLinearLayout.setOrientation(LinearLayout.VERTICAL);
	      localLinearLayout.setTag(app);
	      
	      TextView localTextView1 = new TextView(paramContext);
	      localTextView1.setSingleLine();
	      localTextView1.setText(app.name);
	      localLinearLayout.addView(localTextView1);
	      
	      TextView localTextView2 = new TextView(paramContext);
	      localTextView2.setSingleLine();
	      localTextView2.setText("渠道："+app.channel);
	      localTextView2.setTextColor(0XFF00ff00);
	      localLinearLayout.addView(localTextView2);
	      
	      localTextView2 = new TextView(paramContext);
	      localTextView2.setSingleLine();
	      localTextView2.setText("版本："+app.version);
	      localLinearLayout.addView(localTextView2);
	      
	      
	      return localLinearLayout;

	    }

	    public int getCount()
	    {
	      if (this.beans == null)
	        return 0;
	      return this.beans.size();
	    }

	    public Object getItem(int paramInt)
	    {
	      if (this.beans == null)
	        return Integer.valueOf(0);
	      return this.beans.get(paramInt % this.beans.size());
	    }

	    public long getItemId(int paramInt)
	    {
	      if (this.beans == null)
	        return 0L;
	      return paramInt;
	    }

	    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	    {
	      App localDevice = (App)this.beans.elementAt(paramInt % this.beans.size());
	      return getAppView(this.context, localDevice);
	    }

	    public void refresh()
	    {
	      ((Activity)this.context).runOnUiThread(new Runnable()
	      {
	        public void run()
	        {
	          notifyDataSetChanged();
	        }
	      });
	    }

	    public void setData(Vector<App> paramVector)
	    {
	      this.beans = paramVector;
	    }
	  }



	public void refresh() {
		adapter.refresh();
		
		// TODO Auto-generated method stub
		
	}

	
	boolean isRunning;

	public void run() {
		if(isRunning)
		{
			if(apps.size()>0)
			{
				startNavi();
			}
			timer.pause();
		}
		else
		{
			isRunning = true;
		}
	}

	public void start() {
		timer.start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				uninstallOtherApps(getContext());
			}}).start();
		
	}	
	
	
	private void uninstallOtherApps(Context context) {
		

		try {
			
			List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
			for (int i = 0; i < packs.size(); i++) {
				PackageInfo p = packs.get(i);
				if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
					continue;
				}
				if(p.packageName.indexOf("com.ming.xposed.xishuashua")!=-1||
				   p.packageName.indexOf("de.robv.android.xposed.installer")!=-1||
				   p.packageName.indexOf(XiShuaShuaConf.pkgName)!=-1||
				   p.packageName.indexOf("com.kingroot.kinguser")!=-1||
				   p.packageName.indexOf("com.moioio.easylauncher")!=-1||
				   p.packageName.indexOf("com.ming.xposed.uid")!=-1
				   
				   )
				{
					continue;
				}
				final String pakName = p.packageName;
				Util.debug("uninstall app---:"+pakName);
				PoyiUtil.uninstallApp(context, p.packageName,new Runnable()
				{

					@Override
					public void run() {
						AUtil.showTips(getContext(), pakName+" have been uninstalled!");
						// TODO Auto-generated method stub
						
					}
				
				});
				
			}			


			
		} catch (Exception e) {
		} 		
		
//		if (RootTools.isAccessGiven()) 
		{
			

		} 
	}	
	
	
	

}
