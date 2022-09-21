package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

/**
 * @author igorg
 * @date 24.05.2022
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Set<Ingredient> findByRecipe(Recipe recipe);

    Optional<Ingredient> findByDescription(String description);

    Optional<Ingredient> findByAmount(BigDecimal amount);
}
