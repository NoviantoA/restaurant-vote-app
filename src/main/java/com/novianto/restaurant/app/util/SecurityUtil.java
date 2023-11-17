package com.novianto.restaurant.app.util;

import com.novianto.restaurant.app.model.AbstractBaseEntity;

import java.util.UUID;

public class SecurityUtil {
    private static UUID id = UUID.randomUUID();

    private SecurityUtil() {
    }

    public static UUID authUserId() {
        return id;
    }

    public static void setAuthUserId(UUID id) {
        SecurityUtil.id = id;
    }
}
