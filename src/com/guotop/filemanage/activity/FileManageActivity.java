package com.guotop.filemanage.activity;

import java.io.File;

import com.guotop.filemanage.R;
import com.guotop.filemanage.R.layout;
import com.guotop.filemanage.R.menu;
import com.guotop.filemanage.bean.FileBean;
import com.guotop.filemanage.bean.FileManageGhostBean;
import com.guotop.filemanage.service.FileManageOpenService;
import com.guotop.filemanage.util.MyFile;
import com.guotop.filemanage.view.FileManageBottomView;
import com.guotop.filemanage.view.FileManageCenterView;
import com.guotop.filemanage.view.FileManageTopView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;

public class FileManageActivity extends Activity {

	private FileManageCenterView centerView;
	private FileManageTopView topView;
	private FileManageBottomView bottomView;
	
	
	/**
	 * 僵尸模式条件
	 */
	private FileManageGhostBean ghostBean;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filemanage_activity_file_manage);
		
		
		
		centerView=new FileManageCenterView(this){
			public void onCheckFile(FileBean params) {
				if(!ghostBean.isGhost()){// 僵尸模式不能随便打开文件
					MyFile.openFile(FileManageActivity.this, new File(params.getPath()));
//					super.onCheckFile(params);
				}else {
					ghostBean.onCheckFile(params);
				}
			}
			public void onCheckFolder(FileBean params) {
				centerView.load(params.getPath());
				topView.load(params.getPath());
				super.onCheckFolder(params);
			}
		};
		

		topView=new FileManageTopView(this){
			public void onPathClick(String path) {
				if(ghostBean.getRootPath().length()>path.length()){
					return ;
				}
				FileBean params=new FileBean(new File(path));
				centerView.onCheckFolder(params);
			}
		};
		
		bottomView = new FileManageBottomView(this);
		
		ghostBean=new FileManageGhostBean(this);
		
		
		//加载文件内容
		centerView.load(ghostBean.getRootPath());
		//初始化顶部工具条
		topView.load(ghostBean.getRootPath());
		
		FileManageOpenService.startInstance(this);
	}
	
	
	/**
	 * 菜单选项
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.filemanage_main, menu);
		return true;
	}

	
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(ghostBean.isGhost()){
    		return false;
    	}
    	
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			topView.onBack();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
    
    public void exitApp(){
    	new AlertDialog.Builder(this)
		.setTitle("系统提示！").setMessage("是否要退出文件管理器？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		}).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		}).show();
    }

	public FileManageCenterView getCenterView() {
		return centerView;
	}


	public void setCenterView(FileManageCenterView centerView) {
		this.centerView = centerView;
	}


	public FileManageTopView getTopView() {
		return topView;
	}


	public void setTopView(FileManageTopView topView) {
		this.topView = topView;
	}


	public FileManageBottomView getBottomView() {
		return bottomView;
	}


	public void setBottomView(FileManageBottomView bottomView) {
		this.bottomView = bottomView;
	}


	public FileManageGhostBean getGhostBean() {
		return ghostBean;
	}


	public void setGhostBean(FileManageGhostBean ghostBean) {
		this.ghostBean = ghostBean;
	}


	
}
