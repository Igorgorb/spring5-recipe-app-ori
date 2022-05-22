package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author igorg
 * Date 22.05.2022
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
