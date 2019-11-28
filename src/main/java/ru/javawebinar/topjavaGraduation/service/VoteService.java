package ru.javawebinar.topjavaGraduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjavaGraduation.model.Vote;
import ru.javawebinar.topjavaGraduation.repository.CrudVoteRepository;
import ru.javawebinar.topjavaGraduation.repository.DataJpaVoteRepository;

import java.util.List;

import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    @Autowired
    private DataJpaVoteRepository repository;

    public Vote getById(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Vote> getAllByUserId(int userId) {
        return repository.findAllByUserId(userId);
    }

    public List<Vote> getAllByLunchId(int lunchId) {
        return repository.findAllByLunchId(lunchId);
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    public Vote update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote), vote.getId());
    }
}
