package com.guotop.filemanage.listener;


import android.R.id;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 *
 *
 *@author: 李杨
 *@time: 2013-8-13下午12:29:39
 */
public class SquareItemListener  implements BaseItemListener{

	private View tempView;
	
	private static SquareItemListener listener;
	
	public static SquareItemListener getInstance() {
		if (listener == null) {
			listener = new SquareItemListener();
		}
		return listener;
	}
	
	
	public boolean onTouch(View v, MotionEvent event) {
		if(tempView!=null&&tempView!=v){
			this.onClickChangeItemBackgroud(tempView, false);
		}
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			this.onClickChangeItemBackgroud(v, true);
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			this.onClickChangeItemBackgroud(v, false);
		} else if(event.getAction() == MotionEvent.ACTION_MOVE){
			this.onClickChangeItemBackgroud(v, false);
		}
		tempView=v;
		return false;
	}
	//重新
	public void onClick(View v) {
		
	}


	public boolean onRClick(View v) {
		return false;
	}

	public void onClickChangeItemBackgroud(View v,boolean flage) {
		if(flage){
			v.setBackgroundColor(Color.parseColor("#4184e9"));
		}else {
			v.setBackgroundColor(Color.alpha(0));
		}
	}

}
