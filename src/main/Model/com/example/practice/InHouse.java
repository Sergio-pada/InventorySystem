package com.example.practice;

public class InHouse extends Part {
    private int getMachineId() {
        return machineId;
    }
    //Getter and Setter
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int machineId;

    //Constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
}
