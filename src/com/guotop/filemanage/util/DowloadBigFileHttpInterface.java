package com.guotop.filemanage.util;

import java.io.File;

/**
 *
 *
 *@author: ����
 *@time: 2013-5-7����2:22:53
 */
public interface DowloadBigFileHttpInterface {
	
	void initFileSize(int fileSize);
	
	void dowloadPlan(int fileSize,int finishFileSize);
	
	void dowloadFinish(File file);
}
