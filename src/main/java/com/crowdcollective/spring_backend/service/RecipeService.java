package com.crowdcollective.spring_backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdcollective.exception.NotFoundException;
import com.crowdcollective.spring_backend.dao.Ingredient;
import com.crowdcollective.spring_backend.dao.Instruction;
import com.crowdcollective.spring_backend.dao.Produce;
import com.crowdcollective.spring_backend.dao.Recipe;
import com.crowdcollective.spring_backend.dto.request.IngredientRequestDTO;
import com.crowdcollective.spring_backend.dto.request.InstructionRequestDTO;
import com.crowdcollective.spring_backend.dto.request.RecipeRequestDTO;
import com.crowdcollective.spring_backend.dto.response.IngredientResponseDTO;
import com.crowdcollective.spring_backend.dto.response.InstructionResponseDTO;
import com.crowdcollective.spring_backend.dto.response.RecipeResponseDTO;
import com.crowdcollective.spring_backend.repository.RecipeRepository;
import com.crowdcollective.spring_backend.repository.Ingredient.IngredientRepository;
import com.crowdcollective.spring_backend.repository.instruction.InstructionRepository;
import com.crowdcollective.spring_backend.repository.produce.ProduceRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private InstructionRepository instructionRepository;
    @Autowired
    private ProduceRepository produceRepository;

    @Transactional
    public Recipe saveOrUpdateRecipe(RecipeRequestDTO recipeRequestDTO) throws NotFoundException {
        Recipe recipe = null;
        if(recipeRequestDTO.recipeId() != null && recipeRequestDTO.recipeId() > 0) {
            recipe = recipeRepository.findById(recipeRequestDTO.recipeId()).orElse(null);
            if (recipe == null) {
                throw new NotFoundException("Could not find recipe");
            }
            recipe.setName(recipeRequestDTO.name());
            recipe.setDescription(recipeRequestDTO.description());
            
        } else {
            recipe = new Recipe(recipeRequestDTO);
            recipeRepository.save(recipe);
        }

        saveInstructions(recipeRequestDTO.instructions(), recipe);
        saveIngredients(recipeRequestDTO.ingredients(), recipe);
        
        return recipe;
    }

    private Set<Ingredient> saveIngredients(List<IngredientRequestDTO> ingredientRequests, Recipe recipe) {
        Set<Ingredient> ingredients = new HashSet<>();
        List<Integer> produceIds = ingredientRequests.stream().map(e -> e.produceId()).collect(Collectors.toList());
        Map<Integer, Produce> produceMap = produceRepository.findAllById(produceIds).stream().collect(Collectors.toMap(e -> e.getProduceId(), e -> e));

        for (IngredientRequestDTO ingredientRequest : ingredientRequests) {
            Ingredient ingredient = new Ingredient(ingredientRequest);
            Produce produce = produceMap.get(ingredientRequest.produceId());
            if (produce == null) {
                throw new NotFoundException("Could not find produce");
            }
            ingredient.setProduce(produce);
            ingredient.setProduceid(produce.getProduceId());
            ingredient.setRecipe(recipe);
            ingredients.add(ingredient);
        }

        recipe.removeIngredients();
        List<Ingredient> saveAll = ingredientRepository.saveAll(ingredients);
        recipe.getIngredients().addAll(saveAll);

        return ingredients;
    }

    private Set<Instruction> saveInstructions(List<InstructionRequestDTO> instructionRequests, Recipe recipe) {
        Set<Instruction> instructions = new HashSet<>();

        for (InstructionRequestDTO instructionRequest : instructionRequests) {
            Instruction instruction = new Instruction(instructionRequest);
            instruction.setRecipe(recipe);
            instructions.add(instruction);
        }
        
        recipe.removeInstructions();
        List<Instruction> saveAll = instructionRepository.saveAll(instructions);
        recipe.getInstructions().addAll(saveAll);
        
        return instructions;
    }

    public RecipeResponseDTO getRecipeById(Integer recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe ==  null) {
            return null;
        }
        RecipeResponseDTO responseDTO = convertRecipeToRecipeResponseDTO(recipe);

        return responseDTO;
    }

    public List<RecipeResponseDTO> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(result::add);

        List<RecipeResponseDTO> recipes = result.stream().map(e -> convertRecipeToRecipeResponseDTO(e)).collect(Collectors.toList());

        return recipes;
    }

    public RecipeResponseDTO convertRecipeToRecipeResponseDTO(Recipe recipe) {
        List<IngredientResponseDTO> ingredientResponse = recipe.getIngredients().stream().map(e -> new IngredientResponseDTO(e)).collect(Collectors.toList());
        List<InstructionResponseDTO> instructionResponse = recipe.getInstructions().stream().map(e -> new InstructionResponseDTO(e)).collect(Collectors.toList());
        RecipeResponseDTO responseDTO = new RecipeResponseDTO(recipe, ingredientResponse, instructionResponse);

        return responseDTO;
    }
}
