package com.half.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * certificate
 *
 * @Author: wangwei
 * @Date: 2020-04-28 17:17
 */
public class CommonUtil {

    private static String httpurl = "https://wireless.tianya.cn/v/cms/getDataBySize?sectionIds=26086&pageSize=1&pageNo=1";

    public static void main(String[] args) throws IOException {
        url();

//        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory=new SimpleClientHttpRequestFactory();
//        simpleClientHttpRequestFactory.setConnectTimeout(10000);
//        simpleClientHttpRequestFactory.setReadTimeout(30000);
//        RestTemplate restTemplate=new RestTemplate(simpleClientHttpRequestFactory);
//
//        ResponseEntity<byte[]> response = restTemplate.getForEntity(httpurl, byte[].class);
//        System.out.println(response.getHeaders());
//
//        String responseString = new String(response.getBody(),StandardCharsets.ISO_8859_1);
//
//        restTemplate.getMessageConverters().set(1,new  StringHttpMessageConverter(StandardCharsets.UTF_8));
//        System.out.println(restTemplate.getForObject(httpurl,String.class));
    }

    public static void url() {

        //System.out.println(DigestUtils.md5DigestAsHex("ddd".getBytes()));
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // connection.setRequestProperty("Accept","text/html,application/xhtml+xml");
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }
        System.out.println(result.length());

        System.out.println(result);
    }

}
