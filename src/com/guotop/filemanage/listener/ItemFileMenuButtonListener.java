package com.guotop.filemanage.listener;


import com.guotop.filemanage.bean.FileBean;
import com.guotop.filemanage.bean.ItemFileMenuButtonBean;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 *
 *
 *@author: ¿Ó—Ó
 *@time: 2013-8-12œ¬ŒÁ5:50:01
 */
public class ItemFileMenuButtonListener implements BaseItemListener{

	
	private ItemFileMenuButtonBean params;
	
	public ItemFileMenuButtonListener(ItemFileMenuButtonBean params) {
		this.params=params;
	}

	public ItemFileMenuButtonListener() {
	}
	

	public boolean onClick(View v,ItemFileMenuButtonBean params,boolean isChoose) {
		return true;
	}
	
	public void onClick(View v) {
		this.onClick(v,params);
	}

	public boolean onClick(View v,ItemFileMenuButtonBean params) {
	
		return true;
	}
	
	private View tempView;
	
	public boolean onTouch(View v, MotionEvent event) {
		return true;
	}
	

	public ItemFileMenuButtonBean getParams() {
		return params;
	}

	public void setParams(ItemFileMenuButtonBean params) {
		this.params = params;
	}

	@Override
	public void onClickChangeItemBackgroud(View v,boolean flage) {
		
	}

	
}
