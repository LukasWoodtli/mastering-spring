package com.woodtli.lukas.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AfterReturningMethodAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(value = "execution(* com.woodtli.lukas.aop.*Dao.*(..))",
        returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After returning from a method: {}", joinPoint);
        logger.info(" Arguments passed: {}", joinPoint.getArgs());
        logger.info(" Returned value: {}", result);
    }

}
