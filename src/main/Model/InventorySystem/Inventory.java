package InventorySystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This class sets up the inventory lists for parts and products.

    A problem that I ran into her was that when looking up a part or product by their name, since each part/product in our list of sample data is capitalized, no parts/products would be returned when a non-capitalized string was inputted into the function.
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**This method adds a new part to our list of all parts.
     @param newPart
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**This method adds a new product to our list of all products.
     @param newProduct
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**This method retrieves all parts.
     @return all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**This method retrieves all products.
     @return all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**This method looks up a part by its ID.
     @param partId
     @return Any parts that match the input or null if none are found.
     */
    public static Part lookupPart(int partId) {
        for(Part part: Inventory.getAllParts()) {
            Part matchingPart;
            if (part.getId() == partId) {
                matchingPart = part;
                return matchingPart;
            }
        }
        return null;
    }
    /**This method looks up a product by its ID.
     @param productId
     @return Any products that match the input or null if none are found.
     */
    public static Product lookupProduct(int productId) {
        for(Product product: Inventory.getAllProducts()) {
            Product matchingProduct;
            if (product.getId() == productId) {
                matchingProduct = product;
                return matchingProduct;
            }
        }
        return null;
    }
    /**This method looks up a part by its name.
     @param partName
     @return Any parts that match the input.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> PartName = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                PartName.add(part);
            }
        }
        return PartName;
    }

    /**This method looks up a product by its name.
     @param productName
     @return Any products that match the input.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> ProductName = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                ProductName.add(product);
            }
        }
        return ProductName;
    }

    /**This method updates a parts data.
     @param index
     @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**This method updates a products data.
     @param index
     @param selectedProduct
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**This method deletes a product.
      * @param selectedProduct
     */
    public static void deleteProduct (Product selectedProduct) {
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
        }
    }

    /**This method deletes a part.
     * @param selectedPart
     */
    public static void deletePart (Part selectedPart) {
        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
        }
    }

}

