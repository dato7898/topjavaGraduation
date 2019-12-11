package ru.javawebinar.topjavaGraduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjavaGraduation.model.Vote;
import ru.javawebinar.topjavaGraduation.repository.DataJpaVoteRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    @Autowired
    private DataJpaVoteRepository repository;

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Vote> getAllByLunch(int lunchId) {
        return repository.getAllByLunch(lunchId);
    }

    public Vote create(Vote vote, int userId, int lunchId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, lunchId);
    }

    public void update(Vote vote, int userId, int lunchId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, lunchId), vote.getId());
    }
}
