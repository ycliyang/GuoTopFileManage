package com.guotop.filemanage.util;

import java.io.File;
import java.util.List;

import com.guotop.filemanage.F;

/**
 *
 *
 *@author: 李杨
 *@time: 2013-5-7上午10:45:20
 */
public class DowloadBigFileHttpThread extends HttpThread{
	
	@SuppressWarnings("unused")
	private String path,fileName;
	private DowloadFileInformaction dowloadFileInformaction;
	
	@SuppressWarnings("unused")
	private List<DowloadFileInformaction> listDowFile;
	
	public DowloadBigFileHttpThread nextDowloadHttpThread;
	public boolean isStart=false;
	
	public DowloadBigFileHttpThread(){
		
	}
	public DowloadBigFileHttpThread(BaseHandler handler){
		this.handler=handler;
	}
	
	public DowloadBigFileHttpThread(BaseHandler handler,DowloadFileInformaction dowFile){
		this.dowloadFileInformaction=dowFile;
		this.handler=handler;
	}
	
	public DowloadBigFileHttpThread(List<DowloadFileInformaction> listDowFile,BaseHandler handler){
		this.listDowFile=listDowFile;
		this.handler=handler;
		initAlignment(listDowFile, this);
	}

	DowloadBigFileHttpThread initAlignment(List<DowloadFileInformaction> listDowFile,DowloadBigFileHttpThread dow){
		if(listDowFile.size()>0){
			dow.dowloadFileInformaction=listDowFile.get(0);
			listDowFile.remove(0);
			dow.nextDowloadHttpThread=initAlignment(listDowFile, new DowloadBigFileHttpThread(this.handler));
			return dow;
		}else{
			return null;
		}
	}
	int fileSize,finishFileSize,percentage;
	
	public void run() {
		isStart=true;//是启动了
		
		new HttpThread(handler){
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						
					}
					if(finishFileSize!=0&&fileSize!=0){
						msg = handler.obtainMessage();
						if(percentage>=100){
//							msg.what=L.dowloadFinish;
//							msg.setData(bundle);
//							handler.sendMessage(msg);
							break;
						}else {
							bundle.putString("fileName", dowloadFileInformaction.getFileName());
							bundle.putInt("percentage", percentage);
							bundle.putInt("finishFileSize", finishFileSize);
							bundle.putInt("fileSize", fileSize);
							bundle.putInt("notificationId", dowloadFileInformaction.getNotificationId());
							msg.what=F.dowloadPercentage;
							msg.setData(bundle);
							handler.sendMessage(msg);
						}
					}
				}
			}
		}.start();
		
		String path=http.getFile(dowloadFileInformaction.getUrl(), dowloadFileInformaction.getParams(), dowloadFileInformaction.getPath(), dowloadFileInformaction.getFileName(),new DowloadBigFileHttpInterface() {
			
			//初始化下载后
			public void initFileSize(int size) {
				fileSize=size;
				msg = handler.obtainMessage();
				bundle.putInt("fileSize", fileSize);
				msg.what = F.dowloadStart;
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
			//现在计划进行中
			public void dowloadPlan(int fileSize,int finishSize) {
				finishFileSize=finishSize;
				percentage=finishSize/(fileSize/100);
//				System.out.println(finishSize+"  :  "+fileSize+"  :  "+percentage);
				if(percentage<-1L){
					System.out.println(percentage);
				}
			}
			//下载完成
			public void dowloadFinish(File file) {
				percentage=101;
			}
		});
		
		msg = handler.obtainMessage();
		bundle.putInt("position",dowloadFileInformaction.getPostting());
		bundle.putString("path", path);
		bundle.putInt("notificationId", dowloadFileInformaction.getNotificationId());
		msg.what = F.dowloadFinish;
		msg.setData(bundle);
		handler.sendMessage(msg);
		if(this.nextDowloadHttpThread!=null){
			nextDowloadHttpThread.start();
		}
	}
	public DowloadFileInformaction getDowloadFileInformaction() {
		return dowloadFileInformaction;
	}
	public void setDowloadFileInformaction(
			DowloadFileInformaction dowloadFileInformaction) {
		this.dowloadFileInformaction = dowloadFileInformaction;
	}
	
	
	
}
