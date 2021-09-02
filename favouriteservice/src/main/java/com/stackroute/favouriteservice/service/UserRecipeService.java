package com.stackroute.favouriteservice.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.model.User;

import com.stackroute.favouriteservice.util.exception.RecipeAlreadyExists;
import com.stackroute.favouriteservice.util.exception.RecipeNotFoundException;


public interface UserRecipeService {

	boolean addUser(Recipe recipe) throws RecipeAlreadyExists;

	boolean deleteRecipe(String userId, String recipeId) throws RecipeNotFoundException;

	boolean deleteAllRecipe(String userId) throws RecipeNotFoundException;	

	Recipe getRecipeByrecipeId(String userId, String recipeId) throws RecipeNotFoundException;

	List<Recipe> getAllRecipeByUserId(String userId);
	
}
