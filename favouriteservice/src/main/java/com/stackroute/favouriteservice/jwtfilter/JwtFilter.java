package com.stackroute.favouriteservice.jwtfilter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.models.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.  
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */

public class JwtFilter extends GenericFilterBean {

		/*
	 * Override the doFilter method of GenericFilterBean.
     * Retrieve the "authorization" header from the HttpServletRequest object.
     * Retrieve the "Bearer" token from "authorization" header.
     * If authorization header is invalid, throw Exception with message. 
     * Parse the JWT token and get claims from the token using the secret key
     * Set the request attribute with the retrieved claims
     * Call FilterChain object's doFilter() method */
	
	
	 @Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
	    	
	        	HttpServletRequest 	httprequest=(HttpServletRequest) request;
	        	HttpServletResponse httpresponse=(HttpServletResponse) response;
	        	
	        	httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("Origin"));
	        	httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS");
	        	httpresponse.setHeader("Access-Control-Allow-Credential", "true");
	        	httpresponse.setHeader("Access-Control-Allow-Headers", "*");
	        	//httpresponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
	        	//
	        	String authheader=httprequest.getHeader("Authorization");
	        	
	        	System.out.println(authheader);
	        	if(httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
	        	{
	                 chain.doFilter(httprequest, httpresponse);
	        	}
	            else
	            {
	        	
	        	if((authheader==null) || (!authheader.startsWith("Bearer")))
	        	throw new ServletException("JWT Token is missing");
	        	
	        	String mytoken=authheader.substring(7);
	        	
	        	try
	        	{
	        		JwtParser jparser=Jwts.parser().setSigningKey("fsdkey");
	        	Jwt jwtobj=jparser.parse(mytoken);
	        	Claims claim=(Claims)jwtobj.getBody();
	        	System.out.println("User logged in is " + claim.getSubject());
	        	
	        	
	        	}
	        	catch(SignatureException e)
	        	{
	        		throw new ServletException("signature mismatch");
	        	//	System.out.println("Signature error " + e);
	        	}
	        		
	        	catch(MalformedJwtException e)
	        	{
	        		throw new ServletException("malformed mismatch");
	        		
	        		//System.out.println("invalid tokdn error " + e);
	        	}
	        	
	        	chain.doFilter(httprequest, httpresponse);
	        	}
    	
	 }
	}



