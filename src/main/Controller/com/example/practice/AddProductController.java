package com.example.practice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private TextField AddProductIDTxt;
    @FXML
    private TextField AddProductNameTxt;
    @FXML
    private TextField AddProductStockTxt;
    @FXML
    private TextField AddProductMaxTxt;
    @FXML
    private TextField AddProductMinTxt;
    @FXML
    private TextField AddProductPriceTxt;
    @FXML
    private TextField AddProductSearchTxt;
    @FXML
    private TableView<Part> AddProductTableView;
    @FXML
    private TableColumn<Part,Integer> PartIdCol1;

    @FXML
    private TableColumn<Part,Integer> PartStockCol1;

    @FXML
    private TableView<Part> RemoveProductTableView;
    @FXML
    private TableColumn<Part,String> PartNameCol1;
    @FXML
    private TableColumn<Part,Double> PartPriceCol1;
    @FXML
    private TableColumn<Part,Integer> PartIdCol2;
    @FXML
    private TableColumn<Part,String> PartNameCol2;
    @FXML
    private TableColumn<Part,Integer> PartStockCol2;
    @FXML
    private TableColumn<Part,Double> PartPriceCol2;

    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();


    //Observable Lists for Table Views
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    @FXML
    void onActionAddProduct(ActionEvent event) {
        Part selectedPart = AddProductTableView.getSelectionModel().getSelectedItem();
        associatedPartsList.add(selectedPart);
        RemoveProductTableView.setItems(associatedPartsList);
    }

    @FXML
    void onActionRemoveProduct(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Products");
        alert.setContentText("Do you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Part selectedPart = RemoveProductTableView.getSelectionModel().getSelectedItem();
            associatedPartsList.remove(selectedPart);
            RemoveProductTableView.setItems(associatedPartsList);
        }
    }

    /*
    FIND OUT HOW TO ASSOCIATE PARTS WITH PRODUCTS ON SAVE
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            //Retrieving Input from TextFields:
            int id = Integer.parseInt(AddProductIDTxt.getText());
            String name = AddProductNameTxt.getText();
            int stock = Integer.parseInt(AddProductStockTxt.getText());
            double price = Double.parseDouble(AddProductPriceTxt.getText());
            int max = Integer.parseInt(AddProductMaxTxt.getText());
            int min = Integer.parseInt(AddProductMinTxt.getText());
            Product product = new Product(id, name, price, stock, min, max);

            if (max > min && stock <= max && stock >= min) {
                Inventory.addProduct(product);
                for (Part part : associatedPartsList) {
                    product.addAssociatedPart(part);
                }
                System.out.println(product.getAllAssociatedParts());
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
    void OnActionSearchParts(ActionEvent event) {
        String searchText = AddProductSearchTxt.getText();
        ObservableList<Part> results = Inventory.lookupPart(searchText);
        if (results.isEmpty()) {
            try {
                int partID = Integer.parseInt(searchText);
                Part part = Inventory.lookupPart(partID);
                if (part != null) {
                    results.add(part);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Parts");
                    alert.setContentText("No part found");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Parts");
                alert.setContentText("No part found");
                alert.showAndWait();
            }
        }

        AddProductTableView.setItems(results);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Generate a unique id and set the id textbox
        int maxId = 0;
        for(Product product: Inventory.getAllProducts()) {
            if (product.getId() > maxId){
                maxId = product.getId();
            }
        }
        AddProductIDTxt.setText(String.valueOf(maxId + 1));



        //Set Cell Values for All Parts Table
        AddProductTableView.setItems(Inventory.getAllParts());
        PartIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));


        //Set Cell Values for Product Parts Table

        RemoveProductTableView.setItems(associatedPartsList);
        PartIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}

