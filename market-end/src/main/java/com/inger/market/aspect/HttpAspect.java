package com.inger.market.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IngerJo on 2018/9/7.
 */
@Aspect
@Component
public class HttpAspect {

    public static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(* com.inger.market.controller.*.*(..))")
    public void log() { }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // url
        logger.info("url={}", request.getRequestURI());

        // method
        logger.info("method={}", request.getMethod());

        // ip
        logger.info("ip={}", request.getRemoteAddr());

        // 类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());

        // 参数
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("After: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }
}