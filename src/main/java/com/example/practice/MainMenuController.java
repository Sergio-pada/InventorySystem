package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField SearchPartTxt;
    @FXML
    private TableView<?> PartsTableView;
    @FXML
    private TableColumn<?,?> PartIdCol;
    @FXML
    private TableColumn<?,?> PartNameCol;
    @FXML
    private TableColumn<?,?> PartInvCol;
    @FXML
    private TableColumn<?,?> PartPriceCol;
    @FXML
    private TextField SearchProductTxt;
    @FXML
    private TableView<?> ProductsTableView;
    @FXML
    private TableColumn<?,?> ProductIdCol;
    @FXML
    private TableColumn<?,?> ProductNameCol;
    @FXML
    private TableColumn<?,?> ProductInvCol;
    @FXML
    private TableColumn<?,?> ProductPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initialized");
    }

    @FXML
    void onActionDisplayModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        System.out.println("Deleting Part");
    }

    @FXML
    void onActionDisplayAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        System.out.println("Deleting");
    }

    @FXML
    void onActionDisplayAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayModifyProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/example/practice/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionExit(ActionEvent event) {
        System.exit(0);
    }
}