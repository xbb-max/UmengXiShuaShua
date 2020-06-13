package com.ming.xposed.xishuashua.conf;

import android.content.Context;

import com.ming.util.android.AUtil;
import com.ming.util.common.HGB2PINYIN;
import com.ming.util.common.Json;
import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.bean.Brand;
import com.ming.xposed.xishuashua.bean.Device;

import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MingDeviceMaker {

//    private static String[] manufacturers_ch = {"三星","HTC","小米","LG","索尼","摩托罗拉","华为","联想","谷歌","魅族","INQ","MOPS","OPPO","TCL","Thl","VOYO","deovo","koobee","阿尔卡特","爱国者","艾诺","昂达","奥可视","奥克斯","北斗星","波导","步步高","创维","大显","戴尔","东信","东芝","多普达","朵唯","泛泰","飞利浦","港利通","海尔","海信","豪特","弘谷电","宏碁","华录","华硕","惠普","技嘉","佳域","桔子","金立","京瓷","康佳","酷派","蓝魔","美拓","明泰","摩奇","纽曼","努比亚","欧谷","欧盛","欧新","普耐尔","七喜","琦基","齐乐","青橙","锐合","萨米","三美琦","盛大","首派","索尼爱立信","台电","泰克飞石","天语","五元素","锡恩帝","夏普","夏新","先锋","亚马逊","亿通","优派","原道","长虹","中德瑞","中恒","中兴","卓普",};
//    String[] manufacturers_ch = {"三星","HTC","小米","LG","索尼","摩托罗拉","华为","联想","谷歌","魅族","360","Acer","INQ","MOPS","OPPO","OnePlus","Smartisan","TCL","Thl","VOYO","deovo","koobee","vivo","阿尔卡特","爱国者","艾诺","昂达","奥可视","奥克斯","北斗星","波导","步步高","创维","大显","戴尔","东信","东芝","多普达","朵唯","泛泰","飞利浦","富可视","港利通","海尔","海信","豪特","弘谷电","宏碁","华录","华硕","惠普","技嘉","佳域","桔子","金立","京瓷","康佳","酷比","酷派","蓝魔","乐视","美图","美拓","明泰","摩奇","纽曼","努比亚","欧谷","欧盛","欧新","普耐尔","七喜","琦基","奇酷","齐乐","青橙","锐合","萨米","三美琦","神舟","盛大","首派","索尼爱立信","台电","泰克飞石","天语","五元素","锡恩帝","夏普","夏新","先锋","亚马逊","亿通","优派","原道","长虹","中德瑞","中国移动","中恒","中兴","卓普"};
//  private static String[] manufacturers_en = {"Samsung","HTC","Xiaomi","LG","Sony","Motorola","Huawei","Lenovo","Google","Meizu","INQ","MOPS","OPPO","TCL","Thl","VOYO","deovo","Koobee","Alcatel","Aigo","ainol","Onda","AOCOS","AUX","Beidou","Bird","Bbk","Skyworth","Daxian","Dell","Eastcom","Toshiba","Dopod","Doov","Pantech","Philips","Kong-profit","Haier","Hisense","HOT","iHKC","Acer","hualu","Asus","HP","Gsmart","JY","iorgane","Gionee","Kyocera","Konka","Coolpad","RAMOS","MeituKiss","Minte","MUCH","Newsmy","nubia","OUGU","iocean","Hosin","Ployer","Hedy","Qigi","Cheer","green orange","Rayhov","saami","miki","Bambook","apanda","Sonyericsson","teclast","TecFace","Tianyu","ifive","CENDE","Sharp","Amoi","Pioneer","Amazon","Eycom","ViewSonic","Window","Changhong","zdreal","Dec","ZTE","ZOPO",};
  private static String[] manufacturers_en =  {"Samsung","HTC","Xiaomi","LG","Sony","Motorola","Huawei","Lenovo","Google","Meizu","360","Acer","INQ","MOPS","OPPO","OnePlus","Smartisan","TCL","Thl","VOYO","deovo","koobee","vivo","Alcatel","Aigo","ainol","Onda","AOCOS","AUX","Beidou","Bird","Bbk","Skyworth","Daxian","Dell","Eastcom","Toshiba","Dopod","Doov","Pantech","Philips","Kong-profit","KPT","Haier","Hisense","HOT","iHKC","Acer","hualu","Asus","HP","Gsmart","JY","iorgane","Gionee","Kyocera","Konka","Exynos","Coolpad","RAMOS","letv","MeituKiss","Minte","MeTime","MOQI","Newsmy","nubia","OUGU","iocean","Hosin","Ployer","Hedy","Qigi","7Cool","Cheer","GreenOrange","Rayhov","saami","miki","Hasee","Bambook","apanda","Sonyericsson","teclast","TecFace","Tianyu","ifive","CENDE","Sharp","Amoi","Pioneer","Amazon","Eycom","ViewSonic","Window","Changhong","zdreal","ChinaMobile","DEC","ZTE","ZOPO"};


	Vector<Brand> brands;
	Hashtable<String,Brand> brandHash;
    
	Context context;
	public MingDeviceMaker(Context context)
	{
		this.context = context;
		brandHash = new Hashtable<String,Brand>();
		brands = new Vector<Brand>();
		for(int i=0;i<manufacturers_en.length;i++)
		{
			Brand brand = new Brand();
			brand.id = i+"";
//			brand.manufacturer_ch = manufacturers_ch[i];
			brand.manufacturer_en = manufacturers_en[i];
			
			brandHash.put(brand.manufacturer_en, brand);
		}
	}
	
	public String getTac(String name)
	{
		String tac = "";
		try
		{
//			byte[] data = AUtil.getAssetsData(context, "conf/tac/"+name.toLowerCase()+"_tac.ini");
//			if(data!=null)
//			{
//				String txt = new String(data);
//				String[] tacs = txt.split("\n");
//				if(tacs.length>0)
//				{
//					String s = Util.getRandomStr(tacs);
//					s = Util.getTagString(s,'<', '>');
//					String[] ss = Util.getDevilString(s, '-');
//					if(ss.length>=2)
//					{
//						tac = ss[0];
//					}
//				}
//			}

			if(tac.length()!=8)
			{
				StringBuffer imei = new StringBuffer();
				imei.append("86");
				for(int i=0;i<6;i++)
				{
					imei.append(Util.getRandom(10));
				}
				tac = imei.toString();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tac;
		
	}
    
    
	public Device getDevice()
	{
		Device device = null;
//		三星 30%华为  22%小米 13% 中兴 10% 联想5%（索爱，天语，摩托，金立，酷派，海信，朵唯，OPPO，魅族）12% 其他品牌 8 %
		String name = "Samsung";
		

//		boolean isChecking = true;
//	    while(isChecking)
	    {
//	    	if(device.cpuDetail==null)
	    	{
				int which = Util.getRandomRate(new float[]{0.5f,0.35f,0.15f});
				if(which==50)
				{
					String[] names = {"Samsung","Huawei","Xiaomi","ZET","Lenovo"};
					name = Util.getRandomStr(names);
				}
				else if(which==35)
				{
					String[] names = {"HTC","Motorola","Meizu","Coolpad","Bbk","Hisense","OPPO","TCL","LG"};
					name = Util.getRandomStr(names);
				}
				else// if(which==15)
				{
					name = Util.getRandomStr(manufacturers_en);
				}

				device = getDeviceByName(name);
			}
//	    	else
//			{
//				isChecking = false;
//			}
		}

		if(device==null)
		{
			device = getDeviceByName("Huawei");
		}




	    return device;
	}    
	
	public Device getDeviceBySize(int size)
	{
		Device device = null;
//		三星 30%华为  22%小米 13% 中兴 10% 联想5%（索爱，天语，摩托，金立，酷派，海信，朵唯，OPPO，魅族）12% 其他品牌 8 %
		
		while(device==null)
		{
			String name = "Samsung";
			int which = Util.getRandomRate(new float[]{0.5f,0.35f,0.15f});
			if(which==50)
			{
		    	String[] names = {"Samsung","Huawei","Xiaomi","ZET","Lenovo"};
		    	name = Util.getRandomStr(names);
			}
		    else if(which==35)
		    {
		    	String[] names = {"HTC","Motorola","Meizu","Coolpad","Bbk","Hisense","OPPO","TCL","LG"};
		    	name = Util.getRandomStr(names);
		    }
		    else// if(which==15)
		    {
		    	name = Util.getRandomStr(manufacturers_en);
		    }

		    device = getDeviceBySize(name,size);
		    if(device!=null)
		    {
		    	break;
		    }
		}
		
	    return device;
	} 	
	
	public Device getDeviceBySize(String name, int size)
	{
		Device device = null;
	    Brand brand = brandHash.get(name);
	    try
	    {
		    if(brand!=null)
		    {
                Vector<Device> devices = new Vector<Device>();
//				byte[] data = Util.readFileData("/sdcard/mingconf/devices/"+brand.id+".json");
				byte[] data = AUtil.getAssetsData(context, "conf/devices/"+brand.id+".json");
				if(data!=null)
				{
	                String str = "{\"devices\":"+new String(data,"utf-8")+"}";
	                Json json = Json.parse(str);
	                Json[] jsons = json.getJsons("devices");
	                for(Json js:jsons)
	                {
	                    Device mydevice = parseDeviceJson(js);
	                    int width = Util.getInt(mydevice.width);
	                    int height = Util.getInt(mydevice.height);
	                    if((height*width)==size)
	                    {
		                    mydevice.brand_en = brand.manufacturer_en;
		                    mydevice.tac = getTac(name.toLowerCase());
		                    devices.addElement(mydevice);
	                    	
	                    }
	                }				
				}
				
				if(devices.size()>0)
				{
					device = devices.elementAt(Util.getRandom(devices.size()));
				}
		    }	    	
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }		
	    return device;
	}	
	
	
    
	public Device getDeviceByName(String name)
	{
		Device device = null;
	    Brand brand = brandHash.get(name);
	    try
	    {
		    if(brand!=null)
		    {
                Vector<Device> devices = new Vector<Device>();
//				byte[] data = Util.readFileData("/sdcard/mingconf/devices/"+brand.id+".json");
				byte[] data = AUtil.getAssetsData(context, "conf/devices/"+brand.id+".json");
				if(data!=null)
				{
	                String str = "{\"devices\":"+new String(data,"utf-8")+"}";
	                Json json = Json.parse(str);
	                Json[] jsons = json.getJsons("devices");
	                for(Json js:jsons)
	                {
	                    Device mydevice = parseDeviceJson(js);
	                    mydevice.brand_en = brand.manufacturer_en;
	                    mydevice.tac = getTac(name.toLowerCase());
	                    devices.addElement(mydevice);
	                }				
				}
				
				if(devices.size()>0)
				{
					device = devices.elementAt(Util.getRandom(devices.size()));
				}
				
				Util.debug("new ----- w"+device.width);
				Util.debug("new ----- h"+device.height);
		    }	    	
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }		
	    return device;
	}	
	
    private Device parseDeviceJson(Json json)
    {
        Device device = new Device();
        device.id = json.getAttr("id");
        device.brand = json.getAttr("brand");
        device.modelName = json.getAttr("modelName");
    	device.modelName = new HGB2PINYIN().getAllPY(device.modelName);
//        device.modelName = countChar(device.modelName);
//        if(Util.isNull(device.modelName))
//        {
//        }
        device.alias = json.getAttr("alias");
        device.resolutionRatio = json.getAttr("resolutionRatio");
        if(device.resolutionRatio.indexOf("x")!=-1)
        {
        	String[] ss = device.resolutionRatio.split("x");
        	if(ss.length>=2)
        	{
        		device.width = ss[0];
        		device.height = ss[1];
        	}
        }
                
        device.api = json.getAttr("api");
        int api = Util.getInt(device.api);
//		if(Build.VERSION.SDK_INT==10)
//		{
//        	device.api = "10";
//		}
//		else if(Build.VERSION.SDK_INT==19)
//		{
//        	device.api = "19";
//		}
//		else
//		{
//	        if(api<=16)
//	        {
//	        	device.api = "16";
//	        }
//		}
//        
        
        
        device.cpu = json.getAttr("cpu");
        device.cpuDetail = json.getAttr("cpuDetail");
        device.cpuDetail = countChar(device.cpuDetail);
        device.modelSerial = json.getAttr("modelSerial");
//        device.modelSerial = countChar(device.modelSerial);
        device.status = json.getAttr("status");
        
        return device;
    }	
    
    private String countChar(String itemlife)
    {
	    String regEx = "[\\u4e00-\\u9fa5]";

	    Pattern p = Pattern.compile(regEx);
	    Matcher m = p.matcher(itemlife);
	
	    return m.replaceAll("");
    }    
    
	
    
    
    
   
    
    

//	if(attrs.length>=4)
//	{
//		Device device = new Device();
//		device.manufacturer = attrs[0];
//		device.imei = attrs[1];
//		device.imsi = attrs[2];
//		try
//		{
//			device.ua = java.net.URLDecoder.decode(attrs[3],"utf-8");
//		}
//		catch(Exception ee)
//		{
//			device.ua = attrs[3];
//		}
//		device.mac = attrs[4];
//		device.sdk = getLetter(sdks);
//		if(rand==null)
//		{
//			rand = new Random();
//		}
//		
//		device.api = Util.getRandomStr(sdks);
//		//Util.getRandomStr(sdks);//
//		device.sdk = "8";//device.api;
//		device.version = getVersion(device.api);
//		device.version_name = getVersionName(device.api);
//		device.netType = getNetType(); 
//		device.oper = getOper();
//		device.id = device.version_name.toUpperCase() + Util.replaceAll(device.version, ".", "", true);
//		device.android_id = getAndroidID();
//		device.ssid = getSSID();
//		device.bssid = getBSSID();
//		device.cup_info = getCupInfo();
//		device.user_agent = "Dalvik/1."+Util.getRandomStr(new String[]{"2","4","6"})+".0 (Linux; U; Android "+device.version+"; "+device.ua+"  Build/"+device.id+")";
//		
////		
////		device.version = getLetter(versions) + "." + (1 + Math.abs(rand.nextInt() % 9));
////        device.version_name = getLetter(version_Names);
////        device.id = device.version_name.toUpperCase() + Util.replaceAll(device.version, ".", "", true);
//        devices.addElement(device);
//	}    
    
    
    
    
    
    
    
    
    
    
    
}
