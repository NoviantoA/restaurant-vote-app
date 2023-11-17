package com.novianto.restaurant.app.util;

import com.novianto.restaurant.app.model.User;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {
    @NonNull
    private User user;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public UUID id() {
        return user.id();
    }
}
