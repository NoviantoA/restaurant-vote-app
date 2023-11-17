package com.novianto.restaurant.app.util;

import java.time.LocalTime;

public class TimeLimitUtil {

    private static final LocalTime TIME_LIMIT = LocalTime.of(11, 0);

    private TimeLimitUtil() {

    }

    public static Boolean canChangeTodayVote() {
        return LocalTime.now().isBefore(TIME_LIMIT);
    }
}
