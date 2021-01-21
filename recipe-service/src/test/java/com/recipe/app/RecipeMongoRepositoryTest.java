package com.recipe.app;

import com.recipe.app.recipe.Recipe;
import com.recipe.app.recipe.RecipeMongoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecipeMongoRepositoryTest {

    @Autowired
    private RecipeMongoRepository recipeMongoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Alice");
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Bob");
        //save product, verify has ID value after save
        assertNull(recipe1.getId());
        assertNull(recipe2.getId());//null before save
        this.recipeMongoRepository.save(recipe1);
        this.recipeMongoRepository.save(recipe2);
        assertNotNull(recipe1.getId());
        assertNotNull(recipe2.getId());
    }

    @Test
    public void testFetchData() {
        /*Test data retrieval*/
        Recipe userA = recipeMongoRepository.findByDescription("Bob");
        assertNotNull(userA);
        /*Get all products, list should only have two*/
        Iterable users = recipeMongoRepository.findAll();
        int count = 0;
        for (Object p : users) {
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testDataUpdate() {
        /*Test update*/
        Recipe userB = recipeMongoRepository.findByDescription("Bob");
        recipeMongoRepository.save(userB);
        Recipe userC = recipeMongoRepository.findByDescription("Bob");
        assertNotNull(userC);
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.recipeMongoRepository.deleteAll();
    }
}
