package com.example.c42project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This classes creates a lists of Parts and Products.
 * @author Zachary Hoppock
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method adds a new part to the Parts list.
     * @param newPart the new part to be added to the Parts collection
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method adds a new product to the Products list.
     * @param newProduct the new product to be added to the Products collection
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method searches up a part in the Parts list by an ID number.
     * @param partId the ID number used to locate respective part information
     * @return the part information the was found by the ID number given
     */
    public static Part lookupPart(int partId) {
        Part foundPart = null;
        for (Part partCheck : allParts) {
            if (partCheck.getId() == partId) {
                foundPart = partCheck;
                break;
            }
        }
        return foundPart;
    }

    /**
     * This method searches up a product in the Products list by an ID number.
     * @param productId the ID number used to locate respective product information
     * @return the product information the was found by the ID number given
     */
    public static Product lookupProduct(int productId) {
        Product foundProducts = null;
        for (Product productCheck : allProducts) {
            if (productCheck.getId() == productId) {
                foundProducts = productCheck;
                break;
            }
        }
        return foundProducts;
    }

    /**
     * This method searches up parts in the Parts list by a string character(s).
     * @param partName the name used to locate respective part information
     * @return the part information that was found by the name given
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for (Part partCheck : allParts) {
            if (partCheck.getName().toLowerCase().contains(partName.toLowerCase())) {
                foundParts.add(partCheck);
            }
        }
        return foundParts;
    }

    /**
     * This method searches up products in the Products list by a string character(s).
     * @param productName the name used to locate respective product information
     * @return the product information that was found by the name given
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for (Product productCheck : allProducts) {
            if (productCheck.getName().toLowerCase().contains(productName.toLowerCase())) {
                foundProducts.add(productCheck);
            }
        }
        return foundProducts;
    }

    /**
     * This method updates the attribute values of a specified part in the Parts list.
     * @param index used to locate the specified part to be updated
     * @param selectedPart the part that was selected to be updated
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * This method updates the attribute values of a specified product in the Products list.
     * @param index used to locate the specified product to be updated
     * @param newProduct the product that was selected to be updated
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * This method deletes a specified part from the Parts list and returns 'true' that it was deleted.
     * @param selectedPart the selected part to be deleted
     * @return if it is 'true' or not that the selected part was deleted
     */
    public static boolean deletePart(Part selectedPart) {
        if(selectedPart != null) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method deletes a specified product from the Products list and returns 'true' that it was deleted.
     * @param selectedProduct the selected product to be deleted
     * @return if it is 'true' or not that the selected product was deleted
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if(selectedProduct != null) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method retrieves all the parts currently in the Parts list.
     * @return a list of all the parts in the inventory system
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method retrieves all the products currently in the Products list.
     * @return a list of all the products in the inventory system
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
