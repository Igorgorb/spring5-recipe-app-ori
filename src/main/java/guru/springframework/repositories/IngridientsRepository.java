package guru.springframework.repositories;

import guru.springframework.domain.Ingridients;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

/**
 * @author igorg
 * Date 24.05.2022
 */
public interface IngridientsRepository extends CrudRepository<Ingridients, Long> {
    Set<Ingridients> findByRecipe(Recipe recipe);

    Optional<Ingridients> findByDescription(String description);

    Optional<Ingridients> findByAmount(BigDecimal amount);
}
