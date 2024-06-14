package com.smma.hoppenhelm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTools {
    private Connection connection;

    public DBTools(String dbFileName) {
        try {
            // Open a connection to the SQLite database
            String url = "jdbc:sqlite:" + dbFileName;
            connection = DriverManager.getConnection(url);
            createTableIfNotExists();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to create the scores table if it does not exist
    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS scores ("
                                + "name TEXT PRIMARY KEY, "
                                + "score INTEGER NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to get all player names and their high scores
    public ResultSet getAllScores() {
        String selectAllSQL = "SELECT name, score FROM scores ORDER BY score";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(selectAllSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Method to create or update a player's score
    public void createOrUpdateScore(String name, int score) {
        String selectSQL = "SELECT score FROM scores WHERE name = ?";
        String insertSQL = "INSERT INTO scores(name, score) VALUES(?, ?)";
        String updateSQL = "UPDATE scores SET score = ? WHERE name = ?";

        try {
            // Check if the player already exists in the database
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int existingScore = rs.getInt("score");

                // Update the score only if the new score is higher
                if (score > existingScore) {
                    PreparedStatement updatePstmt = connection.prepareStatement(updateSQL);
                    updatePstmt.setInt(1, score);
                    updatePstmt.setString(2, name);
                    updatePstmt.executeUpdate();
                }
            } else {
                // If the player does not exist, insert a new record
                PreparedStatement insertPstmt = connection.prepareStatement(insertSQL);
                insertPstmt.setString(1, name);
                insertPstmt.setInt(2, score);
                insertPstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Close the connection when the object is destroyed
    @Override
    protected void finalize() throws Throwable {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        super.finalize();
    }
}
