package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.topjavaGraduation.model.Lunch;

public interface CrudLunchRepository extends JpaRepository<Lunch, Integer> {
}
