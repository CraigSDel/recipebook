package com.recipe.app.recipe;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ingredients")
@Data
public class Ingredient {

    @Id
    private String id;
    private String description;
}
