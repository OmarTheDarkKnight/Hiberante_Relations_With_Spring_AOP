package com.bat.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingUsingAspect {
    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.bat.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.bat.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.bat.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.bat.dto.*.*(..))")
    private void forDtoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() || forDtoPackage()")
    private void forAppFlow() {}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====>> in @Before: calling method: " + theMethod);

        // display the arguments to the method

        // get the arguments
        Object[] args = theJoinPoint.getArgs();

        // loop thru and display args
        for (Object tempArg : args) {
            myLogger.info("=====>> argument: " + tempArg);
        }

    }
}
