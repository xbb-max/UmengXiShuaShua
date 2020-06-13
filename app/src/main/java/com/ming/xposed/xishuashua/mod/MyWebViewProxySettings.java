package com.ming.xposed.xishuashua.mod;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Proxy;
import android.os.Build;
import android.os.Parcelable;
import android.webkit.WebView;

import com.ming.util.common.Util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@SuppressLint("NewApi")
public class MyWebViewProxySettings {
	static String LOG_TAG = "minglog";
	public static boolean setProxy(WebView webview, String host, String p, Context applicationClassName) {
		int port = Util.getInt(p);
	    // 3.2 (HC) or lower
	    if (Build.VERSION.SDK_INT <= 13) {
	        return setProxyUpToHC(webview, host, port);
	    }
	    // ICS: 4.0
	    else if (Build.VERSION.SDK_INT <= 15) {
	        return setProxyICS(webview, host, port);
	    }
	    // 4.1-4.3 (JB)
	    else if (Build.VERSION.SDK_INT <= 18) {
	        return setProxyJB(webview, host, port);
	    }
	    // 4.4 (KK) & 5.0 (Lollipop)
	    else {
	        return setProxyKitKat(applicationClassName, host, port);
	    }
//	    return true;
	}

	/**
	 * Set Proxy for Android 3.2 and below.
	 */
	@SuppressWarnings("all")
	private static boolean setProxyUpToHC(WebView webview, String host, int port) {
	    Util.debug( "Setting proxy with <= 3.2 API.");
//
//	    HttpHost proxyServer = new HttpHost(host, port);
//	    // Getting network
//	    Class networkClass = null;
//	    Object network = null;
//	    try {
//	        networkClass = Class.forName("android.webkit.Network");
//	        if (networkClass == null) {
//	            Util.debug( "failed to get class for android.webkit.Network");
//	            return false;
//	        }
//	        Method getInstanceMethod = networkClass.getMethod("getInstance", Context.class);
//	        if (getInstanceMethod == null) {
//	            Util.debug( "failed to get getInstance method");
//	        }
//	        network = getInstanceMethod.invoke(networkClass, new Object[]{webview.getContext()});
//	    } catch (Exception ex) {
//	        Util.debug( "error getting network: " + ex);
//	        return false;
//	    }
//	    if (network == null) {
//	        Util.debug( "error getting network: network is null");
//	        return false;
//	    }
//	    Object requestQueue = null;
//	    try {
//	        Field requestQueueField = networkClass
//	                .getDeclaredField("mRequestQueue");
//	        requestQueue = getFieldValueSafely(requestQueueField, network);
//	    } catch (Exception ex) {
//	        Util.debug( "error getting field value");
//	        return false;
//	    }
//	    if (requestQueue == null) {
//	        Util.debug( "Request queue is null");
//	        return false;
//	    }
//	    Field proxyHostField = null;
//	    try {
//	        Class requestQueueClass = Class.forName("android.net.http.RequestQueue");
//	        proxyHostField = requestQueueClass
//	                .getDeclaredField("mProxyHost");
//	    } catch (Exception ex) {
//	        Util.debug( "error getting proxy host field");
//	        return false;
//	    }
//
//	    boolean temp = proxyHostField.isAccessible();
//	    try {
//	        proxyHostField.setAccessible(true);
//	        proxyHostField.set(requestQueue, proxyServer);
//	    } catch (Exception ex) {
//	        Util.debug( "error setting proxy host");
//	    } finally {
//	        proxyHostField.setAccessible(temp);
//	    }
//
//	    Util.debug( "Setting proxy with <= 3.2 API successful!");
	    return true;
	}

