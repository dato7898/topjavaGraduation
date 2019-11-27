package ru.javawebinar.topjavaGraduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lunch_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Lunch lunch;

    public Vote() {

    }

    public Vote(LocalDateTime dateTime, User user, Lunch lunch) {
        this(null, dateTime, user, lunch);
    }

    public Vote(Integer id, LocalDateTime dateTime, User user, Lunch lunch) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.lunch = lunch;
    }
}
