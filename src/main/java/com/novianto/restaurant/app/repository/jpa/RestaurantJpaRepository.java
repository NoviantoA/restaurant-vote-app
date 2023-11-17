package com.novianto.restaurant.app.repository.jpa;

import com.novianto.restaurant.app.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, UUID> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :id")
    UUID delete(UUID id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithDishes(UUID id);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE r.id = :id AND d.enabled = true")
    Restaurant getWithEnabledDishes(UUID id);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithVotes(UUID id);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT OUTER JOIN FETCH r.dishes d WHERE d.enabled = true ORDER BY r.name")
    List<Restaurant> getAllWithEnabledDishes();
}
