package com.stackroute.userservice.test.controller;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.userservice.controller.UserController;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
   

    @MockBean
     UserService userservice;
    
    @InjectMocks    
    UserController usercontroller;
    
    List<User> userlist=null;
	Optional<User> options;
	
	private User user;
	
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
	    public void addUserSuccess() throws Exception {
	    	when(userservice.saveUser(user)).thenReturn(true);
	    	System.out.println(user);
	    	
			mockMvc.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
			.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());


	    }

	  @Test
	    public void findUserSuccess() throws Exception {

	        when(userservice.findByUserIdAndPassword("kalai@gmail.com", "password")).thenReturn(user);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/finduser/kalai@gmail.com/password")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	  
	  

	  
	  private static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
