package com.ming.xposed.xishuashua.mod.logic;

import android.graphics.Rect;
import android.view.View;

public class XY {
	
	public float x;
	public float y;
	
	public static XY getXY(View view)
	{
		Rect rect = new Rect();
		view.getGlobalVisibleRect(rect);
		XY xy = new XY();
		xy.x = rect.left;
		xy.y = rect.top;
		
		return xy;
	}
	

}
