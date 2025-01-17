package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author igorg
 * @date 07.06.2022
 */
@SpringBootTest(classes = {RecipeServiceImpl.class})
public class RecipeServiceImplTest {

  @Autowired
  RecipeService recipeService;
  @MockBean
  RecipeRepository recipeRepository;

  @MockBean
  RecipeToRecipeCommand recipeToRecipeCommand;
  @MockBean
  RecipeCommandToRecipe recipeCommandToRecipe;

//  @BeforeEach
//  public void setUp() throws Exception {
//    MockitoAnnotations.initMocks(this);
//
//    //recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
//  }

  @Test
  public void getRecipesTest() throws Exception {
    Recipe recipe = new Recipe();
    HashSet recipeData = new HashSet<>();
    recipeData.add(recipe);

    when(recipeService.findAll()).thenReturn(recipeData);

    Set<Recipe> recipes = recipeService.findAll();

    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull(recipeReturned, "Null recipe returned");
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void getRecipeByIdTestNotFound() throws Exception {
    Optional<Recipe> recipeOptional = Optional.empty();

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    assertThrows(NotFoundException.class, () -> {
      Recipe recipeReturned = recipeService.findById(1L);
    });

    // should go boom
  }

  @Test
  public void getRecipeCommandByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);

    when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

    RecipeCommand commandById = recipeService.findCommandById(1L);

    assertNotNull(commandById, "Null recipe returned");
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void testDeleteById() throws Exception {
    // given
    Long idToDelete = 2L;

    //when
    recipeService.deleteById(idToDelete);

    //then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }
}
