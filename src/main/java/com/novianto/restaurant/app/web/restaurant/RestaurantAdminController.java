package com.novianto.restaurant.app.web.restaurant;

import com.novianto.restaurant.app.model.Restaurant;
import lombok.AllArgsConstructor;
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
@RequestMapping(value = RestaurantAdminController.REST_URL)
@AllArgsConstructor
public class RestaurantAdminController extends AbstractRestaurantController{

    static final String REST_URL = "/admin/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable UUID id) {
        return super.get(id);
    }

    @GetMapping("/{id}/with-dishes")
    public Restaurant getWithDishes(@PathVariable UUID id) {
        return super.getWithDishes(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        super.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
       Restaurant create = super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(create.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(create);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Update(@Valid @RequestBody Restaurant restaurant, @PathVariable UUID id) {
        super.update(restaurant, id);
    }
}
