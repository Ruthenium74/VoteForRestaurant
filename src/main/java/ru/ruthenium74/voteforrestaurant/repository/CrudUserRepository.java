package ru.ruthenium74.voteforrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ruthenium74.voteforrestaurant.model.User;

@Repository
public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
