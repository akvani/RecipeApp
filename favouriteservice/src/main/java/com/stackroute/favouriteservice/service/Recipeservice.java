package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.util.exception.RecipeAlreadyExists;
import com.stackroute.favouriteservice.util.exception.RecipeNotFoundException;



public interface Recipeservice {
	

	boolean addRecipe(Recipe recipe) throws RecipeAlreadyExists ;

	boolean deleteRecipe(String id) throws RecipeNotFoundException ;
	
	 List<Recipe> getAllrecipe();
	


}
