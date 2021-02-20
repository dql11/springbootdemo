//package com.dql.demo;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.date.DateTime;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.crypto.SecureUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.dql.demo.mybatisplus.UserService;
//import com.dql.demo.redis.entity.User;
//import com.google.common.collect.Lists;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Slf4j
//public class UserServiceTest1 extends DemoApplicationTests {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate<String, Serializable> redisCacheTemplate;
//
//    @Test
//    public void get() {
//        // 测试线程安全，程序结束查看redis中count的值是否为1000
//        ExecutorService executorService = Executors.newFixedThreadPool(1000);
//        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));
//
//        stringRedisTemplate.opsForValue().set("k1", "v1");
//        String k1 = stringRedisTemplate.opsForValue().get("k1");
//        log.debug("【k1】= {}", k1);
//
//        // 以下演示整合，具体Redis命令可以参考官方文档
//        String key = "xkcoding:user:1";
//        redisCacheTemplate.opsForValue().set(key, new User(1L, "user1"));
//        // 对应 String（字符串）
//        User user = (User) redisCacheTemplate.opsForValue().get(key);
//        log.debug("【user】= {}", user);
//    }
//
//
//
//}