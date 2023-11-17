package com.novianto.restaurant.app.web.dish;

import com.novianto.restaurant.app.model.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/restaurants/{restaurantId}")
public class DishAdminController extends AbstractDishController{

    @GetMapping("/dishes/{dishId}")
    public Dish get(@PathVariable UUID restaurantId, @PathVariable UUID dishId) {
        return super.get(dishId);
    }

    @GetMapping("/dishes")
    public List<Dish> getAllByRestaurant(@PathVariable UUID restaurantId) {
        return super.getAllByRestaurant(restaurantId);
    }

    @PostMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@PathVariable UUID restaurantId, @Valid @RequestBody Dish dish) {
        Dish created = super.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable UUID restaurantId, UUID id) {
        super.update(dish, id, restaurantId);
    }

    @PatchMapping("/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable UUID restaurantId, @PathVariable UUID dishId, @RequestParam Boolean enable) {
        super.enable(dishId, enable);
    }

    @DeleteMapping("/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID restaurantId, @PathVariable UUID dishId) {
        super.delete(dishId);
    }
}
