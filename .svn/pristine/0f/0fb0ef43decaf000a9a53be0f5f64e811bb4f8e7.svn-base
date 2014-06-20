package com.guotop.filemanage.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.guotop.filemanage.R;
import com.guotop.filemanage.listener.BaseItemListener;
import com.guotop.filemanage.listener.ItemFileMenuButtonListener;
import com.guotop.filemanage.listener.SquareItemListener;

/**
 *最小的View 
 *
 *@author: 李杨
 *@time: 2013-8-12下午4:15:53
 */
public class SquareItem extends BaseItem{

	
	private ImageView itemMenuImage;
	
	private TextView itemMenuText;
	
	private View view;

	private SquareItemListener listener;
	
	
	private Context context;
	

	
	public SquareItem(Context context,int menuImage,String menuText) {
		listener=SquareItemListener.getInstance();
		init(context,menuImage,menuText);
	}
	
	private void init(Context context,int menuImage,String menuText) {
		
		
		this.context=context;
		this.view = LayoutInflater.from(context).inflate(R.layout.filemanage_item_square_file, null);
		this.itemMenuImage=(ImageView)view.findViewById(R.id.itemMenuImage);
		this.itemMenuText=(TextView)view.findViewById(R.id.imteMenuText);
		
		//设置图片
		this.itemMenuImage.setBackgroundResource(menuImage);
		//设置文字
		this.itemMenuText.setText(menuText);
		
		
		this.view.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				listener.onClick(v);
			}
		});
		
		this.view.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				return listener.onTouch(v, event);
			}
		});
		
	}


	public SquareItemListener getListener() {
		return listener;
	}

	public void setListener(SquareItemListener listener) {
		this.listener = listener;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public ImageView getItemMenuImage() {
		return itemMenuImage;
	}

	public void setItemMenuImage(ImageView itemMenuImage) {
		this.itemMenuImage = itemMenuImage;
	}

	public TextView getItemMenuText() {
		return itemMenuText;
	}

	public void setItemMenuText(TextView itemMenuText) {
		this.itemMenuText = itemMenuText;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}


}
