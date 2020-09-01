package ru.ruthenium74.voteforrestaurant.web.dish;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ruthenium74.voteforrestaurant.model.Dish;
import ru.ruthenium74.voteforrestaurant.repository.CrudDishRepository;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;
import ru.ruthenium74.voteforrestaurant.web.restaurant.AdminRestaurantRestController;

import java.net.URI;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {
    private CrudDishRepository dishRepository;
    private CrudRestaurantRepository restaurantRepository;

    public AdminDishRestController(CrudDishRepository dishRepository, CrudRestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping(value = "/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@PathVariable int id, @RequestBody Dish dish) {
        dish.setRestaurant(restaurantRepository.getOne(id));
        Dish created = dishRepository.save(dish);

        URI uriOfNewResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(AdminRestaurantRestController.REST_URL + "/{id}/dishes/{dish_id}")
                .buildAndExpand(id, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResources).body(created);
    }
}
