package com.guotop.filemanage.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.guotop.filemanage.F;

/**
 * 
 * 
 * 
 * @author: 李杨
 * @time: 2013-3-18下午4:35:42
 */
public class Http {

	HttpClient httpClient=null;
	private static final int REQUEST_TIMEOUT = 3*1000;//设置请求超时3秒钟  
	private static final int SO_TIMEOUT = 15*1000;  //设置等待数据超时时间15秒钟  
	   
	{
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
			httpClient = new DefaultHttpClient(httpParams);
	};
	public String SUCCESS = "{success:true}";

	public String postJson(String url, String json) {
		String result = null;
		HttpPost post = new HttpPost(url);
		post.setHeader("Cookie", F.COOKIE);
		Log.d("cookie", F.COOKIE);
		try {
			post.setEntity(new StringEntity(json,HTTP.UTF_8));
			HttpResponse resonse = httpClient.execute(post);
			resonse.setHeader("Content-type","Content-Type: application/json; charset=UTF-8");
			resonse.setHeader("X-Requested-With","XMLHttpRequest");
			if (resonse.getStatusLine().getStatusCode() == 200) {
				result = getResult(resonse, "UTF-8");
			}
		} catch (ClientProtocolException e) {

		} catch (IOException e) {

		}
		return result;
	}
	


	public String post(String url, List<NameValuePair> params) {
		String result = null;
		HttpPost post = new HttpPost(url);
		if(F.COOKIE!=null&&!"".equals(F.COOKIE)){
			post.setHeader("Cookie", F.COOKIE);
			Log.d("cookie", F.COOKIE);
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse resonse = httpClient.execute(post);
//			if(F.COOKIE==null||"".equals(F.COOKIE)){
//				F.COOKIE = resonse.getFirstHeader("Set-Cookie").getValue();
//			}
			if (resonse.getStatusLine().getStatusCode() == 200) {
				result = getResult(resonse, "UTF-8");
			}
		} catch (ClientProtocolException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String post(String url, List<NameValuePair> params, String cookie) {
		if (F.newWorkStatus) {
			String result = null;
			HttpPost post = new HttpPost(url);
			post.setHeader("Cookie", cookie);
			try {
				post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				HttpResponse resonse = httpClient.execute(post);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					// result = EntityUtils.toString(resonse.getEntity(),
					// "UTF-8");
					result = getResult(resonse, "UTF-8");
				}
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}
			return result;
		} else {
			return SUCCESS;
		}
	}
	
	public String postFile(String url,Map<String,String> paramMap,List<UploadFileBean> files){
		UploadFile upload=new UploadFile();
		String result=upload.post(url, paramMap, files,F.COOKIE);
		return result;
	}
	
	public byte[] getFile(String url, List<NameValuePair> params) {
		if (F.newWorkStatus) {
			byte[] result = null;
			HttpPost post = new HttpPost(url);
			post.setHeader("Cookie", F.COOKIE);
			try {
				post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				HttpResponse resonse = httpClient.execute(post);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					result = EntityUtils.toByteArray(resonse.getEntity());
				}
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}
			return result;
		} else {
			return null;
		}
	}
	
	

