package com.novianto.restaurant.app.web.restaurant;

import com.novianto.restaurant.app.model.Restaurant;
import com.novianto.restaurant.app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static com.novianto.restaurant.app.util.ValidationUtil.assureIdConsistent;
import static com.novianto.restaurant.app.util.ValidationUtil.checkNew;

abstract public class AbstractRestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant get(UUID id) {
        return restaurantRepository.get(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAllWithEnabledDishes();
    }

    public Restaurant getWithDishes(UUID id) {
        return restaurantRepository.getWithDishes(id);
    }

    public Restaurant getWithEnableDishes(UUID id) {
        return restaurantRepository.getWithEnabledDishes(id);
    }

    public Restaurant getWithVotes(UUID id) {
        return restaurantRepository.getWithVotes(id);
    }

    public void delete(UUID id) {
        restaurantRepository.delete(id);
    }

    public void update(Restaurant restaurant, UUID id) {
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }
}
