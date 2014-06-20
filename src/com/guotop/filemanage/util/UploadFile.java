package com.guotop.filemanage.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;





/**
 * 
 *
 *
 *@author: ����
 *@time: 2013-6-13����7:07:36
 */
public class UploadFile {
	String multipart_form_data = "multipart/form-data";
	String twoHyphens = "--";
	String boundary = "****************SoMeTeXtWeWiLlNeVeRsEe"; // ���ݷָ���
	String lineEnd ="\r\n";
//	String lineEnd = System.getProperty("line.separator"); // The value is
															// "\r\n" in
															// Windows.

	/*
	 * �ϴ�ͼƬ���ݣ���ʽ��ο�HTTP Э���ʽ��
	 * ������Photos.upload�еġ�������á�http://wiki.dev.renren.com/wiki/Photos.upload#.E7.A8.8B.E5.BA.8F.E8.B0.83.E7.94.A8
	 * �����ʽ���͵ķǳ������� ��ʽ������ʾ�� --****************fD4fH3hK7aI6 Content-Disposition:
	 * form-data; name="upload_file"; filename="apple.jpg" Content-Type:
	 * File/jpeg
	 * 
	 * ������ļ������ݣ�������������ʽ
	 */
	private void addFileContent(List<UploadFileBean> files, DataOutputStream output) throws IOException {
		if (files != null) {
			for (UploadFileBean file : files) {
				StringBuilder split = new StringBuilder();
				split.append(twoHyphens + boundary + lineEnd);
				split.append("Content-Disposition: form-data; name=\""+ file.getFormName() + "\"; filename=\""+ file.getFileName() + "\"" + lineEnd);
				split.append("Content-Type: " + file.getContentType()+ lineEnd);
				split.append(lineEnd);

//				// ����ͼƬ����
				output.writeBytes(split.toString());
				write(file,output);
				
//				try {
//					// ����ͼƬ����
//					output.writeBytes(split.toString());
//					if (file.getData().length > 0) {
//						output.write(file.getData(), 0, file.getData().length);
//						output.writeBytes(lineEnd);
//					}
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
			}
		}
	}
	
	public void write(UploadFileBean file, DataOutputStream output){
		
		FileInputStream in;
		try {
			in = new FileInputStream(file.getFile());
			byte [] b=new byte[1024];
			while (in.read(b)>0) {
//				output.write(b);
				output.write(b, 0, b.length);
			}
			output.writeBytes(lineEnd);
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
	}
	

	/*
	 * �������ֶ����ݣ���ʽ��ο�HTTP Э���ʽ����FireBug����ץȡ��������ݣ���(�Ա��ϴ������Ӧ�Ĳ���ֵ) ��ʽ������ʾ��
	 * --****************fD4fH3hK7aI6 Content-Disposition: form-data;
	 * name="action" // һ���У������� upload
	 */
	private void addFormField(Map<String, String> params,DataOutputStream output) {
		if(params!=null){
			for (Entry<String, String> param : params.entrySet()) {
				StringBuilder sb = new StringBuilder();
				sb.append(twoHyphens + boundary + lineEnd);
				sb.append("Content-Disposition: form-data; name=\""+ param.getKey() + "\"" + lineEnd);
				sb.append(lineEnd);
				sb.append(param.getValue() + lineEnd);
				try {
					output.write(sb.toString().getBytes("utf-8"));// ���ͱ��ֶ�����
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	

	public String post(String actionUrl, Map<String, String> params,List<UploadFileBean> files) {
		return this.post(actionUrl, params, files, null);
	}

	/**
	 * ֱ��ͨ�� HTTP Э���ύ���ݵ���������ʵ�ֱ��ύ���ܡ�
	 * 
	 * @param actionUrl
	 *            �ϴ�·��
	 * @param params
	 *            �������keyΪ��������valueΪ����ֵ
	 * @param files
	 *            �ϴ��ļ���Ϣ
	 * @return ����������
	 */
	public String post(String actionUrl, Map<String, String> params,List<UploadFileBean> files,String cookie) {
		HttpURLConnection conn = null;
		DataOutputStream output = null;
		BufferedReader input = null;
		try {
			URL url = new URL(actionUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.setConnectTimeout(120000);
			conn.setDoInput(true); // ��������
			conn.setDoOutput(true); // �������
			conn.setUseCaches(false); // ��ʹ��Cache
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Content-Type", multipart_form_data
					+ "; boundary=" + boundary);

			conn.connect();
			output = new DataOutputStream(conn.getOutputStream());

			addFileContent(files, output); // ���ͼƬ����

			addFormField(params, output); // ��ӱ��ֶ�����

			output.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);// ���ݽ�����־
			output.flush();

			int code = conn.getResponseCode();
			if (code != 200) {
				throw new RuntimeException("����" + actionUrl + "��ʧ�ܣ�");
			}

			input = new BufferedReader(new InputStreamReader(conn
					.getInputStream()));
			StringBuilder response = new StringBuilder();
			String oneLine;
			while ((oneLine = input.readLine()) != null) {
				response.append(oneLine + lineEnd);
			}

			return response.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// ͳһ�ͷ���Դ
			try {
				if (output != null) {
					output.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			if (conn != null) {
				conn.disconnect();
			}
		}
	}
}
