package com.ming.xposed.xishuashua.mod;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import de.robv.android.xposed.XC_MethodHook;

public class MingRuntime extends MingMethodHook {

	public MingRuntime(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
		// TODO Auto-generated constructor stub
	}
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		    String mName = methodParam.method.getName();
			if(values!=null)
			{
			    if (mName.equals("exec"))
				{
			    	String param = (String)methodParam.args[0];
//				    Util.debug("Runtime---cpa---o---"+mName+"---"+param);
			    	if(param.endsWith("cat /proc/cpuinfo"))
			    	{
						methodParam.setResult(new MingCpuInfoProcess((String)get("getCpuAddress")));
			    	}
			    }				
			}
	  }	
}

class MingCpuInfoProcess extends Process
{

	String serial;
	public MingCpuInfoProcess(String string) {
		this.serial = string;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int exitValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() {
		
		String s = "Hardware	: MT6592\n"+
				   "Revision	: 0000\n"+
				   "Serial		: "+serial+"\n";
		byte[] data = s.getBytes();
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(data);
	}

	@Override
	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int waitFor() throws InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}
}