package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectConfig {

    private Logger log =  LoggerFactory.getLogger(AspectConfig.class);



    @Before(value = "execution(* com.cognizant.carrentalservice_20220831.carrentalservice_20220831.controllers.*.*(..))")
    public void logStatementBefore(JoinPoint joinPoint){
        log.info("Executing before  {}", joinPoint.getSignature().getName());

    }

    @After(value = "execution(* com.cognizant.carrentalservice_20220831.carrentalservice_20220831.controllers.*.*(..))")
    public void logStatementAfter(JoinPoint joinPoint){
        log.info("Executing after {}", joinPoint.getSignature().getDeclaringTypeName());
    }

}

