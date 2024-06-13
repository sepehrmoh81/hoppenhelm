package com.smma.hoppenhelm.controller;
import com.smma.hoppenhelm.model.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameController {
    @FXML private Label playerName;
    @FXML private Label score;
    @FXML private HBox objectParent;

    private Player player;
    private ImageView img;
    public void setPlayer(Player player) {
        this.player = player;
        this.playerName.setText(player.getName());
        updatePlayerInfo();
    }

    @FXML
    private void onMove() {
        player.move();

        updatePlayerInfo();
    }

    @FXML
    private void onAttack() {
        player.attack();
    }

    @FXML
    private void onShield() {
        player.shield();
    }

    private void updatePlayerInfo() {
        score.setText(String.valueOf(player.getX()));
    }
}
