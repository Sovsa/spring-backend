package com.crowdcollective.spring_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdcollective.exception.NotFoundException;
import com.crowdcollective.spring_backend.dao.Recipe;
import com.crowdcollective.spring_backend.dto.ResponseHandler;
import com.crowdcollective.spring_backend.dto.request.RecipeRequestDTO;
import com.crowdcollective.spring_backend.dto.response.RecipeResponseDTO;
import com.crowdcollective.spring_backend.service.RecipeService;



@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping()
    public ResponseEntity<Object> saveOrUpdateRecipe(@RequestBody RecipeRequestDTO requestbody) {
        Recipe recipe = recipeService.saveOrUpdateRecipe(requestbody);
        RecipeResponseDTO recipeResponseDto = recipeService.convertRecipeToRecipeResponseDTO(recipe);
        
        return ResponseHandler.generateResponse("Recipe successfully created", HttpStatus.OK, recipeResponseDto);
    }
    
    @GetMapping("/{recipeId}")
    public ResponseEntity<Object> getRecipeWithId(@PathVariable(name = "recipeId") Integer recipeId) {
        RecipeResponseDTO recipe = recipeService.getRecipeById(recipeId);
        if (recipe == null) {
            throw new NotFoundException("Could not find recipe");
        }
        return ResponseHandler.generateResponse("Recipe found", HttpStatus.OK, recipe);
    }
    
    @GetMapping
    public ResponseEntity<Object> getAllRecipes() {
        List<RecipeResponseDTO> allRecipes = recipeService.getAllRecipes();

        return ResponseHandler.generateResponse(null, HttpStatus.OK, allRecipes);
    }
}
