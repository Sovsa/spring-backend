package com.crowdcollective.spring_backend.dto.response;

import com.crowdcollective.spring_backend.dao.Ingredient;

public record IngredientResponseDTO(Integer id,
                                    Float amount,
                                    String unit,
                                    Integer produceId) {
    
    public IngredientResponseDTO(Ingredient ingredient) {
        this(ingredient.getIngredientId(),
        ingredient.getAmount(),
        ingredient.getUnit(),
        ingredient.getProduceid());
    }
}
