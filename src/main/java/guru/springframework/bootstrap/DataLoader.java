package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingridients;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author igorg
 * Date 25.05.2022
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UnitOfMeasure teaSpoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure eaches = unitOfMeasureRepository.findByDescription("Eaches").get();

        Recipe guacamole = new Recipe();
        guacamole.setCookTime(10);
        guacamole.setPrepTime(10);
        guacamole.setDescription("How to Make the Best Guacamole");
        guacamole.setDirections("Direction 1");
        guacamole.setServings(4);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("www.simplyrecipes.com recipes perfect_guacamole ");
        guacamole.setDifficulty(Difficulty.EASY);
        Set<Ingridients> ins = new HashSet<>();

        Ingridients in1 = new Ingridients();
        in1.setAmount(BigDecimal.valueOf(2));
        in1.setDescription("ripe avocados");
        in1.setUom(eaches);
        ins.add(in1);

        Ingridients in2 = new Ingridients();
        in2.setAmount(BigDecimal.valueOf(0.25));
        in2.setDescription("kosher salt, plus more to taste");
        in2.setUom(teaSpoon);
        ins.add(in2);

        Ingridients in3 = new Ingridients();
        in3.setAmount(BigDecimal.valueOf(1));
        in3.setDescription("fresh lime or lemon juice");
        in3.setUom(tableSpoon);
        ins.add(in3);

        Ingridients in4 = new Ingridients();
        in4.setAmount(BigDecimal.valueOf(3));
        in4.setDescription("minced red onion or thinly sliced green onion");
        in4.setUom(tableSpoon);
        ins.add(in4);

        Ingridients in5 = new Ingridients();
        in5.setAmount(BigDecimal.valueOf(2));
        in5.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        in5.setUom(eaches);
        ins.add(in5);

        Ingridients in6 = new Ingridients();
        in6.setAmount(BigDecimal.valueOf(2));
        in6.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        in6.setUom(tableSpoon);
        ins.add(in6);

        Ingridients in7 = new Ingridients();
        in7.setAmount(BigDecimal.valueOf(1));
        in7.setDescription("freshly ground black pepper");
        in7.setUom(pinch);
        ins.add(in7);

        Ingridients in8 = new Ingridients();
        in8.setAmount(BigDecimal.valueOf(0.5));
        in8.setDescription("ripe tomato, chopped (optional)");
        in8.setUom(eaches);
        ins.add(in8);

        guacamole.setIngridients(ins);
        recipeRepository.save(guacamole);

        Recipe takoChicken = new Recipe();
        takoChicken.setCookTime(15);
        takoChicken.setPrepTime(10);
        takoChicken.setDescription("Spicy Grilled Chicken Tacos");
        takoChicken.setDirections("Direction 2");
        takoChicken.setServings(4);
        takoChicken.setSource("Simply Recipes");
        takoChicken.setUrl("www.simplyrecipes.com recipes spicy_grilled_chicken_tacos ");
        Set<Ingridients> ings = new HashSet<>();

        Ingridients ingr1 = new Ingridients();
        ingr1.setAmount(BigDecimal.valueOf(2));
        ingr1.setDescription("ancho chili powder");
        ingr1.setUom(tableSpoon);
        ings.add(ingr1);

        Ingridients ingr2 = new Ingridients();
        ingr2.setAmount(BigDecimal.valueOf(1));
        ingr2.setDescription("dried oregano");
        ingr2.setUom(teaSpoon);
        ings.add(ingr2);

        Ingridients ingr3 = new Ingridients();
        ingr3.setAmount(BigDecimal.valueOf(1));
        ingr3.setDescription("dried cumin");
        ingr3.setUom(teaSpoon);
        ings.add(ingr3);

        Ingridients ingr4 = new Ingridients();
        ingr4.setAmount(BigDecimal.valueOf(1));
        ingr4.setDescription("sugar");
        ingr4.setUom(teaSpoon);
        ings.add(ingr4);

        Ingridients ingr5 = new Ingridients();
        ingr5.setAmount(BigDecimal.valueOf(0.5));
        ingr5.setDescription("salt");
        ingr5.setUom(teaSpoon);
        ings.add(ingr5);

        Ingridients ingr6 = new Ingridients();
        ingr6.setAmount(BigDecimal.valueOf(1));
        ingr6.setDescription("clove garlic, finely chopped");
        ingr6.setUom(eaches);
        ings.add(ingr6);

        Ingridients ingr7 = new Ingridients();
        ingr7.setAmount(BigDecimal.valueOf(1));
        ingr7.setDescription("finely grated orange zest");
        ingr7.setUom(tableSpoon);
        ings.add(ingr7);

        Ingridients ingr8 = new Ingridients();
        ingr8.setAmount(BigDecimal.valueOf(3));
        ingr8.setDescription("fresh-squeezed orange juice");
        ingr8.setUom(tableSpoon);
        ings.add(ingr8);

        Ingridients ingr9 = new Ingridients();
        ingr9.setAmount(BigDecimal.valueOf(2));
        ingr9.setDescription("olive oil");
        ingr9.setUom(tableSpoon);
        ings.add(ingr9);

        Ingridients ingr10 = new Ingridients();
        ingr10.setAmount(BigDecimal.valueOf(5));
        ingr10.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
        ingr10.setUom(eaches);
        ings.add(ingr10);

        takoChicken.setIngridients(ings);
        recipeRepository.save(takoChicken);
    }
}
