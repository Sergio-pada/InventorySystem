package InventorySystem;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**This class contains the logic for the modify product screen.

    A possible future enhancement could be to show the difference in cost to produce a product after making modifications to the associated parts.
 */
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

    /**This method sets up the tables with the part data.
     @param url
     @param rb
     */
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

        PartIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddProductTableView.setItems(allParts);

        PartIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
        RemoveProductTableView.setItems(productParts);
    }

    /**This method adds a selected part to the associated parts list.
     @param event
     */
    @FXML
    void onActionAddProduct(ActionEvent event) {
        Part selectedPart = AddProductTableView.getSelectionModel().getSelectedItem();
        productParts.add(selectedPart);
        RemoveProductTableView.setItems(productParts);
    }
    /**This method removes a selected part from the associated parts list.
     @param event
     */
    @FXML
    void onActionRemoveProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you want to remove this part from the associated parts list?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Part selectedPart = RemoveProductTableView.getSelectionModel().getSelectedItem();
            productParts.remove(selectedPart);
        }
    }

    /**This method searches the all parts table with input from the textfield above the all parts table.
     *@param event
     */
    @FXML
    void OnActionSearchParts(ActionEvent event) {
        String productSearchTxt = AddProductSearchTxt.getText();
        ObservableList<Part> returnedParts = Inventory.lookupPart(productSearchTxt);
        if (returnedParts.isEmpty()) {
            try {
                int partID = Integer.parseInt(productSearchTxt);
                Part part = Inventory.lookupPart(partID);
                if (part != null) {
                    returnedParts.add(part);
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
        AddProductTableView.setItems(returnedParts);
    }
    /**This method updates the product we are modifying to the list of parts.
     @param event
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        int id = 0;
        String name = null;
        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;
        if(!isInteger(AddProductStockTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the inventory field");
            alert.showAndWait();
        }else if (!isInteger(AddProductMaxTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the max field");
            alert.showAndWait();
        } else if (!isInteger(AddProductMinTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the min field");
            alert.showAndWait();
        }else if (!isDouble(AddProductPriceTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a decimal value for the price field");
            alert.showAndWait();
        }else{
            id = Integer.parseInt(AddProductIDTxt.getText());
            name = AddProductNameTxt.getText();
            stock = Integer.parseInt(AddProductStockTxt.getText());
            price = Double.parseDouble(AddProductPriceTxt.getText());
            max = Integer.parseInt(AddProductMaxTxt.getText());
            min = Integer.parseInt(AddProductMinTxt.getText());
        }
        try {


            if(name.length() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a value for the name field");
                alert.showAndWait();
            }else {
                if (max > min && stock <= max && stock >= min) {
                    Inventory.addProduct(new Product(id, name, price, stock, min, max));
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/InventorySystem/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                    Inventory.deleteProduct(MainMenuController.getPassedProduct());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Minimum value should be less than maximum value and the value of inventory should be between those ");
                    alert.showAndWait();
                }
            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid data in each textfield");
            alert.showAndWait();
        }

    }
    /**This method redirects us to the main menu screen.
     @param event
     */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/InventorySystem/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
