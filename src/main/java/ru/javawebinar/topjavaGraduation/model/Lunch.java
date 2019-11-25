package ru.javawebinar.topjavaGraduation.model;

import java.time.LocalDate;
import java.util.List;

public class Lunch extends AbstractBaseEntity {

    private Double price;

    private String description;

    private LocalDate date;

    private List<User> votes;

    public Lunch() {

    }

    public Lunch(Integer id, Double price, String description, LocalDate date) {
        super(id);
        this.price = price;
        this.description = description;
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
