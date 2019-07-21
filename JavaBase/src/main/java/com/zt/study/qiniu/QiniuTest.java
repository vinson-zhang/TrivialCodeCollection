package com.zt.study.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.zt.study.util.PropertiesConfig;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ZhangTao
 * 2018/9/26 21:38
 * Description:
 */
public class QiniuTest {

    private String ak = PropertiesConfig.getProp("qiniu.ak");
    private String sk = PropertiesConfig.getProp("qiniu.sk");


    @Test
    public void testPic() throws QiniuException {
        String url = "http://ai.qiniuapi.com/v1/pulp";

        Map<String,Object> map = new HashMap<>();
        map.put("uri", "http://7xlv47.com1.z0.glb.clouddn.com/pulpsexy.jpg");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("data", map);

        System.out.println(Json.encode(map1));



        Auth auth = Auth.create(ak, sk);
        StringMap stringMap = auth.authorizationV2(url, "POST", StringUtils.utf8Bytes(Json.encode(map1)), "application/json");
        stringMap.put("Content-Type", "application/json");
//        StringMap stringMap = auth.authorization(url,  StringUtils.utf8Bytes(Json.encode(map1)), "application/json");
        System.out.println(stringMap.get("Authorization"));
        Client client = new Client();

        Response response = client.post(url, StringUtils.utf8Bytes(Json.encode(map1)), stringMap);

//        String result = doPost(url, map1,stringMap);
        System.out.println(Json.encode(response));


    }

    @Test
    public void getSign(){

    }

    public static String doPost(String url, Map<String, Object> paramMap, StringMap authorization) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", authorization.get("Authorization")+"");
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Test
    public void testTemp() throws QiniuException {
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", "1080");
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        String url = "https://www.baidu.com";
        String url2 = "https://www.google.com.hk";
        Client client = new Client();
        Response response = client.get(url);
        System.out.println(response.bodyString());
        Response response1 = client.get(url2);
        System.out.println(response1.bodyString());

    }

    public void regTest(){
        String temp = "hhhhfff,jjjjjjggdfe,{\"jjjj\":\"fdfdgfd\",\"jjdffdg\":\"fdfddfgfd\"}";
        String pattern = "(\\D*)(\\d+)(.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher("");
    }



}
