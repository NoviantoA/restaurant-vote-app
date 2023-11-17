package com.novianto.restaurant.app.web.user;

import com.novianto.restaurant.app.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController extends AbstractUserController {

    @GetMapping("/{id}")
    public User get(@PathVariable UUID id) {
        return  super.get(id);
    }

    @GetMapping
    public List<User> getAll() {
        return  super.getAll();
    }
}
