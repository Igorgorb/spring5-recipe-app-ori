package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.BadRequestException;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author igorg
 * Date 20.06.2022
 */
@Slf4j
@Controller
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping
  @RequestMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {
    Long id_in;
    try {
      id_in = Long.valueOf(id);
    } catch (NumberFormatException nfe) {
      throw new BadRequestException("Url request contain incredible part: " + id);
    }
    model.addAttribute("recipe", recipeService.findById(id_in));
    return "recipe/show";
  }

  @GetMapping
  @RequestMapping("recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());

    return "recipe/recipeform";
  }

  @GetMapping
  @RequestMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return "recipe/recipeform";
  }

  @GetMapping
  @RequestMapping("recipe/{id}/delete")
  public String deleteRecipe(@PathVariable String id) {
    recipeService.deleteById(Long.valueOf(id));

    log.debug("Deleting id: " + id);

    return "redirect:/";
  }

  @PostMapping
  @RequestMapping("recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFound(Exception exception) {
    log.error("Handing not found exception");
    log.error(exception.getMessage());

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("404error");
    modelAndView.addObject("exception", exception);

    return modelAndView;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ModelAndView handleBadRequest(Exception exception) {
    log.error("Handing bad request exception");
    log.error(exception.getMessage());

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("400error");
    modelAndView.addObject("exception", exception);

    return modelAndView;
  }
}
