package com.crowdcollective.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowdcollective.spring_backend.dao.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
    
}
