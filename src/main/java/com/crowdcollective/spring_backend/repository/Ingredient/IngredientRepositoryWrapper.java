package com.crowdcollective.spring_backend.repository.Ingredient;

import java.util.List;

public interface IngredientRepositoryWrapper {
    void delete(Integer id);
    void delete(List<Integer> ids);
}
