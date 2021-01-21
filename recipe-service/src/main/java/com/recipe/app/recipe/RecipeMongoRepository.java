package com.recipe.app.recipe;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeMongoRepository extends MongoRepository<Recipe, String> {
    Recipe findByDescription(String name);
}
