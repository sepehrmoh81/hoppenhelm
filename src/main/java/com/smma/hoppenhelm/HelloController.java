import java.io.IOException;

import com.smma.hoppenhelm.controller.GameController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TextField nameTextField;

    @FXML
    private void handleStartGame() {
        String playerName = nameTextField.getText();
        if (playerName.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Name cannot be empty!");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.setPlayerName(playerName);

            Stage stage = (Stage) nameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
