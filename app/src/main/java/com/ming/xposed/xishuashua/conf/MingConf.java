package com.ming.xposed.xishuashua.conf;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

import com.ming.util.android.AUtil;
import com.ming.util.common.Element;
import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.bean.Device;

import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class MingConf {
	
	public int operType;
	public static int CHINA_MOBILE = 1;
	public static int CHINA_UNICOM = 2;
	public static int CHINA_TELECOM = 3;
	public static int UNKNOW = 0;
	public Device device;
	public MingDeviceMaker maker;
	Context context;
	int screen_size = 0;
    String[] mobileIps;
	 String[] unicomIps;
	 String[] telecomIps;
	
	public MingConf(Context context)
	{
		this.context = context;
	    WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
    	Util.debug("getWidth+++"+display.getWidth());
    	Util.debug("getHeight"+display.getHeight());
    	screen_size = display.getHeight()*display.getWidth();
		maker = new MingDeviceMaker(context);
		
		mobileIps = getIps("mobile");
		unicomIps = getIps("unicom");
		telecomIps = getIps("telecom");
	}
	
	public String[] getIps(String name)
	{
		String[] ips = null;
		try
		{
			byte[] data = AUtil.getAssetsData(context, "conf/ip/"+name+".txt");
			if(data!=null)
			{
				String txt = new String(data);
				ips = txt.split("\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return ips;
	}	
	

	public String ID;//:JDQ39E
	public String BOARD;//:herring
	public String BOOTLOADER;//:I9020XXKA3
	public String BRAND;//:Google
	public String CPU_ABI;//:armeabi-v7a
	public String CPU_ABI2;//:armeabi
	public String DEVICE;//:crespo
	public String DISPLAY;//:cm_crespo-userdebug 4.2.2 JDQ39E eng.jenkins.20130923.115858 test-keys
	public String FINGERPRINT;//:google/soju/crespo:4.1.2/JZO54K/485486:user/release-keys
	public String HARDWARE;//:herring
	public String HOST;//:cyanogenmod
	public String MANUFACTURER;//:Samsung
	public String MODEL;//:Nexus S
	public String PRODUCT;//:soju
	public String RADIO;//:I9020XXKF1
	public String TAGS;//:test-keys
	public long TIME;//:1379962788000
	public String TYPE;//:userdebug
	public String USER;//:jenkins
	public String VERSION_CODENAME;//:REL
	public String VERSION_INCREMENTAL;//:eng.jenkins.20130923.115858
	public String VERSION_RELEASE;//:4.2.2
	public String VERSION_SDK;//:17
	public int VERSION_SDK_INT;//:17	

	public String android_id;
	public String hasFrontFacingCamera;
	public String hasBackFacingCamera;
	public String hasSDCard;
	public String getSdCardId;
	public String getAvailableInternalMemorySize;
	public String getTotalMemInfo;
	public String getTotalInternalMemorySize;
	
	public String getBluetoothMac;
	public String getCpuCurFreq;
	public String getCpuAddress;
	public String getCpuMaxFreq;
	public String getCpuMinFreq;
	public String getCpuName;
	public String getNetAddressInfo;
	public String hasBackFacingCameraFalsh;
	public String getCpuNumCores;
	
	public String getHeight;
	public String getWidth;
	
	public boolean isUsedMobileNetwork;
	public String getHostAddress;
	
//	XposedHelpers.findAndHookMethod(clz,"hasFrontFacingCamera",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"hasBackFacingCamera",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"hasSDCard",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getSdCardId",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getAvailableInternalMemorySize",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getTotalMemInfo",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getBluetoothMac",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getCpuCurFreq",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getCpuAddress",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getCpuMaxFreq",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getCpuMinFreq",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getCpuName",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getNetAddressInfo",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"hasBackFacingCameraFalsh",mingCpaDeviceUtil);
//	XposedHelpers.findAndHookMethod(clz,"getCpuNumCores",mingCpaDeviceUtil);	
	
	
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getDeviceId---861442782636759
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---hasFrontFacingCamera---true
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---hasBackFacingCamera---true
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---hasSDCard---true
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---checkQEmuDriverFile---false
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---checkPipes---false
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getSdCardId---unknow
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getAvailableInternalMemorySize---930M
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getTotalMemInfo---396 MB
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getMacAddress---683C2D8A9CC
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getBluetoothMac---3C:5A:37:91:A2:67
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getDeviceId---861442782636759
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getIMSI---460003327494950
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getCpuCurFreq---1.0GHz
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getCpuAddress---343328e4c11800ec
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getCpuMaxFreq---1.4GHz
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getCpuMinFreq---0.1GHz
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getCpuName---ARMv7 Processor rev 2 (v7l)
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getNetworkOperatorName---CHINA MOBILE
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getPhoneNumber---8618761527104
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getBuildInfo---device:Coolpad_49_785,model:7266,product:7266_8.2.485,brand:Coolpad,release:4.2.4,display:7266_8.2.485,locale:zh_CN
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getNetAddressInfo---fe80::b607:f9ff:fee8:9434%wlan0, 192.168.1.113
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---hasBackFacingCameraFalsh---true
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getCpuNumCores---1
//	01-01 09:21:48.380: DEBUG/dalvikvm(2241): minglog-CpaDeviceUtil---getNetworkISO---cn
	
	
	
//	01-01 09:15:25.380: INFO/System.out(949): minglog-CpaDeviceUtil---getDeviceId---861357176449406
//	01-01 09:15:25.430: INFO/System.out(949): minglog-CpaDeviceUtil---hasFrontFacingCamera---false
//	01-01 09:15:25.430: INFO/System.out(949): minglog-CpaDeviceUtil---hasBackFacingCamera---true
//	01-01 09:15:25.430: INFO/System.out(949): minglog-CpaDeviceUtil---hasSDCard---false
//	01-01 09:15:25.430: INFO/System.out(949): minglog-CpaDeviceUtil---checkQEmuDriverFile---false
//	01-01 09:15:25.430: INFO/System.out(949): minglog-CpaDeviceUtil---checkPipes---false
//	01-01 09:15:25.440: INFO/System.out(949): minglog-CpaDeviceUtil---getSdCardId---unknow
//	01-01 09:15:25.440: INFO/System.out(949): minglog-CpaDeviceUtil---getAvailableInternalMemorySize---58M
//	01-01 09:15:25.460: INFO/System.out(949): minglog-CpaDeviceUtil---getTotalMemInfo---202MB
//	01-01 09:15:25.470: INFO/System.out(949): minglog-CpaDeviceUtil---getMacAddress---FA7ED3AD8015
//	01-01 09:15:25.470: INFO/System.out(949): minglog-CpaDeviceUtil---getBluetoothMac---unknow
//	01-01 09:15:25.470: INFO/System.out(949): minglog-CpaDeviceUtil---getDeviceId---861357176449406
//	01-01 09:15:25.480: INFO/System.out(949): minglog-CpaDeviceUtil---getIMSI---460009549739450
//	01-01 09:15:25.480: INFO/System.out(949): minglog-CpaDeviceUtil---getCpuCurFreq---1.0GHz
//	01-01 09:15:25.500: INFO/System.out(949): minglog-CpaDeviceUtil---getCpuAddress---0000000000000000
//	01-01 09:15:25.500: INFO/System.out(949): minglog-CpaDeviceUtil---getCpuMaxFreq---1.0GHz
//	01-01 09:15:25.500: INFO/System.out(949): minglog-CpaDeviceUtil---getCpuMinFreq---0.4GHz
//	01-01 09:15:25.500: INFO/System.out(949): minglog-CpaDeviceUtil---getCpuName---ARMv7 Processor rev 1 (v7l)
//	01-01 09:15:25.500: INFO/System.out(949): minglog-CpaDeviceUtil---getNetworkOperatorName---CHINA MOBILE
//	01-01 09:15:25.520: INFO/System.out(949): minglog-CpaDeviceUtil---getPhoneNumber---8618772494950
//	01-01 09:15:25.540: INFO/System.out(949): minglog-CpaDeviceUtil---getBuildInfo---device:Samsung_18_462,model:W2014,product:W2014_0.8.778,brand:Samsung,release:3.0,display:W2014_0.8.778,locale:zh_CN
//	01-01 09:15:25.540: INFO/System.out(949): minglog-CpaDeviceUtil---getNetAddressInfo---192.168.1.131
//	01-01 09:15:25.540: INFO/System.out(949): minglog-CpaDeviceUtil---hasBackFacingCameraFalsh---false
//	01-01 09:15:25.540: INFO/System.out(949): minglog-CpaDeviceUtil---getCpuNumCores---1
//	01-01 09:15:25.540: INFO/System.out(949): minglog-CpaDeviceUtil---getNetworkISO---cn
	
	
	

    
    
    
    
	public Element makeBuildConf()
	{
//		String[] vs = {"1.0","1.1","1.5","1.6",
//				       "2.0","2.0.1","2.1","2.2."+Util.getRandom(10),"2.3.2","2.3.7",
//				       "3.0","3.1","3.2."+Util.getRandom(10),"4.0.2","4.0.4",
//					   "4.1."+Util.getRandom(10),"4.2."+Util.getRandom(10),"4.3."+Util.getRandom(10),"4.4."+Util.getRandom(10)};
		
		Hashtable<String, String> vs = new Hashtable<String, String>();
		vs.put("7","2.1");
		vs.put("8","2.2");
		vs.put("9","2.3.1");
		vs.put("10","2.3.3");
		vs.put("11","3.0");
		vs.put("12","3.1");
		vs.put("13","3.2");
		vs.put("14","4.0");
		vs.put("15","4.0.3");
		vs.put("16","4.1.2");
		vs.put("17","4.2.2");
		vs.put("18","4.3.1");
		vs.put("19","4.4.2");
		vs.put("20","4.4W.2");
		vs.put("21","5.0.1");
		vs.put("22","5.1.1");
		vs.put("23","6.0");
		vs.put("24","7.0");
		vs.put("25","7.1.1");

		
		
		
		String[] hardwares = {"herring","mul","oboea","qcom"};
		
		ID = Util.getRandomStr(letters).toUpperCase()+Util.getRandomStr(letters).toUpperCase()+Util.getRandomStr(letters).toUpperCase()+Util.getRandom(10)+Util.getRandom(10)+Util.getRandomStr(letters).toUpperCase();
		BOARD = device.cpuDetail.trim().toLowerCase();
		BOOTLOADER = device.modelName;
		if(Util.isNull(BOOTLOADER)) 
		{
			BOOTLOADER = "unknown";//
		}
		BRAND = device.cpuDetail.toUpperCase();//device.brand_en;
		CPU_ABI = "armeabi-v7a";
		CPU_ABI2 = "armeabi";
		
		DEVICE = device.modelName;//device.brand_en+"_"+Util.getRandom(100)+"_"+Util.getRandom(1000);
		
		DISPLAY = vs.get(device.api)+"."+Util.getRandom(10)+"."+ID+"."+Util.getRandom(1000)+"."+Util.getRandom(100);//device.modelName+"_"+Util.getRandom(10)+"."+Util.getRandom(10)+"."+Util.getRandom(1000);
		FINGERPRINT = device.brand_en+"/"+DEVICE+"/"+DISPLAY+"/"+ID+"/"+(668+Util.getRandom(10000))+":user/release-keys";
		if(BRAND.toLowerCase().startsWith("msm"))
		{
			HARDWARE = "qcom";//hardwares[Util.getRandom(hardwares.length)];//device.brand_en;
		}
		else
		{
			HARDWARE = hardwares[Util.getRandom(hardwares.length)];//device.brand_en;
		}
		HOST = device.brand_en;
		MANUFACTURER = device.brand_en;
		MODEL = device.modelName;
		MANUFACTURER = device.brand_en;
		PRODUCT = MODEL;
		RADIO = device.modelName;//device.modelName;
		if(Util.isNull(RADIO)) 
		{
			RADIO = "unknown";//
		}
		
		TAGS = "release-keys";
		TIME = Util.getLong("1"+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+"000");
		TYPE = "user";
		USER = "root";
		VERSION_CODENAME = "REL";
		VERSION_INCREMENTAL = "eng.root.201"+Util.getRandom(5)+"."+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10)+Util.getRandom(10);
		VERSION_RELEASE = vs.get(device.api); 
		VERSION_SDK = device.api;
		VERSION_SDK_INT = Util.getInt(device.api);	
		
//		String aid = Settings.Secure.getString(context.getContentResolver(), "android_id");
		
		Random r = new Random();
		long v = r.nextLong();
		android_id = Long.toHexString(v);
		
		if(Util.getRandomRate(new float[]{0.4f,0.6f})==60)
		{
			isUsedMobileNetwork = true;
		} else {
			isUsedMobileNetwork = false;
		}
		
//		isUsedMobileNetwork = true;

		
		hasFrontFacingCamera = Util.getRandom(6)%2==0?"true":"false";
		hasBackFacingCamera = "true";
		hasSDCard = Util.getRandom(6)%2==0?"true":"false";
		getSdCardId = "unknow";
		if(Util.getBoolean(hasSDCard))
		{
			getSdCardId = "001";//+Util.getRandom(3)+"";
		}
		int totalSize = (120000+Util.getRandom(60000));
		getTotalInternalMemorySize = String.valueOf(totalSize);//getFileSizeStr2(1024*1024*(800+Util.getRandom(800)));

		int availableSize = totalSize*(3+Util.getRandom(6))/10;
		getAvailableInternalMemorySize = String.valueOf(availableSize);

		int totalMem = 266230+Util.getRandom(266230);
		getTotalMemInfo = String.valueOf(totalMem);

		//10-18 23:46:26.190: INFO/System.out(1604): minglog-l----4096a-----21662-----b-----47360


		getBluetoothMac = getMac();
		getCpuAddress = getAndroidID();


		int basicFreq = 800000;
		int maxFreq = basicFreq+100000*Util.getRandom(8);
		int minFreq = maxFreq-600000;
		getCpuMaxFreq = String.valueOf(maxFreq);
		getCpuMinFreq = String.valueOf(minFreq);
		getCpuCurFreq = getCpuMaxFreq;

//		1000000
//	    10-18 22:15:02.150: INFO/System.out(2143): minglog-line----1000000
//	    10-18 22:15:02.150: INFO/System.out(2143): minglog-line----400000
		getCpuName = getCupName();
		getNetAddressInfo = getNetAddressInfo();
		if(isUsedMobileNetwork)
		{
			getNetAddressInfo = getMobileNetAddressInfo();
		}
		getHostAddress = getNetAddressInfo;


		hasBackFacingCameraFalsh = Util.getRandom(6)%2==0?"true":"false";
		getCpuNumCores = "1";//(1+Util.getRandom(2))+"";




		getWidth = device.width;
		getHeight = device.height;


		return makeBuildNode();
	}


	private String getMobileNetAddressInfo() {
		StringBuffer sb = new StringBuffer();
		String[] ips = null;
		if(operType==CHINA_MOBILE)
		{
			ips = mobileIps;
		}
		if(operType==CHINA_UNICOM)
		{
			ips = unicomIps;
		}
		if(operType==CHINA_TELECOM)
		{
			ips = telecomIps;
		}
		if(ips==null)
		{
			ips = telecomIps;
		}
		String host = ips[Util.getRandom(ips.length)];
		host = host.substring(0,host.length()-1);
		sb.append(host);
		sb.append(".");
		sb.append(""+Util.getRandom(255));

		return sb.toString();
	}


	private Element makeBuildNode()
	{
		Element node = new Element("Build");
		node.setAttr("ID", ID);
		node.setAttr("BOARD", BOARD);
		node.setAttr("BOOTLOADER", BOOTLOADER);
		node.setAttr("BRAND", BRAND);
		node.setAttr("CPU_ABI", CPU_ABI);
		node.setAttr("CPU_ABI2", CPU_ABI2);
		node.setAttr("DEVICE", DEVICE);
		node.setAttr("DISPLAY", DISPLAY);
		node.setAttr("FINGERPRINT", FINGERPRINT);
		node.setAttr("HARDWARE", HARDWARE);
		node.setAttr("HOST", HOST);
		node.setAttr("MANUFACTURER", MANUFACTURER);
		node.setAttr("MODEL", MODEL);
		node.setAttr("MANUFACTURER", MANUFACTURER);
		node.setAttr("PRODUCT", PRODUCT);
		node.setAttr("RADIO", RADIO);
		node.setAttr("TAGS", TAGS);
		node.setAttr("TIME", Long.toString(TIME));
		node.setAttr("TYPE", TYPE);
		node.setAttr("USER", USER);
		node.setAttr("VERSION_CODENAME", VERSION_CODENAME);
		node.setAttr("VERSION_INCREMENTAL", VERSION_INCREMENTAL);
		node.setAttr("VERSION_RELEASE", VERSION_RELEASE);
		node.setAttr("VERSION_SDK", VERSION_SDK);
		node.setAttr("VERSION_SDK_INT", Integer.toString(VERSION_SDK_INT));

		node.setAttr("android_id", android_id);
		node.setAttr("hasFrontFacingCamera", hasFrontFacingCamera);
		node.setAttr("hasBackFacingCamera", hasBackFacingCamera);
		node.setAttr("hasSDCard", hasSDCard);
		node.setAttr("getSdCardId", getSdCardId);
		node.setAttr("getAvailableInternalMemorySize", getAvailableInternalMemorySize);
		node.setAttr("getTotalInternalMemorySize", getTotalInternalMemorySize);
		node.setAttr("getTotalMemInfo", getTotalMemInfo);
		node.setAttr("getBluetoothMac", getBluetoothMac);
		node.setAttr("getCpuCurFreq", getCpuCurFreq);
		node.setAttr("getCpuAddress", getCpuAddress);
		node.setAttr("getCpuMaxFreq", getCpuMaxFreq);
		node.setAttr("getCpuMinFreq", getCpuMinFreq);
		node.setAttr("getCpuName", getCpuName);
		node.setAttr("getNetAddressInfo", getNetAddressInfo);
		node.setAttr("hasBackFacingCameraFalsh", hasBackFacingCameraFalsh);
		node.setAttr("getCpuNumCores", getCpuNumCores);

		node.setAttr("getWidth", getWidth);
		node.setAttr("getHeight", getHeight);

		node.setAttr("isUsedMobileNetwork", isUsedMobileNetwork+"");

		node.setAttr("getHostAddress", getHostAddress);




		return node;
	}



	public String imei;
	public String getSimSerialNumber;
	public String imsi;
	public String getLine1Number;
	public String getSimCountryIso;
	public String getSimOperator;
	public String getSimOperatorName;
	public String getNetworkCountryIso;
	public String getNetworkOperator;
	public String getNetworkOperatorName;
	public int getNetworkType;
	public int getPhoneType;
	public boolean hasIccCard;
	public int getSimState;
	public String getDeviceSoftwareVersion;
	public boolean isNetworkRoaming;
	public int getCallState;

	public String getMacAddress;
	public String getSSID;
	public String getBSSID;

	public Element makeTelephonyManagerConf(Device device, String imei_, String imsi_)
	{
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getDeviceId---:99000524195160
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getSimSerialNumber---:null
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getSubscriberId---:null
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getLine1Number---:null
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getSimCountryIso---:cn
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getSimOperator---:46001
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getSimOperatorName---:
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getNetworkOperator---:46001
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getNetworkOperatorName---:CHN-UNICOM
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getNetworkType---:0
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-getPhoneType---:1
//		08-19 04:06:58.414: VERBOSE/RenderScript_jni(192): ming-hasIccCard---:true

		imei = getImei(device);
		imsi = getImsi();
		if(!Util.isNull(imei_))
		{
			imei = imei_;
		}
		if(!Util.isNull(imsi_))
		{
			imsi = imsi_;
		}

		getSimSerialNumber = getSimSerialNumber();
		getLine1Number = "+86"+getLineNumber();
		getSimCountryIso = "cn";
        getSimOperator = imsi.substring(0,5);
		getSimOperatorName = getOperatoeName();
		getNetworkCountryIso = "cn";
		getNetworkOperator = getSimOperator;
		getNetworkOperatorName = getOperatoeName();
		getNetworkType = getNetworkType();
		getPhoneType = getPhoneType();
		hasIccCard = true;
		getSimState = TelephonyManager.SIM_STATE_READY;
		getDeviceSoftwareVersion = "01";
		isNetworkRoaming = Util.getRandom(2)==1?true:false;
		getCallState = TelephonyManager.CALL_STATE_IDLE;

		return makeTelephonyManagerNode();
	}

	private Element makeTelephonyManagerNode()
	{
		Element node = new Element("TelephonyManager");
		node.setAttr("imei", imei);
		node.setAttr("imsi", imsi);
		node.setAttr("getSimSerialNumber", getSimSerialNumber);
		node.setAttr("getLine1Number", getLine1Number);
		node.setAttr("getSimCountryIso", getSimCountryIso);
		node.setAttr("getSimOperator", getSimOperator);
		node.setAttr("getSimOperatorName", getSimOperatorName);
		node.setAttr("getNetworkCountryIso", getNetworkCountryIso);
		node.setAttr("getNetworkOperator", getNetworkOperator);
		node.setAttr("getNetworkOperatorName", getNetworkOperatorName);
		node.setAttr("getNetworkType", ""+getNetworkType);
		node.setAttr("getPhoneType", ""+getPhoneType);
		node.setAttr("hasIccCard", ""+hasIccCard);
		node.setAttr("getSimState", ""+getSimState);
		node.setAttr("getDeviceSoftwareVersion", ""+getDeviceSoftwareVersion);
		node.setAttr("isNetworkRoaming", ""+isNetworkRoaming);
		node.setAttr("getCallState", ""+getCallState);
		return node;

	}



	public Element makeWifiInfoConf(String mac)
	{
//		08-19 08:14:52.355: INFO/System.out(1913): ming-getMacAddress---:B4:07:F9:D5:9D:9C
//		08-19 08:14:52.355: INFO/System.out(1913): ming-getBSSID---:ec:26:ca:90:d4:8f
//		08-19 08:14:52.359: INFO/System.out(1913): ming-getSSID---:FAST_M
		getMacAddress = getMac().toUpperCase();
		if(!Util.isNull(mac))
		{
			getMacAddress = mac.toUpperCase();
		}
		getBSSID = getMac().toLowerCase();
		getSSID = Util.getRandomStr(MingData.SSIDS);

		return makeWifiInfoNode();
	}

	private Element makeWifiInfoNode()
	{
		Element node = new Element("WifiInfo");
		node.setAttr("getMacAddress", ""+getMacAddress);
		node.setAttr("getBSSID", ""+getBSSID);
		node.setAttr("getSSID", ""+getSSID);

		return node;

	}


	public String getImei(Device device)
	{
		StringBuffer imei = new StringBuffer();
		imei.append(device.tac);
		for(int i=0;i<7;i++)
		{
			imei.append(Util.getRandom(10));
		}
		return imei.toString();

//		StringBuffer imei = new StringBuffer();
//		imei.append("86");
//		imei.append(System.currentTimeMillis());
//		String s = imei.toString();
//		if(s.length()>15)
//		{
//			s = s.substring(0, 15);
//		}
//		return s;
	}



	private static String getMac() {

		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			if (i != 0)
				sb.append(':');
			int v = r.nextInt(256);
			if (i == 0)
				v = v & 0xFC; // unicast, globally unique
			sb.append(Integer.toHexString(0x100 | v).substring(1));
		}
		return sb.toString().toUpperCase();

//		StringBuffer mac = new StringBuffer();
//		Random r = new Random();
//		mac.append(Integer.toHexString(0xFF & Byte.valueOf((byte)r.nextInt(255)).byteValue()));
//		mac.append(":");
//
//		mac.append(Integer.toHexString(0xFF & Byte.valueOf((byte)r.nextInt(255)).byteValue()));
//		mac.append(":");
//
//		mac.append(Integer.toHexString(0xFF & Byte.valueOf((byte)r.nextInt(255)).byteValue()));
//		mac.append(":");
//
//		mac.append(Integer.toHexString(0xFF & Byte.valueOf((byte)r.nextInt(255)).byteValue()));
//		mac.append(":");
//
//		mac.append(Integer.toHexString(0xFF & Byte.valueOf((byte)r.nextInt(255)).byteValue()));
//		mac.append(":");
//
//		mac.append(Integer.toHexString(0xFF & Byte.valueOf((byte)r.nextInt(255)).byteValue()));
//
//		return mac.toString();
	}





    private int getPhoneType() {
    	int type = TelephonyManager.PHONE_TYPE_NONE;

		if(operType==CHINA_MOBILE)
		{
			type = TelephonyManager.PHONE_TYPE_GSM;
		}
		else if(operType==CHINA_UNICOM)
		{
			type = TelephonyManager.PHONE_TYPE_GSM;
		}
		else if(operType==CHINA_TELECOM)
		{
			type = TelephonyManager.PHONE_TYPE_CDMA;
		}

		// TODO Auto-generated method stub
		return type;
	}



	public String getSimOperator()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("8986");

		return sb.toString();
	}


//	08-19 02:59:37.367: INFO/System.out(769): ming-getSimSerialNumber---:89860114818076413946
	public String getSimSerialNumber() {
		StringBuffer sb = new StringBuffer();
		sb.append("8986");
		sb.append(imsi.substring(2,4));

	    for(int i=0;i<14;i++)
	    {
			sb.append(Util.getRandom(10));
	    }

		return sb.toString();
	}


	public String getLineNumber() {

		String[] cm = { "135", "136", "137", "138", "139","150", "151", "152", "158", "159", "134","147", "157", "187", "188" };
		String[] cu = { "130", "131", "132", "156","186", "185"};
		String[] ct = { "133", "134", "180", "153", "189"};
		StringBuffer sb = new StringBuffer();


		if(operType==CHINA_MOBILE)
		{
			sb.append(Util.getRandomStr(cm));
		}
		else if(operType==CHINA_UNICOM)
		{
			sb.append(Util.getRandomStr(cu));
		}
		else if(operType==CHINA_TELECOM)
		{
			sb.append(Util.getRandomStr(ct));
		}

	    for(int i=0;i<4;i++)
	    {
			sb.append(Util.getRandom(10));
	    }

	    for(int i=0;i<4;i++)
	    {
			sb.append(Util.getRandom(10));
	    }


		// TODO Auto-generated method stub
		return sb.toString();
	}



	private String getImsi()
	{
		String[] cm = { "00", "02"};
//		String[] cu = { "01", };
//		String[] ct = { "03", "05"};


		StringBuffer imsi = new StringBuffer();
		imsi.append("460");


		if(operType==CHINA_MOBILE)
		{
			imsi.append(Util.getRandomStr(cm));
		}
		else if(operType==CHINA_UNICOM)
		{
			imsi.append("01");//Util.getRandomStr(cu)
		}
		else if(operType==CHINA_TELECOM)
		{
			imsi.append("03");//Util.getRandomStr(ct)
		}
		for(int i=0;i<10;i++)
		{
			imsi.append(Util.getRandom(10));
		}
		return imsi.toString();
	}


	public int getNetworkType() {
		int[] types  = {1,2,4,7,11,3,5,6,8,9,10,12,14,15,13};//TelephonyManager.NETWORK_TYPE_HSDPA,
		return types[Util.getRandom(types.length)];
//		if(operType==CHINA_MOBILE)
//		{
////			return TelephonyManager.NETWORK_TYPE_GPRS;
//		}
//		else if(operType==CHINA_UNICOM)
//		{
//			int[] types  = {TelephonyManager.NETWORK_TYPE_UMTS,TelephonyManager.NETWORK_TYPE_EDGE};//TelephonyManager.NETWORK_TYPE_HSDPA,
//			return types[Util.getRandom(types.length)];
//		}
//		else if(operType==CHINA_TELECOM)
//		{
//			int[] types  = {TelephonyManager.NETWORK_TYPE_CDMA,TelephonyManager.NETWORK_TYPE_EVDO_0,TelephonyManager.NETWORK_TYPE_EVDO_A,TelephonyManager.NETWORK_TYPE_1xRTT};
//			return types[Util.getRandom(types.length)];
//		}
//		else
//		{
//			return TelephonyManager.NETWORK_TYPE_UNKNOWN;
//		}
	}


	public String getOperatoeName() {
		if(operType==CHINA_MOBILE)
		{
			return "CHINA MOBILE";
		}
		else if(operType==CHINA_UNICOM)
		{
			return "CHN-UNICOM";
		}
		else if(operType==CHINA_TELECOM)
		{
			return "CHN-TELECOM";
		}
		else
		{
			return "unknow";
		}
	}

	public String makeConf() {

		device = maker.getDevice(); //getDeviceBySize(screen_size);//
		int which = Util.getRandomRate(new float[]{0.5f,0.3f,0.2f});
		operType = MingConf.CHINA_MOBILE;
		if(which==50)
		{
			operType = MingConf.CHINA_MOBILE;
		}
		else if(which==30)
		{
			operType = MingConf.CHINA_UNICOM;
		}
		else if(which==20)
		{
			operType = MingConf.CHINA_TELECOM;
		}

		Element buildNode = this.makeBuildConf();
		Element tmNode = this.makeTelephonyManagerConf(device,null,null);
		Element wiNode = this.makeWifiInfoConf(null);
		StringBuffer sb = new StringBuffer();
		sb.append(buildNode.getXml()+"\n");
		sb.append(tmNode.getXml()+"\n");
		sb.append(wiNode.getXml()+"\n");
		confProp = sb.toString();
		makeTmpDataFile();
		return confProp;
	}



	public String confProp;
	public String makeConf(String imei_, String imsi_, String mac, String manufacturer, String ua) {

		device = maker.getDeviceByName(manufacturer);
		device.modelName = ua;
		int which = Util.getRandomRate(new float[]{0.5f,0.3f,0.2f});
		operType = MingConf.CHINA_MOBILE;
		if(which==50)
		{
			operType = MingConf.CHINA_MOBILE;
		}
		else if(which==30)
		{
			operType = MingConf.CHINA_UNICOM;
		}
		else if(which==20)
		{
			operType = MingConf.CHINA_TELECOM;
		}
		Element buildNode = this.makeBuildConf();
		Element tmNode = this.makeTelephonyManagerConf(device,imei_,imsi_);
		Element wiNode = this.makeWifiInfoConf(mac);
		StringBuffer sb = new StringBuffer();
		sb.append(buildNode.getXml()+"\n");
		sb.append(tmNode.getXml()+"\n");
		sb.append(wiNode.getXml()+"\n");
		confProp = sb.toString();
		makeTmpDataFile();
		return confProp;
	}


	public String makeConf(String manufacturer) {

		device = maker.getDeviceByName(manufacturer);
		int which = Util.getRandomRate(new float[]{0.5f,0.3f,0.2f});
		operType = MingConf.CHINA_MOBILE;
		if(which==50)
		{
			operType = MingConf.CHINA_MOBILE;
		}
		else if(which==30)
		{
			operType = MingConf.CHINA_UNICOM;
		}
		else if(which==20)
		{
			operType = MingConf.CHINA_TELECOM;
		}
		Element buildNode = this.makeBuildConf();
		Element tmNode = this.makeTelephonyManagerConf(device,null,null);
		Element wiNode = this.makeWifiInfoConf(null);
		StringBuffer sb = new StringBuffer();
		sb.append(buildNode.getXml()+"\n");
		sb.append(tmNode.getXml()+"\n");
		sb.append(wiNode.getXml()+"\n");
		confProp = sb.toString();
		makeTmpDataFile();
		return confProp;
	}




	public String getConf() {
		Element buildNode = this.makeBuildNode();
		Element tmNode = this.makeTelephonyManagerNode();
		Element wiNode = this.makeWifiInfoNode();
		StringBuffer sb = new StringBuffer();
		sb.append(buildNode.getXml()+"\n");
		sb.append(tmNode.getXml()+"\n");
		sb.append(wiNode.getXml()+"\n");
		makeTmpDataFile();
		return sb.toString();
	}

	public void setConf(String conf) {
		if(!Util.isNull(conf))
		{
			parse(Element.parse(conf));
			makeTmpDataFile();
		}
	}

	private void makeTmpDataFile()
	{
		String cn = "Processor   : "+getCpuName+"\n";
		AUtil.writeAppFileData(context,"cpuinfo", cn.getBytes());

		String scaling_cur_freq = getCpuCurFreq+"\n";
		AUtil.writeAppFileData(context,"scaling_cur_freq", scaling_cur_freq.getBytes());

		String cpuinfo_max_freq = getCpuMaxFreq+"\n";
		AUtil.writeAppFileData(context,"cpuinfo_max_freq", cpuinfo_max_freq.getBytes());

		String cpuinfo_min_freq = getCpuMinFreq+"\n";
		AUtil.writeAppFileData(context,"cpuinfo_min_freq", cpuinfo_min_freq.getBytes());

		String meminfo = "MemTotal:         "+getTotalMemInfo+" kB\n";
		AUtil.writeAppFileData(context,"meminfo", meminfo.getBytes());
	}


	private static String getAndroidID() {
		Random r = new Random();
		long v = r.nextLong();
		return Long.toHexString(v);
	}
//01-01 13:28:25.590: INFO/System.out(1011): minglog-ming------------http-----------:http://gw.m.360buy.com/client.action?functionId=uploadMsg&uuid=869899002377903-842ED5B52C31&clientVersion=4.3.1&build=20440&client=android&d_brand=Hisense&d_model=E86&osVersion=3.0&screen=480*320&partner=bjxg003&networkType=2g&sign=B3D4TNRQn5fQkS6NrbKjHg&sv=1&st=1357018072083

	private static String getCupName() {
		StringBuffer androidid = new StringBuffer();
		androidid.append("ARMv7 Processor ");
		androidid.append("rev "+(1+Util.getRandom(9))+" ");
		androidid.append("(v7"+(1+Util.getRandom(9))+")");

		String s = androidid.toString();
		return s;
	}

	private static String getNetAddressInfo() {
		StringBuffer androidid = new StringBuffer();
		androidid.append("192.168.");
		androidid.append(""+Util.getRandom(6)+".");
		androidid.append(""+Util.getRandom(255));
		String s = androidid.toString();
		return s;
	}


	private void parse(Element node)
	{
		String tag = node.getTag();
		if(tag.equals("Build"))
		{
			ID = node.getAttr("ID");
			BOARD = node.getAttr("BOARD");
			BOOTLOADER = node.getAttr("BOOTLOADER");
			BRAND = node.getAttr("BRAND");
			CPU_ABI = node.getAttr("CPU_ABI");
			CPU_ABI2 = node.getAttr("CPU_ABI2");
			DEVICE = node.getAttr("DEVICE");
			DISPLAY = node.getAttr("DISPLAY");
			FINGERPRINT = node.getAttr("FINGERPRINT");
			HARDWARE = node.getAttr("HARDWARE");
			HOST = node.getAttr("HOST");
			MANUFACTURER = node.getAttr("MANUFACTURER");
			MODEL = node.getAttr("MODEL");
			MANUFACTURER = node.getAttr("MANUFACTURER");
			PRODUCT = node.getAttr("PRODUCT");
			RADIO = node.getAttr("RADIO");
			TAGS = node.getAttr("TAGS");
			TIME = Util.getLong(node.getAttr("TIME"));
			TYPE = node.getAttr("TYPE");
			USER = node.getAttr("USER");
			VERSION_CODENAME = node.getAttr("VERSION_CODENAME");
			VERSION_INCREMENTAL = node.getAttr("VERSION_INCREMENTAL");
			VERSION_RELEASE = node.getAttr("VERSION_RELEASE");
			VERSION_SDK = node.getAttr("VERSION_SDK");
			VERSION_SDK_INT = Util.getInt(node.getAttr("VERSION_SDK_INT"));

			android_id = node.getAttr("android_id");
			if(Util.isNull(android_id))
			{
				android_id = getAndroidID();
			}

			hasFrontFacingCamera = node.getAttr("hasFrontFacingCamera");
			hasBackFacingCamera = node.getAttr("hasBackFacingCamera");
			hasSDCard = node.getAttr("hasSDCard");
			getSdCardId = node.getAttr("getSdCardId");
			getAvailableInternalMemorySize = node.getAttr("getAvailableInternalMemorySize");
			getTotalMemInfo = node.getAttr("getTotalMemInfo");
			getBluetoothMac = node.getAttr("getBluetoothMac");
			getCpuCurFreq = node.getAttr("getCpuCurFreq");
			getCpuAddress = node.getAttr("getCpuAddress");
			getCpuMaxFreq = node.getAttr("getCpuMaxFreq");
			getCpuMinFreq = node.getAttr("getCpuMinFreq");
			getCpuName = node.getAttr("getCpuName");
			getNetAddressInfo = node.getAttr("getNetAddressInfo");
			hasBackFacingCameraFalsh = node.getAttr("hasBackFacingCameraFalsh");
			getCpuNumCores = node.getAttr("getCpuNumCores");
			if(Util.isNull(hasFrontFacingCamera))
			{
				hasFrontFacingCamera = Util.getRandom(6)%2==0?"true":"false";
				hasBackFacingCamera = "true";
				hasSDCard = Util.getRandom(6)%2==0?"true":"false";
				getSdCardId = "unknow";
				if(Util.getBoolean(hasSDCard))
				{
					getSdCardId = "000"+Util.getRandom(3)+"";
				}
				int totalSize = (120000+Util.getRandom(60000));
				getTotalInternalMemorySize = String.valueOf(totalSize);//getFileSizeStr2(1024*1024*(800+Util.getRandom(800)));

				int availableSize = totalSize*(3+Util.getRandom(6))/10;
				getAvailableInternalMemorySize = String.valueOf(availableSize);

				int totalMem = 266230+Util.getRandom(266230);
				getTotalMemInfo = String.valueOf(totalMem);
				
				//10-18 23:46:26.190: INFO/System.out(1604): minglog-l----4096a-----21662-----b-----47360

				
				getBluetoothMac = getMac();
				getCpuAddress = getAndroidID();
				
				
				int basicFreq = 800000;
				int maxFreq = basicFreq+100000*Util.getRandom(8);
				int minFreq = maxFreq-600000;
				getCpuMaxFreq = String.valueOf(maxFreq);
				getCpuMinFreq = String.valueOf(minFreq);
				getCpuCurFreq = getCpuMaxFreq;
			}
			
			getWidth = node.getAttr("getWidth");
			getHeight = node.getAttr("getHeight");

			
			
			
//			if(Build.VERSION.SDK_INT==10)
//			{
//				VERSION_SDK = "10";//node.getAttr("VERSION_SDK");
//				VERSION_SDK_INT = 10;//Util.getInt(node.getAttr("VERSION_SDK_INT"));		
//			}
//			else if(Build.VERSION.SDK_INT==19)
//			{
//				VERSION_SDK = "19";
//				VERSION_SDK_INT = 19;
//			}
//			else
//			{
//		        if(VERSION_SDK_INT<=16)
//		        {
//					VERSION_SDK = "16";//node.getAttr("VERSION_SDK");
//					VERSION_SDK_INT = 16;//Util.getInt(node.getAttr("VERSION_SDK_INT"));		
//		        }
//			}
//			
			
		}
		else if(tag.equals("TelephonyManager"))
		{
			imei = node.getAttr("imei");
			imsi = node.getAttr("imsi");
			getSimSerialNumber = node.getAttr("getSimSerialNumber");
			getLine1Number = node.getAttr("getLine1Number");
			getSimCountryIso = node.getAttr("getSimCountryIso");
			getSimOperator = node.getAttr("getSimOperator");
			getSimOperatorName = node.getAttr("getSimOperatorName");
			getNetworkCountryIso = node.getAttr("getNetworkCountryIso");
			getNetworkOperator = node.getAttr("getNetworkOperator");
			getNetworkOperatorName = node.getAttr("getNetworkOperatorName");
			getNetworkType = Util.getInt(node.getAttr("getNetworkType"));
			getPhoneType = Util.getInt(node.getAttr("getPhoneType"));
			hasIccCard = Util.getBoolean(node.getAttr("hasIccCard"));
			getSimState = Util.getInt(node.getAttr("getSimState"));
			getDeviceSoftwareVersion = node.getAttr("getDeviceSoftwareVersion");
			isNetworkRoaming = Util.getBoolean(node.getAttr("isNetworkRoaming"));
			getCallState = Util.getInt(node.getAttr("getCallState"));
		}
		else if(tag.equals("WifiInfo"))
		{
			getMacAddress = node.getAttr("getMacAddress");
			getBSSID = node.getAttr("getBSSID");
			getSSID = node.getAttr("getSSID");
		}
		
		Vector<Element> nodes = node.getChildren();
		for(Element el:nodes)
		{
			parse(el);
		}
	}	
	
	private static String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

//    private static  String getFileSizeStr2(long fileSize)
//	{
//		String sFileSize = "";
//		if(fileSize>0)
//		{
//			double dFileSize = (double)fileSize;
//			
//			double kiloByte = dFileSize/1024;
//			if(kiloByte < 1)
//			{
//				return sFileSize + "B";
//			}
//			double megaByte = kiloByte/1024;
//			if(megaByte < 1)
//			{
//				sFileSize = String.format("%.0f",kiloByte);
//				return sFileSize + "K";
//			}
//			
//			double gigaByte = megaByte/1024;
//			if(gigaByte<1)
//			{
//				sFileSize = String.format("%.0f",megaByte);
//				return sFileSize + "M";
//			}
//			
//			double teraByte = gigaByte/1024;
//			if(teraByte<1)
//			{
//				sFileSize = String.format("%.1f",gigaByte);
//				return sFileSize + "G";
//			}
//			
//			sFileSize = String.format("%.0f",teraByte);
//			return sFileSize + "T";
//		}
//		return sFileSize;
//	}	
	
//    private static  String getFileSizeStr(long fileSize)
//	{
//		String sFileSize = "";
//		if(fileSize>0)
//		{
//			double dFileSize = (double)fileSize;
//			
//			double kiloByte = dFileSize/1024;
//			if(kiloByte < 1)
//			{
//				return sFileSize + "B";
//			}
//			double megaByte = kiloByte/1024;
//			if(megaByte < 1)
//			{
//				sFileSize = String.format("%.0f",kiloByte);
//				return sFileSize + "K";
//			}
//			
//			double gigaByte = megaByte/1024;
//			if(gigaByte<1)
//			{
//				sFileSize = String.format("%.0f",megaByte);
//				return sFileSize + "MB";
//			}
//			
//			double teraByte = gigaByte/1024;
//			if(teraByte<1)
//			{
//				sFileSize = String.format("%.1f",gigaByte);
//				return sFileSize + "G";
//			}
//			
//			sFileSize = String.format("%.0f",teraByte);
//			return sFileSize + "T";
//		}
//		return sFileSize;
//	} 	
//    
//    private static  String getHGZStr(long fileSize)
//	{
//		String sFileSize = "";
//		if(fileSize>0)
//		{
//			double dFileSize = (double)fileSize;
//			
//			double kiloByte = dFileSize/1024;
//			if(kiloByte < 1)
//			{
//				return sFileSize + "B";
//			}
//			double megaByte = kiloByte/1024;
//			if(megaByte < 1)
//			{
//				sFileSize = String.format("%.0f",kiloByte);
//				return sFileSize + "K";
//			}
//			
//			double gigaByte = megaByte/1024;
//			if(gigaByte<1)
//			{
//				sFileSize = String.format("%.0f",megaByte);
//				return sFileSize + "M";
//			}
//			
//			double teraByte = gigaByte/1024;
//			if(teraByte<1)
//			{
//				sFileSize = String.format("%.1f",gigaByte);
//				return sFileSize + "GHz";
//			}
//			
//			sFileSize = String.format("%.0f",teraByte);
//			return sFileSize + "T";
//		}
//		return sFileSize;
//	}     	
	
	

}
