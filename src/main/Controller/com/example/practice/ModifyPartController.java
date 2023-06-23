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
    private TextField PartStockTxt;
    @FXML
    private TextField PartPriceTxt;
    @FXML
    private TextField PartMaxTxt;
    @FXML
    private TextField MacIdComNameTxt;
    @FXML
    private TextField PartMinTxt;

    @FXML
    void labelChange(){
        if (InHouseRBtn.isSelected()){
            MacIdComNameLbl.setText("Machine ID");
        }
        if(OutsourcedRBtn.isSelected()){
            MacIdComNameLbl.setText("Company Name");
        }
    }
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        //Retrieving Input from TextFields:
        int id = Integer.parseInt(PartIdTxt.getText());
        String name = PartNameTxt.getText();
        int stock = Integer.parseInt(PartStockTxt.getText());
        double price = Double.parseDouble(PartPriceTxt.getText());
        int max = Integer.parseInt(PartMaxTxt.getText());
        int min = Integer.parseInt(PartMinTxt.getText());
        boolean isInHouse;

        //Part Object Creation On Save
        /*
        FIND OUT HOW TO REMOVE OUTDATED OBJECT ON SAVE
         */
        if(InHouseRBtn.isSelected()) {
            int machineId = Integer.parseInt(MacIdComNameTxt.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
        } else {
            String companyName = MacIdComNameTxt.getText();
            Inventory.addPart(new OutSourced(id, name, price, stock, min, max, companyName));
        }

        //Return to Main Menu On Save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
