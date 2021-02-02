package com.dql.demo;

import cn.hutool.core.util.StrUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("hello")
    public String hello(@RequestParam(required =false ,name= "who")String who){
       if(Strings.isNotBlank(who)){
           who="world";
       }
       return StrUtil.format("hello,{}",who);
    }

}





