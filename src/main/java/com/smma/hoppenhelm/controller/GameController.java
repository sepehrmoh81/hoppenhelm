package com.smma.hoppenhelm.controller;
import com.smma.hoppenhelm.model.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class GameController {

    @FXML
    private Label welcomeLabel;

    private Player player;

    public void setPlayerName(String playerName) {
        this.player = new Player(0, 100);
        welcomeLabel.setText("Welcome, " + playerName + "!");
    }

    @FXML
    private void handleMove() {
        player.move();
        updatePlayerInfo();
    }

    @FXML
    private void handleAttack() {
        player.attack();
    }

    @FXML
    private void handleShield() {
        player.shield();
    }

    private void updatePlayerInfo() {
        welcomeLabel.setText("Player at position: " + player.getX() + " with health: " + player.getHealth());
    }
}
