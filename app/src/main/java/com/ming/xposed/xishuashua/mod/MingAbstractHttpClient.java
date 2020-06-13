package com.ming.xposed.xishuashua.mod;

import de.robv.android.xposed.XC_MethodHook;

public class MingAbstractHttpClient extends XC_MethodHook{


	  protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodParams)
	    throws Throwable
	  {
//		    XiShuaShuaConf.getAbstractHttpClientMonitor(methodParams);
//		    HttpUriRequest localHttpUriRequest = (HttpUriRequest)methodParams.args[0];
//		    URI localURI = localHttpUriRequest.getURI();		  
//		    Util.debug("ming------------apache.http-----------1:"+localURI.toString());
//		    String url = localURI.toString();
//		    if(url.startsWith("http://loc.map.baidu.com/sdk.php"))
//		    {
//		    	HttpRequestBase hqb = (HttpRequestBase)methodParams.args[0];
//		    	
//			    Util.debug("methodParams.args[0]------------apache.http-----------:"+methodParams.args[0]);
//			    URI uri = new URI(XiShuaShuaConf.ming_api_baidumap_url);
//			    hqb.setURI(uri);
//			    
//			    localURI = hqb.getURI();		  
//			    Util.debug("ming------------apache.http-----------2:"+localURI.toString());
//		    }
//		    else
//		    {
//			    XposedConf.setProxy(localHttpUriRequest);
//		    }
	  }	

}
