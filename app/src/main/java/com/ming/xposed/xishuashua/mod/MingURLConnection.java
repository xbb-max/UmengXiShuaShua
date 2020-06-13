package com.ming.xposed.xishuashua.mod;

import de.robv.android.xposed.XC_MethodHook;

public class MingURLConnection extends XC_MethodHook{
	
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParams)
	    throws Throwable
	  {
//  		   if(methodParams.thisObject instanceof HttpURLConnection)
//  		   {
//  			  HttpURLConnection httpcon = (HttpURLConnection)methodParams.thisObject;
//  			  String url = httpcon.getURL().toString();
//  			  try
//  			  {
//  	  			  if(url.indexOf("functionId=cpa&")!=-1)
//  	  			  {
//  	  	  			  InputStream is = (InputStream)methodParams.getResult();
//
//  	  	              java.io.ByteArrayOutputStream fos = new java.io.ByteArrayOutputStream();
//
//  	  	              int BUFFER_SIZE = 8*1024;
//  	  	              int length = 0;
//  	  	              byte[] buf = new byte[BUFFER_SIZE];
//  	  	              int size = 0;
//
//  	  	              while ((size = is.read(buf)) != -1) {
//  	  	              	length += size;
//  	  	                fos.write(buf, 0, size);
//  	  	              }
//  	  	              byte[] data = fos.toByteArray();
//  	  	              fos.close();
//   	  	              is.mark(0);
//  	  	              byte[] buffdata = data;
//  	  	              byte[] mydata = Gzip.inflate(data);
//  	  	              
//  	  	  			  String code = new String(mydata);
//  	  	  			  Util.debug("ming------------httpcon-----------:"+httpcon.getURL());
//  	  	  			  Util.debug("ming------------httpcon--data--------"+code);
//  	  	  			  if(code.indexOf("{\"code\":\"0\"}")!=-1)
//  	  	  			  {
//  	  	  		      	XiShuaShuaConf.isCpaOK = true;
//  	    	  			XiShuaShuaConf.isNetCount = true;
//  	  	  			  }
//  	  	  			  
//  	  	  			  java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(buffdata);
//  	  	  			  methodParams.setResult(bais);
//  	  			  }  				  
//  			  } 
//  			  catch(Exception e)
//  			  {
//  	  			  Util.debug("MingURLConnection------------error-----------:"+url);
//  				 e.printStackTrace();  
//  			  }
//  			  
//  			  
//  			  
//  		   }
	  }	
	

}
