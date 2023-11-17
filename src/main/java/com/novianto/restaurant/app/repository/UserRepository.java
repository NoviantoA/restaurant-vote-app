package com.novianto.restaurant.app.repository;

import com.novianto.restaurant.app.model.User;
import com.novianto.restaurant.app.repository.jpa.UserJpaRepository;
import com.novianto.restaurant.app.util.UserUtil;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.novianto.restaurant.app.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class UserRepository {

    public static final Sort SORT_EMAIL = Sort.by(Sort.Direction.ASC, "email");

    private final UserJpaRepository userJpaRepository;

    private final PasswordEncoder passwordEncoder;

    public UserRepository(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        return userJpaRepository.save(UserUtil.preparedToSave(user, passwordEncoder));
    }

    public void delete(UUID id) {
        checkNotFoundWithId(userJpaRepository.delete(id) != null, id);
    }

    public User get(UUID id) {
        return checkNotFoundWithId(userJpaRepository.findById(id).orElse(null), id);
    }

    public User getRef(UUID id) {
        return userJpaRepository.getById(id);
    }

    public User getByEmail(String email) {
        return userJpaRepository.findByEmailIgnoreCase(email).orElse(null);
    }

    public List<User> getAll() {
        return userJpaRepository.findAll(SORT_EMAIL);
    }
}
