package com.novianto.restaurant.app.to;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class VoteResultTo {

    private UUID restaurantId;
    private String name;
    @Size(min = 1)
    private Long votesNumber;
}
