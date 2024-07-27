package com.example.lab7;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SwitcherController {
    //public static void switchToHome(MouseEvent event) {
    //    ((Node)event.getSource()).getScene().getWindow().hide();
    //}

    public static void openNewScene(final String windowPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource(windowPath));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}