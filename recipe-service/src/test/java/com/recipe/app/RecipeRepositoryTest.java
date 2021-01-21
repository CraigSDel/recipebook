package com.recipe.app;

import com.recipe.app.recipe.Recipe;
import com.recipe.app.recipe.RecipeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Alice");
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Bob");
        //save recipes, verify has ID value after save
        assertNull(recipe1.getId());
        assertNull(recipe2.getId());//null before save
        this.recipeRepository.save(recipe1);
        this.recipeRepository.save(recipe2);
        assertNotNull(recipe1.getId());
        assertNotNull(recipe2.getId());
    }

    @Test
    public void testFetchData() {
        /*Test data retrieval*/
        Recipe recipe = recipeRepository.findByDescription("Bob");
        assertNotNull(recipe);
        /*Get all recipes, list should only have two*/
        Iterable recipes = recipeRepository.findAll();
        int count = 0;
        for (Object p : recipes) {
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testDataUpdate() {
        /*Test update*/
        Recipe recipeB = recipeRepository.findByDescription("Bob");
        recipeRepository.save(recipeB);
        Recipe recipeC = recipeRepository.findByDescription("Bob");
        assertNotNull(recipeC);
    }

    @AfterEach
    public void tearDown() {
        this.recipeRepository.deleteAll();
    }
}
