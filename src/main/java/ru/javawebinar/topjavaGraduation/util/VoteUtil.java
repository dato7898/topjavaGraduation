package ru.javawebinar.topjavaGraduation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VoteUtil {

    public static boolean dateTimeFilter(LocalDateTime now, LocalDate lunch) {
        return now.toLocalDate().isEqual(lunch) && now.isBefore(lunch.atStartOfDay().plusHours(23));
    }
}
