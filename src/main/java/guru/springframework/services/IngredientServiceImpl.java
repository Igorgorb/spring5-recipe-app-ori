package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * @author igorg
 * @data 21.07.2022
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  //    private final IngredientRepository ingredientRepository;
  private final RecipeRepository recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  public IngredientServiceImpl(RecipeRepository recipeRepository,
    UnitOfMeasureRepository unitOfMeasureRepository,
    IngredientCommandToIngredient ingredientCommandToIngredient,
    IngredientToIngredientCommand ingredientToIngredientCommand) {
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if (!recipeOptional.isPresent()) {
      // todo impl error handing
      log.error("recipe id not found. Id: " + recipeId);
    }

    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
      .stream()
      .filter(ingredient -> ingredient.getId().equals(ingredientId))
      .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

    if (!ingredientCommandOptional.isPresent()) {
      // todo impl error handing
      log.error("Ingredient id not found: " + ingredientId);
    }

    return ingredientCommandOptional.get();
  }

  @Override
  @Transactional
  public IngredientCommand saveIngredientCommand(IngredientCommand command) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

    if (!recipeOptional.isPresent()) {

      // todo toss error if not found!
      log.error("Recipe not found for id: " + command.getRecipeId());
      return new IngredientCommand();
    } else {
      Recipe recipe = recipeOptional.get();
      Optional<Ingredient> ingredientOptional = recipe
        .getIngredients()
        .stream()
        .filter(ingredient -> ingredient.getId().equals(command.getId()))
        .findFirst();

      if (ingredientOptional.isPresent()) {
        Ingredient ingredientFound = ingredientOptional.get();
        ingredientFound.setDescription(command.getDescription());
        ingredientFound.setAmount(command.getAmount());
        ingredientFound.setUom(unitOfMeasureRepository
          .findById(command.getUom().getId())
          .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));  // todo address this
      } else {
        // add new Ingredient
        Ingredient ingredient = ingredientCommandToIngredient.convert(command);
        ingredient.setRecipe(recipe);
        recipe.addIngredient(ingredient);
      }

      Recipe savedRecipe = recipeRepository.save(recipe);

      Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
        .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
        .findFirst();

      //check by description
      if (!savedIngredientOptional.isPresent()) {
        //not totally safe... But best guess
        savedIngredientOptional = savedRecipe.getIngredients().stream()
          .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
          .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
          .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
          .findFirst();
      }

      //todo check for fail
      return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
    }
  }

  @Override
  public void deleteById(Long recipeId, Long ingredientId) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

    if (recipeOptional.isPresent()) {
      Recipe recipe = recipeOptional.get();

      Optional<Ingredient> ingredientOptional = recipe
        .getIngredients()
        .stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .findFirst();

      if (ingredientOptional.isPresent()) {
        Ingredient ingredientToDelete = ingredientOptional.get();
        ingredientToDelete.setRecipe(null);
        recipe.getIngredients().remove(ingredientToDelete);
        recipeRepository.save(recipe);
      }
    } else {
      // todo toss error if not found!
      log.error("Recipe not found for id: " + recipeId);
    }
  }
}
