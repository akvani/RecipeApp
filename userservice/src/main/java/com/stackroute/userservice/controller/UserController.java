package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class UserController {
	@Autowired
	UserService userv;
	
	public UserController(UserService userv) {
		this.userv=userv;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> testing(){
		return new ResponseEntity<String>("User Not created",HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user)
	{
		try {
			Boolean result=userv.saveUser(user);
			if(result) {
				return new ResponseEntity<User>(user,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("User Not created",HttpStatus.CONFLICT);
			}
			
		}catch(UserAlreadyExistsException e) {
			{
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
			}
		}
		
	}
	

	 @GetMapping("/finduser/{email}/{password}")
	public ResponseEntity<?> userLogin(@PathVariable("email") String userId, @PathVariable("password") String password)
	{
		 System.out.println("controller"+userId+password);
		try {
			User Existinguser=userv.findByUserIdAndPassword(userId, password);
		//	System.out.println(Existinguser.getPassword());
			if(Existinguser==null)
			{
				return new ResponseEntity<String>("Invalid user",HttpStatus.UNAUTHORIZED);
			}
			else {
				String tokendata=generateToken(Existinguser);   
				   HashMap hashmap = new HashMap();
				   hashmap.put("token",tokendata);
				   
				   return new ResponseEntity<HashMap>(hashmap,HttpStatus.OK);
			}
			
		}catch(UserNotFoundException e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	public String generateToken(User user)
    {
 	   
 	   long duration=90_000_00_00;
 	  return Jwts.builder().setSubject(user.getUserId())
 			  	           .setIssuedAt(new Date(System.currentTimeMillis()))
 			  	           .setExpiration(new Date(System.currentTimeMillis()+duration))
 			  	           .signWith(SignatureAlgorithm.HS256, "fsdkey")
 			  	           .compact();
 	   
    }

}
