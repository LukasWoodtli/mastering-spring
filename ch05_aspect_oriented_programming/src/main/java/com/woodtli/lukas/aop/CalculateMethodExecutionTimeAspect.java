package com.woodtli.lukas.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class CalculateMethodExecutionTimeAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.woodtli.lukas.aop.*Dao.*(..))")
    public Object after(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object retval = proceedingJoinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        logger.info("Method {} took {} ms to execute", proceedingJoinPoint, duration);
        return retval;
    }

}
