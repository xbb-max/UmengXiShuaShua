package com.ming.xposed.xishuashua.mod;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ming.util.android.AUtil;
import com.ming.util.common.Element;
import com.ming.util.common.Util;



import java.util.Hashtable;
import java.util.Vector;

import static com.ming.xposed.xishuashua.logic.ShuaLiang.getSDPath;

public class XposedConf {

	
	public static String prop_Path = getSDPath();//"/data/data/com.ming.xposed.xishuashua/files";
	public static long timeDelay;
	
	public void setProxy() {
		
//		System.getProperties().setProperty("proxySet", "false");
//		System.getProperties().setProperty("http.proxyHost", "");
//		System.getProperties().setProperty("http.proxyPort", "");
		
//		try
//		{
//			byte[] data = Util.readFileData(prop_Path+"/mingip.prop");
//			if (data != null)
//			{
//				String ipConf = new String(data, "utf-8");
//			    Util.debug("ip---prop--------------"+ipConf);
//
//				String prop = Util.getTagString(ipConf);
//				String[] params = prop.split(",");
//				if(params.length==4)
//				{
//					boolean  isProxy = Util.getBoolean(params[0]);
//					String host = params[1];
//					String port = params[2];
//					String ua = params[3];
//					Util.debug("isProxy--------------"+isProxy+"---host---"+host+":"+port+"---user-agent---"+ua);
//
//					if(isProxy)
//					{
//						System.getProperties().setProperty("proxySet", "true");
//						System.getProperties().setProperty("https.proxyHost", host);
//						System.getProperties().setProperty("https.proxyPort", port);
//					}
////					System.getProperties().setProperty("http.agent", ua);
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}	
	
	
	
	
	
//
//	public static void setProxy(HttpUriRequest localHttpUriRequest) {
//
////		System.getProperties().setProperty("proxySet", "false");
////		System.getProperties().setProperty("http.proxyHost", "");
////		System.getProperties().setProperty("http.proxyPort", "");
//
//		try
//		{
//			byte[] data = Util.readFileData(prop_Path+"/mingip.prop");
//			if (data != null)
//			{
//				String ipConf = new String(data, "utf-8");
//			    Util.debug("apache.http---ip---prop--------------"+ipConf);
//
//				String prop = Util.getTagString(ipConf);
//				String[] params = prop.split(",");
//				if(params.length==4)
//				{
//					boolean isProxy = Util.getBoolean(params[0]);
//					String host = params[1];
//					String port = params[2];
//					String ua = params[3];
//					Util.debug("apache.http---isProxy--------------"+isProxy+"---host---"+host+":"+port+"---user-agent---"+ua);
//
//					if(isProxy)
//					{
//			    	    HttpHost localHttpHost = new HttpHost(host, Util.getInt(port));
//					    localHttpUriRequest.getParams().setParameter("http.route.default-proxy",localHttpHost);
//					}
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	
	
	public void loadTimeDelay() {
		
//		System.getProperties().setProperty("proxySet", "false");
//		System.getProperties().setProperty("http.proxyHost", "");
//		System.getProperties().setProperty("http.proxyPort", "");
		
		try 
		{
			byte[] data = Util.readFileData(prop_Path+"/mingtime.prop");
			if (data != null) 
			{
				String timeConf = new String(data, "utf-8");
			    Util.debug("time---delay--------------"+timeConf);
			    
				String prop = Util.getTagString(timeConf);
				
				XposedConf.timeDelay = Util.getLong(prop);
				
			    Util.debug("XposedConf.timeDelay--------------"+ XposedConf.timeDelay);
				

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}		
	
	
	
	Hashtable<String, Hashtable<String, String>> allConfs;
	
	public XposedConf()
	{
		allConfs = new Hashtable<String, Hashtable<String, String>>();
	}
	
	public Hashtable<String, String> getValues(String key) {
		// TODO Auto-generated method stub
		return allConfs.get(key);
	}

	public void load(String xml) {
		try
		{
			Element node = Element.parse(xml);
			parse(node);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void load() {
		try
		{
			String data = Util.readFileSdcard(prop_Path+"/mingconf.prop");
			if(data!=null)
			{
//				String xml = new String(data,"utf-8");
				Util.debug("read file size "+data.length()
				+" filePath=" +prop_Path+"/mingconf.prop");
				Util.debug("load-conf-------"+data);
//				showTips("read file size "+data.length() +"\n"
//						+" data = "+data,5);
//				AUtil.WriteFileData(prop_Path+"/bei2.prop",data);

				load(data);
			}else{
//				showTips("data== null",5);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		loadTimeDelay();
	}
	
	private void parse(Element node)
	{
		String tag = node.getTag();
		Hashtable<String, String> values = new Hashtable<String, String>();
		Vector<String> attrs = node.getAttrs();
		for(String attr:attrs)
		{
			values.put(attr, node.getAttr(attr));
		}
        Util.debug("tag"+tag +" values "+values.toString());
		allConfs.put(tag, values);
		
		Vector<Element> nodes = node.getChildren();
		for(Element el:nodes)
		{
			parse(el);
		}
	}

	

	
	
	
}
