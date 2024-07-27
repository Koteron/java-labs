package com.example.lab7;

import com.example.lab7.lab5.EmptyCollectionException;
import com.example.lab7.lab5.StreamHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Objects;

public class Lab5Controller {

    private static final ArrayList<String> VARIANTS = new ArrayList<>();

    @FXML
    private ChoiceBox<String> functionChoice;

    @FXML
    private Button executeBtn;

    @FXML
    private TextArea outputBox;

    @FXML
    private TextArea inputBox;

    static
    {
        VARIANTS.add("Nothing is selected");
        VARIANTS.add("GetAverageValue");
        VARIANTS.add("TransformListOfStrings");
        VARIANTS.add("GetListOfSquares");
        VARIANTS.add("GetLastElement");
        VARIANTS.add("GetStringsStartingWithLetter");
        VARIANTS.add("GetSumOfEvenElements");
        VARIANTS.add("CreateMapFromList");
    }

    @FXML
    void initialize()
    {
        functionChoice.getItems().addAll(VARIANTS);
        functionChoice.setValue(VARIANTS.get(0));
        functionChoice.setOnAction(e -> {
            switch (functionChoice.getValue()) {
                case "Nothing is selected" -> inputBox.clear();
                case "GetAverageValue", "GetListOfSquares", "GetLastElement" -> inputBox.setText("1 2 3 4 5");
                case "TransformListOfStrings", "CreateMapFromList" -> inputBox.setText("One Two Four Six Eight");
                case "GetStringsStartingWithLetter" -> inputBox.setText("One Two Three Four Five T");
                case "GetSumOfEvenElements" -> inputBox.setText("1 2 3 4 5 6");
            }
            outputBox.clear();
        });
        executeBtn.setOnAction(e -> {
            if (Objects.equals(functionChoice.getValue(), "Nothing is selected"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can not execute the laboratory");
                alert.setContentText("Please, select a function to execute");
                alert.showAndWait();
            }
            else
            {
                try
                {
                    outputBox.setText(StreamHandler.start(functionChoice.getValue(), inputBox.getText()));
                }
                catch (NumberFormatException ex)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Can not execute the laboratory");
                    alert.setContentText("Please, enter a list of numbers!");
                    alert.showAndWait();
                }
                catch (EmptyCollectionException | StringIndexOutOfBoundsException ex)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Can not execute the laboratory");
                    alert.setContentText("The input is empty! Please, enter something.");
                    alert.showAndWait();
                }
            }
        });
    }
}

