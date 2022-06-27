package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * @author igorg
 * Date 22.05.2022
 */
public interface RecipeService {
    Set<Recipe> findAll();

    Recipe findById(Long id);
}
