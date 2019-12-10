package ru.javawebinar.topjavaGraduation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VoteUtil {

    public static boolean dateTimeFilter(LocalDateTime now, LocalDate lunch) {
        return now.toLocalDate().isEqual(lunch) && lunch.atStartOfDay().plusHours(23).isAfter(now);
    }
}
