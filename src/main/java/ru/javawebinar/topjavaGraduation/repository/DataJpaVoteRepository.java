package ru.javawebinar.topjavaGraduation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjavaGraduation.model.Lunch;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjavaGraduation.util.VoteUtil.dateTimeFilter;

@Repository
public class DataJpaVoteRepository {
    @Autowired
    private CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Autowired
    private CrudLunchRepository lunchRepository;

    @Transactional
    public Vote save(Vote vote, int userId, int lunchId, LocalDateTime now) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        Lunch lunch = lunchRepository.getOne(lunchId);
        if (!dateTimeFilter(now, lunch.getDate())) {
            return null;
        }
        vote.setLunch(lunch);
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    public Vote get(int id, int userId) {
        return voteRepository.findById(id).filter(lunch -> lunch.getUser().getId() == userId).orElse(null);
    }

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public List<Vote> getAllByUser(int userId) {
       return voteRepository.getAllByUser(userId);
    }

    public List<Vote> getAllByLunch(int lunchId) {
        return voteRepository.getAllByLunch(lunchId);
    }
}
