package com.stackroute.userservice.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepo;
	public UserServiceImpl(UserRepository userrepo) {
		this.userrepo=userrepo;
	}
	
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		 System.out.println("servuce"+userId+password);
		try {
			User user=userrepo.findByUserIdAndPassword(userId, password);
			
					
			if(user!=null) {
//				
//				System.out.println(user.getPassword()+"-"+password);
//				if(user.getPassword()==password) {
//					System.out.println("password match");
//					
					return user;
//				}else
//				{
//					System.out.println("password Not matching");
//					return null;
//				}
				
    			
    		}
    		else {
    			System.out.println("user not found");
    			return null;
    		}
    		
    	}catch(NoSuchElementException  e)
    	{
    		throw new UserNotFoundException ("No such element exists");
    	}
	
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		boolean result=false;
		try {
			Optional<User> newuser=userrepo.findById(user.getUserId());
			if(newuser.isPresent()) {
				throw new UserAlreadyExistsException("User already exists");
				
			}
			else {
				userrepo.save(user);
				result=true;
			}
		}catch (Exception ne) {
			System.out.println("exception");
			return false;
		}				
		
		return result;
	}

	
	

}
