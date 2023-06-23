package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {
    Stage stage;
    Parent scene;
    @FXML
    private RadioButton AddPartInHouseRBtn;
    @FXML
    private RadioButton AddPartOutsourcedRBtn;
    @FXML
    private TextField AddPartIdTxt;
    @FXML
    private TextField AddPartNameIdTxt;
    @FXML
    private TextField AddPartInventoryTxt;
    @FXML
    private TextField AddPartPriceTxt;
    @FXML
    private TextField AddPartMaxTxt;
    @FXML
    private TextField AddPartLabelTxt;
    @FXML
    private TextField AddPartMinTxt;

    @FXML
    void onActionSavePart(ActionEvent event) {
    }

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
