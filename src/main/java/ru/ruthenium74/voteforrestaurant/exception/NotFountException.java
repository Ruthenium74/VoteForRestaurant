package ru.ruthenium74.voteforrestaurant.exception;

public class NotFountException extends RuntimeException {
    public NotFountException(String message) {
        super(message);
    }

    public static String getRestaurantNotFoundMessage(int restaurantId) {
        return String.format("Restaurant with id=%d not found.", restaurantId);
    }
}
