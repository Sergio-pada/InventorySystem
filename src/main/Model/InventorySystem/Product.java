package InventorySystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This class sets up the creation of product objects. */
public class Product {
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**This method constructs a product object.
     @param id
     @param max
     @param min
     @param name
     @param price
     @param stock
     */    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**This method gets the value for id.
      @return products id
     */
    public int getId() {
        return id;
    }

    /**This method sets the value for id.
     @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**This method gets the value for name.
     @return products name
     */
    public String getName() {
        return name;
    }

    /**This method sets the value for name.
     @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This method gets the value for price.
     @return products price
     */

    public double getPrice() {
        return price;
    }

    /**This method sets the value for price.
     @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**This method gets the value for stock.
     @return products stock
     */
    public int getStock() {
        return stock;
    }

    /**This method sets the value for stock.
     @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**This method gets the value for min.
     @return products min.
     */
    public int getMin() {
        return min;
    }

    /**This method sets the value for min.
     @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**This method gets the value for max.
     @return products max.
     */
    public int getMax() {
        return max;
    }

    /**This method sets the value for max.
     @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**This method adds a part to the products associated parts list.
     @param part
     */
    public static void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**This method removes a part from the products associated parts list.
     @param selectedPart
     */
    public void removeAssociatedPart(Part selectedPart){
        associatedParts.remove(selectedPart);
    }

    /**This method retreives all associated parts.
     @return List of associated products
     */
    public static ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}


