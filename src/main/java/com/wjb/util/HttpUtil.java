package com.wjb.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 不同项目之间接口调用工具类
 * 使用示例:
	String json = HttpUtil.doGet(VALIDATE_URL);
	ObjectMapper mapper = new ObjectMapper();
	SimpleResult result = mapper.readValue(json, SimpleResult.class);
*@Author:wjb
*@params:
*@Date:16:15 2017/10/19
*/
public class HttpUtil {
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";

	public static String doGet(String url) throws Exception {
		return doGet(url, 30000);
	}

	public static byte[] doGetStream(String url) throws Exception  {
		return doGetStream(url, 30000);
	}
	
	public static String doPost(String url) throws Exception {
		return doPost(url, null, null, null);
	}

	public static String doPost(String url, String json) throws Exception {
		String charset = "UTF-8";
		return doPost(url, json, charset);
	}

	public static String doPost(String url, Map<String, String> params) throws Exception {
		String charset = "UTF-8";
		return doPost(url, params, null, charset);
	}

	public static String doPost(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
		String charset = "UTF-8";
		return doPost(url, params, headers, charset);
	}

	public static String doGet(String url, int connectionRequestTimeout) throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception();
		}

		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet get = new HttpGet(url);
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).build();
			get.setConfig(config);
			response = client.execute(get);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}

			client.close();
		}
	}
	
	public static String doPost(String url, Map<String, String> params, Map<String, String> headers, String charset)
			throws Exception {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(20000).build();
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("User-Agent", USER_AGENT);
			if (headers != null) {
				Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> param = iterator.next();
					httpPost.addHeader(param.getKey(), param.getValue());
				}
			}

			if (params != null) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> param = iterator.next();
					list.add(new BasicNameValuePair(param.getKey(), param.getValue()));
				}

				if (list.size() > 0) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
					httpPost.setEntity(entity);
				}
			}

			response = client.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}

			client.close();
		}
	}

	public static String doPost(String url, String json, String charset) throws Exception {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(20000).build();
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("User-Agent", USER_AGENT);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(json, charset));
			response = client.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}

			client.close();
		}
	}

	public static byte[] doGetStream(String url, int connectionRequestTimeout) throws Exception {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.build();
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("User-Agent", USER_AGENT);
			httpPost.setEntity(new StringEntity(url, "UTF-8"));
			response = client.execute(httpPost);
			return EntityUtils.toByteArray(response.getEntity());
		} catch (Exception e) {
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}

			client.close();
		}
	}
}
