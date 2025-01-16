package com.crowdcollective.spring_backend.dto.request;

public record IngredientRequestDTO(Integer id,
                                   Float amount,
                                   String unit,
                                   Integer produceId) {

}
