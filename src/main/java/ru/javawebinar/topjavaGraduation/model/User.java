package ru.javawebinar.topjavaGraduation.model;

import java.util.List;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String password;

    private String email;

    private List<Lunch> votes;

    private Set<Role> roles;

    public User(Integer id, String name, String password, String email, Set<Role> roles) {
        super(id, name);
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
