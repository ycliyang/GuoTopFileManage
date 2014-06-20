package com.guotop.filemanage.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.guotop.filemanage.R;
import com.guotop.filemanage.item.BaseItem;
import com.guotop.filemanage.listener.ItemFileMenuButtonListener;

/**
 *
 *格式化View 例如点击事件
 *
 *@author: 李杨
 *@time: 2013-8-12下午5:46:13
 */
public class ItemFileMenuButtonBean {
	
	private BaseItem item;
	
	private View view;

	private FileBean params;
	
	private ItemFileMenuButtonListener listener;
	
	private boolean isChoose=false;
	

	public ItemFileMenuButtonBean(BaseItem item,FileBean params){
		this.view=item.getView();
		this.params=params;
	}
	public ItemFileMenuButtonBean(BaseItem item,FileBean params,ItemFileMenuButtonListener listener){
		this.item=item;
		this.view=item.getView();
		this.params=params;
		this.listener=listener;
		init();
	}
	
	private void init(){
		
		/**
		 * 重写Item里面的View事件
		 * 原生点击事件被替换掉
		 */
		this.view.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isChoose=isChoose?false:true;
				listener.onClick(v, ItemFileMenuButtonBean.this,isChoose);
			}
		});

		/**
		 * 重写Item里面的View事件
		 * 有条件拦截触摸事件
		 */
		this.view.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(listener.onTouch(v, event)){
					item.getListener().onTouch(v, event);
				}
				return false;
			}
		});
	}
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public FileBean getParams() {
		return params;
	}

	public void setParams(FileBean params) {
		this.params = params;
	}

	public ItemFileMenuButtonListener getListener() {
		return listener;
	}

	public void setListener(ItemFileMenuButtonListener listener) {
		this.listener = listener;
	}
	public BaseItem getItem() {
		return item;
	}
	public void setItem(BaseItem item) {
		this.item = item;
	}
	
}