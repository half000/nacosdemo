package com.half.base.feign;

import com.half.base.MyFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: wangwei
 * @Date: 2019-11-26 21:39
 */
@FeignClient(contextId = "ITestController", value = "nacosprovider"
        , configuration = {MyFeignClientConfiguration.class}
        )
@RequestMapping("test")
public interface ITestController {

    /**
     * @PathVariable
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("{id}/{name}")
    public String test(@PathVariable("id") int id ,@PathVariable("name") String name);

    /**
     *
     * @param id
     *      不支持解析Map 如果参数为Map，则当body解析， 则方法变为POST
     *      如果不添加注解，则认为是body的数据
     *      如果是BODY，则发送方法变成POST(JDK中HttpURLConnection 兼容设置)
     * @param name
     * @param dd
     * @return
     */
    @GetMapping("{id}/{name}/{str}")
    public String test1(@PathVariable int id ,@PathVariable String name,@PathVariable("str") String dd);

    /**
     *
     * @Param header: @RequestHeader 只能一个
     * @Param param: @RequestParam  修饰的Map只能一个
     * @Param body: @RequestBody 只能一个
     * @return: java.lang.String
     */
    @PostMapping("")
    public String post(@RequestHeader Map<String,Object> header ,@RequestParam  Map<String,Object> param,@RequestBody Map<String,Object> body);

    @PutMapping("{id}")
    String put(@PathVariable int id,@RequestParam String name ,@RequestParam String note, @RequestBody Map<String, Object> param);

    @DeleteMapping("{id}")
    String delete(@PathVariable int id);

}
