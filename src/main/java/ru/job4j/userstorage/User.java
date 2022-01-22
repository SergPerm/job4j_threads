package ru.job4j.userstorage;

public class User {

    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void increase(int amount) {
        this.amount += amount;
    }

    public void decrease(int amount) {
        this.amount -= amount;
    }
}
