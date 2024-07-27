package com.example.lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import static com.example.lab7.SwitcherController.openNewScene;

public class MainController {
    private static final ArrayList<String> VARIANTS = new ArrayList<>();
    private static final Map<String, String> PATH = new HashMap<>();

    static {
        PATH.put("Laboratory № 1", "lab1-view.fxml");
        PATH.put("Laboratory № 2", "lab2-view.fxml");
        PATH.put("Laboratory № 3", "lab3-view.fxml");
        PATH.put("Laboratory № 4", "lab4-view.fxml");
        PATH.put("Laboratory № 5", "lab5-view.fxml");
        PATH.put("Laboratory № 6", "lab6-view.fxml");
        VARIANTS.add("Nothing is selected");
        VARIANTS.add("Laboratory № 1");
        VARIANTS.add("Laboratory № 2");
        VARIANTS.add("Laboratory № 3");
        VARIANTS.add("Laboratory № 4");
        VARIANTS.add("Laboratory № 5");
        VARIANTS.add("Laboratory № 6");
    }

    @FXML
    private Button confirmBtn;

    @FXML
    private ChoiceBox<String> labSelection;

    @FXML
    public void initialize() {
        labSelection.getItems().addAll(VARIANTS);
        labSelection.setValue(VARIANTS.get(0));
        confirmBtn.setOnAction(click -> {
            if (labSelection.getValue().equals("Nothing is selected")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can not confirm laboratory");
                alert.setContentText("Please, select a laboratory before confirmation");
                alert.showAndWait();
            }
            else {
                openNewScene(PATH.get(labSelection.getValue()));
            }
        });
    }
}