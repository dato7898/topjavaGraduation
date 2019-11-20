package ru.javawebinar.topjavaGraduation.model;

import java.time.LocalDate;

public class Lunch extends AbstractNamedEntity {

    private Double price;

    private LocalDate date;

    public Lunch(Integer id, String name) {
        super(id, name);
    }
}
