package com.wjb.config.web;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送参数json格式的的参数
     *
     * @param url
     * @param params
     * @return String
     * @author: miying@iflytek.com
     * @createTime: 2016年5月20日 上午12:44:10
     * @history:
     */
    public static String sendPostJSON(String url, Object params) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        JsonObject jsonObject = objToJsonObject(params);
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);

        post.addHeader("Content-type", "application/json;charset=UTF-8");
        post.addHeader("Accept", "application/json");
        post.addHeader("User-Agent", "Apache-HttpClient/4.1.2");
//		post.setHeader("Origin:", host);
//		post.setHeader("Referer", host+"/api/");
//		post.setHeader("Accept", "application/json");  
        post.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36");
        try {
            HttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("服务异常--> {}", e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception ex) {
                logger.error("服务异常--> {}", ex.getMessage());
            }
        }
        return null;
    }

    private static JsonObject objToJsonObject(Object obj) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return jsonObject;
    }

    /**
     * 发送参数json格式的的参数
     *
     * @param url
     * @param params
     * @return String
     * @author: miying@iflytek.com
     * @createTime: 2016年5月20日 上午12:44:10
     * @history:
     */
    public static String sendPostJSON(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        JsonObject jsonObject = new JsonObject();
        if (params != null) {
            Set<Entry<String, String>> set = params.entrySet();
            for (Iterator<Entry<String, String>> iter = set.iterator(); iter.hasNext(); ) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                jsonObject.addProperty(key, value);
            }
        }
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);

        post.addHeader("Content-type", "application/json;charset=UTF-8");
//		post.setHeader("Host", host);
//		post.setHeader("Cookie", "saeut=CkMPGlYk1Ske2DcpGBPrAg==; csrftoken=NvwFYdB7ZPE2PnKCrG6IFHOLdThhT9yp; sessionid=uh7wy5d6wc5qhjst3jc38crxbhtv77mw");
//		post.setHeader("Origin:", host);
//		post.setHeader("Referer", host+"/api/");
//		post.setHeader("Accept", "application/json");  
        post.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36");
        try {
            HttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("服务异常--> {}", e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception ex) {
                logger.error("服务异常--> {}", ex.getMessage());
            }
        }

        return null;
    }

    /**
     * 发送form表单数据的post请求
     *
     * @param url
     * @param params
     * @return String
     * @author: miying@iflytek.com
     * @createTime: 2016年10月10日 上午10:54:25
     * @history:
     */
    public static String sendPostForm(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            Set<Entry<String, String>> set = params.entrySet();
            for (Iterator<Entry<String, String>> iter = set.iterator(); iter.hasNext(); ) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                nvps.add(new BasicNameValuePair(key, value));
            }
        }
        HttpPost post = new HttpPost(url);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
            post.setEntity(entity);
            post.addHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36");
            HttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("服务异常--> {}", e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception ex) {
                logger.error("服务异常--> {}", ex.getMessage());
            }
        }
        return null;
    }

    /**
     * 发送form表单数据的post请求
     *
     * @param url
     * @param params
     * @return String
     * @author: miying@iflytek.com
     * @createTime: 2016年10月10日 上午10:54:25
     * @history:
     */
    public static String sendPostObjectForm(String url, Map<String, Object> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            Set<Entry<String, Object>> set = params.entrySet();
            for (Iterator<Entry<String, Object>> iter = set.iterator(); iter.hasNext(); ) {
                Entry<String, Object> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue().toString();
                nvps.add(new BasicNameValuePair(key, value));
            }
        }
        HttpPost post = new HttpPost(url);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
            post.setEntity(entity);
            post.addHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36");
            HttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            logger.error("服务异常--> {}", e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception ex) {
                logger.error("服务异常--> {}", ex.getMessage());
            }
        }
        return null;
    }

    public static String get(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}
