package com.novianto.restaurant.app.web.restaurant;

import com.novianto.restaurant.app.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantUserController extends AbstractRestaurantController{

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getWithEnableDishes(@PathVariable UUID id) {
        return super.getWithEnableDishes(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}
