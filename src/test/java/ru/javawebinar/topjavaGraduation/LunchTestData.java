package ru.javawebinar.topjavaGraduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjavaGraduation.model.Lunch;

import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjavaGraduation.TestUtil.readListFromJsonMvcResult;
import static ru.javawebinar.topjavaGraduation.model.AbstractBaseEntity.START_SEQ;

public class LunchTestData {
    public static final int LUNCH1_ID = START_SEQ + 2;

    public static final Lunch LUNCH1 = new Lunch(LUNCH1_ID, 2000.0, "Средне", of(2015, Month.MAY, 30));
    public static final Lunch LUNCH2 = new Lunch(LUNCH1_ID + 1, 1500.0, "Бедно", of(2015, Month.MAY, 30));
    public static final Lunch LUNCH3 = new Lunch(LUNCH1_ID + 2, 3000.0, "По богатому", of(2015, Month.MAY, 30));
    public static final Lunch LUNCH4 = new Lunch(LUNCH1_ID + 3, 3000.0, "По богатому", of(2015, Month.MAY, 31));
    public static final Lunch LUNCH5 = new Lunch(LUNCH1_ID + 4, 2500.0, "Средне", of(2015, Month.MAY, 31));
    public static final Lunch LUNCH6 = new Lunch(LUNCH1_ID + 5, 2000.0, "Бедно", of(2015, Month.MAY, 31));

    public static final List<Lunch> LUNCHES = List.of(LUNCH6, LUNCH5, LUNCH4, LUNCH3, LUNCH2, LUNCH1);

    public static Lunch getNew() {
        return new Lunch(null, 2000.0, "Созданный ужин", of(2015, Month.JUNE, 1));
    }

    public static Lunch getUpdated() {
        return new Lunch(LUNCH1_ID, 2500.0, "Обновленный ланч", LUNCH1.getDate());
    }

    public static void assertMatch(Lunch actual, Lunch expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Lunch> actual, Lunch... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Lunch> actual, Iterable<Lunch> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Lunch... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<Lunch> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Lunch.class)).isEqualTo(expected);
    }
}
