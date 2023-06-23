package com.example.practice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private TableColumn<?,?> PartIdCol1;

    @FXML
    private TableColumn<?,?> PartStockCol1;

    @FXML
    private TableView<Part> RemoveProductTableView;
    @FXML
    private TableColumn<?,?> PartNameCol1;
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

    //Observable Lists for Table Views
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    @FXML
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionRemoveProduct(ActionEvent event){
    }

    /*
    FIND OUT HOW TO ASSOCIATE PARTS WITH PRODUCTS ON SAVE
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        //Retrieving Input from TextFields:
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

