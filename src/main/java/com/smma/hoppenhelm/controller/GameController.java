package com.smma.hoppenhelm.controller;
import java.util.Random;

import com.smma.hoppenhelm.model.*;
import com.smma.hoppenhelm.model.HealthPotion;
import com.smma.hoppenhelm.model.Torch;

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
    @FXML private Button attackButton;
    @FXML private Button moveButton;
    @FXML private Button shieldButton;
    @FXML
    private ImageView playerView;
    private boolean isRunning = true;
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
        this.playerName.setText(player.getName());
        updatePlayerInfo();
        renderGame();
    }
    private void loseGame(){
        isRunning = false;
        score.setText("!GAME OVER!" + "Score: " + player.getX() + " Coins: " + player.getCoins());
    }

    @FXML
    private void onMove() {
        if(isRunning){
        moveButton.setDisable(true); //Disable move button to avoid bugs

        player.move();
        updatePlayerInfo();
        animateMove();
            switch (GameState.moveStates()) {
                case 1 -> player.addCoins(1);
                case 2 -> player.gainHealth(1);
                case 3, 4 -> {
                    if (!player.loseHealth(1)) loseGame();
                }
                default -> {
                }
            }
        }
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
        score.setText("Health: " + player.getHealth() + "Score: " + player.getX() + " Coins: " + player.getCoins());
    }

    private void renderGame(){
        //Init player view
        playerView = player.draw();
        playerView.setTranslateY(60);

        //Init level 1 view
        level1.setTranslateY(20);

        //Draw views
        objectParent.getChildren().addFirst(playerView);
        for(int i = 0 ; i < 4; i++){
            level1.getChildren().add(new Blank().draw());
            level0.getChildren().add(new Ground().draw());
        }
    }

    private void renderNextTile() {
        Random rand = new Random(System.currentTimeMillis());
        int nextGround = rand.nextInt(7);

        ImageView level1Image;
        switch (nextGround) {
            case 0 -> {
                GameState.addState(2);
                level1Image = new HealthPotion(1).draw();
            }
            case 1 -> {
                GameState.addState(0);
                level1Image = new Torch().draw();
            }
            case 2 -> {
                GameState.addState(1);
                level1Image = new Coin(1).draw();
            }
            case 3 -> {
                GameState.addState(4);
                level1Image = new Enemy().draw();
            }
            case 4 -> {
                GameState.addState(3);
                SpikedGround spikedGround = new SpikedGround(1000);
                level1Image = spikedGround.draw();
                spikedGround.start();
            }
            default -> {
                GameState.addState(0);
                level1Image = new Blank().draw();
            }
        }

        level1.getChildren().add(level1Image);
        level0.getChildren().add(new Ground().draw());
    }

    private void animateMove(){
        TranslateTransition level0Transition = new TranslateTransition(Duration.millis(500));
        level0Transition.setByX(-90);
        level0Transition.setCycleCount(1);
        level0Transition.setAutoReverse(false);
        level0Transition.setNode(level0);

        TranslateTransition level1Transition = new TranslateTransition(Duration.millis(500));
        level1Transition.setByX(-90);
        level1Transition.setCycleCount(1);
        level1Transition.setAutoReverse(false);
        level1Transition.setNode(level1);

        TranslateTransition playerTransition = new TranslateTransition(Duration.millis(250));
        playerTransition.setByY(-100);
        playerTransition.setCycleCount(2);
        playerTransition.setAutoReverse(true);
        playerTransition.setNode(playerView);

        level0Transition.setOnFinished(actionEvent -> {
            level0.getChildren().remove(0);
            level0.setTranslateX(0);
        });

        level1Transition.setOnFinished(actionEvent -> {
            level1.getChildren().remove(0);
            level1.setTranslateX(0);
            renderNextTile();
            moveButton.setDisable(false);
        });

        playerTransition.play();
        level0Transition.play();
        level1Transition.play();
    }
}