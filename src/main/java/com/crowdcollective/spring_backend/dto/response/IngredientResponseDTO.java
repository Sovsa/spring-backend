package com.crowdcollective.spring_backend.dto.response;

import com.crowdcollective.spring_backend.dao.Ingredient;

public record IngredientResponseDTO(Integer id,
                                    Float amount,
                                    String unit,
                                    ProduceResponseDTO produceResponseDTO) {
    
    public IngredientResponseDTO(Ingredient ingredient) {
        this(ingredient.getIngredientId(),
        ingredient.getAmount(),
        ingredient.getUnit(),
        new ProduceResponseDTO(ingredient.getProduce()));
    }
}
