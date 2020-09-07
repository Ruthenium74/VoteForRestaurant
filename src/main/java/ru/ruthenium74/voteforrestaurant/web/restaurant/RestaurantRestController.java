package ru.ruthenium74.voteforrestaurant.web.restaurant;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.ruthenium74.voteforrestaurant.exception.NotFountException;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.ruthenium74.voteforrestaurant.exception.NotFountException.getRestaurantNotFoundMessage;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_URL = "/rest/restaurants";

    private final CrudRestaurantRepository restaurantRepository;

    private static final Logger log = getLogger(RestaurantRestController.class);

    public RestaurantRestController(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getAll(@RequestParam @Nullable boolean withDishes) {
        if (withDishes) {
            log.info("Get all restaurants with dishes.");
            return restaurantRepository.getAllWithDishes();
        }
        log.info("Get all restaurants.");
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant get(@RequestParam @Nullable boolean withDishes, @PathVariable int restaurantId) {
        if (withDishes) {
            log.info("Get restaurant with id={} with dishes.", restaurantId);
            return restaurantRepository.getWithDishes(restaurantId)
                    .orElseThrow(() -> new NotFountException(getRestaurantNotFoundMessage(restaurantId)));
        }
        log.info("Get restaurant with id={}", restaurantId);
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFountException(getRestaurantNotFoundMessage(restaurantId)));
    }
}
