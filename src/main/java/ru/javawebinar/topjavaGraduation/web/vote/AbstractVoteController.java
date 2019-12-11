package ru.javawebinar.topjavaGraduation.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjavaGraduation.model.Vote;
import ru.javawebinar.topjavaGraduation.service.VoteService;

import java.util.List;

import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjavaGraduation.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjavaGraduation.web.SecurityUtil.authUserId;

public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote get(int id) {
        int userId = authUserId();
        log.info("get vote {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public List<Vote> getAll() {
        int userId = authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public List<Vote> getAllByLunch(int lunchId) {
        log.info("getAll By lunchId {}", lunchId);
        return service.getAllByLunch(lunchId);
    }

    public Vote create(Vote vote, int lunchId) {
        int userId = authUserId();
        checkNew(vote);
        log.info("create {} for user {} and lunch {}", vote, userId, lunchId);
        return service.create(vote, userId, lunchId);
    }

    public void update(Vote vote, int id, int lunchId) {
        int userId = authUserId();
        assureIdConsistent(vote, id);
        log.info("update {} for user {} and lunch {}", vote, userId, lunchId);
        service.update(vote, userId, lunchId);
    }
}
