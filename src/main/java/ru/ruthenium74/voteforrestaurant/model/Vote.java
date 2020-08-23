package ru.ruthenium74.voteforrestaurant.model;

import java.util.Date;

public class Vote extends AbstractBaseEntity {
    private Date date = new Date();

    private User user;

    private Restaurant restaurant;

    public Vote(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
