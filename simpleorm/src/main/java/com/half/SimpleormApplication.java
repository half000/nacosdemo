package com.half;

import com.half.proxy.demo.Lissa;
import com.half.proxy.demo.Person;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.half.dao")//扫描dao层代码
public class SimpleormApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SimpleormApplication.class, args);

        Person person = applicationContext.getBean(Lissa.class);
        person.sendMail();
        System.out.println("-------------------分割线-----------------------------------------");
        try {
            person.sendMail1();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
