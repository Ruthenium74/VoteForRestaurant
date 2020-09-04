package ru.ruthenium74.voteforrestaurant.web.restaurant;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;

import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {
    public static final String REST_URL = "/rest/admin/restaurants";
    private static final Logger log = getLogger(AdminRestaurantRestController.class);

    private CrudRestaurantRepository restaurantRepository;

    public AdminRestaurantRestController(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        log.info("Create new restaurant: {}", restaurant);

        Restaurant created = restaurantRepository.save(restaurant);

        URI uriOfNewResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResources).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        log.info("Update restaurant {} with id={}", restaurant, id);

        restaurantRepository.save(restaurant);
    }
}
