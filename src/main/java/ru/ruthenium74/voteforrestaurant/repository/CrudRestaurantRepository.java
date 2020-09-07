package ru.ruthenium74.voteforrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishes dish where dish.date=current_date ORDER BY r.id")
    List<Restaurant> getAllWithDishes();

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes dish WHERE r.id=:restaurantId AND dish.date=current_date")
    Optional<Restaurant> getWithDishes(@Param("restaurantId") int restaurantId);
}
