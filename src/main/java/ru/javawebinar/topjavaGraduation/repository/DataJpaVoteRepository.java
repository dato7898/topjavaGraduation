package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjavaGraduation.model.Vote;

@Repository
public class DataJpaVoteRepository {

    @Autowired
    private CrudVoteRepository voteRepository;

    @Autowired
    private CrudLunchRepository lunchRepository;

    @Autowired
    private CrudUserRepository userRepository;

    public Vote save(Vote vote, int lunchId, int userId) {
        if (!vote.isNew() && getByIdAndLunchIdAndUserId(vote.getId(), lunchId, userId) == null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        vote.setLunch(lunchRepository.getOne(lunchId));
        return voteRepository.save(vote);
    }

    public Vote getByIdAndLunchIdAndUserId(int id, int lunchId, int userId) {
        return voteRepository.findByIdAndLunchIdAndUserId(id, lunchId, userId);
    }
}
