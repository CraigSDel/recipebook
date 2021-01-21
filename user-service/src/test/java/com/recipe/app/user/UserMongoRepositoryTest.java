package com.recipe.app.user;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMongoRepositoryTest {

    private final String username1 = "user1_username";
    private final String username2 = "user2_username";
    private final String email1 = "user1@123.com";
    private final String email2 = "user2@123.com";

    @Autowired
    private UserMongoRepository userMongoRepository;

    @BeforeEach
    public void setUp() {
        User user1 = new User(username1, email1);
        User user2 = new User(username2, email2);
        //save product, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save
        this.userMongoRepository.save(user1);
        this.userMongoRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    public void testFetchData() {
        // test data retrieval
        User userA = userMongoRepository.findByUsername(username1);
        assertNotNull(userA);
        assertEquals(email1, userA.getEmail());

        // get all entries, expecting 2
        Iterable users = userMongoRepository.findAll();
        int count = 0;
        for (Object currUser : users) { //TODO: this should be User not Object
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void testDataUpdate() {
        // test update
        String newEmail = "new_user2@123.com";
        User userB = userMongoRepository.findByUsername(username2);
        userB.setEmail(newEmail);
        userMongoRepository.save(userB);
        User userC = userMongoRepository.findByUsername(username2);
        assertEquals(newEmail, userC.getEmail());
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.userMongoRepository.deleteAll();
    }
}
