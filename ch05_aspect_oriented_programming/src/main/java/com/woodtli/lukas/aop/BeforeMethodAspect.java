package com.woodtli.lukas.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class BeforeMethodAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.woodtli.lukas.aop.*Dao.*(..))")
    public void before(JoinPoint joinPoint) {

        logger.info("Before executing a method: {}", joinPoint);
        logger.info(" Arguments passed: {}", joinPoint.getArgs());
    }
}
