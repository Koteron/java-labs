package com.example.lab7;

import com.example.lab7.lab6.ThreadHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Lab6Controller {

    @FXML
    private Button executeBtn;

    @FXML
    private TextArea lab6Output;

    @FXML
    void initialize()
    {
        executeBtn.setOnAction(e -> {
            lab6Output.setText(ThreadHandler.start());
        });
    }
}
