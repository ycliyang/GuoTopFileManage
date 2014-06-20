package com.guotop.filemanage.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.guotop.filemanage.R;
import com.guotop.filemanage.activity.FileManageActivity;

/**
 *
 *文件管理器的底部部分
 *
 *@author: 李杨
 *@time: 2013-8-12下午3:44:04
 */
public class FileManageBottomView {
	private FileManageActivity activity;
	
	private LinearLayout bottomLayout;
	
	private Button submitButton,refreshButton,returnButton;
	
	
	public FileManageBottomView(FileManageActivity fileManageActivity) {
		this.activity=fileManageActivity;
		bottomLayout = (LinearLayout)fileManageActivity.findViewById(R.id.bottomLayout);
		
		submitButton=(Button)fileManageActivity.findViewById(R.id.submitButton);
		refreshButton=(Button)fileManageActivity.findViewById(R.id.refreshButton);
		returnButton=(Button)fileManageActivity.findViewById(R.id.returnButton);
		
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			}
		});
		refreshButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(activity, "目录正在刷新请稍等......", Toast.LENGTH_LONG).show();
				activity.getCenterView().refresh();
			}
		});
		
		returnButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				activity.exitApp();
			}
		});
	}
	

	public LinearLayout getBottomLayout() {
		return bottomLayout;
	}
	
	
	public void setBottomLayout(LinearLayout bottomLayout) {
		this.bottomLayout = bottomLayout;
	}
	
	
	public Button getSubmitButton() {
		return submitButton;
	}


	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}
	
	
}