	@SuppressWarnings("all")
	private static boolean setProxyICS(WebView webview, String host, int port) {
	    try
	    {
	        Util.debug( "Setting proxy with 4.0 API.");

	        Class jwcjb = Class.forName("android.webkit.JWebCoreJavaBridge");
	        Class params[] = new Class[1];
	        params[0] = Class.forName("android.net.ProxyProperties");
	        Method updateProxyInstance = jwcjb.getDeclaredMethod("updateProxy", params);

	        Class wv = Class.forName("android.webkit.WebView");
	        Field mWebViewCoreField = wv.getDeclaredField("mWebViewCore");
	        Object mWebViewCoreFieldInstance = getFieldValueSafely(mWebViewCoreField, webview);

	        Class wvc = Class.forName("android.webkit.WebViewCore");
	        Field mBrowserFrameField = wvc.getDeclaredField("mBrowserFrame");
	        Object mBrowserFrame = getFieldValueSafely(mBrowserFrameField, mWebViewCoreFieldInstance);

	        Class bf = Class.forName("android.webkit.BrowserFrame");
	        Field sJavaBridgeField = bf.getDeclaredField("sJavaBridge");
	        Object sJavaBridge = getFieldValueSafely(sJavaBridgeField, mBrowserFrame);

	        Class ppclass = Class.forName("android.net.ProxyProperties");
	        Class pparams[] = new Class[3];
	        pparams[0] = String.class;
	        pparams[1] = int.class;
	        pparams[2] = String.class;
	        Constructor ppcont = ppclass.getConstructor(pparams);

	        updateProxyInstance.invoke(sJavaBridge, ppcont.newInstance(host, port, null));

	        Util.debug( "Setting proxy with 4.0 API successful!");
	        return true;
	    }
	    catch (Exception ex)
	    {
	        Util.debug( "failed to set HTTP proxy: " + ex);
	        return false;
	    }
	}

	/**
	 * Set Proxy for Android 4.1 - 4.3.
	 */
	@SuppressWarnings("all")
	private static boolean setProxyJB(WebView webview, String host, int port) {
	    Util.debug( "Setting proxy with 4.1 - 4.3 API.");

	    try {
	        Class wvcClass = Class.forName("android.webkit.WebViewClassic");
	        Class wvParams[] = new Class[1];
	        wvParams[0] = Class.forName("android.webkit.WebView");
	        Method fromWebView = wvcClass.getDeclaredMethod("fromWebView", wvParams);
	        Object webViewClassic = fromWebView.invoke(null, webview);

	        Class wv = Class.forName("android.webkit.WebViewClassic");
	        Field mWebViewCoreField = wv.getDeclaredField("mWebViewCore");
	        Object mWebViewCoreFieldInstance = getFieldValueSafely(mWebViewCoreField, webViewClassic);

	        Class wvc = Class.forName("android.webkit.WebViewCore");
	        Field mBrowserFrameField = wvc.getDeclaredField("mBrowserFrame");
	        Object mBrowserFrame = getFieldValueSafely(mBrowserFrameField, mWebViewCoreFieldInstance);

	        Class bf = Class.forName("android.webkit.BrowserFrame");
	        Field sJavaBridgeField = bf.getDeclaredField("sJavaBridge");
	        Object sJavaBridge = getFieldValueSafely(sJavaBridgeField, mBrowserFrame);

	        Class ppclass = Class.forName("android.net.ProxyProperties");
	        Class pparams[] = new Class[3];
	        pparams[0] = String.class;
	        pparams[1] = int.class;
	        pparams[2] = String.class;
	        Constructor ppcont = ppclass.getConstructor(pparams);

	        Class jwcjb = Class.forName("android.webkit.JWebCoreJavaBridge");
	        Class params[] = new Class[1];
	        params[0] = Class.forName("android.net.ProxyProperties");
	        Method updateProxyInstance = jwcjb.getDeclaredMethod("updateProxy", params);

	        updateProxyInstance.invoke(sJavaBridge, ppcont.newInstance(host, port, null));
	    } catch (Exception ex) {
	        Util.debug("Setting proxy with >= 4.1 API failed with error: " + ex.getMessage());
	        return false;
	    }

	    Util.debug( "Setting proxy with 4.1 - 4.3 API successful!");
	    return true;
	}

