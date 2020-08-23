package ru.ruthenium74.voteforrestaurant.model;

public abstract class AbstractBaseEntity {
    public final static int START_SEQ = 100000;

    protected Integer id;

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return '{' + getClass().getSimpleName() +
                " id=" + id +
                '}';
    }
}
