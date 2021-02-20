//package com.dql.demo.ratelimit;
//
//import cn.hutool.core.lang.Dict;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//public class RateLimiterController {
//
//    @RateLimiter(value = 1.0, timeout = 300)
//    @GetMapping("/test1")
//    public Dict test1() {
//        log.info("【test1】被执行了。。。。。");
//        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~");
//    }
//
//    @GetMapping("/test2")
//    public Dict test2() {
//        log.info("【test2】被执行了。。。。。");
//        return Dict.create().set("msg", "hello,world!").set("description", "我一直都在，卟离卟弃");
//    }
//}
