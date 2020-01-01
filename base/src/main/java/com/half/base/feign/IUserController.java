package com.half.base.feign;

import com.half.base.MyFeignClientConfiguration;
import com.half.base.bean.User;
import com.half.base.hystrix.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wangwei
 * @Date: 2019-11-30 13:09
 */
@FeignClient(contextId = "IUserController", value = "nacosprovider" ,
        configuration = {MyFeignClientConfiguration.class},fallback = UserFallBack.class)
@RequestMapping("user")
public interface IUserController {

    /**
     * @Param id:
     * @return: com.half.base.bean.User
     */
    @GetMapping("{id}")
    User get(@PathVariable int id);

    /**
     * @Param user:
     * @return: int
     */
    @PostMapping
    int add(@RequestBody User user);

    /**
     * @Param id:
     * @Param user:
     * @return: com.half.base.bean.User
     */
    @PutMapping("{id}")
    User update(@PathVariable Integer id, @RequestBody User user);

    /**
     * @Param id:
     * @return: com.half.base.bean.User
     */
    @DeleteMapping("{id}")
    User delete(@PathVariable int id);
}
