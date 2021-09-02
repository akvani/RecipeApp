package com.stackroute.favouriteservice.aop;

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

import com.stackroute.favouriteservice.model.Recipe;

@Aspect
@Component
public class LoggerAspect {
	
	Logger recipelogger=LoggerFactory.getLogger(LoggerAspect.class);
	
	@Before("execution (* com.stackroute.favouriteservice.controller.RecipeController.deleterecipe(..))")
	public void deleterecipe(JoinPoint  jp)
	{
		recipelogger.info("Recipe Added to the list");
	}
	
	
	@Before("adduser()")
	public void adduser(JoinPoint jp)
	{
		recipelogger.info("user will be updated");
	}
	@After("adduser()")
	public void adduserA(JoinPoint jp)
	{
		recipelogger.info("user is not present and added");
	}
	
	@Around("adduser()")
	public Object aroundadduser(ProceedingJoinPoint proceedobj) throws Throwable
	{
		
       ResponseEntity<?> obj=(ResponseEntity<?>) proceedobj.proceed();		
     
     	ResponseEntity<?> resentity=obj;
     	try
     	{
    		Recipe recipe=(Recipe)resentity.getBody();
    		recipelogger.info("client added recipe named " + recipe.getTitle() + " of id " + recipe.getId() );
     		
     	}
		catch(Exception e)
     	{
			recipelogger.warn("Updated call is not success whil adding user. Excepiton raised" + e.getMessage());
     	}
     	
     	return obj;
		
	}
	
	@AfterThrowing(pointcut="adduser()",throwing="exceptobj")
	public void adduserhandler(Exception exceptobj)
	{
		recipelogger.warn("Exception raised while adding user" + exceptobj);
	}
	
	
	@Pointcut("execution (* com.stackroute.favouriteservice.controller.RecipeController.adduser(..))")
	public void adduser()
	{
		
	}
	

}
