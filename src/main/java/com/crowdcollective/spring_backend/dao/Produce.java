package com.crowdcollective.spring_backend.dao;

import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Produce")
@Table(name = "produce")
public class Produce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produceid", unique = true, nullable = false)
    private Integer produceId;
    private String name;
    private String description;
    private boolean allergen;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Diet.class)
    @Fetch(value = FetchMode.JOIN)
    private Set<Diet> diets = new HashSet<>();

    public Integer getProduceId() {
        return produceId;
    }
    public void setProduceId(Integer produceId) {
        this.produceId = produceId;
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
    public boolean isAllergen() {
        return allergen;
    }
    public void setAllergen(boolean allergen) {
        this.allergen = allergen;
    }
    public Set<Diet> getDiets() {
        return diets;
    }
    public void setDiets(Set<Diet> diet) {
        this.diets = diet;
    }
}
