package com.recipe.app.user;


import com.recipe.app.user.model.User;
import com.recipe.app.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMongoRepositoryTest {

    private final String username1 = "user1_username";
    private final String username2 = "user2_username";
    private final String username3 = "user3_username";
    private final String email1 = "user1@123.com";
    private final String email2 = "user2@123.com";
    private final String email3 = username1;
    private final String id2 = "1234567890";

    @Autowired
    private UserRepository userMongoRepository;

    @BeforeEach
    public void setUp() {
        User user1 = new User(username1, email1);
        User user2 = new User(username2, email2);

        //save product, to verify has ID value after save. Manually set ID #2
        assertNull(user1.getId());
        assertNull(user2.getId());
        user2.setId(id2);
        this.userMongoRepository.save(user1);
        this.userMongoRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    public void testFetchData() {
        // test data retrieval
        Optional<User> user = userMongoRepository.findById(id2);
        User userB = user.get();
        assertNotNull(userB);
        assertEquals(email1, userB.getEmail());

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
        User userB = userMongoRepository.findById(id2).get();
        userB.setEmail(newEmail);
        userMongoRepository.save(userB);
        User userC = userMongoRepository.findById(id2).get();
        assertEquals(newEmail, userC.getEmail());
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.userMongoRepository.deleteAll();
    }
}
