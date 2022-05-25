package guru.springframework.repositories;

import guru.springframework.domain.Ingredients;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

/**
 * @author igorg
 * Date 24.05.2022
 */
public interface IngridientsRepository extends CrudRepository<Ingredients, Long> {
    Set<Ingredients> findByRecipe(Recipe recipe);

    Optional<Ingredients> findByDescription(String description);

    Optional<Ingredients> findByAmount(BigDecimal amount);
}
