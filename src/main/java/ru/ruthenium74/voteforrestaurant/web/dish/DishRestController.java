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
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.ruthenium74.voteforrestaurant.exception.NotFountException.getRestaurantNotFoundMessage;

@RestController
@RequestMapping(DishRestController.DISH_REST_URL)
public class DishRestController {
    public static final String DISH_REST_URL = "/rest/restaurants";

    private static final Logger log = getLogger(DishRestController.class);

    private CrudRestaurantRepository restaurantRepository;
    private CrudDishRepository dishRepository;

    public DishRestController(CrudRestaurantRepository restaurantRepository, CrudDishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @GetMapping(value = "/{restaurantId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("Get all dishes of restaurant with id={}", restaurantId);

        return dishRepository.getAll(restaurantId)
                .orElseThrow(() -> new NotFountException(getRestaurantNotFoundMessage(restaurantId)));
    }
}
