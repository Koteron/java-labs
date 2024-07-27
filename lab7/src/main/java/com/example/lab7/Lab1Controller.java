package com.example.lab7;
import com.example.lab7.lab1.*;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class Lab1Controller {

    @FXML
    private TextArea Output;

    @FXML
    private TextArea InputCommands;

    @FXML
    private TextArea XOffset;

    @FXML
    private TextArea YOffset;

    @FXML
    private Button manaBtn;

    @FXML
    private Button restBtn;

    @FXML
    private Button executeBtn;

    @FXML
    private Button rideBtn;

    @FXML
    private Button teleportBtn;

    @FXML
    private Button walkBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button exitBtn;

    private void checkAndAppend(String command)
    {
        try
        {
            int x = Integer.parseInt(XOffset.getText());
            int y = Integer.parseInt(YOffset.getText());
            InputCommands.appendText(command + " " + x + " " + y + "\n");
        }
        catch (NumberFormatException exc)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("X or Y offset is not a number");
            alert.setContentText("Please, check if your inputs are numbers");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        manaBtn.setOnAction(e -> {
            InputCommands.appendText("Mana\n");
        });
        restBtn.setOnAction(e -> {
            InputCommands.appendText("Rest\n");
        });
        rideBtn.setOnAction(e -> {
            checkAndAppend("Ride");
        });
        walkBtn.setOnAction(e -> {
            checkAndAppend("Walk");
        });
        teleportBtn.setOnAction(e -> {
            checkAndAppend("Teleport");
        });
        executeBtn.setOnAction(e -> {
            if (!InputCommands.getText().contains("Exit\n"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Exit command not found");
                alert.setContentText("Please, check if your input contains \"Exit\" command");
                alert.showAndWait();
            }
            else {
                Output.setText(HeroHandler.start(InputCommands.getText()));
            }
        });
        clearBtn.setOnAction(e -> {
            InputCommands.clear();
            XOffset.clear();
            YOffset.clear();
        });
        exitBtn.setOnAction(e -> {
            InputCommands.appendText("Exit\n");
        });

    }

}