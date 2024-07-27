package com.example.lab7;

import com.example.lab7.lab5.StreamHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Lab5Controller {

    private static final ArrayList<String> VARIANTS = new ArrayList<>();

    @FXML
    private ChoiceBox<String> exampleChoice;

    @FXML
    private Button executeBtn;

    @FXML
    private TextArea outputBox;

    @FXML
    private TextArea inputBox;

    static
    {
        VARIANTS.add("Nothing is selected");
        VARIANTS.add("Example №1");
        VARIANTS.add("Example №2");
        VARIANTS.add("Example №3");
        VARIANTS.add("Example №4");
        VARIANTS.add("Example №5");
        VARIANTS.add("Example №6");
        VARIANTS.add("Example №7");
    }

    @FXML
    void initialize()
    {
        exampleChoice.getItems().addAll(VARIANTS);
        exampleChoice.setValue(VARIANTS.get(0));
        exampleChoice.setOnAction(e -> {
            outputBox.clear();
            switch (exampleChoice.getValue())
            {
                case "Example №1":
                case "Example №3":
                case "Example №4":
                    inputBox.setText("Integer List: 0, 1, 2, 3, 4");
                    break;

                case "Example №2":
                case "Example №5":
                    inputBox.setText("String List: \"One\", \"Two\", \"Three\", \"Four\", \"Five\"");
                    break;

                case "Example №6":
                    inputBox.setText("int array: 0, 1, 2, 3, 4");
                    break;

                case "Example №7":
                    inputBox.setText("String List: \"One\", \"Three\", \"Four\", \"Six\"");
                    break;
            }
        });
        executeBtn.setOnAction(e -> {
            if (exampleChoice.getValue() == "Nothing is selected")
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can not execute the laboratory");
                alert.setContentText("Please, select an example to execute");
                alert.showAndWait();
            }
            else
            {
                outputBox.setText(StreamHandler.start(exampleChoice.getValue()));
            }
        });
    }
}

