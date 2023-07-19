package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Generate a unique id and set the id textbox
        int maxId = 0;
        for(Part part: Inventory.getAllParts()) {
            if (part.getId() > maxId){
                maxId = part.getId();
            }
        }
        AddPartIdTxt.setText(String.valueOf(maxId + 1));
        }




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
        try {
            int id = Integer.parseInt(AddPartIdTxt.getText());
            String name = AddPartNameIdTxt.getText();
            int stock = Integer.parseInt(AddPartInventoryTxt.getText());
            double price = Double.parseDouble(AddPartPriceTxt.getText());
            int max = Integer.parseInt(AddPartMaxTxt.getText());
            int min = Integer.parseInt(AddPartMinTxt.getText());


            if (max > min && stock <= max && stock >= min) {

                //Part Object Creation On Save
                if (AddPartInHouseRBtn.isSelected()) {
                    int machineId = Integer.parseInt(AddPartLabelTxt.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                } else {
                    String companyName = AddPartLabelTxt.getText();
                    Inventory.addPart(new OutSourced(id, name, price, stock, min, max, companyName));
                }

                //Return to Main Menu On Save
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Minimum value should be less than maximum value and the value of inventory should be between those ");

                alert.showAndWait();
            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid data in each textfield");

            alert.showAndWait();
        }

    }



    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    private boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @FXML
    private boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

