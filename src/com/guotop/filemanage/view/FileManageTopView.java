package com.guotop.filemanage.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.guotop.filemanage.R;
import com.guotop.filemanage.activity.FileManageActivity;

/**
 *
 *文件管理器的头部部分
 *
 *@author: 李杨
 *@time: 2013-8-12下午3:43:34
 */
public class FileManageTopView {

	private FileManageActivity activity;
	
	private RelativeLayout topLayout;
	
	private LinearLayout topLeft,topRight,topCenter;
	
	private Button editModeButton,backButton;
	
	private List<Button> buttons=new ArrayList<Button>();
	
	private List<String> paths = new ArrayList<String>();
	private String lastPath;
	
	private String rootPath="0";
	
	public FileManageTopView(FileManageActivity fileManageActivity) {
		this.activity=fileManageActivity;
		topLayout = (RelativeLayout)fileManageActivity.findViewById(R.id.topLayout);
		
		topLeft = (LinearLayout)fileManageActivity.findViewById(R.id.topLeft);
		topRight = (LinearLayout)fileManageActivity.findViewById(R.id.topRight);
		topCenter = (LinearLayout)fileManageActivity.findViewById(R.id.topCenter);

		editModeButton = (Button)fileManageActivity.findViewById(R.id.editModeButton);
		backButton = (Button)fileManageActivity.findViewById(R.id.backButton);
		
		/**
		 * 开启编辑模式
		 */
		editModeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(activity.getCenterView().isEditMode()){
					activity.getCenterView().setEditMode(false);
					((Button)v).setText("编辑");
				}else {
					activity.getCenterView().setEditMode(true);
					((Button)v).setText("浏览");
				}
			}
		});
		
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onBack();
			}
		});
		
	}
	
	public void load(String path){
		if(lastPath!=null&&path.equals(lastPath)){
			return ;
		}
		paths.clear();
		buttons.clear();
		topCenter.removeAllViews();
		String [] ps = path.split("/");
		for (String string : ps) {
			paths.add(string);
		}
		lastPath=paths.get(paths.size()-1);
		for(int i=0;i<paths.size();i++){
			Button button=new Button(activity);
			button.setId(i);
			button.setText(paths.get(i));
			buttons.add(button);
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					onPathClick(createPath(((Button)v).getText()+""));
				}
			});
			topCenter.addView(button);
		}
		topCenter.removeViewAt(0);
	}
	
	public void onPathClick(String path){
		if(rootPath.length()>path.length()){
			return ;
		}
		activity.getCenterView().load(path);
	}
	
	public String createPath(String fileName){
		StringBuffer pbBuffer=new StringBuffer();
		for (String path : paths) {
			pbBuffer.append(path);
			if((fileName).equals(path)){
				break;
			}
			pbBuffer.append("/");
		}
		return pbBuffer.toString();
	}
	
    
    public void onBack(){
    	if(paths.size()>2){
    		paths.remove(paths.size()-1);
			onPathClick(createPath(paths.get(paths.size()-1)));
		}else {
			activity.exitApp();
		}
    }

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public Button getEditModeButton() {
		return editModeButton;
	}

	public void setEditModeButton(Button editModeButton) {
		this.editModeButton = editModeButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}
    
    
    
}
