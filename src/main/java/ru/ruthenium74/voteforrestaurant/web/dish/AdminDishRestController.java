package ru.ruthenium74.voteforrestaurant.web.dish;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ruthenium74.voteforrestaurant.exception.NotFountException;
import ru.ruthenium74.voteforrestaurant.model.Dish;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.repository.CrudDishRepository;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;
import ru.ruthenium74.voteforrestaurant.web.restaurant.AdminRestaurantRestController;

import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {
    private CrudDishRepository dishRepository;
    private CrudRestaurantRepository restaurantRepository;

    private static final Logger log = getLogger(AdminDishRestController.class);

    public AdminDishRestController(CrudDishRepository dishRepository, CrudRestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping(value = "/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@PathVariable int id, @RequestBody Dish dish) {
        checkNew(dish);

        log.info("Create new dish {} for restaurant with id={}", dish, id);

        dish.setRestaurant(restaurantRepository.getOne(id));
        Dish created = dishRepository.save(dish);

        URI uriOfNewResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(AdminRestaurantRestController.REST_URL + "/{id}/dishes/{dish_id}")
                .buildAndExpand(id, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResources).body(created);
    }

    @PutMapping(value = "/{restaurantId}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId, @PathVariable int dishId, @RequestBody Dish dish) {
        assureIdConsistent(dish, dishId);

        log.info("Update dish {} for restaurant with id={}", dish, restaurantId);

        Restaurant restaurant = getRestaurantIfExist(restaurantId);

        assureRestaurantHasDish(restaurant, dishId);
        dish.setRestaurant(restaurant);
        dishRepository.save(dish);
    }

    @DeleteMapping(value = "/{restaurantId}/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int dishId) {
        log.info("Delete dish with id={} for restaurant with id={}", dishId, restaurantId);

        Restaurant restaurant = getRestaurantIfExist(restaurantId);
        assureRestaurantHasDish(restaurant, dishId);

        dishRepository.deleteById(dishId);
    }

    private Restaurant getRestaurantIfExist(int restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new NotFountException(String.format("Restaurant with id=%d not found.", restaurantId))
        );
    }

    private void assureRestaurantHasDish(Restaurant restaurant, int dishId) {
        if (restaurant.getDishes().stream().noneMatch(dish -> dish.getId().equals(dishId))) {
            throw new NotFountException(
                    String.format("Dish with id=%d and with restaurantId=%d not found.", dishId, restaurant.getId())
            );
        }
    }
}
