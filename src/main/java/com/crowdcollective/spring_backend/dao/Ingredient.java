package com.crowdcollective.spring_backend.dao;

import com.crowdcollective.spring_backend.dto.request.IngredientRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Ingredient")
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredientid", unique = true, nullable = false)
    private Integer ingredientId;
    private Float amount;
    private String unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produceid")
    private Produce produce;
    @Column(name = "produceid", insertable=false, updatable=false, nullable = false)
    private Integer produceid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeid")
    private Recipe recipe;
    @Column(name = "recipeid", insertable=false, updatable=false)
    private Integer recipeid;

    public Ingredient(IngredientRequestDTO ingredientRequestDTO) {
        if (ingredientRequestDTO.id() != null) {
            this.ingredientId = ingredientRequestDTO.id();
        }
        this.amount = ingredientRequestDTO.amount();
        this.unit = ingredientRequestDTO.unit();
    }

    public Ingredient() {}

    public Integer getIngredientId() {
        return ingredientId;
    }
    public void setIngredientId(Integer id) {
        this.ingredientId = id;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public Produce getProduce() {
        return produce;
    }

    public void setProduce(Produce produce) {
        this.produce = produce;
    }

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceId) {
        this.produceid = produceId;
    }
    
}
