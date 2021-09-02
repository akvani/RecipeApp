package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.repo.RecipeRepository;
import com.stackroute.favouriteservice.util.exception.RecipeAlreadyExists;
import com.stackroute.favouriteservice.util.exception.RecipeNotFoundException;

@Service
public class RecipeserviceImpl implements Recipeservice {
	
@Autowired
RecipeRepository reciperepo;

public RecipeserviceImpl(RecipeRepository reciperepo)  {
	this.reciperepo=reciperepo;
}





@Override
public boolean addRecipe(Recipe recipe) throws RecipeAlreadyExists {
	boolean result=false;
	try {
		Optional<Recipe> Existingrecipe=reciperepo.findById(recipe.getId());
		if(Existingrecipe.isPresent()) {
			
			throw new RecipeAlreadyExists("Recipe is already in your favourite list");
			
		}else {
			reciperepo.save(recipe);
			result= true;
			
		}
		
	}catch (Exception ne) {
		System.out.println("exception");
		return false;
	}
	return result;
	
}

@Override
public boolean deleteRecipe(String id) throws RecipeNotFoundException {
	boolean result=false;
	try {
		Optional<Recipe> Existingrecipe=reciperepo.findById(id);
		if(Existingrecipe.isPresent()) {
			reciperepo.delete(Existingrecipe.get());
			result= true;
		
			
		}else {
			
			throw new RecipeNotFoundException("Recipe is already in your favourite list");			
			
			
		}
		
	}catch (Exception ne) {
		System.out.println("exception");
		return false;
	}
	return result;
}

@Override
public List<Recipe> getAllrecipe() {
	List<Recipe> recipeList=reciperepo.findAll();
	return recipeList;
	
}

	
	

}
