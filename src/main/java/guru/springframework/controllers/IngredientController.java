package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author igorg 
 * @date 21.07.2022
 */
@Slf4j
@Controller
public class IngredientController {

  private final IngredientService ingredientService;
  private final RecipeService recipeService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
    this.ingredientService = ingredientService;
    this.recipeService = recipeService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("Getting ingredient list for recipe id: " + recipeId);

    //use command object to avoid lazy load errors in Thymeleaf.
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

    return "recipe/ingredient/list";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
  public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
    return "recipe/ingredient/show";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/new")
  public String newRecipeIngredient(@PathVariable String recipeId, Model model) {

    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
    // todo raise exception if null

    //need to return back parent id for hidden form property
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setRecipeId(recipeCommand.getId());
    // init uom
    ingredientCommand.setUom(new UnitOfMeasureCommand());
    model.addAttribute("ingredient", ingredientCommand);

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
  public String updateRecipeIngredient(@PathVariable String recipeId,
    @PathVariable String id, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
  public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
    ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

    log.debug("Deleting ingredient id: " + ingredientId);

    return "redirect:/recipe/" + recipeId + "/ingredients";
  }

  @PostMapping
  @RequestMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

    log.debug("saved recipe id:" + savedCommand.getRecipeId());
    log.debug("saved ingredient id:" + savedCommand.getId());

    return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
  }
}
