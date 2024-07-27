package com.example.lab7;

import com.example.lab7.lab4.DictionaryHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class Lab4Controller {

    @FXML
    private TextField dictPathBox;

    @FXML
    private TextField textPathBox;

    @FXML
    private Button executeBtn;

    @FXML
    private TextArea lab4Output;

    @FXML
    private Button text_path_button;

    @FXML
    private Button dict_path_button;

    final FileChooser fileChooser = new FileChooser();

    @FXML
    void initialize()
    {
        executeBtn.setOnAction(e -> {
            if (dictPathBox.getText().isEmpty() || textPathBox.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No path found");
                alert.setContentText("Please, enter the paths to dictionary and the text");
                alert.showAndWait();
            }
            else
            {
                lab4Output.setText(DictionaryHandler.start(dictPathBox.getText(), textPathBox.getText()));
            }
        });
        text_path_button.setOnAction(e -> {
                    File file = fileChooser.showOpenDialog(null);
                    if (file != null)
                        textPathBox.setText(file.getAbsolutePath());
            }
        );
        dict_path_button.setOnAction(e -> {
                    File file = fileChooser.showOpenDialog(null);
                    if (file != null)
                        dictPathBox.setText(file.getAbsolutePath());
                }
        );
    }
}
