package InventorySystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
//JavaDoc folder is located in InventorySys folder

/** This class creates the inventory application and loads the main menu. It also creates sample data.

     An error occurred when I tried to add associated parts to the sample product in this method. I fixed this by removing that bit of code.
 */
public class Main extends Application{
    /**This method loads our mainMenu when the application starts
     @param stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    /**This method generates sample data for the tables and launches the application
     @param args
     */
    public static void main(String[] args) {

        InHouse cheese = new InHouse(1, "Cheese", 0.50, 50, 1, 100, 560);
        InHouse bun = new InHouse(2, "Bun", 1.0, 40, 1, 100, 561);
        InHouse lettuce = new InHouse(3, "Lettuce", 0.10, 13, 1, 100, 562);
        InHouse tomato = new InHouse(4, "Tomato", 0.10, 26, 1, 100, 563);
        OutSourced onion = new OutSourced(5, "Onion", 0.10, 30, 1, 100, "Onion Operation Inc.");
        OutSourced patty = new OutSourced(6, "Patty", 6.00, 25, 1, 100, "Patty Producers LLC");

        Inventory.addPart(cheese);
        Inventory.addPart(bun);
        Inventory.addPart(lettuce);
        Inventory.addPart(tomato);
        Inventory.addPart(onion);
        Inventory.addPart(patty);

        Product cheeseburger = new Product(1, "Cheese Burger", 9.99, 30, 1, 35);
        Inventory.addProduct(cheeseburger);


        launch();
    }
}

