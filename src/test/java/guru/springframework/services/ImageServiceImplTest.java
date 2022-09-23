package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import org.springframework.mock.web.MockMultipartFile;

/**
 *
 * @author igorg
 * @date 22.09.2022
 */
public class ImageServiceImplTest {

  @Mock
  RecipeRepository recipeRepository;

  ImageService imageService;

  public ImageServiceImplTest() {
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    imageService = new ImageServiceImpl(recipeRepository);
  }

  @Test
  public void testSaveImageFile() throws Exception {
    //given
    Long id = 1L;
    MockMultipartFile multipartFile
      = new MockMultipartFile("imagefile", "testing.txt",
        "text/plain", "Spring Framework Guru".getBytes());

    Recipe recipe = new Recipe();
    recipe.setId(id);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

    //when
    imageService.saveImageFile(id, multipartFile);

    //then
    verify(recipeRepository, times(1)).save(argumentCaptor.capture());
    Recipe savedRecipe = argumentCaptor.getValue();
    assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
  }

}
