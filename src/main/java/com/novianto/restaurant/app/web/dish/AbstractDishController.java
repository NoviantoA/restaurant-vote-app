package com.novianto.restaurant.app.web.dish;

import com.novianto.restaurant.app.model.Dish;
import com.novianto.restaurant.app.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

import static com.novianto.restaurant.app.util.ValidationUtil.assureIdConsistent;
import static com.novianto.restaurant.app.util.ValidationUtil.checkNew;

abstract public class AbstractDishController {

    @Autowired
    private DishRepository dishRepository;

    public Dish create(Dish dish, UUID restaurantId) {
        Assert.notNull(dish, "dish tidak boleh null");
        checkNew(dish);
        return dishRepository.save(dish, restaurantId);
    }

    public void update(Dish dish, UUID id, UUID restaurantId) {
        assureIdConsistent(dish, id);
        dishRepository.update(dish, restaurantId);
    }

    public Dish get(UUID id) {
        return dishRepository.get(id);
    }

    public Dish getEnabled(UUID id) {
        return dishRepository.getEnabled(id);
    }

    public List<Dish> getAllByRestaurant(UUID id) {
        return dishRepository.getAllByRestaurant(id);
    }

    public List<Dish> getAllEnabledByRestaurant(UUID id) {
        return dishRepository.getAllEnabledByRestaurant(id);
    }

    public void delete(UUID id) {
        dishRepository.delete(id);
    }

    public void enable(UUID id, Boolean enable) {
        dishRepository.enabled(id, enable);
    }
}
