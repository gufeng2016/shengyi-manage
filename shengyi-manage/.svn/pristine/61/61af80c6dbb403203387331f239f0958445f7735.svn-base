package com.sz.common.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * HttpClient工具类
 * @author john.jiang
 *
 */
public class HttpClientUtil {

	private static final Logger logger = LogManager.getLogger(HttpClientUtil.class);


	//httpGet连接
	public static String get(String url, List<NameValuePair> nvps) throws Exception {
		StringBuilder responseString = null;
		BufferedReader br = null;
		HttpClient httpclient = HttpConnectionManager.getHttpClient();
//		String params = HttpClientUtil.buildHttpGetParams(nvps);
//		URI uri = URIUtils.createURI(null, url, 80, null, URLEncodedUtils.format(nvps, HTTP.UTF_8), null);
//		URI uri = URIUtils.createURI(null, url, 80, null, params, null);
//		logger.info(uri);
//		HttpGet httpGet = new HttpGet(uri);
		String params = URLEncodedUtils.format(nvps, HTTP.UTF_8);
		String connectUrl = url + "?" + params;
//		logger.info("准备get连接 ：" + connectUrl);h
		HttpGet httpGet = new HttpGet(connectUrl);
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (entity != null && statusCode == 200) {
			br = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8));
			String line = "";
			responseString = new StringBuilder();
			while ((line = br.readLine()) != null) {
				responseString.append(br.readLine());
			}
		} else {
			logger.error("httpclient post connect error! status code: " + statusCode + " | url:" + connectUrl);
		}
		if (br != null) {
			br.close();
		}
		if (entity != null) {
			entity.consumeContent();
		}
		logger.info("response:" + responseString.toString());

		return responseString.toString();
	}

	//httpPost连接
	public static String post(String url, List<NameValuePair> nvps) throws Exception {
		String responseString = null;
		BufferedReader br = null;
		HttpClient httpclient = HttpConnectionManager.getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		String params = buildHttpGetParams(nvps);
//        logger.info("准备post连接 :" + url + " | param:" + params);
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (entity != null && statusCode == 200) {
			br = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8));
			responseString = br.readLine();
		} else {
			logger.error("httpclient get connect error! status code: " + statusCode + " | url:" + url + " | param:" + params);
		}
		if (br != null) {
			br.close();
		}
		if (entity != null) {
			entity.consumeContent();
		}
		logger.info("response:" + responseString);

		return responseString;
	}

	//构造get方法参数//没有URL转码
	public static String buildHttpGetParams(List<NameValuePair> params) {
		String paramStr = null;
		StringBuffer paramStrBuff = new StringBuffer();
		if (params.size() > 0) {
			paramStrBuff.append("?");
			for (NameValuePair nvp : params) {
//				paramStrBuff.append(nvp.getName() + "=" + nvp.getValue() + "&");
				paramStrBuff.append(nvp.toString()).append("&");
			}
			String str = paramStrBuff.toString();
			paramStr = str.substring(0, str.length() - 1);
		}

		return paramStr;
	}

	/**
	 * 构造get请求URL
	 * @param url
	 * @param nvps
	 * @return
	 */
	public static String buildGetUrl(String url, List<NameValuePair> nvps) {
		StringBuffer connectUrl = new StringBuffer(url);
		if (url.indexOf("?") == -1) {
			if (nvps.size() != 0 && nvps.size() > 0) {
				connectUrl.append("?");
				connectUrl.append(URLEncodedUtils.format(nvps, HTTP.UTF_8));
			}
		} else {
			if (nvps.size() != 0 && nvps.size() > 0) {
				connectUrl.append("&");
				connectUrl.append(URLEncodedUtils.format(nvps, HTTP.UTF_8));
			}
		}

		return connectUrl.toString();
	}
	 /**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws AdminException 
	 */
	public static String sendPost(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = null;
		System.out.println("要发送的param是："+param);
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("charset", "UTF-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(10000);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			result = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				throw new Exception(e.getMessage(), e);
			}
		}
		return result;
	}
	

}
