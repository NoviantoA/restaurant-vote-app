package com.novianto.restaurant.app.web.user;

import com.novianto.restaurant.app.model.User;
import com.novianto.restaurant.app.to.UserTo;
import com.novianto.restaurant.app.util.AuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.net.URI;

import static com.novianto.restaurant.app.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = ProfileController.REST_UI, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController{

    public static final String REST_UI = "/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal @ApiIgnore AuthUser authUser) {
        return authUser.getUser();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        User create = super.create(userTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build().toUri();

        return ResponseEntity.created(uriOfNewResource).body(create);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @AuthenticationPrincipal @ApiIgnore AuthUser authUser) {
        assureIdConsistent(user, authUser.id());
        super.update(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal @ApiIgnore AuthUser authUser) {
        super.delete(authUser.id());
    }
}
