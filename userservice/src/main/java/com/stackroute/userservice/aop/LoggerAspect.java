package com.stackroute.userservice.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.stackroute.userservice.model.User;

@Aspect
@Component
public class LoggerAspect {
	Logger userlogger=LoggerFactory.getLogger(LoggerAspect.class);
	
	@Before("execution (* com.stackroute.userservice.controller.UserController.saveUser(..))")
	public void showdata(JoinPoint  jp)
	{
		userlogger.info("User Added to the database");
	}
	
	
	@Before("userlogin()")
	public void userlogin(JoinPoint jp)
	{
		userlogger.info("User will logged in if user ID and Password matches");
	}
	@After("userlogin()")
	public void afterlogin(JoinPoint jp)
	{
		userlogger.info("User is successfully logged in");
	}
	
	@Around("userlogin()")
	public Object aroundlogin(ProceedingJoinPoint proceedobj) throws Throwable
	{
		
       ResponseEntity<?> obj=(ResponseEntity<?>) proceedobj.proceed();		
     
     	ResponseEntity<?> resentity=obj;
     	try
     	{
    		User user=(User)resentity.getBody();
    		userlogger.info("client loged in with named " + user.getFirstname() + " of id " + user.getUserId() );
     		
     	}
		catch(Exception e)
     	{
			userlogger.warn("Login call is not success check user ID and password. Excepiton raised" + e.getMessage());
     	}
     	
     	return obj;
		
	}
	
	@AfterThrowing(pointcut="userlogin()",throwing="exceptobj")
	public void updateexceptionhandler(Exception exceptobj)
	{
		userlogger.warn("Exception raised while login" + exceptobj);
	}
	
	
	@Pointcut("execution (* com.stackroute.userservice.controller.UserController.userLogin(..))")
	public void userlogin()
	{
		
	}

}
