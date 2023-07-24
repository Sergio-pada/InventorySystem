package InventorySystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class contains the logic for the modify part screen.

    A problem that I ran into here was that on save, a cduplicate product would be created instead of the new product replacing the old one. I fixed this by deleting the passed part before redirecting the user to the home screen.
 */
public class ModifyPartController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton InHouseRBtn;
    @FXML
    private RadioButton OutsourcedRBtn;
    @FXML
    private Label MacIdComNameLbl;
    @FXML
    private TextField PartIdTxt;
    @FXML
    private TextField PartNameTxt;
    @FXML
    private TextField PartStockTxt;
    @FXML
    private TextField PartPriceTxt;
    @FXML
    private TextField PartMaxTxt;
    @FXML
    private TextField MacIdComNameTxt;
    @FXML
    private TextField PartMinTxt;
    /**This method sets the text of the textfields to match the data for the selected part.
     @param url
     @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartIdTxt.setText(String.valueOf(MainMenuController.getPassedPart().getId()));
        PartNameTxt.setText(MainMenuController.getPassedPart().getName());
        PartStockTxt.setText(String.valueOf(MainMenuController.getPassedPart().getStock()));
        PartPriceTxt.setText(String.valueOf(MainMenuController.getPassedPart().getPrice()));
        PartMaxTxt.setText(String.valueOf(MainMenuController.getPassedPart().getMax()));
        PartMinTxt.setText(String.valueOf(MainMenuController.getPassedPart().getMin()));

        if(MainMenuController.getPassedPart() instanceof InHouse){
            MacIdComNameTxt.setText(String.valueOf(((InHouse) MainMenuController.getPassedPart()).getMachineId()));
            InHouseRBtn.setSelected(true);

        }else{
            MacIdComNameTxt.setText(String.valueOf(((OutSourced) MainMenuController.getPassedPart()).getCompanyName()));
            OutsourcedRBtn.setSelected(true);
            MacIdComNameLbl.setText("Company Name");
        }
    }
    /**This method changes the text within the last label depending on which radio button is selected. */
    @FXML
    void labelChange(){
        if (InHouseRBtn.isSelected()){
            MacIdComNameLbl.setText("Machine ID");
        }
        if(OutsourcedRBtn.isSelected()){
            MacIdComNameLbl.setText("Company Name");
        }
    }

    /**This method saves the part we are modifying to the list of parts.
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
        if(!isInteger(PartStockTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the inventory field");
            alert.showAndWait();
        }else if (!isInteger(PartMaxTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the max field");
            alert.showAndWait();
        } else if (!isInteger(PartMinTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an integer value for the min field");
            alert.showAndWait();
        }else if (!isDouble(PartPriceTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a decimal value for the price field");
            alert.showAndWait();
        } else if(!isInteger(MacIdComNameTxt.getText()) && InHouseRBtn.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a value for the machine ID field");
            alert.showAndWait();
        } else {
            id = Integer.parseInt(PartIdTxt.getText());
            name = PartNameTxt.getText();
            stock = Integer.parseInt(PartStockTxt.getText());
            price = Double.parseDouble(PartPriceTxt.getText());
            max = Integer.parseInt(PartMaxTxt.getText());
            min = Integer.parseInt(PartMinTxt.getText());
        }




            String companyName = MacIdComNameTxt.getText();

            if(name.length() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a value for the name field");
                alert.showAndWait();
            }else {
                if (max > min && stock <= max && stock >= min) {
                    if (InHouseRBtn.isSelected()) {
                        int machineId = Integer.parseInt(MacIdComNameTxt.getText());
                            Inventory.deletePart(MainMenuController.getPassedPart());
                            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

                    }
                    if(OutsourcedRBtn.isSelected()){
                        Inventory.deletePart(MainMenuController.getPassedPart());
                        Inventory.addPart(new OutSourced(id, name, price, stock, min, max, companyName));
                    }

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/InventorySystem/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Minimum value should be less than maximum value and the value of inventory should be between those ");
                    alert.showAndWait();
                }
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