	public String  getFile(String url,List<NameValuePair> params,String path,String fileName) {
		if (F.newWorkStatus) {
			File file=null;
			HttpPost post = new HttpPost(url);
			post.setHeader("Cookie", F.COOKIE);
			try {
				if(params!=null){
					post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				}
				HttpResponse resonse = httpClient.execute(post);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					if(fileName==null){
						fileName=resonse.getHeaders("Content-Disposition")[0].getValue().split("filename=")[1].replaceAll("\"", "");
					}
					InputStream inStream= resonse.getEntity().getContent();
					file=new File(path);
					if(!file.exists()){
						file.mkdirs();
					}
					file=new File(path+fileName);
					file.createNewFile();
					FileOutputStream outStream = new FileOutputStream(file);
					// 设置一个缓冲区
					byte[] buffer = new byte[1024];
					int len = 0;
					// 判断输入流长度是否等于-1，即非空
					while ((len = inStream.read(buffer)) != -1) {
						// 把缓冲区的内容写入到输出流中，从0开始读取，长度为len
						outStream.write(buffer, 0, len);
					}
					// 关闭输入流
					inStream.close();
					outStream.flush();
					outStream.close();
				}
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}
			return file.getPath();
		} else {
			return null;
		}
	}
	
	public String  getFile(String url,List<NameValuePair> params,String path,String fileName,DowloadBigFileHttpInterface di) {
		if (F.newWorkStatus) {
			File file=null;
			HttpPost post = new HttpPost(url);
			post.setHeader("Cookie", F.COOKIE);
			try {
				if(params!=null){
					post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				}
				HttpResponse resonse = httpClient.execute(post);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					InputStream inStream= resonse.getEntity().getContent();
					file=new File(F.toolsApkTempFile);
					file.mkdirs();
					file=new File(F.toolsApkTempFile+fileName);
					FileOutputStream outStream = new FileOutputStream(file);
					int fileSize=(int) resonse.getEntity().getContentLength();
					di.initFileSize(fileSize);
					// 设置一个缓冲区
					byte[] buffer = new byte[1024];
					int dowlen=0;
					int len = 0;
					// 判断输入流长度是否等于-1，即非空
					while ((len =  inStream.read(buffer)) != -1) {
						// 把缓冲区的内容写入到输出流中，从0开始读取，长度为len
						outStream.write(buffer, 0, len);
						dowlen+=len;
						di.dowloadPlan(fileSize,dowlen);
					}
					// 关闭输入流
					inStream.close();
					outStream.flush();
					outStream.close();
					di.dowloadFinish(file);
				}
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}
			return file.getPath();
		} else {
			return null;
		}
	}

	public byte[] getFile(String url) {
		if (F.newWorkStatus) {
			byte[] result = null;
			HttpGet get = new HttpGet(url);
			get.setHeader("Cookie", F.COOKIE);
			try {
				HttpResponse resonse = httpClient.execute(get);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					result = EntityUtils.toByteArray(resonse.getEntity());
				}
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}
			return result;
		} else {
			return null;
		}
	}
	

	public String reLogin(String url) {
		if(F.newWorkStatus){
			String result = null;
			HttpGet get = new HttpGet(url);
			try {
//				this.createConn();//收到服务器返回的超时信息后已经重新设置过
				get.setHeader("Cookie", F.COOKIE);
//				String cookie=null;
				HttpResponse resonse = httpClient.execute(get);
//				org.apache.http.Header hd=resonse.getFirstHeader("Set-Cookie");
//				if(hd!=null){
//					cookie =hd.getValue();
//				}
				
				if (resonse.getStatusLine().getStatusCode() == 200) {
					result = getResult(resonse, "UTF-8");
				}
//				if(result!=null){
//					L.COOKIE=cookie;
//				}
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			return result;
		}else {
			return SUCCESS;
		}
	}

	public String get(String url) {
		if(F.newWorkStatus){
			String result = null;
			HttpGet get = new HttpGet(url);
			get.setHeader("Cookie", F.COOKIE);
			try {
				HttpResponse resonse = httpClient.execute(get);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					result = getResult(resonse, "UTF-8");
				}
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			return result;
		}else {
			return SUCCESS;
		}
	}

	public String get(String url, String cookie) {
		if(F.newWorkStatus){
			String result = null;
			HttpGet get = new HttpGet(url);
			get.setHeader("Cookie", cookie);
			try {
				HttpResponse resonse = httpClient.execute(get);
				if (resonse.getStatusLine().getStatusCode() == 200) {
					result = getResult(resonse, "UTF-8");
				}
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			return result;
		}else {
			return SUCCESS;
		}
	}

	
	public String createConn(String url) {
		if(F.newWorkStatus){
			String cookie = null;
			HttpGet get = new HttpGet(url + "userAction!createConnection.action");
			try {
				HttpResponse resonse = httpClient.execute(get);
				cookie = resonse.getFirstHeader("Set-Cookie").getValue();
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			if (cookie != null && "".equals(cookie)) {
	
			} else {
				F.COOKIE = cookie;
			}
		}
		return F.COOKIE;
	}
	

	public byte[] httpFileByte(String url) {
		if(F.newWorkStatus){
			byte[] data = null;
			HttpGet get = new HttpGet(url);
			try {
				get.setHeader("Cookie", F.COOKIE);
				HttpResponse httpResponse = new DefaultHttpClient().execute(get);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					data = readInputStream(httpResponse.getEntity().getContent());
				}
			} catch (Exception e) {
				e.getMessage().toString();
			}
			return data;
		}else {
			return null;
		}
	}

	public byte[] httpFileByte(String url, String cookie) {
		if(F.newWorkStatus){
			byte[] data = null;
			HttpGet get = new HttpGet(url);
			try {
				get.setHeader("Cookie", cookie);
				HttpResponse httpResponse = new DefaultHttpClient().execute(get);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					data = readInputStream(httpResponse.getEntity().getContent());
				}
			} catch (Exception e) {
				e.getMessage().toString();
	
			}
			return data;
		}else{
			return null;
		}
	}

	public byte[] httpFileByte(String url, List<NameValuePair> params) {
		if(F.newWorkStatus){
			byte[] data = null;
			HttpPost post = new HttpPost(url);
			try {
				post.setHeader("Cookie", F.COOKIE);
				post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				HttpResponse httpResponse = new DefaultHttpClient().execute(post);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					data = readInputStream(httpResponse.getEntity().getContent());
				}
			} catch (Exception e) {
				e.getMessage().toString();
			}
			return data;
		}else{
			return null;
		}
	}

	public byte[] readInputStream(InputStream inStream) throws Exception {
		// 构造一个ByteArrayOutputStream
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 设置一个缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		// 判断输入流长度是否等于-1，即非空
		while ((len = inStream.read(buffer)) != -1) {
			// 把缓冲区的内容写入到输出流中，从0开始读取，长度为len
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		return outStream.toByteArray();
	}

	public String getResult(HttpResponse response, String code) {
		StringBuffer result = new StringBuffer("");

		
		
		try {
			if ("gzip".equals(response.getEntity().getContentEncoding())) {
				GZIPInputStream gzip = new GZIPInputStream(response.getEntity()
						.getContent());
				BufferedReader breader = new BufferedReader(new InputStreamReader(gzip, code));
				String str = breader.readLine();
				while (str != null) {
					result.append(str);
					str = breader.readLine();
				}
				breader.close();
			} else {
				result.append(EntityUtils.toString(response.getEntity(),
						"UTF-8"));
			}
		} catch (IllegalStateException e) {

		} catch (IOException e) {

		}
		return result.toString();
	}
	

}
