package com.dql.demo.log;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.omg.CORBA.UNKNOWN;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.reflect.generics.scope.MethodScope;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Aspect
@Component
@Slf4j
public class AopLog {
    @Pointcut("execution(public * com.dql.demo.log.controller.*Controller.*(..))")
    public void log(){}


    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable{
        final ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        long startTime=System.currentTimeMillis();
        final Object result = point.proceed();
        final String header = request.getHeader("User-Agent");
        final UserAgent userAgent = UserAgent.parseUserAgentString(header);
        final Log l= Log.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s",point.getSignature().getDeclaringTypeName(),point.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(point))
                .result(result)
                .timeCost(System.currentTimeMillis()-startTime)
                .userAgent(header)
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString()).build();
        log.info("Request log info:{}", JSONUtil.toJsonStr(l));
        return result;
    }

    private Map<String,Object> getNameAndValue(ProceedingJoinPoint point) {
        final Signature signature = point.getSignature();
        MethodSignature methodSignature =(MethodSignature)signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = point.getArgs();
        if(ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)){
            return Collections.emptyMap();
        }
        if(names.length !=args.length){
            log.warn("{}方法参数名和参数值数量不一致",methodSignature.getName());
            return  Collections.emptyMap();
        }
        Map<String,Object> map =new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i],args[i]);
        }
        return map;

    }

    private static final String UNKNOWN = "unknown";
    public static  String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip==null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        String comma=",";
        String localhost ="127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if(localhost.equals(ip)){
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(),e);
            }
        }

        return ip;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Log{
        private String threadId;

        private String threadName;

        private String ip;

        private String url;

        private String httpMethod;

        private String classMethod;

        private Object requestParams;

        private Object result;

        private Long timeCost;

        private String os;

        private String browser;

        private String userAgent;

    }
}
