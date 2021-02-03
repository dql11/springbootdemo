package com.dql.demo;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        int length = context.getBeanDefinitionNames().length;
        log.trace("trace启动了{}bean",length);
        log.debug("debug启动了{}bean",length);
        log.info("info启动了{}bean",length);
        log.warn("warn启动了{}bean",length);
        log.error("error启动了{}bean",length);
//        try {
//            int i=0;
//            int j=1/i;
//        } catch (Exception e) {
//            log.error("启动异常",e);
//        }
    }

    @GetMapping("hello")
    public String hello(@RequestParam(required =false ,name= "who")String who){
       if(Strings.isNotBlank(who)){
           who="world";
       }
       return StrUtil.format("hello,{}",who);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

}





