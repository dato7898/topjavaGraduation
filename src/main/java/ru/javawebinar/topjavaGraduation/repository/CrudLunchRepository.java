package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjavaGraduation.model.Lunch;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudLunchRepository extends JpaRepository<Lunch, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Lunch l WHERE l.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT l FROM Lunch l ORDER BY l.date DESC, l.id DESC")
    List<Lunch> getAll();

    @Query("SELECT l from Lunch l WHERE l.date >= :startDate AND l.date < :endDate ORDER BY l.date DESC, l.id DESC")
    List<Lunch> getBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
