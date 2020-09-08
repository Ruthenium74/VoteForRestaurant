package ru.ruthenium74.voteforrestaurant.web.dish;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ruthenium74.voteforrestaurant.exception.NotFountException;
import ru.ruthenium74.voteforrestaurant.model.Dish;
import ru.ruthenium74.voteforrestaurant.repository.CrudDishRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.ruthenium74.voteforrestaurant.exception.NotFountException.getRestaurantAndDishNotFoundMessage;
import static ru.ruthenium74.voteforrestaurant.exception.NotFountException.getRestaurantNotFoundMessage;

@RestController
@RequestMapping(DishRestController.DISH_REST_URL)
public class DishRestController {
    public static final String DISH_REST_URL = "/rest/restaurants";

    private static final Logger log = getLogger(DishRestController.class);

    private CrudDishRepository dishRepository;

    public DishRestController(CrudDishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping(value = "/{restaurantId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("Get all dishes of restaurant with id={}", restaurantId);

        return dishRepository.getAll(restaurantId)
                .orElseThrow(() -> new NotFountException(getRestaurantNotFoundMessage(restaurantId)));
    }

    @GetMapping(value = "/{restaurantId}/dishes/{dishId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable int restaurantId, @PathVariable int dishId) {
        log.info("Get dish with id={} of restaurant with id={}", dishId, restaurantId);

        return dishRepository.get(restaurantId, dishId)
                .orElseThrow(() -> new NotFountException(getRestaurantAndDishNotFoundMessage(restaurantId, dishId)));
    }
}
