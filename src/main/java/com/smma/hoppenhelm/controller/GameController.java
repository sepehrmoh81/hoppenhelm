package com.smma.hoppenhelm.controller;
import java.util.Random;

import com.smma.hoppenhelm.model.Blank;
import com.smma.hoppenhelm.model.GameState;
import com.smma.hoppenhelm.model.Ground;
import com.smma.hoppenhelm.model.Player;
import com.smma.hoppenhelm.model.SpikedGround;

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
   
    private Player player;
    private void loseGame(){

    }
    public void setPlayer(Player player) {
        this.player = player;
        this.playerName.setText(player.getName());
        updatePlayerInfo();
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
        playerView = player.draw();
        playerView.setTranslateY(60);
        objectParent.getChildren().addFirst(playerView);

        level1.setTranslateY(20);
        for(int i = 0 ; i < 4; i++){
            level1.getChildren().add(new Blank().draw());
            level0.getChildren().add(new Ground().draw());
        }
    }

    private void renderNextTile() {
        Random rand = new Random(System.currentTimeMillis());
        int nextGround = rand.nextInt(5);
        ImageView level1Image = new Blank().draw();
        if (nextGround > 3){
            GameState.addState(1);
            level1Image = new SpikedGround(10).draw();
        }
        level1.getChildren().add(level1Image);
        level0.getChildren().add(new Ground().draw());
    }

    private void animateMove(){
        moveButton.setDisable(true);
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
            if(GameState.moveStates() != 0)
                loseGame();
            renderNextTile();
        });

        level1Transition.setOnFinished(actionEvent -> {
            level1.getChildren().remove(0);
            level1.setTranslateX(0);
            moveButton.setDisable(false);
        });

        playerTransition.play();
        level0Transition.play();
        level1Transition.play();
    }
}