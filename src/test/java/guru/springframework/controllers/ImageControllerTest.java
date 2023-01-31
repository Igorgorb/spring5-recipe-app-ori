package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author igorg
 * @date 22.09.2022
 */
public class ImageControllerTest {

  @Mock
  ImageService imageService;

  @Mock
  RecipeService recipeService;

  ImageController controller;

  MockMvc mockMvc;

  public ImageControllerTest() {
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    controller = new ImageController(imageService, recipeService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
      .setControllerAdvice(new ControllerExceptionHandler())
      .build();
  }

  @Test
  public void getImageForm() throws Exception {
    //given
    RecipeCommand command = new RecipeCommand();
    command.setId(1L);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    //when
    mockMvc.perform(get("/recipe/1/image"))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("recipe"));

    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  public void handleImagePost() throws Exception {
    MockMultipartFile multipartFile
      = new MockMultipartFile("imagefile", "testing.txt",
        "text/plain", "Spring Framework Guru".getBytes());

    mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
      .andExpect(status().is3xxRedirection())
      .andExpect(header().string("Location", "/recipe/1/show"));

    verify(imageService, times(1)).saveImageFile(anyLong(), any());
  }

  @Test
  public void renderImageFromDB() throws Exception {
    //given
    RecipeCommand command = new RecipeCommand();
    command.setId(1L);

    String s = "fake image text";
    Byte[] byteObject = new Byte[s.getBytes().length];

    int i = 0;

    for (byte b : s.getBytes()) {
      byteObject[i++] = b;
    }

    command.setImage(byteObject);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    //when
    MockHttpServletResponse response
      = mockMvc.perform(get("/recipe/1/recipeimage"))
        .andExpect(status().isOk())
        .andReturn().getResponse();

    byte[] responseBytes = response.getContentAsByteArray();

    assertEquals(s.getBytes().length, responseBytes.length);
  }

  @Test
  public void testGetImageNumberFormatException() throws Exception {
    //when
    mockMvc.perform(get("/recipe/tfgxsjg/image"))
      .andExpect(status().isBadRequest())
      .andExpect(view().name("400error"));
  }

}
