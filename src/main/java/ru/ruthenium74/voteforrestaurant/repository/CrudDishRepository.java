package ru.ruthenium74.voteforrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ruthenium74.voteforrestaurant.model.Dish;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.date=current_date ORDER BY d.id")
    Optional<List<Dish>> getAll(@Param("restaurantId") int restaurantId);
}
