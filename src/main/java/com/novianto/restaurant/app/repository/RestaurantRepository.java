package com.novianto.restaurant.app.repository;

import com.novianto.restaurant.app.model.Restaurant;
import com.novianto.restaurant.app.repository.jpa.RestaurantJpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.novianto.restaurant.app.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class RestaurantRepository {

    public static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final RestaurantJpaRepository restaurantJpaRepository;

    public RestaurantRepository(RestaurantJpaRepository restaurantJpaRepository) {
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantJpaRepository.save(restaurant);
    }

    public Restaurant get(UUID id) {
        return checkNotFoundWithId(restaurantJpaRepository.findById(id).orElse(null), id);
    }

    public Restaurant getRef(UUID id) {
        return restaurantJpaRepository.getById(id);
    }

    public void delete(UUID id) {
        checkNotFoundWithId(restaurantJpaRepository.delete(id) != null, id);
    }

    public Restaurant getWithDishes(UUID id) {
        return checkNotFoundWithId(restaurantJpaRepository.getWithEnabledDishes(id), id);
    }

    public Restaurant getWithEnabledDishes(UUID id) {
        return checkNotFoundWithId(restaurantJpaRepository.getWithEnabledDishes(id), id);
    }

    public Restaurant getWithVotes(UUID id) {
        return checkNotFoundWithId(restaurantJpaRepository.getWithVotes(id), id);
    }

    @Transactional
    public List<Restaurant> getAll() {
        return restaurantJpaRepository.findAll(SORT_NAME);
    }

    @Transactional
    public List<Restaurant> getAllWithEnabledDishes() {
        return restaurantJpaRepository.getAllWithEnabledDishes();
    }
}
