package ru.ruthenium74.voteforrestaurant;

import ru.ruthenium74.voteforrestaurant.model.Dish;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER =
            TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "date", "restaurant");

    public static final int DISH1_ID = RestaurantTestData.RESTAURANT6.getId() + 1;
    public static final Dish DISH1 = new Dish(DISH1_ID, "Салат цезарь", 35000);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Борщ", 40000);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Пюре с котлетой", 50000);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Сбитень", 32000);

    public static Dish getNew() {
        return new Dish("new Dish", 40000);
    }
    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated dish", 55000);
    }

}
