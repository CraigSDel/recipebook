package com.recipe.app.recipe;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Recipe findByDescription(String name);
}
