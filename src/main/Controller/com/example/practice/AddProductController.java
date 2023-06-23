package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductController {
    Stage stage;
    Parent scene;
    @FXML
    private TextField AddProductIDTxt;
    @FXML
    private TextField AddProductNameTxt;
    @FXML
    private TextField AddProductInvTxt;
    @FXML
    private TextField AddProductMaxTxt;
    @FXML
    private TextField AddProductMinTxt;
    @FXML
    private TextField AddProductPriceTxt;
    @FXML
    private TextField AddProductSearchTxt;
    @FXML
    private TableView<?> AddProductTableView;
    @FXML
    private TableColumn<?,?> PartIdCol1;

    @FXML
    private TableColumn<?,?> NameInvCol1;

    @FXML
    private TableView<?> RemoveProductTableView;
    @FXML
    private TableColumn<?,?> PartNameCol1;
    @FXML
    private TableColumn<?,?> PartPriceCol1;
    @FXML
    private TableColumn<?,?> PartIdCol2;
    @FXML
    private TableColumn<?,?> PartNameCol2;
    @FXML
    private TableColumn<?,?> PartInvCol2;
    @FXML
    private TableColumn<?,?> PriceCol2;

    @FXML
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionRemoveProduct(ActionEvent event) {
    }

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
