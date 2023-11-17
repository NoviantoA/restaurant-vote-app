package com.novianto.restaurant.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
abstract public class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 128)
    protected String name;

    public AbstractNamedEntity(UUID id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name +
                ')';
    }
}
