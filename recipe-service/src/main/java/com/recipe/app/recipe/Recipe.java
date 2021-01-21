package com.recipe.app.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    @Id
    private String id;
    private String description;

}
