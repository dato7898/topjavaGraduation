package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findAllByUserId(int userId);

    List<Vote> findAllByLunchId(int lunchId);

    Vote findByIdAndLunchIdAndUserId(int id, int lunchId, int userId);
}
