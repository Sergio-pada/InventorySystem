package com.example.practice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField AddProductIDTxt;
    @FXML
    private TextField AddProductNameTxt;
    @FXML
    private TextField AddProductStockTxt;
    @FXML
    private TextField AddProductPriceTxt;
    @FXML
    private TextField AddProductMaxTxt;
    @FXML
    private TextField AddProductMinTxt;
    @FXML
    private TextField AddProductSearchTxt;
    @FXML
    private TableView<Part> AddProductTableView;
    @FXML
    private TableColumn<?,?> PartIdCol1;
    @FXML
    private TableView<Part> RemoveProductTableView;
    @FXML
    private TableColumn<?,?> PartNameCol1;
    @FXML
    private TableColumn<?,?> PartStockCol1;
    @FXML
    private TableColumn<?,?> PartPriceCol1;
    @FXML
    private TableColumn<?,?> PartIdCol2;
    @FXML
    private TableColumn<?,?> PartNameCol2;
    @FXML
    private TableColumn<?,?> PartStockCol2;
    @FXML
    private TableColumn<?,?> PartPriceCol2;
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private Product passedProduct = MainMenuController.getPassedProduct();
    @FXML
    void onActionAddProduct(ActionEvent event) {
        Part selectedPart = AddProductTableView.getSelectionModel().getSelectedItem();
        productParts.add(selectedPart);
        RemoveProductTableView.setItems(productParts);
    }

    @FXML
    void onActionRemoveProduct(ActionEvent event) {
        productParts.remove(RemoveProductTableView.getSelectionModel().getSelectedItem());
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

    @FXML
    void onActionSave(ActionEvent event) throws IOException {


        int id = Integer.parseInt(AddProductIDTxt.getText());
        String name = AddProductNameTxt.getText();
        int stock = Integer.parseInt(AddProductStockTxt.getText());
        double price = Double.parseDouble(AddProductPriceTxt.getText());
        int max = Integer.parseInt(AddProductMaxTxt.getText());
        int min = Integer.parseInt(AddProductMinTxt.getText());
        boolean isInHouse;

        Inventory.addProduct(new Product(id, name, price, stock, min, max));


        //Return to Main Menu On Save
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        Inventory.deleteProduct(MainMenuController.getPassedProduct());
    }

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddProductIDTxt.setText(String.valueOf(MainMenuController.getPassedProduct().getId()));
        AddProductNameTxt.setText(MainMenuController.getPassedProduct().getName());
        AddProductStockTxt.setText(String.valueOf(MainMenuController.getPassedProduct().getStock()));
        AddProductPriceTxt.setText(String.valueOf(MainMenuController.getPassedProduct().getPrice()));
        AddProductMaxTxt.setText(String.valueOf(MainMenuController.getPassedProduct().getMax()));
        AddProductMinTxt.setText(String.valueOf(MainMenuController.getPassedProduct().getMin()));

        productParts = passedProduct.getAllAssociatedParts();
        allParts = Inventory.getAllParts();

        //Set Cell Values for All Parts Table
        PartIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddProductTableView.setItems(allParts);



        //Set Cell Values for Product Parts Table
        PartIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
        RemoveProductTableView.setItems(productParts);


    }


}
