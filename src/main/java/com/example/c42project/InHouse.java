package com.example.c42project;

/**
 * This class creates an In-House Part with specified attributes.
 * @author Zachary Hoppock
 */
public class InHouse extends Part{

    private int machineId;

    /**
     * This method is the constructor for an In-House Part.
     * @param id the InHouse constructor ID
     * @param name the InHouse constructor name
     * @param price the InHouse constructor price
     * @param stock the InHouse constructor stock value
     * @param min the InHouse constructor min value
     * @param max the InHouse constructor max value
     * @param machineId the InHouse constructor machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * This method assigns a value to the In-House Part's machine ID attribute.
     * @param machineId the machine ID to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * This method retrieves the In-House Part's machine ID value.
     * @return the machine ID
     */
    public int getMachineId() {
        return this.machineId;
    }
}
