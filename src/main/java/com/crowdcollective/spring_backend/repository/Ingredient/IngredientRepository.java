package com.crowdcollective.spring_backend.repository.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowdcollective.spring_backend.dao.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>, IngredientRepositoryWrapper  {

}
