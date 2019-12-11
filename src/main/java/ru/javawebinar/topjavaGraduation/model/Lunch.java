package ru.javawebinar.topjavaGraduation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "lunch")
public class Lunch extends AbstractBaseEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

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
        return "Lunch{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
