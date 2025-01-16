package com.crowdcollective.spring_backend.dto.response;

import java.util.List;

import com.crowdcollective.spring_backend.dao.Recipe;

public record RecipeResponseDTO(Integer recipeId,
                                String name,
                                String description,
                                List<IngredientResponseDTO> ingredients,
                                List<InstructionResponseDTO> instructions) {

    public RecipeResponseDTO(Recipe recipe,
                             List<IngredientResponseDTO> ingredients,
                             List<InstructionResponseDTO> instructions) {
        this(recipe.getRecipeid(),
        recipe.getName(),
        recipe.getDescription(),
        ingredients,
        instructions);
    }

}
