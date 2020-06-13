package com.ming.xposed.xishuashua.bean;

import com.ming.util.common.Element;
import com.ming.util.common.Util;

import java.io.Serializable;
import java.util.Vector;

public class App implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;
	public String name;
	public String channel;
	public String pack;
	public String cls;
	public String imsi;
	public String type;
	public long delayTime;
	public Vector<String> cachePaths;
	public String devices;
	public String shua_type;
	public int index;
	public String partner;
	public String version;
	
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public App()
	{
		cachePaths = new Vector<String>();
	}
	
	public static App parse(Element node)
	{
		App app = new App();
		
		app.id = node.getAttr("id");
		app.name = node.getAttr("name");
		app.channel = node.getAttr("channel");
		app.pack = node.getAttr("pack");
		app.cls = node.getAttr("cls");
		app.imsi = node.getAttr("imsi");
		app.type = node.getAttr("type");
		app.shua_type = node.getAttr("shua_type");
		app.partner = node.getAttr("partner");
		app.version = node.getAttr("version");
				
		app.delayTime = Util.getLong(node.getAttr("delay"));
		
		Vector<Element> nodes = node.getChildren();
		for(Element el:nodes)
		{
			String tag = el.getTag();
			if(tag.equals("cache"))
			{
				app.cachePaths.addElement(el.getAttr("path"));
			}
			else if(tag.equals("device"))
			{
				app.devices = el.getAttr("devices");
			}
			
		}
		
		return app;
	}

}
