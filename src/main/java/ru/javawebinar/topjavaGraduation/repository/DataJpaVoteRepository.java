package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository {
    @Autowired
    private CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Autowired
    private CrudLunchRepository lunchRepository;

    @Transactional
    public Vote save(Vote vote, int userId, int lunchId) {
        if (!vote.isNew() && get(vote.getId()) == null) {
            return null;
        }
        vote.setLunch(lunchRepository.getOne(lunchId));
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    public Vote get(int id) {
        return voteRepository.findById(id).orElse(null);
    }

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public List<Vote> findAllByUserId(int userId) {
       return voteRepository.findAllByUserId(userId);
    }

    public List<Vote> findAllByLunchId(int lunchId) {
        return voteRepository.findAllByLunchId(lunchId);
    }
}
