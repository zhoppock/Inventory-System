package com.example.c42project;

/**
 * This class creates an Outsourced Part with specified attributes.
 * @author Zachary Hoppock
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     * This method is the constructor for an Outsourced Part.
     * @param id the Outsourced constructor ID
     * @param name the Outsourced constructor name
     * @param price the Outsourced constructor price
     * @param stock the Outsourced constructor stock value
     * @param min the Outsourced constructor min value
     * @param max the Outsourced constructor max value
     * @param companyName the Outsourced constructor company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * This method assigns a value to the Outsourced Part's company name attribute.
     * @param companyName the company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * This method retrieves the Outsourced Part's company name value.
     * @return the company name
     */
    public String getCompanyName() {
        return this.companyName;
    }
}
