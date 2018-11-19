
CREATE TABLE Recipes (
	recipe_id int NOT NULL,
	dish varchar(255) NOT NULL,
	imageURL varchar(512) NOT NULL,
	PRIMARY KEY (recipe_id)
);

CREATE TABLE Ingredients (
	ingredient_id int NOT NULL,
	recipe_id int NOT NULL,
	directions varchar(2000) NOT NULL,
	ingredients varchar(1000) NOT NULL,
	PRIMARY KEY (ingredient_id),
	FOREIGN KEY (recipe_id) REFERENCES Recipes(recipe_id)
);

CREATE TABLE Categories (
	category_id int NOT NULL,
	recipe_id int NOT NULL,
    nationality varchar(255),
	course varchar(255) NOT NULL,
	cookingStyle varchar(255),
	mainIngredient varchar(255),
	PRIMARY KEY (category_id),
	FOREIGN KEY (recipe_id) REFERENCES Recipes(recipe_id)
);

INSERT INTO Recipes (recipe_id, dish, imageURL)
	VALUES (1, 'Crawfish Dip', 'resources/Images/crawfishdip.jpg'),
(2, 'Prosciutto Stuffed Mushrooms', 'resources/Images/mushrooms.jpg'),
(3, 'Mussels in Garlic Sauce', 'resources/Images/mussels.jpg'),
(4, 'Cheesy Crab Poppers', 'resources/Images/crabpoppers.jpg'),
(5, 'Butter Chicken', 'resources/Images/butterchicken.jpg'),
(6, 'Thai Grilled Chicken', 'resources/Images/thaichicken.jpg'),
(7, 'Seafood Paella', 'Images/seafoodpaella.jpg'),
(8, 'Crawfish Gratin', 'Images/crawfishgratin.jpg'),
(9, 'Mushroom Stroganoff', 'Images/mushroomstroganoff.jpg'),
(10, 'Coconut and Lime Macarons', 'Images/macarons.jpg'),
(11, 'Blackberry Souffle', 'resources/Images/souffle.jpg');

