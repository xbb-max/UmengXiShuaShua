package com.ming.xposed.xishuashua.mod;

import android.webkit.WebView;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.mod.logic.RunLogic;

import de.robv.android.xposed.XC_MethodHook;

public class MingWebViewClient extends XC_MethodHook{

	
	  RunLogic runLogic;
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
			    throws Throwable
	  {	
		    String mName = methodParam.method.getName();
		    WebView webview = (WebView)methodParam.args[0];
			Util.debug("WebView-----------------"+mName+"----webview---http---"+webview.getUrl());
		    if (mName.equals("onPageFinished"))
		    {
				if(runLogic!=null)
				{
//					runLogic.onPageFinished(webview,(String)methodParam.args[1]);
				}
		    }
		    if (mName.equals("onPageStarted"))
		    {
				if(runLogic!=null)
				{
//					runLogic.onPageStarted(webview,(String)methodParam.args[1]);
				}
		    }
	  }	

}
