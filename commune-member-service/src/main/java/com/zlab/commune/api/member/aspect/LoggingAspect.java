package com.zlab.dzuko.mvc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.zlab.dzuko.mvc.controller.*.*(..))")
    private void forControllerPackage(){

    }

    @Pointcut("execution(* com.zlab.dzuko.mvc.service.*.*(..))")
    private void forServicePackage(){

    }

    @Pointcut("execution(* com.zlab.dzuko.mvc.dao.*.*(..))")
    private void forDaoPackage(){

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){

    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        //display method we are calling
        String method = joinPoint.getSignature().toLongString();
        logger.info("===>> in @Before: calling method:" +method);


        Object[] args = joinPoint.getArgs();

        for(Object arg: args){

            logger.info("===>> argument: " +arg);
        }
    }


    //add @AfterReturing Advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        //display method we are calling
        String method = joinPoint.getSignature().toLongString();
        logger.info("===>> in @AfterReturning: calling method:" +method);

        //display returned result
        logger.info("===>> return result: " +result);
    }
}
