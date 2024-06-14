package com.smma.hoppenhelm.controller;

import java.util.Random;

import com.smma.hoppenhelm.model.Blank;
import com.smma.hoppenhelm.model.Coin;
import com.smma.hoppenhelm.model.Enemy;
import com.smma.hoppenhelm.model.GameState;
import com.smma.hoppenhelm.model.GameState.State;
import com.smma.hoppenhelm.model.Ground;
import com.smma.hoppenhelm.model.HealthPotion;
import com.smma.hoppenhelm.model.Player;
import com.smma.hoppenhelm.model.SpikedGround;
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
    @FXML
    private Label playerName;
    @FXML
    private Label score;
    @FXML
    private VBox objectParent;
    @FXML
    private HBox level0;
    @FXML
    private HBox level1;
    @FXML
    private Button attackButton;
    @FXML
    private Button moveButton;
    @FXML
    private Button shieldButton;
    @FXML
    private ImageView playerView;
    private boolean isRunning = true;
    private Player player;
    private boolean shouldConsume = false;

    public void setPlayer(Player player) {
        this.player = player;
        this.playerName.setText(player.getName());
        updatePlayerInfo();
        renderGame();
    }

    private void loseGame() {
        isRunning = false;
    }

    @FXML
    private void onMove() {
        if (isRunning) {
            moveButton.setDisable(true); //Disable move button to avoid bugs

            player.move();
            switch (GameState.moveStates()) {
                case COIN -> {
                    System.out.println("Coin");
                    player.addCoins(1);
                    shouldConsume = true;
                }
                case POTION -> {
                    System.out.println("Potion");
                    player.gainHealth(1);
                    shouldConsume = true;
                }
                case ENEMY -> {
                    System.out.println("Enemy");
                    if (!player.loseHealth(1)) loseGame();
                }
                case SPIKE -> {
                    System.out.println("Spike");
                    SpikedGround sg = (SpikedGround) GameState.getObjects()[0];
                    if(sg.isExtended()) {
                        if (!player.loseHealth(1)) loseGame();
                    }
                }
                default -> {
                }
            }
            animateMove();
            updatePlayerInfo();
        }
    }

    @FXML
    private void onAttack() {
        player.attack();
        if(GameState.getPlayerFront() == State.ENEMY)
        {
            GameState.modifyState(State.BLANK, 1);
            level1.getChildren().remove(1);
            level1.getChildren().add(1, new Blank().draw());
        }
        
    }

    @FXML
    private void onShield() {
        player.shield();
    }

    private void updatePlayerInfo() {
        if (player.getHealth() == 0)
            score.setText("!GAME OVER!" + " Score: " + player.getX() + " Coins: " + player.getCoins());
        else
            score.setText("Health: " + player.getHealth() + " Score: " + player.getX() + " Coins: " + player.getCoins());
    }

    private void renderGame() {
        //Init player view
        playerView = player.draw();
        playerView.setTranslateY(60);
        GameState.initStates();
        //Init level 1 view
        level1.setTranslateY(20);

        //Draw views
        objectParent.getChildren().addFirst(playerView);
        for (int i = 0; i < 4; i++) {
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
                HealthPotion hp = new HealthPotion(1);
                GameState.addState(State.POTION, hp);
                level1Image = hp.draw();
            }
            case 1 -> {
                Torch t = new Torch();
                GameState.addState(State.BLANK, t);
                level1Image = t.draw();
            }
            case 2 -> {
                Coin c = new Coin(1);
                GameState.addState(State.COIN, c);
                level1Image = c.draw();
            }
            case 3 -> {
                Enemy e = new Enemy();
                GameState.addState(State.ENEMY, e);
                level1Image = e.draw();
            }
            case 4 -> {
                SpikedGround sp = new SpikedGround(1000);
                GameState.addState(State.SPIKE, sp);
                level1Image = sp.draw();
                sp.start();
            }
            default -> {
                Blank b = new Blank();
                GameState.addState(State.BLANK, b);
                level1Image = b.draw();
            }
        }

        level1.getChildren().add(level1Image);
        level0.getChildren().add(new Ground().draw());
    }

    private void animateMove() {
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
            level0.getChildren().removeFirst();
            level0.setTranslateX(0);
        });

        level1Transition.setOnFinished(actionEvent -> {
            level1.getChildren().removeFirst();
            level1.setTranslateX(0);
            renderNextTile();
            if(shouldConsume) consume();
            shouldConsume = false;
            moveButton.setDisable(false);
        });

        playerTransition.play();
        level0Transition.play();
        level1Transition.play();
    }

    private void consume(){
        level1.getChildren().removeFirst();
        level1.getChildren().addFirst(new Blank().draw());
    }
}