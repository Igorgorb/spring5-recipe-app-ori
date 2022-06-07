package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author igorg
 * Date 07.06.2022
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}