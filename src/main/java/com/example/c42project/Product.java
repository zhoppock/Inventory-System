package com.example.c42project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates a product with specified attributes.
 * @author Zachary Hoppock
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int id;

    private String name;

    private double price;

    private int stock;

    private int min;

    private int max;

    /**
     * This method is the constructor for a Product.
     * @param id the Product constructor ID
     * @param name the Product constructor name
     * @param price the Product constructor price
     * @param stock the Product constructor stock value
     * @param min the Product constructor min value
     * @param max the Product constructor max value
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method assigns a value to the Product's ID attribute.
     * @param id the ID number to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method assigns a value to the Product's name attribute.
     * @param name the product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method assigns a value to the Product's price attribute.
     * @param price the fixed price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method assigns a value to the Product's stock attribute.
     * @param stock the inventory stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This method assigns a value to the Product's min attribute.
     * @param min the minimum amount of product to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This method assigns a value to the Product's max attribute.
     * @param max the maximum amount of product to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method retrieves the Product's ID value.
     * @return the ID number
     */
    public int getId() {
        return this.id;
    }

    /**
     * This method retrieves the Product's name value.
     * @return the product name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method retrieves the Product's price value.
     * @return the fixed price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * This method retrieves the Product's stock value.
     * @return the inventory stock
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * This method retrieves the Product's min value.
     * @return the minimum amount of product
     */
    public int getMin() {
        return this.min;
    }

    /**
     * This method retrieves the Product's max value.
     * @return the maximum amount of product
     */
    public int getMax() {
        return this.max;
    }

    /**
     * This method adds a selected part to the Product's associated parts list.
     * @param part the part to add to the product
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method removes a selected part from the Product's associated parts list.
     * @param selectedAssociatedPart the part of the product that is selected
     * @return that it is 'true' that the associated part was removed from the product
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * This method retrieves a list of all the Product's associated parts.
     * @return a list of all the parts associated with the product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }
}
