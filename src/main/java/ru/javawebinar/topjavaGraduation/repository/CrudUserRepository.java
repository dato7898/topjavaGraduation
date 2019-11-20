package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.topjavaGraduation.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
