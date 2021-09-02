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

//@RestController
@CrossOrigin
//@RequestMapping("/api/v1/recipe")
public class RecipeController1 {
	@Autowired
	Recipeservice respserv;
	
	public RecipeController1(Recipeservice respserv) {
		this.respserv=respserv;
	}
	
	
	@PostMapping("/addrecipe")
	public ResponseEntity<?> addrecipe(@RequestBody Recipe recipe){
		try {
			boolean result=respserv.addRecipe(recipe);
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
	
	@DeleteMapping("/deleterecipe/{recipeid}")
	public ResponseEntity<?> deleterecipe(@PathVariable("recipeid") String rid){
		try {
			boolean result=respserv.deleteRecipe(rid);
			if(result) {
				return new ResponseEntity<String>("Successfully deleted the recipe",HttpStatus.CREATED);
			}else
			{
				return new ResponseEntity<String>("Recipe not deleted",HttpStatus.CONFLICT);
			}
			
			
		}catch(Exception e)
		{
			return new ResponseEntity<String>("Recipe not deleted",HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/getallrecipe")
	public ResponseEntity<?> getAllRecipe(){
		List<Recipe> recipeList=respserv.getAllrecipe();
		return new ResponseEntity<List<Recipe>>(recipeList,HttpStatus.OK);
	}

}
