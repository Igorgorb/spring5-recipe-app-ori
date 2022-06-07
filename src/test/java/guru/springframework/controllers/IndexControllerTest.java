package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author igorg
 * Date 07.06.2022
 */
public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(categoryRepository, unitOfMeasureRepository, recipeService);
    }

    @Test
    public void getIndexPage() {
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet<>();
        recipeData.add(recipe);
        when(recipeService.findAll()).thenReturn(recipeData);

        Optional<Category> categoryOptional = Optional.of(new Category());
        when(categoryRepository.findByDescription("American")).thenReturn(categoryOptional);

        Optional<UnitOfMeasure> unitOfMeasureOptional = Optional.of(new UnitOfMeasure());
        when(unitOfMeasureRepository.findByDescription("Teaspoon")).thenReturn(unitOfMeasureOptional);

        String expected = "index";
        String actual = indexController.getIndexPage(model);
        assertEquals(expected, actual);

        verify(recipeService, times(1)).findAll();
        verify(categoryRepository, times(1)).findByDescription("American");
        verify(unitOfMeasureRepository, times(1)).findByDescription("Teaspoon");
        verify(model, times(1)).addAttribute("recipes", recipeData);
    }
}