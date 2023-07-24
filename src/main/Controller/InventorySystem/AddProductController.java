package InventorySystem;
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
/**This class contains the logic for the add product screen.

    A possible future enhancement could be eliminating the ability to have duplicates of the same part in the products associated parts list.
 */
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
    private TableColumn<Part,String> PartNameCol1;
    @FXML
    private TableColumn<Part,Double> PartPriceCol1;

    @FXML
    private TableView<Part> RemoveProductTableView;
    @FXML
    private TableColumn<Part,Integer> PartIdCol2;
    @FXML
    private TableColumn<Part,String> PartNameCol2;
    @FXML
    private TableColumn<Part,Integer> PartStockCol2;
    @FXML
    private TableColumn<Part,Double> PartPriceCol2;

    private ObservableList<Part> productParts = FXCollections.observableArrayList();

    /**This method adds a selected part from the list of all parts to the list of associated parts for the product we're adding.
     @param event
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = AddProductTableView.getSelectionModel().getSelectedItem();
        productParts.add(selectedPart);
        RemoveProductTableView.setItems(productParts);
    }
    /**This method removes an associated part
     @param event
     */
    @FXML
    void onActionRemoveProduct(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Products");
        alert.setContentText("Do you want to remove this part from the associated parts list?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Part selectedPart = RemoveProductTableView.getSelectionModel().getSelectedItem();
            productParts.remove(selectedPart);
            RemoveProductTableView.setItems(productParts);
        }
    }

    /**This method adds the product we are creating to the list of parts.
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

            Product product = new Product(id, name, price, stock, min, max);

            if (max > min && stock <= max && stock >= min) {
                if(name.length() == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a value for the name field");
                    alert.showAndWait();
                }else {
                    Inventory.addProduct(product);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/InventorySystem/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                for (Part part : productParts) {
                    product.addAssociatedPart(part);
                }

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

    /**This redirects us to the main menu screen.
     @param event
     */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/InventorySystem/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method searches the all parts table with input from the textfield above the all parts table.
     *@param event
     */
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
                alert.setHeaderText(null);
                alert.setContentText("No part found");

                alert.showAndWait();
            }
        }
        AddProductTableView.setItems(results);
    }

    /**This method generates a unique id for the new product and sets up the tables with the part data.
     @param url
     @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int maxId = 0;
        for(Product product: Inventory.getAllProducts()) {
            if (product.getId() > maxId){
                maxId = product.getId();
            }
        }
        AddProductIDTxt.setText(String.valueOf(maxId + 1));

        AddProductTableView.setItems(Inventory.getAllParts());
        PartIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));

        RemoveProductTableView.setItems(productParts);
        PartIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));


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

