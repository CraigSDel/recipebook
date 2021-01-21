package com.recipe.app.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String email;

}
