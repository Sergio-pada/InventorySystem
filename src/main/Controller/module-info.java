module com.example.practice {
    requires javafx.controls;
    requires javafx.fxml;


    opens InventorySystem to javafx.fxml;
    exports InventorySystem;
}