package InventorySystem;
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

/**This class contains the logic for the main menu screen

    A future enhancement would be to implement sorting within the tables by different fields. For example, only returning items with a very low inventory.
 */
public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;

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
    private ObservableList<Part> productParts = FXCollections.observableArrayList();

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

    ObservableList<Part> results;
    ObservableList<Product> returnedProducts;

    /**This method retrieves the passedPart variable. */
    public static Part getPassedPart() {
        return passedPart;
    }

    static Part passedPart;

    /**This method retrieves the passedPart variable. */
    public static Product getPassedProduct() {
        return passedProduct;
    }

    static Product passedProduct;

    /**This method sets up the tables for the menu.
     @param url
     @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PartsTableView.setItems(Inventory.getAllParts());

        PartIdCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));

        ProductsTableView.setItems(Inventory.getAllProducts());

        ProductIdCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        ProductStockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
    }

    /**This method searches the parts table with input from the textfield above the parts table.
     *@param event
      */
    @FXML
    void OnActionSearchParts(ActionEvent event) {
        String searchText = SearchPartTxt.getText();
        results = Inventory.lookupPart(searchText);
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
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Parts");
                alert.setContentText("No part found");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        PartsTableView.setItems(results);
    }

    /**This method searches the products table with input from the textfield above the products table.
     @param event
     */
    @FXML
    void OnActionSearchProducts(ActionEvent event) {
        String searchProductTxt = SearchProductTxt.getText();
        returnedProducts = Inventory.lookupProduct(searchProductTxt);
        if (returnedProducts.isEmpty()) {
            try {
                int productID = Integer.parseInt(searchProductTxt);
                Product product = Inventory.lookupProduct(productID);
                if (product != null) {
                    returnedProducts.add(product);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Products");
                    alert.setContentText("No product found");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Products");
                alert.setContentText("No product found");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        ProductsTableView.setItems(returnedProducts);
    }
    /**This method redirects us to the add part screen.
     @param event
     */
    @FXML
    void onActionDisplayAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/InventorySystem/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method redirects us to the add product screen.
     @param event
     */
    @FXML
    void onActionDisplayAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/InventorySystem/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This method redirects us to the modify part screen.
     @param event
     */
    @FXML
    void onActionDisplayModifyPart(ActionEvent event) throws IOException {
        passedPart = PartsTableView.getSelectionModel().getSelectedItem();
        if (passedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part");
            alert.showAndWait();
        }else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/InventorySystem/ModifyPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**This method redirects us to the modify product screen.
     @param event
     */
    @FXML
    void onActionDisplayModifyProduct(ActionEvent event) throws IOException {
        passedProduct  = ProductsTableView.getSelectionModel().getSelectedItem();
        if (passedProduct == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product");
            alert.showAndWait();
        }else {
            passedProduct = ProductsTableView.getSelectionModel().getSelectedItem();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/InventorySystem/ModifyProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**This method deletes a selected part.
     @param event
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part selectedPart = PartsTableView.getSelectionModel().getSelectedItem();


        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                results.remove(selectedPart);
                PartsTableView.setItems(results);
                Inventory.deletePart(selectedPart);


            }
        }
    }

    /**This method deletes a selected product.
     @param event
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product selectedProduct = ProductsTableView.getSelectionModel().getSelectedItem();
        System.out.println(productParts.isEmpty());
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product");
            alert.showAndWait();
        }else if(!selectedProduct.getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please remove all associated parts before deletion");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Products");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();
            //Delete product when the alert box's ok button is pressed
            if (result.isPresent() && result.get() == ButtonType.OK) {
                returnedProducts.remove(selectedProduct);
                ProductsTableView.setItems(returnedProducts);
                Inventory.deleteProduct(selectedProduct);

            }
        }
    }
    /**This method exits the application.
     @param event
     */
    @FXML
    void OnActionExit(ActionEvent event) {
        System.exit(0);
    }
}