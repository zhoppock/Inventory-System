package com.example.c42project;

/**
 * Supplied class Part.java
 */

/**
 * This class creates a part with specified attributes.
 * @author Place Your Name Here
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * This method is the constructor for a Part.
     * @param id the Part constructor ID
     * @param name the Part constructor name
     * @param price the Part constructor price
     * @param stock the Part constructor stock value
     * @param min the Part constructor min value
     * @param max the Part constructor max value
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method retrieves the Part's ID value.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * This method assigns a value to the Part's ID attribute.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method retrieves the Part's name value.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * This method assigns a value to the Part's name attribute.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method retrieves the Part's price value.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method assigns a value to the Part's price attribute.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method retrieves the Part's stock value.
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * This method assigns a value to the Part's stock attribute.
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This method retrieves the Part's min value.
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * This method assigns a value to the Part's min attribute.
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This method retrieves the Part's max value.
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * This method assigns a value to the Part's max attribute.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}