package com.t3h.projectclothes.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  /*
   Pointcut áp dụng cho các class có @Service, @Repository, @RestController
  */
  @Pointcut("within(@org.springframework.stereotype.Service *)"
      + " || within(@org.springframework.stereotype.Repository *)"
      + " || within(@org.springframework.web.bind.annotation.RestController *)")
  public void springBeanPointcut() {
  }

  /*
   Pointcut áp dụng cho các package chính
  */
  @Pointcut(
      "within(com.t3h.projectclothes.service..*)" + " || within(com.t3h.projectclothes.repository..*)"
          + " || within(com.t3h.projectclothes.controller..*)")
  public void applicationPackagePointcut() {
  }

  /*
    Trả về log
  */
  private Logger logger(JoinPoint joinPoint) {
    return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
  }

  /*
    Log exception khi có lỗi
  */
  @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    Logger log = logger(joinPoint);
    String cause = (e.getCause() != null) ? e.getCause().toString() : "NULL";
    log.error("Error in {}() - Cause: '{}' - Message: '{}'", joinPoint.getSignature().getName(),
        cause, e.getMessage(), e);
  }

  /*
    Log khi method start and finish ( execution time )
  */
  @Around("applicationPackagePointcut() && springBeanPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Logger log = logger(joinPoint);
    long start = System.currentTimeMillis();

    if (log.isInfoEnabled()) {
      log.info("Start: {}() with parameter: {}", joinPoint.getSignature().getName(),
          Arrays.toString(joinPoint.getArgs()));
    }

    try {
      Object result = joinPoint.proceed();
      long elapsed = System.currentTimeMillis() - start;

      if (log.isInfoEnabled()) {
        log.info("Finish: {}() with result: {} (time execution: {}ms)",
            joinPoint.getSignature().getName(), result, elapsed);
      }

      return result;
    } catch (IllegalArgumentException e) {
      log.error("Invalid parameter: {} in {}()", Arrays.toString(joinPoint.getArgs()),
          joinPoint.getSignature().getName());
      throw e;
    }
  }
}
