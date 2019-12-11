package ru.javawebinar.topjavaGraduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjavaGraduation.TestUtil.readListFromJsonMvcResult;
import static ru.javawebinar.topjavaGraduation.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;

public class VoteTestDate {
    public static final int VOTE1_ID = START_SEQ + 9;
    public static final int VOTE_TODAY_ID = VOTE1_ID + 4;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, of(2015, Month.MAY, 30, 12, 0));
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, of(2015, Month.MAY, 30, 12, 0));
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, of(2015, Month.MAY, 31, 12, 0));
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, of(2015, Month.MAY, 31, 12, 0));
    public static final Vote VOTE_TODAY = new Vote(VOTE_TODAY_ID, now());

    public static final List<Vote> USER_VOTES = List.of(VOTE3, VOTE1);

    public static Vote getNew() {
        return new Vote(null, now());
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_TODAY_ID, now());
    }

    public static Vote getUpdatedAfterElevenOClock() {
        return new Vote(VOTE_TODAY_ID, now().withHour(23));
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
        return result -> assertMatch(readListFromJsonMvcResult(result, Vote.class), expected);
    }
}
