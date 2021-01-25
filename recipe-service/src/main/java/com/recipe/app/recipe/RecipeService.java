package com.recipe.app.recipe;

import java.util.Optional;

public interface RecipeService {
    Optional<Recipe> getById(String id);
}
