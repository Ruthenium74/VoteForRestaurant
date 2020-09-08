package ru.ruthenium74.voteforrestaurant;

import ru.ruthenium74.voteforrestaurant.model.Dish;

import java.util.List;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER =
            TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "date", "restaurant");

    public static final int DISH1_ID = 100008;
    public static final Dish DISH1 = new Dish(DISH1_ID, "Салат цезарь", 35000);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Борщ", 40000);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Пюре с котлетой", 50000);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Сбитень", 32000);

    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Салат королевский", 36000);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Паста карбонара", 65000);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Капучино", 120000);

    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Сельдь под шубой", 35000);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "Пельмени", 50000);
    public static final Dish DISH10 = new Dish(DISH1_ID + 9, "Морс", 15000);

    public static final Dish DISH11 = new Dish(DISH1_ID + 10, "Салат греческий", 38000);
    public static final Dish DISH12 = new Dish(DISH1_ID + 11, "Ребрышки фирменные", 120000);
    public static final Dish DISH13 = new Dish(DISH1_ID + 12, "Имбирный чай", 120000);

    public static final Dish DISH14 = new Dish(DISH1_ID + 13, "Oatmeal", 25000);
    public static final Dish DISH15 = new Dish(DISH1_ID + 14, "Beef sandwich", 35000);
    public static final Dish DISH16 = new Dish(DISH1_ID + 15, "Pale ale", 35000);

    public static final Dish DISH17 = new Dish(DISH1_ID + 16, "Хачапури по аджарски", 37000);
    public static final Dish DISH18 = new Dish(DISH1_ID + 17, "Чахохбили", 42000);
    public static final Dish DISH19 = new Dish(DISH1_ID + 18, "Саперави", 35000);

    public static final List<Dish> DISHES_OF_RESTAURANT1 = List.of(DISH1, DISH2, DISH3, DISH4);

    public static final int WRONG_DISH_ID = 55;

    public static Dish getNew() {
        return new Dish("new Dish", 40000);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated dish", 55000);
    }

}
