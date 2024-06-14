package com.smma.hoppenhelm.model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player implements IDrawable, Runnable {
    private final String name;
    private int x;
    private int health;
    private int coins;
    private ImageView playerImageView;
    private Thread attackThread;
    private AttackCallback callback;

    public Player(String name, int health, int coins) {
        this.name = name;
        this.health = health;
        this.coins = coins;
        this.x = 0;
    }

    @Override
    public ImageView draw() {
        String imagePath = "player_f1.png";
        playerImageView = new ImageView(new Image(imagePath));
        playerImageView.setFitWidth(90);
        playerImageView.setFitHeight(90);
        return playerImageView;
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

    public int getCoins() {
        return coins;
    }

    public void move() {
        x++;
    }

    public void attack(AttackCallback callback) {
        attackThread = new Thread(this);
        this.callback = callback;
        start();
    }

    public void addCoins(int value) {
        coins += value;
    }

    public void gainHealth(int value) {
        health += value;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean loseHealth(int value) {
        health -= value;
        return health > 0;
    }

    public void start() {
        attackThread.start();
    }

    @Override
    public void run() {
        try {
            playerImageView.setImage(new Image("player_f2.png"));
            Thread.sleep(20);
            playerImageView.setImage(new Image("player_f3.png"));
            Thread.sleep(20);
            playerImageView.setImage(new Image("player_f2.png"));
            Thread.sleep(20);
            playerImageView.setImage(new Image("player_f1.png"));
            Platform.runLater(() -> callback.onAttackDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface AttackCallback {
        void onAttackDone();
    }
}