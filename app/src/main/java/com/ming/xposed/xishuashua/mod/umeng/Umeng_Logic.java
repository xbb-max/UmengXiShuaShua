package com.ming.xposed.xishuashua.mod.umeng;

import android.app.Activity;
import android.content.Context;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.mod.logic.RunLogic;


public class Umeng_Logic extends RunLogic {

	
	public static String partner = "";
	public static boolean isRegistOK = false;
	static Context scontext;

	public Umeng_Logic(Context context) {
		super(context);
		Umeng_Logic.scontext = context;
		
//		try
//		{
//			Activity activity = ((Activity) context);
//			InputStream is = activity.getAssets().open("config.properties");
//			byte[] data = Util.inputStreamToBytes(is);
//            String config = new String(data);
//            String[] ss = config.split("\n");
//            if(ss!=null)
//            {
//            	ss = ss[0].split("=");
//            }
//            if(ss!=null)
//            {
//            	partner = ss[1].substring(0, ss[1].length()-1);
//            }
////            loadOtherConf();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
//	    String cn = ((Activity) context).getLocalClassName();
//	    Util.debug("jingdong-:"+cn);
	    
//        if(cn.indexOf("LauchActivity")!=-1)
//        {
//	    	myLogic = new LauchActivity(context);
//        }
//        
//        this.defaultLogic = new Default(context);
//	    this.setApp();
	}
	
	
	public void loadLogic(Context context)
	{
	    String cn = ((Activity) context).getLocalClassName();
	    Util.debug("umeng-:"+cn);
//        this.defaultLogic = new Default(context);
//	    myLogic = logics.get(cn);	    
//	    Util.debug("amazon-myLogic:"+myLogic);
	    
	    this.setApp();
	}	
	


}
