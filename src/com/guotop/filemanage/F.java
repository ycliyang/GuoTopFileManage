package com.guotop.filemanage;

/**
 *
 *
 *@author: ����
 *@time: 2013-8-14����7:42:27
 */
public class F {

	public static String COOKIE=null;
	public static boolean newWorkStatus=true;


	public static String FILEURL="/mnt/sdcard/elearning/";
	public static String toolsApkTempFile=FILEURL+"toolsApk/temp/";
	
	public static final int result=0x001001;	
	public static final int dowloadAlignmentFile=0x001004;//���������ļ�
	public static final int dowloadPercentage=0x001005;//���ص��ļ��Ľ���
	public static final int dowloadFinish=0x001006;//���ص��ļ����
	public static final int dowloadStart=0x001007;//���������ļ�
	public static final int connectionServerSuccess=0x01008;//���ӷ������ɹ�
	public static final int connectionServerError=0x01009;//���ӷ��������ִ���ɹ�
}
