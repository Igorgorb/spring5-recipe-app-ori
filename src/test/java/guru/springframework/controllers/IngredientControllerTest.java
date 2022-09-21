package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author igorg
 * @date 21.07.2022
 */
public class IngredientControllerTest {

  @Mock
  UnitOfMeasureService unitOfMeasureService;
  @Mock
  IngredientService ingredientService;
  @Mock
  RecipeService recipeService;

  IngredientController controller;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    controller = new IngredientController(ingredientService, recipeService, unitOfMeasureService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    //when
    mockMvc.perform(get("/recipe/1/ingredients"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/ingredient/list"))
      .andExpect(model().attributeExists("recipe"));

    //then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  public void testShowIngredients() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();
    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/ingredient/show"))
      .andExpect(model().attributeExists("ingredient"));
  }

  @Test
  public void testNewIngredientForm() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);

    //when
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

    //then
    mockMvc.perform(get("/recipe/1/ingredient/new"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/ingredient/ingredientform"))
      .andExpect(model().attributeExists("ingredient"))
      .andExpect(model().attributeExists("uomList"));

    verify(recipeService, times(1)).findCommandById(anyLong());

  }

  @Test
  public void testUpdateIngredientForm() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();
    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
    when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/update"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/ingredient/ingredientform"))
      .andExpect(model().attributeExists("ingredient"))
      .andExpect(model().attributeExists("uomList"));
  }

  @Test
  public void testSaveOrUpdate() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(3L);
    ingredientCommand.setRecipeId(2L);
    //when
    when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

    //then
    mockMvc.perform(post("/recipe/2/ingredient")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("id", "")
      .param("description", "some string")
    )
      .andExpect(status().is3xxRedirection())
      .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
  }

}
