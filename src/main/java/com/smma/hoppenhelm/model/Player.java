package com.smma.hoppenhelm.model;

public class Player {
    private int x;
    private int health;
    private int coins;

    public void move() {
        // Implement movement logic
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