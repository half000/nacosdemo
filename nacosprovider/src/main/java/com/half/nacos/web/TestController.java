package com.half.nacos.web;

import com.alibaba.fastjson.JSON;
import com.half.nacos.common.KeyBean;
import com.half.nacos.common.UserBean;
import com.half.nacos.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: wangwei
 * @Date: 2019/10/26 8:54
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    /**
     * json body用map
     * @Param header:
     * @Param param:
     * @Param body:
     * @return: java.lang.String
     */
    @PostMapping("json")
    public String exchange(@RequestHeader(required = false) Map<String,String> header,@RequestParam(required = false) Map<String,String> param,@RequestBody(required = false) Map<String,Object> body){
        LogUtils.info(log,header,"head:");
        LogUtils.info(log,param,"param:");
        LogUtils.info(log,body,"body:");
        return JSON.toJSONString(header)+JSON.toJSONString(param)+JSON.toJSONString(body);
    }

    /**
     * url body 用MultiValueMap
     * @Param header:
     * @Param param:
     * @Param body:
     * @return: java.lang.String
     */
    @PostMapping("url")
    public String exchange(@RequestHeader(required = false) Map<String,String> header,@RequestParam(required = false) Map<String,String> param,@RequestBody(required = false) MultiValueMap<String,String> body){
        LogUtils.info(log,header,"head:");
        LogUtils.info(log,param,"param:");
        LogUtils.info(log,body,"body:");
        return JSON.toJSONString(header)+JSON.toJSONString(param)+JSON.toJSONString(body);
    }

    //PathVariable
     /**
     * @PathVariable 绑定url模板路径中的变量到方法变量名
     * @Param id:{id} String
     * @Param name:{name} String
     * @return: java.lang.String
     */
    @GetMapping("{id}/{name}")
   // @ApiOperation
    public String getPathVariable(@PathVariable String id,@PathVariable String name) {
        log.info("getPathVariable: id={}  name={} ",id, name);
        return "getPathVariable: " +id+","+ name;
    }

    /**
     * @PathVariable 绑定url模板路径中的变量到方法参数map
     *
     * @Param param: Map<String,String>
     * @return: java.lang.String
     */
    @GetMapping("/{id}/{name}/{dd}")
    public String getPathVariableToMap(@PathVariable Map<String,Object> param) {
        LogUtils.info(log,param,"getPathVariableToMap:");
        return "getPathVariableToMap " + JSON.toJSONString(param);
    }

    //@RequestParam

    /**
     * @RequestParam 绑定请求参数到方法参数
     *
     * @Param name:String
     * @return: java.lang.String
     */
    @GetMapping("")
    public String getRequestParam(@RequestParam String name) {
        log.info("getRequestParam: name ={} " , name);
        return "getRequestParam " + name;
    }

    /***
     * @RequestParam 绑定请求头参数到方法参数对象
     * @RequestBody  绑定请求体中参数到方法参数对象
     *
     * @Param param:
     * @return: java.lang.String
     */
    @PostMapping("")
    public String post(@RequestHeader Map<String,Object> header, @RequestParam Map<String,Object> param,@RequestBody Map<String,Object> paramBody) {
        LogUtils.info(log,header,"head:");
        LogUtils.info(log,param,"postRequestParamToMap param:");
        LogUtils.info(log,paramBody,"postRequestParamToMap body:");
        return "postRequestParamToMap " + JSON.toJSONString(header)+JSON.toJSONString(param)+ JSON.toJSONString(paramBody);
    }
    /**
     * 更新
     *
     * @param id
     * @param param
     * @return
     */
    @PutMapping("{id}")
    public String put(@PathVariable String id,@RequestHeader Map<String,Object> header, @RequestParam Map<String,Object> param,@RequestBody Map<String,Object> body){
        param.put("id",id);
        LogUtils.info(log,header,"head:");
        LogUtils.info(log,param,"postRequestParamToMap param:");
        LogUtils.info(log,body,"postRequestParamToMap body:");

        return "put " + JSON.toJSONString(param);
    }
    /**
     *
     * @ModelAttribute 绑定请求参数到方法参数Bean  不能绑定json请求 需要另行解决
     *
     * @param user
     * @return
     */
    @PostMapping("postModelAttribute")
    public String postModelAttribute(@ModelAttribute("user") UserBean user, @ModelAttribute UserBean user1, @RequestBody KeyBean keyData, Model model) {
        log.info("postModelAttribute {}" , JSON.toJSONString(user));
        log.info("postModelAttribute {}" , JSON.toJSONString(user1));
        log.info("postModelAttribute {}" , JSON.toJSONString(keyData));
        return "postModelAttribute " + JSON.toJSONString(user)+JSON.toJSONString(user1)+JSON.toJSONString(keyData);
    }

    /**
     * 这里先拦截请求，把keyBean设置到model里
     * 如果这里使用了@RequestBody会导致BODY的输入流无法第二次读取
     * @param keyBean
     * @return
     */
   /* @ModelAttribute
    private KeyBean getKeyBean(@RequestBody(required=false) KeyBean keyBean){
        return keyBean;
    }*/



    /**
     * delete
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        log.info("delete id: {}",id);
        return "delete id " + id;
    }

    /**
     * 会默认把user放入MAV中
     * @return
     */
    //@ModelAttribute("user")
    @RequestMapping("setUserToMav")
    public UserBean setUserToMav(){
        UserBean user=new UserBean();
        user.setId(1000);
        user.setName("half");
        log.info("setUserToMav {}", JSON.toJSONString(user));
        return user;
    }

    /**
     * 会默认把user放入MAV中,不设置属性名，则是类名驼峰
     * @return
     */
    @ModelAttribute
    public void setUserToMav2(Model model){
        UserBean user=new UserBean();
        user.setId(2000);
        user.setName("jim");
        model.addAttribute(user);
    }

}
