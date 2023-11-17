package com.novianto.restaurant.app.util;

import com.novianto.restaurant.app.model.Role;
import com.novianto.restaurant.app.model.User;
import com.novianto.restaurant.app.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User preparedToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
