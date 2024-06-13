package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player implements IDrawable {
    private final String name;
    private int x;
    private int health;
    private int coins;
    private final String imagePath = "amogus.png";

    public Player(String name, int health, int coins) {
        this.name = name;
        this.health = health;
        this.coins = coins;
        this.x = 0;
    }
    @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(imagePath));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
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