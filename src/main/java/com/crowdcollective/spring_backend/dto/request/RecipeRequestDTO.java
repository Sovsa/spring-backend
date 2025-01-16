package com.crowdcollective.spring_backend.dto.request;

import java.util.List;

public record RecipeRequestDTO(Integer recipeId,
                               String name,
                               String description,
                               List<InstructionRequestDTO> instructions,
                               List<IngredientRequestDTO> ingredients) {

}
