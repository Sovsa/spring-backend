package com.crowdcollective.spring_backend.dto.request;

import java.util.List;

public record ProduceRequestDTO(String name,
                                String description,
                                boolean allergen,
                                List<String> diets) {

}
