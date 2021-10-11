package com.woodtli.lukas.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AfterMethodAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.woodtli.lukas.aop.*Dao.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("After executing a method: {}", joinPoint);
        logger.info(" Arguments passed: {}", joinPoint.getArgs());
    }

}
