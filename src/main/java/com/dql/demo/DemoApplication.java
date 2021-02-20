package com.dql.demo;

import cn.hutool.core.util.StrUtil;
import com.dql.demo.datasource.DatasourceConfigCache;
import com.dql.demo.datasource.DatasourceConfigContextHolder;
import com.dql.demo.datasource.mapper.DatasourceConfigMapper;
import com.dql.demo.datasource.model.DatasourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@RestController
@Slf4j
public class DemoApplication implements CommandLineRunner {

    private final DatasourceConfigMapper configMapper;
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

    @Override
    public void run(String... args) {
        // 设置默认的数据源
        DatasourceConfigContextHolder.setDefaultDatasource();
        // 查询所有数据库配置列表
        List<DatasourceConfig> datasourceConfigs = configMapper.selectAll();
        System.out.println("加载其余数据源配置列表: " + datasourceConfigs);
        // 将数据库配置加入缓存
        datasourceConfigs.forEach(config -> DatasourceConfigCache.INSTANCE.addConfig(config.getId(), config));
    }

    @GetMapping("hello")
    public String hello(@RequestParam(required =false ,name= "who")String who){
       if(Strings.isNotBlank(who)){
           who="world";
       }
       return StrUtil.format("hello,{}",who);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(DemoApplication.class);
//    }

}





