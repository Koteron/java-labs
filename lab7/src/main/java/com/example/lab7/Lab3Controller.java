package com.example.lab7;

import com.example.lab7.lab3.AnimalHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lab3Controller {

    private static final ArrayList<String> VARIANTS = new ArrayList<>();

    @FXML
    private TextArea dataInput;

    @FXML
    private TextArea dataOutput;

    @FXML
    private ChoiceBox<String> exampleSelection;

    @FXML
    private Button executeBtn;

    static
    {
        VARIANTS.add("Nothing is selected");
        VARIANTS.add("Example №1");
        VARIANTS.add("Example №2");
        VARIANTS.add("Example №3");
    }

    @FXML
    public void initialize()
    {
        exampleSelection.getItems().addAll(VARIANTS);
        exampleSelection.setValue(VARIANTS.get(0));
        exampleSelection.setOnAction(e -> {
            dataOutput.clear();
            switch (exampleSelection.getValue())
            {
                case "Example №1":
                    dataInput.setText("Source collection class: Mammal" +
                            "\nCommon Hedgehog: Dark Gray, 10" +
                            "\nLynx: Orange, 30" +
                            "\nManul: White, 35" +
                            "\nDestination collections classes: Hedgehog, Felidae, Predator");
                    break;

                case "Example №2":
                    dataInput.setText("Source collection class: Mammal" +
                            "\nManul: White, 35" +
                            "\nLynx: Brown, 36" +
                            "\nManul: White, 38"+
                            "\nDestination collections classes: Chordate, Manul, Felidae");
                    break;

                case "Example №3":
                    dataInput.setText("Common Hedgehog: Gray, 24" +
                            "\nCommon Hedgehog: Dark Gray, 21" +
                            "\nCommon Hedgehog: Brown, 17"+
                            "\nDestination collections classes: Insectivore, Predator, Predator");
                    break;
            }
        });
        executeBtn.setOnAction(e -> {
            if (exampleSelection.getValue() == "Nothing is selected")
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can not execute the laboratory");
                alert.setContentText("Please, select an example to execute");
                alert.showAndWait();
            }
            else
            {
                dataOutput.setText(AnimalHandler.start(exampleSelection.getValue()));
            }
        });
    }
}
