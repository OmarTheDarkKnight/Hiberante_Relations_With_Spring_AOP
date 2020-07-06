package com.bat.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingUsingAspect {
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
}
