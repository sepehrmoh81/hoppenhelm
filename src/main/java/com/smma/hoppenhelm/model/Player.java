package com.smma.hoppenhelm.model;

public class Player {
    private final String name;
    private int x;
    private int health;
    private int coins;

    public Player(String name, int health, int coins) {
        this.name = name;
        this.health = health;
        this.coins = coins;
        this.x = 0;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void move() {
        x++;
    }

    public void attack() {
        // Implement attack logic
    }

    public void shield() {
        // Implement shield logic
    }

    public void addCoins(int value) {
        coins += value;
    }

    public void gainHealth(int value) {
        health += value;
    }

    public void loseHealth(int value) {
        health -= value;
    }
}