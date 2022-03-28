package com.cashinyourpocket.expenses.aspects;

import java.util.Arrays;
import java.util.List;

import com.cashinyourpocket.expenses.application.user.model.JwtRequest;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Component
public class CheckAspect {

  private static final Logger LOGGER = LogManager.getLogger(CheckAspect.class);

  @Before("execution(* com.cashinyourpocket.expenses.apirest.controller.*.*(..)) || "
      + "execution(* com.cashinyourpocket.expenses.application.service.*.*(..))")
  public void beforeController(JoinPoint joinPoint) {
    List<String> splitName = Arrays.asList(joinPoint.getTarget().getClass().getName().split("\\."));
    LOGGER.debug("Before to {}.{}",splitName.get(splitName.size()-1),joinPoint.getSignature().getName());
    Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
      try{
        LOGGER.debug("Arg: {}",new Gson().toJson(arg));
      } catch (Exception ex) {
        LOGGER.debug("Arg: ");
      }
    });
  }

  @Before("@annotation(com.cashinyourpocket.expenses.apirest.auth.CheckLogin)")
  public void beforeLogin(JoinPoint joinPoint) {
    JwtRequest authenticationRequest = (JwtRequest) joinPoint.getArgs()[0];
    LOGGER.debug("Login User: {}",authenticationRequest.getUsername());
  }

  @After("execution(* com.cashinyourpocket.expenses.apirest.controller.*.*(..)) || "
      + "execution(* com.cashinyourpocket.expenses.application.service.*.*(..))")
  public void afterController(JoinPoint joinPoint) {
    List<String> splitName = Arrays.asList(joinPoint.getTarget().getClass().getName().split("\\."));
    LOGGER.debug("Return to {}.{}",splitName.get(splitName.size()-1),joinPoint.getSignature().getName());
  }

  @AfterReturning(pointcut="execution(* com.cashinyourpocket.expenses.apirest.controller.*.*(..)) || "
      + "execution(* com.cashinyourpocket.expenses.application.service.*.*(..))", returning="retVal")
  public void logAfterReturningService(Object retVal) {
    if(retVal instanceof ResponseEntity){
      ResponseEntity responseEntity = (ResponseEntity)retVal;
      LOGGER.debug(new Gson().toJson(responseEntity.getBody()));
    } else {
      LOGGER.debug(new Gson().toJson(retVal));
    }

  }

}