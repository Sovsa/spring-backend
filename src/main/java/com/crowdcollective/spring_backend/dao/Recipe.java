package com.crowdcollective.spring_backend.dao;

import java.util.Set;

import com.crowdcollective.spring_backend.dto.request.RecipeRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeid", unique = true, nullable = false)
    private Integer recipeid;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "recipeid")
    private Set<Instruction> instructions;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "recipeid")
    private Set<Ingredient> ingredients;
    
    public Recipe(RecipeRequestDTO recipeRequestDTO) {
        if (recipeRequestDTO.recipeId() != null) {
            this.setRecipeid(recipeRequestDTO.recipeId());
        }
        this.setName(recipeRequestDTO.name());
        this.setDescription(recipeRequestDTO.description());
    }

    public Recipe() {
    }

    public Integer getRecipeid() {
        return recipeid;
    }
    public void setRecipeid(Integer id) {
        this.recipeid = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Instruction> getInstructions() {
        return instructions;
    }
    public void setInstructions(Set<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
