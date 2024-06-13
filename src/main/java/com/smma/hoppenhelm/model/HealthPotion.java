package com.smma.hoppenhelm.model;

public class HealthPotion implements Consumable {
    private final int healthGainAmount;

    public HealthPotion(int healthGainAmount) {
        this.healthGainAmount = healthGainAmount;
    }

    public int getHealthGainAmount() {
        return healthGainAmount;
    }
}