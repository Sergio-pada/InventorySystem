package InventorySystem;

/**This class sets up the creation of in house objects. */
public class InHouse extends Part {
    public int machineId;

    /**This method sets the value for machineId.
     @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**This method gets the value for machineId.
     @return parts machine id
     */
    public int getMachineId() {
        return machineId;
    }

    /**This method constructs an in house object.
     @param id
     @param max
     @param min
     @param name
     @param price
     @param stock
     @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
}
