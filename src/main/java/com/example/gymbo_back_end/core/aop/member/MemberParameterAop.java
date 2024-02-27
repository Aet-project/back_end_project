package com.example.gymbo_back_end.core.aop.member;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MemberParameterAop {

    @Pointcut("execution(* com.example.gymbo_back_end.member.controller..*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void beFore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object obj : args) {
            log.info("type : {}",obj.getClass().getSimpleName());
            log.info("value: {}",obj);
        }
    }

    @AfterReturning(value = "pointCut()",returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {

        log.info("return obj : {}",returnObj);
    }
}
