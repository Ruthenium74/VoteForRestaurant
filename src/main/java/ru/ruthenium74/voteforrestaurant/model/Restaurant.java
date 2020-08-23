package ru.ruthenium74.voteforrestaurant.model;

import java.util.Set;

public class Restaurant extends AbstractNamedEntity {

    private Set<Dish> dishes;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
