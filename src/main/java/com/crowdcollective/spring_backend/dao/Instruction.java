package com.crowdcollective.spring_backend.dao;

import com.crowdcollective.spring_backend.dto.request.InstructionRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Instruction")
@Table(name = "instruction")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructionid", unique = true, nullable = false)
    private Integer instructionId;
    @Column(name = "instructiontext")
    private String instructionText;
    @Column(name = "orderindex")
    private Integer orderIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeid")
    private Recipe recipe;
    @Column(name = "recipeid", insertable=false, updatable=false)
    private Integer recipeid;
    
    public Instruction(InstructionRequestDTO instructionRequestDTO) {
        if (instructionRequestDTO.id() != null) {
            this.instructionId = instructionRequestDTO.id();
        }
        this.instructionText = instructionRequestDTO.instructionText();
        this.orderIndex = instructionRequestDTO.index();
    }

    public Instruction() {}

    public Integer getInstructionId() {
        return instructionId;
    }
    public void setInstructionId(Integer id) {
        this.instructionId = id;
    }
    public String getInstructionText() {
        return instructionText;
    }
    public void setInstructionText(String instructionText) {
        this.instructionText = instructionText;
    }
    public Integer getOrderIndex() {
        return orderIndex;
    }
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
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
    
}