	// from https://stackoverflow.com/questions/19979578/android-webview-set-proxy-programatically-kitkat
	private static boolean setProxyKKPlus(WebView webView, String host, int port, String applicationClassName) {
	    Util.debug( "Setting proxy with >= 4.4 API.");

	    Context appContext = webView.getContext().getApplicationContext();
	    System.setProperty("http.proxyHost", host);
	    System.setProperty("http.proxyPort", port + "");
	    System.setProperty("https.proxyHost", host);
	    System.setProperty("https.proxyPort", port + "");
	    try {
	        Class applictionCls = Class.forName(applicationClassName);
	        Field loadedApkField = applictionCls.getField("mLoadedApk");
	        loadedApkField.setAccessible(true);
	        Object loadedApk = loadedApkField.get(appContext);
	        Class loadedApkCls = Class.forName("android.app.LoadedApk");
	        Field receiversField = loadedApkCls.getDeclaredField("mReceivers");
	        receiversField.setAccessible(true);
	        Map receivers = (Map) receiversField.get(loadedApk);
	        for (Object receiverMap : receivers.values()) {
	            for (Object rec : ((Map) receiverMap).keySet()) {
	                Class clazz = rec.getClass();
	                if (clazz.getName().contains("ProxyChangeListener")) {
	                    Method onReceiveMethod = clazz.getDeclaredMethod("onReceive", Context.class, Intent.class);
	                    Intent intent = new Intent(Proxy.PROXY_CHANGE_ACTION);

	                    onReceiveMethod.invoke(rec, appContext, intent);
	                }
	            }
	        }

	        Util.debug( "Setting proxy with >= 4.4 API successful!");
	        return true;
	    } catch (ClassNotFoundException e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        Util.debug( e.getMessage());
	        Util.debug( exceptionAsString);
	    } catch (NoSuchFieldException e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        Util.debug( e.getMessage());
	        Util.debug( exceptionAsString);
	    } catch (IllegalAccessException e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        Util.debug( e.getMessage());
	        Util.debug( exceptionAsString);
	    } catch (IllegalArgumentException e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        Util.debug( e.getMessage());
	        Util.debug( exceptionAsString);
	    } catch (NoSuchMethodException e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        Util.debug( e.getMessage());
	        Util.debug( exceptionAsString);
	    } catch (InvocationTargetException e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        Util.debug( e.getMessage());
	        Util.debug( exceptionAsString);
	    } 
	    return false;
	}
	
	  private static boolean setProxyKitKat(Context appContext, String host, int port) {
		     System.setProperty("http.proxyHost", host);
		     System.setProperty("http.proxyPort", port + "");
		     System.setProperty("https.proxyHost", host);
		     System.setProperty("https.proxyPort", port + "");
		     try {
		       Class applictionCls = Class.forName("android.app.Application");
		       Field loadedApkField = applictionCls.getDeclaredField("mLoadedApk");
		       loadedApkField.setAccessible(true);
		       Object loadedApk = loadedApkField.get(appContext);
		       Class loadedApkCls = Class.forName("android.app.LoadedApk");
		       Field receiversField = loadedApkCls.getDeclaredField("mReceivers");
		       receiversField.setAccessible(true);
		       Map receivers = (Map) receiversField.get(loadedApk);
		       for (Object receiverMap : receivers.values()) {
		         for (Object rec : ((Map) receiverMap).keySet()) {
		           Class clazz = rec.getClass();
		           if (clazz.getName().contains("ProxyChangeListener")) {
		             Method onReceiveMethod = clazz.getDeclaredMethod("onReceive", Context.class, Intent.class);
		             Intent intent = new Intent(Proxy.PROXY_CHANGE_ACTION);
		             /*********** optional, may be need in future *************/
		             final String CLASS_NAME = "android.net.ProxyProperties";
		             Class cls = Class.forName(CLASS_NAME);
		             Constructor constructor = cls.getConstructor(String.class, Integer.TYPE, String.class);
		             constructor.setAccessible(true);
		             Object proxyProperties = constructor.newInstance(host, port, null);
		             intent.putExtra("proxy", (Parcelable) proxyProperties);
		             /*********** optional, may be need in future *************/
		             onReceiveMethod.invoke(rec, appContext, intent);
		           }
		         }
		       }
		     } catch (Exception ex) {
		    	 ex.printStackTrace();
		       Util.debug( "Setting proxy for KitKat failed with error: " + ex.getMessage());  
		       return false;  
		     }  
		     Util.debug( "Setting proxy for KitKat succeeded");  
		     return true;  
		   }  


	private static Object getFieldValueSafely(Field field, Object classInstance) throws IllegalArgumentException, IllegalAccessException {
	    boolean oldAccessibleValue = field.isAccessible();
	    field.setAccessible(true);
	    Object result = field.get(classInstance);
	    field.setAccessible(oldAccessibleValue);
	    return result;
	}
}
