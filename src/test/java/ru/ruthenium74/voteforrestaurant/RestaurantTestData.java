package ru.ruthenium74.voteforrestaurant;

import ru.ruthenium74.voteforrestaurant.model.AbstractBaseEntity;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER =
            TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "dishes");

    public static final int RESTAURANT1_ID = AbstractBaseEntity.START_SEQ + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Сова");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Слон");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Уральские пельмени");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Рёбрышковая");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT1_ID + 4, "CHESTER");
    public static final Restaurant RESTAURANT6 = new Restaurant(RESTAURANT1_ID + 5, "У Гоги");

    public static Restaurant getNew() {
        return new Restaurant("New Restaurant");
    }
}
