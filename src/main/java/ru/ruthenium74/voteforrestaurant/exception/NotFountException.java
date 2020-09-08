package ru.ruthenium74.voteforrestaurant.exception;

public class NotFountException extends RuntimeException {
    public NotFountException(String message) {
        super(message);
    }

    public static String getRestaurantNotFoundMessage(int restaurantId) {
        return String.format("Restaurant with id=%d not found.", restaurantId);
    }

    public static String getRestaurantAndDishNotFoundMessage(int restaurantId, int dishId) {
        return String.format("Dish with id=%d of restaurant with id=%d not found.", dishId, restaurantId);
    }
}
