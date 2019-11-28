package ru.javawebinar.topjavaGraduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjavaGraduation.model.Lunch;
import ru.javawebinar.topjavaGraduation.repository.CrudLunchRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class LunchService {

    @Autowired
    private CrudLunchRepository repository;

    public Lunch get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public List<Lunch> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        return repository.getBetween(startDate, endDate);
    }

    public List<Lunch> getAll() {
        return repository.getAll();
    }

    public void update(Lunch lunch) {
        Assert.notNull(lunch, "lunch must not be null");
        checkNotFoundWithId(repository.save(lunch), lunch.getId());
    }

    public Lunch create(Lunch lunch) {
        Assert.notNull(lunch, "lunch must not be null");
        return repository.save(lunch);
    }
}
