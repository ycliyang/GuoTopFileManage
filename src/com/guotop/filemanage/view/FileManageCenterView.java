package com.guotop.filemanage.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.guotop.filemanage.F;
import com.guotop.filemanage.R;
import com.guotop.filemanage.activity.FileManageActivity;
import com.guotop.filemanage.bean.FileBean;
import com.guotop.filemanage.bean.ItemFileMenuButtonBean;
import com.guotop.filemanage.container.SquareContainer;
import com.guotop.filemanage.item.BaseItem;
import com.guotop.filemanage.item.SquareItem;
import com.guotop.filemanage.listener.ItemFileMenuButtonListener;
import com.guotop.filemanage.util.BaseHandler;
import com.guotop.filemanage.util.HttpThread;
import com.guotop.filemanage.util.MyFile;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 
 * �ļ����������м䲿��
 * 
 *����������������һ��
 *
 *@author: ����
 *@time: 2013-8-12����3:43:47
 */
public class FileManageCenterView {

	private FileManageActivity activity;
	private LinearLayout conterLayout;

	List<FileBean> beans;
	List<ItemFileMenuButtonBean> items;
	
	private List<View> rowViews;
	
	private String nowPath;
	
	private  ItemFileMenuButtonListener buttonListener;
	
	private Map<String, ItemFileMenuButtonBean> chooseFileList=new HashMap<String, ItemFileMenuButtonBean>();
	

	
	
	private boolean editMode=false;
	
	public FileManageCenterView(FileManageActivity fileManageActivity){
		this.activity=fileManageActivity;
		conterLayout = (LinearLayout)fileManageActivity.findViewById(R.id.conterLayout);
		
		buttonListener = new ItemFileMenuButtonListener(){
			public boolean onClick(View v,ItemFileMenuButtonBean bean,boolean isChoose) {
				if(!isEditMode()){
					if(bean.getParams().isDirectory()){
						onCheckFolder(bean.getParams());
					}else {
						onCheckFile(bean.getParams());
					}
				}else {
					if(isChoose){
						chooseFileList.put(bean.getParams().getPath(), bean);
						bean.getItem().getListener().onClickChangeItemBackgroud(bean.getItem().getView(),true);
					}else {
						chooseFileList.remove(bean.getParams().getPath());
						bean.getItem().getListener().onClickChangeItemBackgroud(bean.getItem().getView(),false);
					}
				}
				return super.onClick(v, bean);
			}
			
			public boolean onTouch(View v, MotionEvent event) {
				if(!isEditMode()){
					return true;
				}
				return false;
			}
		};
	}
	
	public void refresh(){
		load(nowPath);
	}
	
	public void load(String path){
		this.nowPath=path;
		beans = new ArrayList<FileBean>();
		conterLayout.removeAllViews();

		//���ر���SD��
		if(activity.getGhostBean().isLoca()){
			//��ȡ��Ŀ¼���������ļ�
			File[] files = MyFile.getFileChildrens(path);
			for (File file : files) {
				beans.add(new FileBean(file));
			}
			showView();
		}else {
			//�����ļ��б�����
//			getNetworkFileChildrens(path);
			activity.getGhostBean().readNetworkFiles(path);
		}
		
	}
	
	public void showView(){
		initSquareItemList();
		for (View view : rowViews) {
			conterLayout.addView(view);
		}
	}
	
	
	public void initSquareItemList(){
		
		items=new ArrayList<ItemFileMenuButtonBean>();
		for (FileBean fileBean : beans) {
			SquareItem squartItem = new SquareItem(activity, fileBean.getIcon(), fileBean.getName());
			ItemFileMenuButtonBean buttonBean=new ItemFileMenuButtonBean(squartItem,fileBean,buttonListener);
			items.add(buttonBean);
		}
		rowViews = new SquareContainer(activity, items).getListContainer();
	}
	
	public void initBarItemList(FileBean params){
		
	}


	/**
	 * ����ļ���
	 * @param params
	 */
	public void onCheckFolder(FileBean params){
		
	}

	/**
	 * ����ļ�
	 */
	public void onCheckFile(FileBean params){
		
	}

	public List<ItemFileMenuButtonBean> getItems() {
		return items;
	}


	public void setItems(List<ItemFileMenuButtonBean> items) {
		this.items = items;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		if(!editMode){
			
			Set<String> keySet=chooseFileList.keySet();
			for (String key : keySet) {
				BaseItem item=chooseFileList.get(key).getItem();
				item.getListener().onClickChangeItemBackgroud(item.getView(), false);
			}
			
			chooseFileList.clear();
		}
	}

	public Map<String, ItemFileMenuButtonBean> getChooseFileList() {
		return chooseFileList;
	}

	public void setChooseFileList(Map<String, ItemFileMenuButtonBean> chooseFileList) {
		this.chooseFileList = chooseFileList;
	}

	public List<FileBean> getBeans() {
		return beans;
	}

	public void setBeans(List<FileBean> beans) {
		this.beans = beans;
	}
	
	
}