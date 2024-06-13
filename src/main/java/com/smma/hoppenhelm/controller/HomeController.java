package com.smma.hoppenhelm.controller;

import java.io.IOException;

import com.smma.hoppenhelm.App;
import com.smma.hoppenhelm.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController {

    @FXML private TextField nameTextField;

    @FXML
    private void handleStartGame() {
        String playerName = nameTextField.getText();
        if (playerName.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Name cannot be empty!");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("game.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.setPlayer(new Player(playerName, 100, 0));

            Stage stage = (Stage) nameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
