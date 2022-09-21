package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * @author igorg 
 * @date 21.07.2022
 */
public interface IngredientService {

  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

  void deleteById(Long recipeId, Long ingredientId);
}
