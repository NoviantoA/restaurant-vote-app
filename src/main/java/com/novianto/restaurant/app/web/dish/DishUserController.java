package com.novianto.restaurant.app.web.dish;

import com.novianto.restaurant.app.model.Dish;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class DishUserController extends AbstractDishController {

    @GetMapping("/restaurants/{restaurantId}/dishes")
    public List<Dish> getAllEnabledByRestaurant(@PathVariable UUID restaurantId) {
        return super.getAllEnabledByRestaurant(restaurantId);
    }

    @GetMapping("/dishes/{id}")
    public Dish getEnabled(@PathVariable UUID id) {
        return super.getEnabled(id);
    }
}
