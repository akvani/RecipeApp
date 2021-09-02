package com.stackroute.favouriteservice.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.favouriteservice.model.Ingredients;
import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.repo.UserRepository;
import com.stackroute.favouriteservice.service.UserRecipeServiceImpl;
import com.stackroute.favouriteservice.util.exception.RecipeAlreadyExists;
import com.stackroute.favouriteservice.util.exception.RecipeNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class UserRecipeServiceTest {
	
	private Recipe recipe;
	private User user;
	private Ingredients ingredient;
	
	@Mock
	private UserRepository userrepo;
	
	@InjectMocks
	private UserRecipeServiceImpl userserv;
	
	private List<Recipe> recipelist=null;
	   private ArrayList<Ingredients> Ingarray=null;
	   Optional<User> options;
	   
	   @BeforeEach
	    public void setUp() throws Exception {

	        MockitoAnnotations.initMocks(this);
	     
	        
	        user = new User();
	        recipe = new  Recipe();
	        ingredient= new Ingredients();
	       
	       // Ingarray=new ArrayList<Ingredients>();
	        ArrayList<Ingredients> Ingarray = new ArrayList<>();
	        List<Recipe> recipelist=new ArrayList();
	       
	        ingredient.setId("12345");
	       ingredient.setAmount("200");
	       ingredient.setName("Mango");
	       ingredient.setUnit("g");
	        
	       Ingarray.add(ingredient);
	        
	        recipe.setId("12345");
	       recipe.setImage("https://spoonacular.com/recipeImages/653149-312x231.jpg");
	        recipe.setTitle("No Cook Cranberry Orange Relish");
	        recipe.setUrl("https://www.foodista.com/recipe/YBWGGS56/no-cook-cranberry-orange-relish");
	        recipe.setUserid("kalai@gmail.com");
	        recipe.setIngredientArray(Ingarray);
	        
	        recipelist.add(recipe);
	        
	        user.setUserid("kalai@gmail.com");
	        user.setRecipe(recipelist);
	        
	        options = Optional.of(user);
	       
	    }
	   
	   @Test
	    public void addRecipeSuccess() throws RecipeAlreadyExists {
	        when(userrepo.save((user))).thenReturn(user);
	        boolean status = userserv.addUser(recipe);
	        assertEquals(true, status);
	    }
	   
	   @Test
	    public void addRecipeFailure() throws RecipeAlreadyExists {
	        when(userrepo.save((null))).thenReturn(null);
	        boolean status = userserv.addUser(null);
	        assertEquals(false, status);
	    }
	
	   @Test
	    public void deleteRecipeSuccess() throws RecipeNotFoundException {
	        when(userrepo.findById(recipe.getUserid())).thenReturn(options);
//	        when(userrepo.delete(user)).thenReturn(user);
	        boolean flag = userserv.deleteRecipe("kalai@gmail.com", recipe.getId());
	        assertEquals(true, flag);
	    }

	   @Test
	    public void deleteRecipeFailure() throws RecipeNotFoundException {
	        when(userrepo.findById(recipe.getUserid())).thenReturn(options);
//	        when(userrepo.delete(user)).thenReturn(user);
	        boolean flag = userserv.deleteRecipe("kalai", recipe.getId());
	        
	     assertEquals(false, flag);
	    }
	  
	   @Test
	    public void deleteAllRecipeSuccess() throws RecipeNotFoundException {

	        when(userrepo.findById("kalai@gmail.com")).thenReturn(options);
	        when(userrepo.save(user)).thenReturn(user);
	        boolean flag = userserv.deleteAllRecipe("kalai@gmail.com");
	        assertEquals(true, flag);

	    }

	    

	    @Test
	    public void deleteAllRecipeFailure() throws RecipeNotFoundException {
	        when(userrepo.findById("kalai@gmail.com")).thenThrow(NoSuchElementException.class);
	        
	        assertThrows(
	        		RecipeNotFoundException.class,
	                    () -> { userserv.deleteAllRecipe("kalai@gmail.com"); });
	       
	    }
	   
}
