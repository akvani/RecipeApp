package com.stackroute.favouriteservice.test.controller;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;



import com.stackroute.favouriteservice.controller.RecipeController;
import com.stackroute.favouriteservice.model.Ingredients;
import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.service.UserRecipeService;
import com.stackroute.favouriteservice.service.UserRecipeServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RecipeControllerTest {

	
	@Autowired
    private MockMvc mockMvc;
   

    @MockBean
     UserRecipeService userservice;
    
    @InjectMocks    
    RecipeController recipecont;
    
   private List<Recipe> recipelist=null;
   private ArrayList<Ingredients> Ingarray=null;
   
   private Recipe recipe;
	private User user;
	private Ingredients ingredient;
    
    
    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipecont).build();
        
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
        
      
        
    }
	
    @Test
    public void addUserSuccess() throws Exception {
    	when(userservice.addUser(recipe)).thenReturn(true);
    	System.out.println(recipe);
    	
		mockMvc.perform(post("/api/v1/recipe/addrecipe").contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipe)))
		.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());


    }
    
    @Test
    public void addUserFailure() throws Exception {
        when(userservice.addUser(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/recipe/addrecipe").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipe)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }
    
    @Test
    public void deleteUserSuccess() throws Exception {

        when(userservice.deleteRecipe("kalai@gmail.com", recipe.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/recipe/deleterecipe/kalai@gmail.com/12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteUserFailure() throws Exception {

        when(userservice.deleteRecipe("kalai@gmail.com", recipe.getId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/recipe/deleterecipe/kalai@gmail.com/12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
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