INSERT INTO Ingredients (ingredient_id, recipe_id, directions, ingredients)
	VALUES (1, 1, 
    '1. In a small pot, heat butter on medium heat. 
    Add trinity and jalapeño and cook until onions are almost translucent. 
    Add minced garlic and continue to cook in the pot and stir.
    
    2. Add the crawfish tails and stir, just until warm, because
    they are already cooked.
    
    3. Add cream cheese to the pot, and stir until melted (about 3-5 minutes). 
    Add salt, pepper, and optional Cajun seasoning. Serve hot or cold!', 

   '4 Tablespoons butter (1/2 stick)
    2 cups trinity (equal parts chopped onion,
    green bell pepper, and celery)
	1/4 jalapeno, finely chopped
	2-4 garlic cloves, minced
	2 cups of boiled crawfish tails
	1 8 oz package of cream cheese
	1/2 Tablespoon of Cajun seasoning (use if not pre-seasoned)
    Salt and pepper, to taste'),
	(2, 2, 
    '1. Remove stems from mushrooms. 
	
    2. Combine cheese, Prosciutto and basil. 

    3. Season with salt and pepper. 
	
    4. Bake in 350 degree oven about 7 minutes or until cheese has browned.',
   '3 lbs. mushrooms, silver dollar
    2 lbs. Bacio whole milk cheese, shredded
    12 oz. Roma prosciutto
    salt and pepper (to taste)'),
    (3, 3, 
    '1. Melt the Butter in a large skillet (with a lid) 
    – saute the Onion, Garlic and Chili Flakes until soft
    
    2. Add the White Wine and simmer until reduced by two thirds 
    – add the Cream and simmer 2 – 3 minutes
    
    3. Add the Mussels and cover the skillet – over low heat, 
    steam the Mussels until cooked – about 8 – 10 minutes
    
    4. Remove from heat – season to taste with Salt and Black Pepper 
    – stir through the Lemon Juice, finely grated Zest and Parsley',
   '2 TB Butter
    1 Onion finely chopped
    2 t Fresh Garlic crushed
    1/2 t Chili Flakes
    1 cup Dry White Wine
    1 cup Fresh Cream (whipping cream)
    450 g Half Shell Mussels ( 1 pound )
    Salt and Black Pepper to taste
    1 Lemon juice and zest
    1/2 cup Parsley chopped'), 
    (4, 4, 
    '1. Combine all of the ingredients in a large bowl and mix well.
    2. Roll the mixture into balls, just slightly smaller 
    than the size of golf balls.
    Set on parchment paper or a baking sheet.
    3. In a large, high walled pot or a wok, heat oil over medium-high.
    You want to make sure there is enough oil to cover 2/3 of the crab balls.
    4. Test the oil to see if it’s ready by dipping the end (handle-side) 
    of a wooden spoon in the oil. If the oil begins to bubble, it’s ready. 
    If not, let it sit a bit longer.
    5. Once the oil is ready, drop in 4-5 crab balls.',
   '1 package (~10 oz) of lump crab meat
    1 box of Jiffy cornmuffin mix
    1 cup panko breadcrumbs
    1 8oz package of cream cheese, softened at room temperature
    1 cup shredded sharp cheddar cheese
    1 large egg
    1 stick of unsalted butter, melted
    1/2 cup milk
    1/2 red bell pepper, finely chopped
    2 green onions, sliced
    1 clove garlic, minced
    2 tablespoons sugar
    Pinch of salt
    Canola oil, for frying'),
	(5, 5, 
    '1. In a large bowl, season the chicken breast with salt, 
    pepper, 1 teaspoon of chili powder, and the teaspoon of turmeric.
    Let sit for 15 minutes to marinate.
    
    2. Melt 2 tablespoons of butter in a large pot over medium heat. 
    Brown the chicken, then remove from the pot.
    
    3. Melt another 2 tablespoons of butter in the pot, 
    then add the onion, garam masala, remaining teaspoon of chili powder, 
    the cumin, ginger, garlic, cayenne, cinnamon, salt and pepper. Cook until fragrant.
    
    4. Add the tomato sauce and bring to a simmer.
    
    5. Add the water and cream and return to a simmer.
    
    6. Return the chicken to the pot, cover, and simmer for another 10-15 minutes.
    
    7. Stir in the last 2 tablespoons of butter and season with more salt and pepper to taste.',
'2 lb boneless, skinless chicken breast, cubed
salt, to taste
pepper, to taste
2 teaspoons chili powder, divided
½ teaspoon turmeric
6 tablespoons butter, divided
1 ½ cups yellow onion, diced
3 teaspoons garam masala
1 teaspoon cumin
1 teaspoon cayenne pepper
1 tablespoon ginger, grated
3 cloves garlic, minced
1 cinnamon, 3 inch (8 cm) stick
14 oz tomato sauce
1 cup water
1 cup heavy cream
rice, for serving
fresh cilantro'),
(6, 6, '1. Place Marinade ingredients in a large 
    ziplock bag. Massage to mix.
    2. Add the chicken into the ziplock bag and massage 
    to spread the marinade over all the chicken. 
    Marinate for a minimum of 3 hours, preferably overnight.
    3. Remove chicken from the Marinade and discard 
    the Marinade.
    4. Heat the outdoor grill on medium high. 
    Or heat 1/2 tbsp oil in a non stick pan over medium high heat on the stove.
    5. Cook the chicken until golden brown - around 3 minutes each side.
    6. Rest for a few minutes before serving with 
    lime wedges on the side, and garnished with fresh chilies and cilantro, if using.',
    '2 lb / 1 kg chicken thigh fillets
	Marinade
	1 large lemongrass stalk , white part 
    only very finely chopped (about 2 tbsp)
    6 cloves garlic , minced
    2 tbsp lime juice
    2 tsp finely chopped red chili (optional but recommended)
    3 tbsp fish sauce
    2 tbsp Chinese cooking wine , sherry or sake (Japanese cooking wine)
    1 tsp sesame oil (optional)
    3 tbsp brown sugar or palm sugar
    2 tbsp honey (or 1 tbsp brown sugar)
    1/2 tbsp black pepper (adjust to taste - this adds spiciness)
	To Serve 
    Lime wedges
    Red chili , finely sliced (optional)
    Cilantro / coriander leaves (optional)'),
(7, 7, '1. In a large paella pan or a large sauté pan over medium-high heat, 
	warm the olive oil. Add the sausage and cook, turning occasionally, 
	until browned on both sides, about 3 minutes. Add the onion, 
	bell pepper and garlic and cook, stirring occasionally, 
	until softened, 3 to 4 minutes. Season with salt and pepper.
	
    2. Add the rice, crumble in the saffron and cook, stirring, 
	until the grains are well coated, about 2 minutes. 
	Pour in the broth and stir in 1 1/2 tsp. salt. Bring to a boil, 
	then reduce the heat to low, cover and cook until the rice has 
	absorbed nearly all of the liquid, about 20 minutes.
	
    3. While the rice is cooking, using a sharp paring knife or kitchen shears, 
	cut along each shrimp’s shell above the vein line and then remove the vein. 
	Leave the shell and tail intact.
	
    4. When the rice has absorbed nearly all of the liquid, press the 
	clams and mussels, hinge side down, into the rice, discarding any 
	that do not close to the touch. Spread the shrimp over the rice and 
	top with the peas. Cover and cook until the shrimp are opaque and the clams have opened, about 5 minutes.',
	'2 Tbs. olive oil
    1 lb. (500 g) Spanish chorizo or other 
    spicy smoked sausages, cut into slices 1/2 inch (12 mm) thick

    1 yellow onion, chopped

    1 red bell pepper, seeded and chopped

    3 garlic cloves, minced

    Kosher salt and freshly ground pepper

    2 cups (14 oz./440 g) Bomba rice or other Spanish paella rice

    1/2 tsp. saffron threads

    4 cups (32 fl. oz./1 l) chicken broth

    1 lb. (500 g) jumbo shrimp

    1 lb. (500 g) small clams, such as littleneck or Manila, scrubbed

    1 lb. (500 g) mussels, scrubbed and debearded

1 cup (5 oz./155 g) thawed frozen peas'),
(8, 8, '1. Preheat oven to 400°.
    2. Butter a 1½-quart baking dish. In a large saucepan over medium heat, 
    melt 4 tablespoons butter. Add onion, and cook until tender, about 5 minutes. 
    Add garlic, celery, and bell pepper, and cook until vegetables are heated, 
    about 3 minutes. Add crawfish tails, tossing to combine. Remove from heat, and let stand.
    3. In a medium saucepan over medium heat, melt remaining  6 tablespoons butter. 
    Add flour, and whisk until smooth. Add wine, milk, and heavy cream, and cook until thickened, 
    about 5 minutes. Add cheeses, parsley, juice, hot sauce, Worcestershire, 
    Old Bay Seasoning, pepper, and salt. Whisk until smooth. 
    Add cream mixture to crawfish mixture, stirring to combine. Pour into prepared dish.
    4. In a small bowl, combine Cheddar with bread crumbs. Sprinkle over crawfish mixture, 
    and bake until golden and bubbly, 12 to 15 minutes.',
    '10 tablespoons unsalted butter plus more for greasing, divided
    1 medium yellow onion, diced
    2 cloves garlic, minced
    1 cup diced celery
    1 green bell pepper, seeded and diced
    2 (16-ounce) packages cooked crawfish tails
    ¾ cup all-purpose flour
    ½ cup dry white wine
    1 cup whole milk
    1 cup heavy whipping cream
    1 cup shredded Gruyère cheese
    1 cup grated Parmesan cheese
    ½ cup chopped fresh parsley
    2 tablespoons fresh lemon juice
    1 teaspoon hot sauce
    1 teaspoon Worcestershire sauce
    1 tablespoon Old Bay Seasoning
    1 teaspoon ground white pepper
    1 teaspoon kosher salt
    1 cup shredded white Cheddar cheese
    1 cup panko (Japanese bread crumbs)'),
(9, 9, '1. Heat a 1 tablespoon of oil in large skillet (or pot) 
	over medium-high heat. While the pot heats, dice onion. 
	Cook onion for 6 minutes, or until it’s translucent and slightly golden brown on edges.
	Meanwhile, cut the ends off the mushrooms and slice. Add mushrooms to the pot and cook 
	for 8 minutes, or until mushrooms have released all their liquids and are golden brown.

    2. Reduce heat to medium. Add remaining 1 tablespoon of oil, 
    minced garlic and freshly picked thyme leaves to the pot. 
    Cook for 2 minutes. Add flour and mix well until vegetables are coated evenly. 
    Then add vegetable stock. Scrape any brown bits off the pan and stir well. Bring liquid back to a boil.

    3. Once boiling, add noodles and non-dairy milk. Use spoon to distribute the noodles 
    so they’re fully covered with liquid. Stir every couple minutes
    to ensure even cooking. 
    Cook uncovered for 8-10 minutes or until the noodles are el dente. 
    Keep an eye on your mixture: 
    if noodles need more liquid, add extra stock in very small increments. 
    Add salt and pepper to taste. Serve immediately and garnish with 
    chopped parsley and cheese of your choice.',
    '2 tablespoons grapeseed oil, divided
    1 large yellow onion

    1 lb / 455 g cremini mushrooms*

    3 cloves of garlic, minced

    2 teaspoons fresh thyme, picked

    3 tablespoons flour (e.g. spelt)

    3 1/2 cups vegetable stock**

    7 oz/200g dry egg noodles***

    1/2 cup plain almond milk

    Salt and pepper'),
(10, 10, '1. In a small sauce pan over medium heat combine the butter,
    sugar, lime zest, lime juice, and salt, 
	whisk to combine.  Add in one yolk at a time until combined. 
	Bring to a simmer and cook for 10-15 minutes until thick like a heavy sauce.  
	Pour into a bowl and place cling film over the top resting on the curd to 
	prevent a skin from forming and refrigerate over night to 1 week.
    
    2. Freeze any unused buttercream in individual containers with 1-1.5 cups in each container. 
    This way you have buttercream at the ready for your next macaron or cupcake craving.
    
    3. Following the recipe from a previous post, after the shells are not tacky to the touch 
    sprinkle generously with flaked coconut and bake as normal. 
    The coconut will toast lightly and add lovely flavor and texture to the cookies.', 
    '4 tbsp unsalted butter
    1/3 cup sugar
    juice of one lime, large (or two small)
    zest of one lime, large (or two small)
    Pinch of salt
    3 egg yolks'),
(11, 11, '1. Preheat the oven to 180˚C, gas mark 4, and put a baking sheet on the top shelf of the oven. 
	Grease 2 large ramekins (200ml in volume) and dust with sugar.
    2. Put the berries and 1 tbsp sugar in a small pan and simmer for 5-6 minutes
    until the fruit has broken up (help it by crushing with a wooden spoon).
    Whizz in a blender, 
    then sieve into a mixing bowl; discard the pips. Cool slightly,
    then stir in the vanilla extract and lemon juice. 
    Put 1 tbsp purée into each ramekin. Mix the cornflour with ½ tbsp water,
    then stir into the remaining purée; set aside.
    3. Put the egg whites in a clean bowl and whisk with 
    electric beaters on a slow, then medium, speed. 
    When the whites reach soft peaks, scatter over the
    remaining 2 tbsp sugar; whisk until dense and silky.
    4. Fold 1 / 3 of the egg white into the purée, 
    then gently fold in the remainder until combined; 
    take care not to overmix. Divide the mixture 
    between the ramekins, filling to the top. 
    Smooth the surface with a knife and clean the edges with 
    your thumb wrapped in a piece of kitchen paper,
    making a slight indent around the edges. Cook on 
    the baking sheet for 10 minutes, 
    until well risen. Dust with icing sugar 
    and serve at once, with crème fraîche, if liked.',
    'unsalted butter, for greasing
    3 tbsp golden caster sugar, plus extra to dust
    150g blackberries
    few drops vanilla extract
    few drops lemon juice
    ½ tbsp cornflour
    2 egg whites
    icing sugar, to dust');

INSERT INTO Categories (category_id, recipe_id, nationality, course, cookingStyle, mainIngredient)
	VALUES (1, 1, 'American', 'Appetizer', 'Mix', 'Crawfish'),
(2, 2, 'Italian', 'Appetizer', 'Baking', 'Mushrooms'),
(3, 3, 'South African', 'Appetizer', 'Steaming', 'Mussels'),
(4, 4, 'American', 'Appetizer', 'Frying', 'Crab'),
(5, 5, 'Indian', 'Main course', 'Simmering', 'Chicken'),
(6, 6, 'Thai', 'Main course', 'Grilling', 'Chicken'),
(7, 7, 'Spanish', 'Main course', 'Boiling', 'Seafood'),
(8, 8, 'French', 'Main course', 'Baking', 'Crawfish'),
(9, 9, 'Russian', 'Main course', 'Boiling', 'Mushroom'),
(10, 10, 'French', 'Dessert', 'Baking', 'Coconut'),
(11, 11, 'French', 'Dessert', 'Baking', 'Blackberries');



