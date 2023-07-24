package InventorySystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class contains the logic for the add part screen.

    A problem that I ran into here was being able to generate a unique ID for new parts. Initially, I just took the size of the array and added one but this lead to duplicate IDs. I solved this by taking the highest value of the IDs and then adding one.
 */
public class AddPartController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    public Label macIdCompNameLbl;
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

    /**This method generates a new and unique id for the new part being added.
     @param url
     @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int maxId = 0;
        for(Part part: Inventory.getAllParts()) {
            if (part.getId() > maxId){
                maxId = part.getId();
            }
        }
        AddPartIdTxt.setText(String.valueOf(maxId + 1));
        }



    /**This method changes the text within the last label depending on which radio button is selected. */
    @FXML
    void labelChange(){
        if (AddPartInHouseRBtn.isSelected()){
            macIdCompNameLbl.setText("Machine ID");
        }
        if(AddPartOutsourcedRBtn.isSelected()){
            macIdCompNameLbl.setText("Company Name");
        }
    }

    /**This method adds the part we are creating to the list of parts.
     @param event
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        int id = 0;
        String name = null;
        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;
        if(!isInteger(AddPartInventoryTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the inventory field");
            alert.showAndWait();
        }else if (!isInteger(AddPartMaxTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the max field");
            alert.showAndWait();
        } else if (!isInteger(AddPartMinTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the min field");
            alert.showAndWait();
        }else if (!isDouble(AddPartPriceTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a decimal value for the price field");
            alert.showAndWait();
        }else if(!isInteger(AddPartLabelTxt.getText()) && AddPartInHouseRBtn.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a value for the machine ID field");
            alert.showAndWait();
        } else{
            id = Integer.parseInt(AddPartIdTxt.getText());
            name = AddPartNameIdTxt.getText();
            stock = Integer.parseInt(AddPartInventoryTxt.getText());
            price = Double.parseDouble(AddPartPriceTxt.getText());
            max = Integer.parseInt(AddPartMaxTxt.getText());
            min = Integer.parseInt(AddPartMinTxt.getText());
        }
            if (max > min && stock <= max && stock >= min) {
                if (name.length() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a value for the name field");
                    alert.showAndWait();
                } else {
                    if (AddPartInHouseRBtn.isSelected()) {
                        int machineId = Integer.parseInt(AddPartLabelTxt.getText());
                        Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                    } else {
                        String companyName = AddPartLabelTxt.getText();
                        Inventory.addPart(new OutSourced(id, name, price, stock, min, max, companyName));
                    }
                    Inventory.deletePart(MainMenuController.getPassedPart());
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/InventorySystem/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                }


            } if (max < min && stock >= max && stock <= min){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Minimum value should be less than maximum value and the value of inventory should be between those ");

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

