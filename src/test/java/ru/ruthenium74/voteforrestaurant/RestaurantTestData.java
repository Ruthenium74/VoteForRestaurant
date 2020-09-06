package ru.ruthenium74.voteforrestaurant;

import ru.ruthenium74.voteforrestaurant.model.AbstractBaseEntity;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;

import java.util.List;
import java.util.Set;

import static ru.ruthenium74.voteforrestaurant.DishTestData.*;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER =
            TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "dishes");

    public static final int RESTAURANT1_ID = AbstractBaseEntity.START_SEQ + 2;
    public static final int WRONG_RESTAURANT_ID = 10;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Сова");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Слон");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Уральские пельмени");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Рёбрышковая");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT1_ID + 4, "CHESTER");
    public static final Restaurant RESTAURANT6 = new Restaurant(RESTAURANT1_ID + 5, "У Гоги");

    static {
        RESTAURANT1.setDishes(Set.of(DISH1, DISH2, DISH3, DISH4));
        RESTAURANT2.setDishes(Set.of(DISH5, DISH6, DISH7));
        RESTAURANT3.setDishes(Set.of(DISH8, DISH9, DISH10));
        RESTAURANT4.setDishes(Set.of(DISH11, DISH12, DISH13));
        RESTAURANT5.setDishes(Set.of(DISH14, DISH15, DISH16));
        RESTAURANT6.setDishes(Set.of(DISH17, DISH18, DISH19));
    }

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4,
            RESTAURANT5, RESTAURANT6);

    public static Restaurant getNew() {
        return new Restaurant("New Restaurant");
    }
    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "new Name");
    }
}
