package InventorySystem;

/**This class sets up the creation of outsourced objects. */
public class OutSourced extends Part {
    private String companyName;

    /**This method gets the value for company name.
     @return parts company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**This method sets the value for company name.
     @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**This method constructs an outsourced object.
     @param id
     @param max
     @param min
     @param name
     @param price
     @param stock
     @param companyName
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
}
