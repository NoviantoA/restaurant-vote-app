package com.novianto.restaurant.app.repository.jpa;

import com.novianto.restaurant.app.model.Dish;
import com.novianto.restaurant.app.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface DishJpaRepository extends JpaRepository<Dish, UUID> {

    List<Dish> getAllByRestaurant(Restaurant restaurant, Sort sort);

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.enabled = true")
    Dish getEnabled(UUID id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :id AND d.enabled = true ORDER BY d.name")
    List<Dish> getAllEnabledByRestaurantId(UUID id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id = :id")
    UUID delete(UUID id);
}
