package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import guru.springframework.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author igorg
 * Date 20.06.2022
 */
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

  @MockBean
  RecipeService recipeService;

//    RecipeController controller;
  @Autowired
  MockMvc mockMvc;

//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//        controller = new RecipeController(recipeService);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
  @Test
  public void testGetRecipe() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    when(recipeService.findById(anyLong())).thenReturn(recipe);

    mockMvc.perform(get("/recipe/1/show"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/show"))
      .andExpect(model().attributeExists("recipe"));
  }

  @Test
  public void testGetRecipeNotFound() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

    mockMvc.perform(get("/recipe/1/show"))
      .andExpect(status().isNotFound());
  }

  @Test
  public void testGetNewRecipeForm() throws Exception {
    mockMvc.perform(get("/recipe/new"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/recipeform"))
      .andExpect(model().attributeExists("recipe"));
  }

  @Test
  public void testPostNewRecipeForm() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeService.saveRecipeCommand(any())).thenReturn(command);

    mockMvc.perform(post("/recipe")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("id", "")
      .param("description", "some string")
    )
      .andExpect(status().is3xxRedirection())
      .andExpect(view().name("redirect:/recipe/2/show"));
  }

  @Test
  public void testGetUpdateView() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(3L);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    mockMvc.perform(get("/recipe/3/update"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/recipeform"))
      .andExpect(model().attributeExists("recipe"));
  }

  @Test
  public void testDeleteAction() throws Exception {
    mockMvc.perform(get("/recipe/1/delete"))
      .andExpect(status().is3xxRedirection())
      .andExpect(view().name("redirect:/"));

    verify(recipeService, times(1)).deleteById(anyLong());
  }
}
