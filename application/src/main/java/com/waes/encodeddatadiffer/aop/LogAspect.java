package com.waes.encodeddatadiffer.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Before("controller()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering in Method :  " + joinPoint.getSignature().getName());
        log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
    }

}
