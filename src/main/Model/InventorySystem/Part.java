package InventorySystem;

/**This class sets up the creation of part objects. */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**This method gets the value for id.
     @return parts id
     */    public int getId() {
        return id;
    }

    /**This method sets the value for id.
     @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**This method gets the value for name.
     @return parts name
     */    public String getName() {
        return name;
    }

    /**This method sets the value for name.
     @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This method gets the value for price.
     @return parts price
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
     @return parts stock
     */    public int getStock() {
        return stock;
    }

    /**This method sets the value for stock.
     @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**This method gets the value for min.
     @return parts min
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
     @return parts max
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

    /**This method constructs a part object.
     @param id
     @param max
     @param min
     @param name
     @param price
     @param stock
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


}