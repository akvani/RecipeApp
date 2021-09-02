package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.model.Recipe;
import com.stackroute.favouriteservice.service.Recipeservice;
import com.stackroute.favouriteservice.service.UserRecipeService;

@RestController
//@CrossOrigin
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/recipe")
public class RecipeController {
	@Autowired
	UserRecipeService respserv;
	
	public RecipeController(UserRecipeService respserv) {
		this.respserv=respserv;
	}
	
	
	@PostMapping("/addrecipe")
	public ResponseEntity<?> adduser(@RequestBody Recipe recipe){
		try {
			boolean result=respserv.addUser(recipe);
			if(result) {
				return new ResponseEntity<String>("Successfully added the recipe",HttpStatus.CREATED);
			}else
			{
				return new ResponseEntity<String>("Recipe not added",HttpStatus.CONFLICT);
			}
			
			
		}catch(Exception e)
		{
			return new ResponseEntity<String>("Successfully not added",HttpStatus.CONFLICT);
		}
		
		
		
	}
	
	@DeleteMapping("/deleterecipe/{userid}/{recipeid}")
	public ResponseEntity<?> deleterecipe(@PathVariable("userid") String uid, @PathVariable("recipeid") String rid){
		try {
			boolean result=respserv.deleteRecipe(uid,rid);
			if(result) {
				return new ResponseEntity<String>("Successfully deleted the recipe",HttpStatus.OK);
			}else
			{
				return new ResponseEntity<String>("Recipe not deleted",HttpStatus.NOT_FOUND);
			}
			
			
		}catch(Exception e)
		{
			return new ResponseEntity<String>("Recipe not deleted",HttpStatus.CONFLICT);
		}
	}
	
	
	
	@GetMapping("/getallrecipe/{userid}")
	public ResponseEntity<?> getAllRecipe(@PathVariable("userid") String uid){
		List<Recipe> recipeList=respserv.getAllRecipeByUserId(uid);
		return new ResponseEntity<List<Recipe>>(recipeList,HttpStatus.OK);
	}

}
