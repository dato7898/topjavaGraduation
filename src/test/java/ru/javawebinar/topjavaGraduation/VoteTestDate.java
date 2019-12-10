package ru.javawebinar.topjavaGraduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjavaGraduation.TestUtil.readListFromJsonMvcResult;
import static ru.javawebinar.topjavaGraduation.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;

public class VoteTestDate {
    public static final int VOTE1_ID = START_SEQ + 8;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, of(2016, Month.MAY, 30, 12, 0));
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, of(2015, Month.MAY, 30, 12, 0));
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, of(2015, Month.MAY, 31, 12, 0));
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, of(2015, Month.MAY, 31, 12, 0));

    public static final List<Vote> LUNCHES = List.of(VOTE4, VOTE3, VOTE2, VOTE1);

    public static Vote getNew() {
        return new Vote(null, of(2015, Month.JUNE, 1, 12, 0));
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, VOTE1.getDateTime());
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "lunch");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "lunch").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Vote... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<Vote> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Vote.class)).isEqualTo(expected);
    }
}
