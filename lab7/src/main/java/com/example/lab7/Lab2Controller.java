package com.example.lab7;

import com.example.lab7.lab2.AnnotationsHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Lab2Controller {

    @FXML
    private Button executeBtn;

    @FXML
    private TextArea lab2Output;

    @FXML
    void initialize()
    {
        executeBtn.setOnAction(e -> {
            lab2Output.setText(AnnotationsHandler.start());
        });
    }
}
