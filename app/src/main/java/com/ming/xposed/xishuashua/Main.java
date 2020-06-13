package com.ming.xposed.xishuashua;


import android.app.Activity;
import android.app.WallpaperManager;
import android.database.ContentObserver;
import android.os.Bundle;
import android.view.WindowManager;

import com.ming.xposed.xishuashua.ui.AppList;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Main extends Activity {

	public AppList applist;
	
//05-30 18:18:12.066 29763-29778/com.shinyv.cnr I/System.out: minglog-umeng-{"appkey":"5eaa8d23570df3a3240003ed","device_model":"M920","sdk_version":"6.1.0","device_id":"862994512464301","channel":"yt","version":"4797"}
//05-30 18:18:12.094 29763-29778/com.shinyv.cnr I/System.out: minglog-umeng-{"header":{"failed_requests":0,"access_subtype":"LTE","appkey":"5eaa8d23570df3a3240003ed","app_version":"6.13.0.4797","resolution":"480*320","version_code":4797,"device_model":"M920","timezone":8,"device_name":"M920","mc":"00:D3:BB:5E:4E:DC","imprint":"GwuMB3ByZXRpbWUYDTE1OTA4MzM4NTM5OTYW2Nix0cxcGCg1YzU0N2E1NTEwZDZkMGNmN2FkZjNi\nMjFmYjdmZTE0ODM2OGI1OGM5ABNpbnN0YWxsX2FwcF92ZXJzaW9uGAs2LjEzLjAuNDc5NxbS8aS8\nzFwYKDE5ZmVhODUzZDFmOGVhNzkxMWQ1NDNhYWI1MTlhYmQ2NGQzZmY4OWYAD2luc3RhbGxfY2hh\nbm5lbBgCeXQW0vGkvMxcGCgxOWZlYTg1MzU3YTU2OGUyMDQ2MmVhZTY5ZWZkYjM2YzAzZDQ3Yzhk\nAAthcHBfdmVyc2lvbhgLNi4xMy4wLjQ3OTcW0vGkvMxcGCgxOWZlYTg1M2ViNGIwZDJmNWU0YTEw\nZDkwNWUwYWE2ZDRhOWE3ZDNlABBpbnN0YWxsX2RhdGV0aW1lGBMyMDIwLTA1LTI5IDIzOjA4OjAx\nFtLxpLzMXBgoMTlmZWE4NTNmY2I0ZGJiOWViMDE5ZTc3NWU2ZjM3YjFmMjA2YjY4NwAKb3NfdmVy\nc2lvbhgDMi4xFtLxpLzMXBgoMTlmZWE4NTM0MGNkOGEwMzI4ZGY2NGYzMTdkOTlhMzRiMjZkZTUz\nNAABdhgFNS01LTUW0vGkvMxcGCgxOWZlYTg1M2FmOWU1NjVkYjNjY2ZkNzU1YmI1YTFiNTY4NDhk\nZGM3AAR1bXR0GCYwMi02MjJmNjg1ZGJiNjBmMmRiNmZkMzNmMTk0NGU4NzctMjEyZhbS8aS8zFwY\nKDE5ZmVhODUzZmMwZDMxOGU3MzYzOWRlYjc2ODI5Zjc3Mzk3MjhiNmIABmppdHRlchgBMBa85+vH\nzFwYKGFlMWIxMTU0YWQwYzJjMzY2ODgyYmRlMzBjZjY1ZGRhODNkM2NiN2QAB2NoYW5uZWwYAnl0\nFtLxpLzMXBgoMTlmZWE4NTNjNjBlMzcwZjg4MmRmOWNkYzU5MTBkMjA1YzY0NWVjNAAEdW1pZBgg\nN2UyZWFhNWQxNjE1ODRjYmFhNzZlMWI1NDI4ZjI0NGYW0vGkvMxcGCgxOWZlYTg1MzNkNTBlZWJi\nNWViZGZhMDczOTQ4ZTMxMGI1YWYzYWVhABUCGCBlMWU4YTRjYzUyNzc1NDMyMDhhNTE4MDJkMjll\nYzg5NAA=\n","req_time":56,"carrier":"CHINA MOBILE","successful_requests":576,"device_board":"omap3630","vertical_type":0,"device_manufacturer":"Samsung","id_tracking":"GwSMBnNlcmlhbBgQMzEzMzE0Qjg1NDgxMDBFQxaO1tu7zFwVAgADbWFjGBEwMDpEMzpCQjo1RTo0\nRTpEQxaK1tu7zFwVAgAEaW1laRgPODYyOTk0NTEyNDY0MzAxFpTW27vMXBUCAAVpZG1kNRggN2Uy\nZWFhNWQxNjE1ODRjYmFhNzZlMWI1NDI4ZjI0NGYWmNbbu8xcFQIAGQwA\n","os":"Android","app_signature":"8E:D8:CD:27:8E:29:C0:DB:28:9D:6B:D3:4A:1E:D7:3F","cpu":"ARMv7 Processor rev 9 (v75)","package_name":"com.shinyv.cnr","sdk_version":"6.1.0","device_id":"862994512464301","device_brand":"OMAP3630","access":"2G\/3G","country":"CN","os_version":"2.1","idmd5":"7e2eaa5d161584cbaa76e1b5428f244f","display_name":"云听","sdk_type":"Android","device_manuid":"YXS29F","mccmnc":"","language":"zh","channel":"yt","device_manutime":1291182427000}}
//05-30 18:18:12.164 29763-29778/com.shinyv.cnr I/System.out: minglog-umeng-{"appkey":"5eaa8d23570df3a3240003ed","umid":"7e2eaa5d161584cbaa76e1b5428f244f","signature":"f9d922085ceb3b1fd65a6fff832d6490b72be563e0c3963633ab5b7beacad15e","checksum":"706c79e9a8b652d97e4c6228474d39e3"}
//05-30 18:18:12.164 29763-29778/com.shinyv.cnr I/System.out: minglog-umeng-{"appkey":"5eaa8d23570df3a3240003ed","umid":"47dce6f5b95bf43961e1bc6c4c389e46","channel":"yt"}
//	public static String rebootTime; 
	ContentObserver mAutoTimeObserver;
    /** Called when the activity is first created. */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        try
        {

//            WallpaperManager mWallManager = WallpaperManager.getInstance(this);
//            mWallManager.setResource(R.drawable.default_wallpaper);

//            rebootTime = com.ming.xposed.xishuashua.util.MyPowerOn.setRestart(this,36);

//		    AUtil.showTips(this, "手机将于 "+rebootTime+" 重启");
//			System.getProperties().setProperty("proxySet", "true");
//			System.getProperties().setProperty("http.proxyHost", "115.239.189.242");
//			System.getProperties().setProperty("http.proxyPort", "80");
//			System.getProperties().setProperty("http.proxyUserName", "sx32");
//			System.getProperties().setProperty("http.proxyPassword", "qqw");
//

//		    new MingVPN().init(this);
            applist = new AppList(this);
            setContentView(applist);
    		applist.start();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    class BasicAuthenticator extends Authenticator {
        String userName;
        String password;

        public BasicAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        /**
         * Called when password authorization is needed.  Subclasses should
         * override the default implementation, which returns null.
         *
         * @return The PasswordAuthentication collected from the
         *         user, or null if none is provided.
         */
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password.toCharArray());
        }  
    }
    
    protected void onPause()
    {
    	super.onPause();
//    	if(applist!=null)
//    	{
//    		applist.onPause();
//    	}
      
    }

    public void onResume()
    {
    	super.onResume();
//    	if(applist!=null)
//    	{
//    		applist.onResume();
//    	}
    }
}