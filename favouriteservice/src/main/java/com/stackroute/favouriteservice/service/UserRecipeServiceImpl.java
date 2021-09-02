package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.repo.UserRepository;
import com.stackroute.favouriteservice.util.exception.RecipeAlreadyExists;
import com.stackroute.favouriteservice.util.exception.RecipeNotFoundException;

@Service
public class UserRecipeServiceImpl implements UserRecipeService {
	
	@Autowired
UserRepository userrepo;
	public UserRecipeServiceImpl(UserRepository userrepo) {
		this.userrepo=userrepo;
	}


	@Override
	public boolean addUser(Recipe newRecipe) throws RecipeAlreadyExists
	{
		//System.out.println(newRecipe.getUserid());
		if (newRecipe==null) {
			return false;
		}
		else {
		try {
			 Optional<User> userexist= userrepo.findById(newRecipe.getUserid());
			 
			 // Existing user - Add favourites only if not present
			 if(userexist.isPresent()) {
				 List<String> userRecipes = userexist.get().getRecipe().stream().map(Recipe::getId).collect(Collectors.toList());
				 if(!userRecipes.contains(newRecipe.getId())) {
					 userexist.get().getRecipe().add(newRecipe);
					 userrepo.save(userexist.get());
					 return true;
				 }
				 
				 // Don't duplicate the recipe in user favourite list
				 return false;
			 }
			 // New User - create and add ( return always true save ( insert / update  ) 
		        else 
		        {        
		        	// User not exist... :) 
		            User user=new User();
		            user.setRecipe(new ArrayList<Recipe>());
		            user.getRecipe().add(newRecipe);
		            user.setUserid(newRecipe.getUserid());
		            userrepo.save(user);
		            
		            if(userrepo.insert(user) == null)
			            {
			            	return true;
			            }
			            else
			            {
			            	return false;
			            }
		            
		        }
			 
//	         if(userexist.isPresent()) {
//	             User Existinguser = userexist.get();
//	             List<Recipe> recipeExist=Existinguser.getRecipe();
//	               Iterator<Recipe> iterator = recipeExist.iterator();
//	                while(iterator.hasNext()) 
//	                 {
//	                     Recipe data = iterator.next();
//	                     if(newRecipe.getId().equalsIgnoreCase(data.getId())) {
//	                       // iterator.remove();
//	                        result=false;
//	                     }
//	                     else 
//	                     {
//	                         result=true;
//	                     }
//	                     
//	                 } 
//	                if(result) 
//	                {
//	                	recipeExist.add(newRecipe);
//	                	Existinguser.setRecipe(recipeExist);
//	                	//Existinguser.setUserid(recipe.getUserid());
//	                     userrepo.save(Existinguser);
//	                }
//	        }
//	        else 
//	        {        
//	            List<Recipe> recipelist = new ArrayList<>();
//	            recipelist.add(newRecipe);
//	            User user=new User();
//	            user.setUserid(newRecipe.getUserid());
//	            user.setRecipe(recipelist);
//	            userrepo.save(user);
//	            if(userrepo.insert(user) != null)
//	            {
//	            	return true;
//	            }
//	            else
//	            {
//	            	return false;
//	            }
//	        } 
		}catch (Exception e) {
				return false;
			}
		}
		}

	@Override
	public boolean deleteRecipe(String userId, String recipeId) throws RecipeNotFoundException {
		boolean result=false;
		try {
			Optional<User> userexist = userrepo.findById(userId);
			if (userexist.isPresent()) {
				User euser = userexist.get();
				List<Recipe> recipelist = euser.getRecipe();
				Iterator<Recipe> iterator = recipelist.iterator();
				//Recipe recipe = null;
				while (iterator.hasNext()) {
					Recipe item = iterator.next();

					if (item.getId().equalsIgnoreCase(recipeId )) {
						iterator.remove();
						
						result= true;
					}
				}
				 if (result) {
					 System.out.println("Saving user");
					 euser.setRecipe(recipelist);	                	
	                     userrepo.save(euser);
	                     return result;
					 
				 }
				
				// throw new NewsNotFoundException("News not found");
			} else {
				//throw new RecipeNotFoundException("Recipe not found");
				return false;
			}
			return result;

		} catch (NoSuchElementException ne) {
			//throw new RecipeNotFoundException("Recipe not found");
			return false;
		}

	}

	@Override
	public boolean deleteAllRecipe(String userId) throws RecipeNotFoundException {
		try {
			Optional<User> userexist = userrepo.findById(userId);
			if (userexist.isPresent()) {
				User euser = userexist.get();
				List<Recipe> newslist = euser.getRecipe();
				newslist.removeAll(newslist);
				return true;

			} else {
				throw new RecipeNotFoundException("Recipe not found in DeleteAll News");
			}

		} catch (NoSuchElementException ne) {
			throw new RecipeNotFoundException("Recipe not found");
		}
	}

	@Override
	public Recipe getRecipeByrecipeId(String userId, String recipeId) throws RecipeNotFoundException {
		try {
			Optional<User> userexist = userrepo.findById(userId);
			if (userexist.isPresent()) {
				User euser = userexist.get();
				List<Recipe> recipelist = euser.getRecipe();
				Iterator<Recipe> iterator = recipelist.iterator();
				Recipe recipe = null;
				while (iterator.hasNext()) {
					Recipe item = iterator.next();

					if (item.getId() == recipeId) {
						recipe = item;
						// return item;
					}
				}
				return recipe;
				// throw new NewsNotFoundException("News not found");
			} else {
				throw new RecipeNotFoundException("Recipe not found");
			}

		} catch (NoSuchElementException ne) {
			throw new RecipeNotFoundException("Recipe not found");
		}

	}

	@Override
	public List<Recipe> getAllRecipeByUserId(String userId) {
		try {
			Optional<User> userexist = userrepo.findById(userId);
			if (userexist.isPresent()) {
				User Existinguser = userexist.get();
				List<Recipe> recipelist = Existinguser.getRecipe();
				return recipelist;
			}

		} catch (NoSuchElementException ne) {
			throw new NoSuchElementException("News not found");
		}

		return null;
	}
	

}
