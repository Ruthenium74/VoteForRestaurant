package ru.ruthenium74.voteforrestaurant;

import ru.ruthenium74.voteforrestaurant.model.Dish;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER =
            TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "date", "restaurant");

    private static final int START_DISH_ID = RestaurantTestData.RESTAURANT6.getId() + 1;
    public static final Dish DISH1 = new Dish(START_DISH_ID, "Салат цезарь", 35000);
    public static final Dish DISH2 = new Dish(START_DISH_ID + 1, "Борщ", 40000);
    public static final Dish DISH3 = new Dish(START_DISH_ID + 2, "Пюре с котлетой", 50000);
    public static final Dish DISH4 = new Dish(START_DISH_ID + 3, "Сбитень", 32000);

    public static Dish getNew() {
        return new Dish("new Dish", 40000);
    }

}
