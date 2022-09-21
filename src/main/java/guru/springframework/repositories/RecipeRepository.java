package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author igorg
 * @date 22.05.2022
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
