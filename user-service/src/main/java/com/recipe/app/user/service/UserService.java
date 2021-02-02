package com.recipe.app.user.service;

import com.recipe.app.user.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getById(String id);
}
