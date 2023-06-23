package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;


public class ModifyPartController {
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton InHouseRBtn;
    @FXML
    private RadioButton OutsourcedRBtn;
    @FXML
    private Label MacIdComNameLbl;
    @FXML
    private TextField PartIdTxt;
    @FXML
    private TextField PartNameTxt;
    @FXML
    private TextField PartInvTxt;
    @FXML
    private TextField PartPriceTxt;
    @FXML
    private TextField PartMaxTxt;
    @FXML
    private TextField MacIdComNameTxt;
    @FXML
    private TextField PartMinTxt;

    @FXML
    void onActionSave(ActionEvent event) {
    }

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
