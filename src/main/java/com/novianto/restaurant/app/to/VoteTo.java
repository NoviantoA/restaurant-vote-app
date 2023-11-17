package com.novianto.restaurant.app.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class VoteTo {

    private UUID id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Jakarta")
    private LocalDateTime dateTime;

    private UUID userId;

    private UUID restaurantId;
}
