package com.novianto.restaurant.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity implements Serializable {

    @Column(name = "email", unique = true)
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Column(name = "password")
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 128)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Vote vote;

    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    public User(UUID id, String email, String password, Role... roles) {
        this(id, email, password, null, Arrays.asList(roles));
    }

    public User(UUID id, String email, String password, Vote vote, Collection<Role> roles) {
        super(id);
        this.email = email;
        this.password = password;
        this.vote = vote;
        setRoles(roles);
    }

    public void setVote(Vote vote) {
        this.vote = vote;
        vote.setUser(this);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", vote=" + (vote != null ? vote.getId().toString() : null) +
                ", roles=" + roles +
                '}';
    }
}
