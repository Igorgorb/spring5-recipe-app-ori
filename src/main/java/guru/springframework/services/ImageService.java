package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author igorg
 * @date 22 вер. 2022 р.
 */
public interface ImageService {

  public void saveImageFile(Long recipeId, MultipartFile file);

}
