package ru.javawebinar.topjavaGraduation.model;

public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    private String name;

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}