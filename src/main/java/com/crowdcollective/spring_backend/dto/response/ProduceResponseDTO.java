package com.crowdcollective.spring_backend.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.crowdcollective.spring_backend.dao.Diet;
import com.crowdcollective.spring_backend.dao.Produce;

public record ProduceResponseDTO(Integer id,
                                 String name,
                                 String description,
                                 boolean allergen,
                                 List<String> diets) {

    public ProduceResponseDTO(Produce produce) {
        this(produce.getProduceId(), 
        produce.getName(), 
        produce.getDescription(), 
        produce.isAllergen(), 
        produce.getDiets().stream().map(Diet::getPrettyName).collect(Collectors.toList()));
    }
}
