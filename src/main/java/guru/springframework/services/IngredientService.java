package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * @author igorg
 * Date 21.07.2022
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
