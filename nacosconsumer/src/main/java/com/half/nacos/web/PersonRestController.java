package com.half.nacos.web;

import com.half.nacos.model.Person;
import org.springframework.web.bind.annotation.*;

/**
 *  {@link RestController}
 *  restful注解 相关测试类
 *
 * @author mercyblitz
 * @date 2017-10-14
 **/
@RestController
//支持跨域请求
@CrossOrigin
public class PersonRestController {

    @GetMapping("/person/{id}")
    public Person person(@PathVariable Long id,
                         @RequestParam(required = false) String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    @PostMapping(value = "/person/json/to/properties",
            consumes = "text/plain",  //请求类型，可消费的类型 content-type
            produces = "application/properties+person" // 响应类型 可生产的类型 accept
    )
    public Person personJsonToProperties(@RequestParam String json) {

        // JSON to Map
        // Map to Properties
        return null;
    }

    @PostMapping(value = "/person/json/to/properties"
           //, produces = "application/properties+person" // 响应类型
           //, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE //请求类型
    )
    public Person personJsonToProperties(@RequestBody Person person) {
        // @RequestBody 的内容是 JSON
        // 响应的内容是 Properties
        return person;
    }

    @PostMapping(value = "/person/properties/to/json"
           //, produces =  MediaType.APPLICATION_JSON_UTF8_VALUE// 响应类型 // Accept
          // , consumes = "application/properties+person"// 请求类型 // Content-Type
    )
    public Person personPropertiesToJson(@RequestBody Person person) {
        // @RequestBody 的内容是 Properties
        // 响应的内容是 JSON
        return person;
    }

}
