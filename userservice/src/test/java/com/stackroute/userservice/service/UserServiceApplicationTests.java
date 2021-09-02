package com.stackroute.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

//@RunWith(SpringRunner.class)

public class UserServiceApplicationTests {
	
	@Mock 
	UserRepository userrepo;	

	User user;
	
	@InjectMocks
	UserServiceImpl userserv;
	
	List<User> userlist=null;
	Optional<User> options;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		user=new User();
		user.setFirstname("Kalaivani");
		user.setSecondname("Anandan");
		user.setUserId("kalai@gmail.com");
		user.setPassword("password");
		
		userlist=new ArrayList<>();
		userlist.add(user);
		
		options=Optional.of(user);			
		
	}
	
	@Test
	public void registerUserSuccess() throws UserAlreadyExistsException
	{
		when(userrepo.save(user)).thenReturn(user);
		boolean result=userserv.saveUser(user);
		assertEquals(true,result);
	}
	
		@Test
    public void registerUserFailure() throws UserAlreadyExistsException {
        when(userrepo.save(user)).thenReturn(null);
        boolean result=userserv.saveUser(null);
        assertEquals(false,result);

    }
	
	
	@Test
    public void testFindByUserIdAndPassword() throws UserNotFoundException {
        Mockito.when(userrepo.findByUserIdAndPassword("kalai@gmail.com", "password")).thenReturn(user);
        User fetchedUser = userserv.findByUserIdAndPassword("kalai@gmail.com", "password");
        assertEquals("kalai@gmail.com", fetchedUser.getUserId());
    }
	

	
	

}

