package ru.javawebinar.topjavaGraduation.web.lunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import ru.javawebinar.topjavaGraduation.model.Lunch;
import ru.javawebinar.topjavaGraduation.service.LunchService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.checkNew;

public abstract class AbstractLunchController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LunchService service;

    public Lunch get(int id) {
        log.info("get meal {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete meal {}", id);
        service.delete(id);
    }

    public List<Lunch> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Lunch create(Lunch lunch) {
        checkNew(lunch);
        log.info("create {}", lunch);
        return service.create(lunch);
    }

    public void update(Lunch lunch, int id) {
        assureIdConsistent(lunch, id);
        log.info("update {}", lunch);
        service.update(lunch);
    }

    public List<Lunch> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        log.info("getBetween dates({} - {})", startDate, endDate);
        return service.getBetweenDates(startDate, endDate);
    }
}
