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
 *@author: 李杨
 *@time: 2013-6-13下午7:07:36
 */
public class UploadFile {
	String multipart_form_data = "multipart/form-data";
	String twoHyphens = "--";
	String boundary = "****************SoMeTeXtWeWiLlNeVeRsEe"; // 数据分隔符
	String lineEnd ="\r\n";
//	String lineEnd = System.getProperty("line.separator"); // The value is
															// "\r\n" in
															// Windows.

	/*
	 * 上传图片内容，格式请参考HTTP 协议格式。
	 * 人人网Photos.upload中的”程序调用“http://wiki.dev.renren.com/wiki/Photos.upload#.E7.A8.8B.E5.BA.8F.E8.B0.83.E7.94.A8
	 * 对其格式解释的非常清晰。 格式如下所示： --****************fD4fH3hK7aI6 Content-Disposition:
	 * form-data; name="upload_file"; filename="apple.jpg" Content-Type:
	 * File/jpeg
	 * 
	 * 这儿是文件的内容，二进制流的形式
	 */
	private void addFileContent(List<UploadFileBean> files, DataOutputStream output) throws IOException {
		if (files != null) {
			for (UploadFileBean file : files) {
				StringBuilder split = new StringBuilder();
				split.append(twoHyphens + boundary + lineEnd);
				split.append("Content-Disposition: form-data; name=\""+ file.getFormName() + "\"; filename=\""+ file.getFileName() + "\"" + lineEnd);
				split.append("Content-Type: " + file.getContentType()+ lineEnd);
				split.append(lineEnd);

//				// 发送图片数据
				output.writeBytes(split.toString());
				write(file,output);
				
//				try {
//					// 发送图片数据
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
	 * 构建表单字段内容，格式请参考HTTP 协议格式（用FireBug可以抓取到相关数据）。(以便上传表单相对应的参数值) 格式如下所示：
	 * --****************fD4fH3hK7aI6 Content-Disposition: form-data;
	 * name="action" // 一空行，必须有 upload
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
					output.write(sb.toString().getBytes("utf-8"));// 发送表单字段数据
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
	 * 直接通过 HTTP 协议提交数据到服务器，实现表单提交功能。
	 * 
	 * @param actionUrl
	 *            上传路径
	 * @param params
	 *            请求参数key为参数名，value为参数值
	 * @param files
	 *            上传文件信息
	 * @return 返回请求结果
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
			conn.setDoInput(true); // 允许输入
			conn.setDoOutput(true); // 允许输出
			conn.setUseCaches(false); // 不使用Cache
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Content-Type", multipart_form_data
					+ "; boundary=" + boundary);

			conn.connect();
			output = new DataOutputStream(conn.getOutputStream());

			addFileContent(files, output); // 添加图片内容

			addFormField(params, output); // 添加表单字段内容

			output.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);// 数据结束标志
			output.flush();

			int code = conn.getResponseCode();
			if (code != 200) {
				throw new RuntimeException("请求‘" + actionUrl + "’失败！");
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
			// 统一释放资源
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
