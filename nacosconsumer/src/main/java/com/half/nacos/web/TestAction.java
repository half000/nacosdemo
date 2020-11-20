package com.half.nacos.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试resttemplate的各种方法
 *  媒体类型 urlencoded 与json 区别
 * @Author: wangwei
 * @Date: 2019-11-18 22:22
 */
@RestController
@RequestMapping("/test")
public class TestAction {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("{id}")
    public String getTest(@PathVariable int id) {
        Map<String, Object> param = new HashMap<>();
        param.put("key1", "value1");
        param.put("key2", "value2");
        param.put("key3", "value3");
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 发送请求
        HttpEntity<Map> entity = new HttpEntity<>(param, headers);

        switch (id) {
            case 0:
                return JSON.toJSONString(restTemplate.optionsForAllow("http://nacosprovider/test"));
            case 1:
                URI url=restTemplate.postForLocation("http://nacosprovider/test", param);
                return url==null?"":url.toString();
            case 2:
                return restTemplate.getForObject("http://nacosprovider/test/id/name/age", String.class);

            case 3:
                return restTemplate.getForObject("http://nacosprovider/test?name=wangwei", String.class);

            case 4:
                return restTemplate.postForObject("http://nacosprovider/test?id=1&name=wangwei", param, String.class);

            case 5:
                return restTemplate.postForObject("http://nacosprovider/test/postModelAttribute?id=1&name=wangwei", param, String.class);
            case 15:
                return restTemplate.postForObject("http://nacosprovider/test/postModelAttribute", param, String.class);
            case 6:
                restTemplate.put("http://nacosprovider/test/postModelAttribute?id=1&name=wangwei", param);
                return "PUT OK";

            case 7:
                restTemplate.delete("http://nacosprovider/test/postModelAttribute?id=1&name=wangwei");
                return "DELETE OK";

            case 8:

                return restTemplate.exchange("http://nacosprovider/test/postModelAttribute?id=2&name=wangwei1", HttpMethod.PUT, entity, String.class).getBody();


            case 9:

                return restTemplate.exchange("http://nacosprovider/test/postModelAttribute?id=2&name=wangwei1", HttpMethod.DELETE, entity, String.class).getBody();


            default:
        }


        return null;
    }
    /**
     *client:
     *      *  ContentType:  MediaType.APPLICATION_JSON
     *      *  body:bean collections
     *      *  server: @RequestBody  用bean或集合类接收
     *      此时BODY中的数据不会和queryString的数据合并
     * server：
     *      @RequestBody 数据类型必须兼容
     * @Param type:
     * @return: java.lang.String
     */
    @GetMapping("json/{type}")
    public String testExchangeJson(@PathVariable int type) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> param=null;
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(type==1){

            param = new HashMap<>();
            param.put("key1", "value1");
            param.put("key2", "value2");
            param.put("key3", "value3");
        }else{

            param = new LinkedMultiValueMap();
            ((MultiValueMap) param).add("key1", "value1");
            ((MultiValueMap) param).add("key1", "value11");
            ((MultiValueMap) param).add("key2", "value2");
            ((MultiValueMap) param).add("key3", "value3");
        }


        // 发送请求
        HttpEntity<Map> entity = new HttpEntity<>(param, headers);

        ResponseEntity responseEntity=restTemplate.exchange("http://nacosprovider/test/json?id=1&name=wangwei1", HttpMethod.POST,entity,String.class);
        return JSON.toJSONString(responseEntity);
    }


    /**
     *  client:
     *  ContentType:  MediaType.APPLICATION_FORM_URLENCODED
     *  body: key=value&keya=valuea 或者 MultiValueMap
     *  server: @RequestBody  必须MultiValueMap接收
     *  此时BODY中的数据会和queryString的数据合并 @RequestParam 与@RequestBody 相同
     *  @RequestParam 可以用Map接收 value如果是list 取前面的
     * @Param type:
     * @return: java.lang.String
     */
    @GetMapping("url/{type}")
    public String testExchange(@PathVariable int type) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity entity;
        // 参数
        if(type==1){
            String param="key=value&key=value11&keya=valuea";
            entity = new HttpEntity(param, headers);
        }else{
            MultiValueMap param= new LinkedMultiValueMap();
            param.add("key1", "value1");
            param.add("key1", "value11");
            param.add("key2", "value2");
            param.add("key3", "value3");
            entity = new HttpEntity(param, headers);
        }

        ResponseEntity responseEntity=restTemplate.exchange("http://nacosprovider/test/url?id=2&name=wangwei2", HttpMethod.POST,entity,String.class);
        return JSON.toJSONString(responseEntity);
    }

}
