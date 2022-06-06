package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author igorg
 * Date 25.05.2022
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());

        if(log.isInfoEnabled()){
            log.info("List of recipe saved ...");
        }
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }
        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupsUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (!ounceUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            log.error("Expected UOM Not Found");
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();

        if(log.isInfoEnabled()){
            log.info("UOM's loaded ...");
        }

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            log.error("Expected Category Not Found");
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            log.error("Expected Category Not Found");
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        if(log.isInfoEnabled()){
            log.info("Categories loaded ...");
        }

        Recipe guacamole = new Recipe();
        guacamole.setCookTime(0);
        guacamole.setPrepTime(10);
        guacamole.setDescription("How to Make the Best Guacamole");
        guacamole.setDirections("1. Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "\n" +
                "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3. Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "\n" +
                "4. Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.");
        guacamole.setServings(4);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.EASY);
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations by adding strawberries, peaches, pineapple, mangoes, or even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with your homemade guacamole!\n" +
                "\n" +
                "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.");
        guacamole.setNotes(guacNotes);
        guacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2), eachUom))
                .addIngredient(new Ingredient("kosher salt, plus more to taste", BigDecimal.valueOf(0.25), teaSpoonUom))
                .addIngredient(new Ingredient("fresh lime or lemon juice", BigDecimal.valueOf(1), tableSpoonUom))
                .addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(3), tableSpoonUom))
                .addIngredient(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", BigDecimal.valueOf(2), eachUom))
                .addIngredient(new Ingredient("freshly ground black pepper", BigDecimal.valueOf(1), pinchUom))
                .addIngredient(new Ingredient("ripe tomato, chopped (optional)", BigDecimal.valueOf(0.5), eachUom));

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        recipes.add(guacamole);

        if(log.isInfoEnabled()){
            log.info("Recipe guacamole made ...");
        }

        Recipe tacoChicken = new Recipe();
        tacoChicken.setCookTime(15);
        tacoChicken.setPrepTime(10);
        tacoChicken.setDescription("Spicy Grilled Chicken Tacos");
        tacoChicken.setServings(4);
        tacoChicken.setSource("Simply Recipes");
        tacoChicken.setUrl("www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoChicken.setDifficulty(Difficulty.MODERATE);
        tacoChicken.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "2. Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3. Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4. Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5. Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes! ");
        tacoChicken.setNotes(tacoNotes);

        tacoChicken.addIngredient(new Ingredient("ancho chili powder", BigDecimal.valueOf(2), tableSpoonUom))
                .addIngredient(new Ingredient("dried oregano", BigDecimal.valueOf(1), teaSpoonUom))
                .addIngredient(new Ingredient("dried cumin", BigDecimal.valueOf(1), teaSpoonUom))
                .addIngredient(new Ingredient("sugar", BigDecimal.valueOf(1), teaSpoonUom))
                .addIngredient(new Ingredient("salt", BigDecimal.valueOf(0.5), teaSpoonUom))
                .addIngredient(new Ingredient("clove garlic, finely chopped", BigDecimal.valueOf(1), eachUom))
                .addIngredient(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1), tableSpoonUom))
                .addIngredient(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), tableSpoonUom))
                .addIngredient(new Ingredient("olive oil", BigDecimal.valueOf(2), tableSpoonUom))
                .addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", BigDecimal.valueOf(5), eachUom));

        tacoChicken.getCategories().add(americanCategory);
        tacoChicken.getCategories().add(mexicanCategory);

        recipes.add(tacoChicken);

        if(log.isInfoEnabled()){
            log.info("Recipe taco chicken made ...");
        }

        return recipes;
    }
}
