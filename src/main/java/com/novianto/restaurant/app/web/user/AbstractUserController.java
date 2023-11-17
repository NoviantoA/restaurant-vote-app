package com.novianto.restaurant.app.web.user;

import com.novianto.restaurant.app.model.User;
import com.novianto.restaurant.app.repository.UserRepository;
import com.novianto.restaurant.app.to.UserTo;
import com.novianto.restaurant.app.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

abstract public class AbstractUserController {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    protected User create(UserTo userTo) {
        return userRepository.save(UserUtil.createNewFromTo(userTo));
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.delete(id);
    }

    public User get(UUID id) {
        return userRepository.get(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
