package ru.ruthenium74.voteforrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;

import java.util.Set;

@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes dish where dish.date=current_date")
    Set<Restaurant> getAllWithDishes();
}
