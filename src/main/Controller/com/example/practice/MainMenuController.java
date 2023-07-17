package com.example.practice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    //Parts Table
    @FXML
    private TextField SearchPartTxt;
    @FXML
    private TableView<Part> PartsTableView;
    @FXML
    private TableColumn<Part,Integer> PartIdCol;
    @FXML
    private TableColumn<Part,String> PartNameCol;
    @FXML
    private TableColumn<Part,Integer> PartStockCol;
    @FXML
    private TableColumn<Part,Double> PartPriceCol;


    //Products Table
    @FXML
    private TextField SearchProductTxt;
    @FXML
    private TableView<Product> ProductsTableView;
    @FXML
    private TableColumn<Product, Integer> ProductIdCol;
    @FXML
    private TableColumn<Product, String> ProductNameCol;
    @FXML
    private TableColumn<Product,Integer> ProductStockCol;
    @FXML
    private TableColumn<Product, Double> ProductPriceCol;
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initialized");
        //Associating getAllParts() observable list with PartsTableView
        PartsTableView.setItems(Inventory.getAllParts());

        //Assigning column data for PartsTableView
        PartIdCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));

        //Associating getAllProducts() observable list with ProductsTableView
        ProductsTableView.setItems(Inventory.getAllProducts());

        //Assigning column data for ProductsTableView
        ProductIdCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        ProductStockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));

        /*
        FUNCTION TESTING - REMOVE LATER
         */


    }
    @FXML
    void onActionDisplayModifyPart(ActionEvent event) throws IOException {
        //Scene Switch to ModifyPart


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part selectedPart = PartsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Parts");
            alert.setContentText("Do you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    @FXML
    void onActionDisplayAddPart(ActionEvent event) throws IOException {
        //Scene Switch to AddPart
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product selectedProduct = ProductsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No product selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Products");
            alert.setContentText("Do you want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        }



    }

    @FXML
    void onActionDisplayAddProduct(ActionEvent event) throws IOException {
        //Scene Switch to AddProduct
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayModifyProduct(ActionEvent event) throws IOException {
        //Scene Switch to ModifyProduct
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

@FXML
void OnActionSearchParts(ActionEvent event) {
    String searchText = SearchPartTxt.getText();
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

    PartsTableView.setItems(results);
}

    @FXML
    void OnActionSearchProducts(ActionEvent event) {
        String searchText = SearchProductTxt.getText();
        ObservableList<Product> results = Inventory.lookupProduct(searchText);

        if (results.isEmpty()) {
            try {
                int productID = Integer.parseInt(searchText);
                Product product = Inventory.lookupProduct(productID);
                if (product != null) {
                    results.add(product);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Products");
                    alert.setContentText("No product found");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Products");
                alert.setContentText("No product found");
                alert.showAndWait();
            }
        }

        ProductsTableView.setItems(results);
    }
    @FXML
    void OnActionExit(ActionEvent event) {
        System.exit(0);
    }




}