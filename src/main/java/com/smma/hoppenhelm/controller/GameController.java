package com.smma.hoppenhelm.controller;
import com.smma.hoppenhelm.model.Player;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameController {
    @FXML private Label playerName;
    @FXML private Label score;
    @FXML private VBox objectParent;
    @FXML private HBox level0;
    @FXML private HBox level1;

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
        this.playerName.setText(player.getName());
        updatePlayerInfo();
    }

    @FXML
    public void initialize() {
        renderGame();
    }

    @FXML
    private void onMove() {
        player.move();
        updatePlayerInfo();
        animateMove();
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

    private void renderGame(){
        for(int i = 0 ; i < 4; i++){
            Button button = new Button();
            button.setPrefWidth(90);
            button.setText("Button " + i);
            level0.getChildren().add(button);
        }
        for(int i = 0 ; i < 4; i++){
            Button button = new Button();
            button.setPrefWidth(90);
            button.setText("Button " + i);
            level1.getChildren().add(button);
        }
    }

    private void renderNextTile(){
        Button button0 = new Button();
        button0.setPrefWidth(90);
        button0.setText("Button " + (player.getX() + 3));
        level0.getChildren().add(button0);

        Button button1 = new Button();
        button1.setPrefWidth(90);
        button1.setText("Button " + (player.getX() + 3));
        level1.getChildren().add(button1);
    }

    private void animateMove(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(500));
        transition.setByX(-90);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.setNode(objectParent);
        transition.play();
        transition.setOnFinished(actionEvent -> {
            level0.getChildren().remove(0);
            level1.getChildren().remove(0);
            objectParent.setTranslateX(0);
            renderNextTile();
        });
    }
}