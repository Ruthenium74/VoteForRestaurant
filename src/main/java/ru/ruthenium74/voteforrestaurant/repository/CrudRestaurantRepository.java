package ru.ruthenium74.voteforrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;

@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
