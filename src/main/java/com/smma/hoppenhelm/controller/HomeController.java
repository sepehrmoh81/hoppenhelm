package com.smma.hoppenhelm.controller;

import java.io.IOException;

import com.smma.hoppenhelm.App;
import com.smma.hoppenhelm.model.PlayerScore;
import com.smma.hoppenhelm.model.drawable.Player;
import com.smma.hoppenhelm.util.DBTools;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HomeController {

    @FXML private TextField nameTextField;
    @FXML private TableView<PlayerScore> table;
    private final DBTools hiScores = new DBTools("hiScore.sqlite");

    @FXML
    public void initialize(){
        TableColumn<PlayerScore, String> name = new TableColumn<>("Name");
        TableColumn<PlayerScore, String> score = new TableColumn<>("Score");

        //Values
        name.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getName()));
        score.setCellValueFactory(p -> new ReadOnlyStringWrapper(String.valueOf(p.getValue().getScore())));

        //Size
        name.setPrefWidth(240);
        score.setPrefWidth(110);
        table.setColumnResizePolicy(resizeFeatures -> false);

        //Data
        ObservableList<PlayerScore> list = FXCollections.observableArrayList(hiScores.getAllScores());
        table.setItems(list);
        table.getColumns().setAll(name, score);

        //Sort
        score.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().setAll(score);
        table.setSortPolicy(playerScoreTableView -> false);
    }

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
            gameController.setPlayer(new Player(playerName, 3, 0));

            Stage stage = (Stage) nameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
