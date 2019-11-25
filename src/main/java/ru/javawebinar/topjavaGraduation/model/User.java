package ru.javawebinar.topjavaGraduation.model;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class User extends AbstractNamedEntity {

    private String password;

    private String email;

    private boolean enabled = true;

    private Date registered = new Date();

    private List<Lunch> votes;

    private Set<Role> roles;

    public User() {

    }

    public User(Integer id, String name, String password, String email, Role role, Role... roles) {
        this(id, name, password, email, true, new Date(),  EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String password, String email, boolean enabled, Date registered, Collection<Role> roles) {
        super(id, name);
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public List<Lunch> getVotes() {
        return votes;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
