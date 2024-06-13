package com.smma.hoppenhelm.model;

public class Coin implements Consumable {
    private final int value;

    public Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}