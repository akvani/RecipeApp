package com.stackroute.favouriteservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.Recipe;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
	
}
