INSERT INTO category (description)
VALUES ('American');
INSERT INTO category (description)
VALUES ('Italian');
INSERT INTO category (description)
VALUES ('Mexican');
INSERT INTO category (description)
VALUES ('Fast Food');

INSERT INTO unit_of_measure (description)
VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description)
VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description)
VALUES ('Cup');
INSERT INTO unit_of_measure (description)
VALUES ('Pinch');
INSERT INTO unit_of_measure (description)
VALUES ('Ounce');
INSERT INTO unit_of_measure (description)
VALUES ('Eaches');

INSERT INTO recipe (COOK_TIME, DESCRIPTION, DIRECTIONS, PREP_TIME, SERVINGS, SOURCE, URL)
VALUES (15, 'Spicy Grilled Chicken Tacos', 'Direction 1', 10, 4, 'Simply recipe',
        'https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/');
INSERT INTO recipe (COOK_TIME, DESCRIPTION, DIRECTIONS, PREP_TIME, SERVINGS, SOURCE, URL)
VALUES (10, 'How to Make the Best Guacamole', 'Direction 2', 10, 4, 'Simply recipe',
        'https://www.simplyrecipes.com/recipes/perfect_guacamole/');

INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (2, 'ancho chili powder', 1, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'dried oregano', 1, 1);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'dried cumin', 1, 1);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'sugar', 1, 1);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (0.5, 'salt', 1, 1);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'clove garlic, finely chopped', 1, 6);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'finely grated orange zest', 1, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (3, 'fresh-squeezed orange juice', 1, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (2, 'olive oil', 1, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (5, 'skinless, boneless chicken thighs (1 1/4 pounds)', 1, 6);

INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (2, 'ripe avocados', 2, 6);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (0.25, 'kosher salt, plus more to taste', 2, 1);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'fresh lime or lemon juice', 2, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (3, 'minced red onion or thinly sliced green onion', 2, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (2, 'minced red onion or thinly sliced green onion', 2, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (2, 'serrano (or jalapeño) chilis, stems and seeds removed, minced', 2, 6);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (2, 'cilantro (leaves and tender stems), finely chopped', 2, 2);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (1, 'freshly ground black pepper', 2, 4);
INSERT INTO ingridients (AMOUNT, DESCRIPTION, RECIPE_ID, UOM_ID)
VALUES (0.5, 'ripe tomato, chopped (optional)', 2, 6);