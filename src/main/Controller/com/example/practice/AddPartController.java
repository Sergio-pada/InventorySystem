package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {
    @FXML
    public Label macIdCompNameLbl;
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
    void labelChange(){
        if (AddPartInHouseRBtn.isSelected()){
            macIdCompNameLbl.setText("Machine ID");
        }
        if(AddPartOutsourcedRBtn.isSelected()){
            macIdCompNameLbl.setText("Company Name");
        }
    }
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        //Retrieving Input from TextFields:
        int id = Integer.parseInt(AddPartIdTxt.getText());
        String name = AddPartNameIdTxt.getText();
        int stock = Integer.parseInt(AddPartInventoryTxt.getText());
        double price = Double.parseDouble(AddPartPriceTxt.getText());
        int max = Integer.parseInt(AddPartMaxTxt.getText());
        int min = Integer.parseInt(AddPartMinTxt.getText());
        boolean isInHouse;

        //Part Object Creation On Save
        if(AddPartInHouseRBtn.isSelected()) {
            int machineId = Integer.parseInt(AddPartLabelTxt.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
        } else {
            String companyName = AddPartLabelTxt.getText();
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
