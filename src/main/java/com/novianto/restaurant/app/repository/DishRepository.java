package com.novianto.restaurant.app.repository;

import com.novianto.restaurant.app.model.Dish;
import com.novianto.restaurant.app.repository.jpa.DishJpaRepository;
import com.novianto.restaurant.app.repository.jpa.RestaurantJpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.novianto.restaurant.app.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class DishRepository {

    public static final Sort SORT_ENABLED_NAME = Sort.by("enabled").descending().and(Sort.by("name"));

    public final DishJpaRepository dishJpaRepository;

    public final RestaurantJpaRepository restaurantJpaRepository;


    public DishRepository(DishJpaRepository dishJpaRepository, RestaurantJpaRepository restaurantJpaRepository) {
        this.dishJpaRepository = dishJpaRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    public Dish get(UUID id) {
        return checkNotFoundWithId(dishJpaRepository.findById(id).orElse(null), id);
    }

    public Dish getEnabled(UUID id) {
        return checkNotFoundWithId(dishJpaRepository.getEnabled(id), id);
    }

    public List<Dish> getAllByRestaurant(UUID restaurantId) {
        return dishJpaRepository.getAllByRestaurant(restaurantJpaRepository.getById(restaurantId), SORT_ENABLED_NAME);
    }

    public List<Dish> getAllEnabledByRestaurant(UUID id) {
        return dishJpaRepository.getAllEnabledByRestaurantId(id);
    }

    public Dish save(Dish dish, UUID restaurantId) {
        dish.setRestaurant(restaurantJpaRepository.getById(restaurantId));
        return dishJpaRepository.save(dish);
    }

    public void update(Dish dish, UUID restaurantId) {
        dish.setRestaurant(restaurantJpaRepository.getById(restaurantId));
        dishJpaRepository.save(dish);
    }

    public void delete(UUID id) {
        checkNotFoundWithId(dishJpaRepository.delete(id) != null, id);
    }

    @Transactional
    public void enabled(UUID id, Boolean enabled) {
        Dish dish = dishJpaRepository.getById(id);
        dish.setEnabled(enabled);
        dishJpaRepository.save(dish);
    }

}
