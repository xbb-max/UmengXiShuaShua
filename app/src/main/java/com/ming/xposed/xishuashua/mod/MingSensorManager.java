package com.ming.xposed.xishuashua.mod;

import android.hardware.Sensor;

import com.ming.util.common.Util;

import java.lang.reflect.Field;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MingSensorManager extends MingMethodHook {

	public MingSensorManager(XposedConf conf) {
		super(conf);
		  values = conf.getValues("Build");
	}
	
//	AK8975 3-axis Magnetic field sensor
//	0.06
//	Asahi Kasei Microdevices
	public String[][] field_sensors = {
			{"AK8975 3-axis Magnetic field sensor","0.06","Asahi Kasei Microdevices"},
			{"MPU3050","0.06","Invensense"},
			{"bmp180","0.01","Bosch"},
			{"mxc622x","0.01","Memsic"},
			{"AK8973 3-axis Magnetic field sensor","0.0625","Asahi Kasei"},
			{"AK8973 3-axis Magnetic field sensor","0.0625","Asahi Kasei Microdevices"},
			{"Corrected Gyroscope Sensor","0.07217305","Google Inc."},
			
	                     };
	
	
	
//	Kionix KXTF9 3-axis Accelerometer
//	0.009576807
//	Kionix
	
	public String[][] accelerometers = {
			{"Kionix KXTF9 3-axis Accelerometer","0.009576807","Kionix"},
			{"accelerometer","0.038320314","KIONIX"},
			{"BMA150 3-axis Accelerometer","0.15328126","Bosh"},
			{"magnetic","0.056380314","AKM"},
			{"KR3DM 3-axis Accelerometer","0.019153614","STMicroelectronics"},
			
			
	                     };
	
	
	
//	AK8975 Orientation sensor
//	0.015625
//	Asahi Kasei Microdevices
	
	public String[][] orientation_sensors = {
			{"AK8975 Orientation sensor","0.015625","Asahi Kasei Microdevices"},
			{"Orientation Sensor","0.00390625","Google Inc."},
			{"AK8973 Orientation sensor","1.0","Asahi Kasei"},
			{"orientation","1.0","AKM"},
			{"Orientation Sensor","0.00390625","Google Inc."},
			
			
	                     };
	
//	TMD2771 Light sensor
//	100000.0
//	LITEON
	
	public String[][] light_sensors = {
			{"TMD2771 Light sensor","100000.0","LITEON"},
			{"ap3426-light","1.0","Dyna Image Corporation"},
			{"CM3602 Light sensor","1.0","Capella Microsystems"},
			{"lsensor","6.0","LITE-ON"},
			{"GP2A Light sensor","1.0","Sharp"},
			
			
	                     };

	
	
//	TMD2771 Proximity sensor
//	1.0
//	LITEON
	
	public String[][] proximity_sensors = {
			{"TMD2771 Proximity sensor","1.0","LITEON"},
			{"TSL27713FN","1.0","Taos"},
			{"ap3426-pocket","1.0","oem"},
			{"CM3602 Proximity sensor","9.0","Capella Microsystems"},
			{"psensor","1.0","LITE-ON"},
			{"GP2A Proximity sensor","5.0","Sharp"},
			
			
	                     };
	
	
//	Gravity Sensor
//	0.009576807
//	Google Inc.
	
	public String[][] gravity_sensors = {
			{"Gravity Sensor","0.009576807","Google Inc."},
			{"Gravity Sensor","0.038320314","Google Inc."},
			{"Gravity Sensor","0.15328126","Google Inc."},
			{"gsensor","0.253","vendor"},
			{"Gravity Sensor","0.019153614","Google Inc."},
			
			
	                     };
	
	
//	Linear Acceleration Sensor
//	0.009576807
//	Google Inc.
	
	public String[][] linear_acceleration_sensors = {
			{"Linear Acceleration Sensor","0.009576807","Google Inc."},
			{"Linear Acceleration Sensor","0.038320314","Google Inc."},
			{"Linear Acceleration Sensor","0.15328126","Google Inc."},
			{"Linear Acceleration Sensor","0.019153614","Google Inc."},
			{"K3G Gyroscope sensor","0.012217305","STMicroelectronics"},
			
			
	                     };
	
	
	
//	Rotation Vector Sensor
//	5.9604645E-8
//	Google Inc.
	public String[][] rotation_vector_sensors = {
			{"Rotation Vector Sensor","5.9604645E-8","Google Inc."},
			{"TSL27713FN","5.0","Taos"},
			{"ap3426-proximity","5.0","Dyna Image Corporation"},
			{"Rotation Vector Sensor","5.9604645E-8","Google Inc."},
			
			
	                     };
	
	
	public Object makeSensor(Class clz, Sensor sensor, int which)
	{
		Object obj = null;
		try
		{
//			Constructor co = clz.getConstructor();
//			co.setAccessible(true);
//			co.
		    obj = sensor;//clz.newInstance();
		    String[][] params = null;
		    if(which>7)
		    {
		    	which = Util.getRandom(8);
		    }
		    if(which==0)
		    {
		    	params = gravity_sensors;
		    }
		    else if(which==1)
		    {
		    	params = rotation_vector_sensors;
		    }
		    else if(which==2)
		    {
		    	params = light_sensors;
		    }
		    else if(which==3)
		    {
		    	params = orientation_sensors;
		    }
		    else if(which==4)
		    {
		    	params = accelerometers;
		    }
		    else if(which==5)
		    {
		    	params = linear_acceleration_sensors;
		    }
		    else if(which==6)
		    {
		    	params = proximity_sensors;
		    }
		    else if(which==7)
		    {
		    	params = field_sensors;
		    }
		    String[] values = params[Util.getRandom(params.length)];
		    Field mName = clz.getDeclaredField("mName");
		    mName.setAccessible(true);  
		    mName.set(obj, values[0]);			
		    
		    Field mResolution = clz.getDeclaredField("mResolution");
		    mResolution.setAccessible(true);  
		    mResolution.set(obj, Util.getFloat(values[1]));			
		    
		    Field mVendor = clz.getDeclaredField("mVendor");
		    mVendor.setAccessible(true);  
		    mVendor.set(obj, values[2]);			
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
	    return obj;
	}
		
	
	
	  protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodParam)
	    throws Throwable
	  {
		  if(values!=null)
		  {
			    String meName = methodParam.method.getName();
			    if (meName.equals("getSensorList"))
			    {
			    	List<Sensor> list = (List<Sensor>)methodParam.getResult();
				    ClassLoader loader = packParam.classLoader;
				    Class clz = loader.loadClass("android.hardware.Sensor");
				    
				    
//				    ArrayList myList = new ArrayList();
				    int size = list.size();//5+Util.getRandom(5);
				    for(int i=0;i<size;i++)
				    {
				    	Sensor sensor = list.get(i);
				    	makeSensor(clz,sensor,i);
//				    	myList.add();
				    }
				    
//				    Object obj = clz.newInstance();
//				    Field mName = clz.getDeclaredField("mName");
//				    mName.setAccessible(true);  
//				    mName.set(obj, arg1);
//				    
//				    
//				    Field mResolution = clz.getDeclaredField("mResolution");
//				    mResolution.setAccessible(true);  
//				    Field mVendor = clz.getDeclaredField("mVendor");
//				    mVendor.setAccessible(true);  
				    
//				    for(Sensor sensor:list)
//				    {
//				    	String name = (String)mName.get(sensor);
//				    	Float resolution = (Float)mResolution.get(sensor);
//				    	String vendor = (String)mVendor.get(sensor);
//					    mName.set(sensor, Util.getRandomStr(name));
//					    mVendor.set(sensor, Util.getRandomStr(vendor));
//					    mResolution.set(sensor, Util.getRandom(resolution));
//				    }			    	
				    methodParam.setResult(list);
			    }  
		  }
	  }

	  XC_LoadPackage.LoadPackageParam packParam;
	public void setPackParam(LoadPackageParam packParam) {
		this.packParam = packParam;
	}	
	
	
	

}
