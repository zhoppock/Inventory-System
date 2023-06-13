package com.example.c42project;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class allows a user to create a new Product.
 * @author Zachary Hoppock
 */
public class addProductController implements Initializable{

    @FXML
    private TableColumn<Part, Integer> assocPartIdCol;

    @FXML
    private TableColumn<Part, Integer> assocPartInventoryCol;

    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    @FXML
    private TableView<Part> assocPartTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TextField productInventoryTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField searchPartTxt;

    private String exceptionMessage = "";

    private final Product newProduct = new Product(0, "", 0, 0, 0, 0);

    /**
     * This method is used to transfer back to the main menu.
     * @param event used to signify that a certain button was pressed
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    public void callDirectory(ActionEvent event) throws IOException {

        Stage stage;
        Parent scene;
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c42project/mainForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method runs through all the text fields to check if any input validation errors occurred.
     * @param name the text value in the name field
     * @param stock the text value in the stock field
     * @param price the text value in the price field
     * @param min the text value in the min field
     * @param max the text value in the max field
     * @return whether it is 'true' that all the text fields have valid input or not
     */
    public boolean inputValidation(String name, String stock, String price, String min, String max) {

        exceptionMessage = "";
        boolean inputValid;

        if(name.isEmpty()) {
            exceptionMessage += " - Product requires a name\n";
        }
        try {
            int testStock = Integer.parseInt(stock);
            int testMax = Integer.parseInt(max);
            int testMin = Integer.parseInt(min);
            if(testStock <= 0) {
                exceptionMessage += " - Inventory must be greater than 0\n";
            }
            if(testStock < testMin || testStock > testMax) {
                exceptionMessage += " - Inventory must be greater or equal than the Minimum value\n   and less than or equal to the Maximum value\n";
            }
        } catch (NumberFormatException e) {
            System.out.println("'Inventory value' Exception: " + e);
            if(stock.isEmpty()) {
                exceptionMessage += " - Product requires inventory\n";
            } else {
                try {
                    Integer.parseInt(stock);
                } catch(NumberFormatException f) {
                    exceptionMessage += " - Inventory must be a number\n";
                    System.out.println("'Inventory' Exception: " + f);
                }
            }
        }
        try {
            double testPrice = Double.parseDouble(price);
            if(testPrice <= 0) {
                exceptionMessage += " - Price must be greater than 0\n";
            }
        } catch (NumberFormatException e) {
            System.out.println("'Price value' Exception: " + e);
            if(price.isEmpty()) {
                exceptionMessage += " - Product requires a price\n";
            } else {
                try {
                    Double.parseDouble(price);
                } catch(NumberFormatException f) {
                    exceptionMessage += " - Price must be a number\n";
                    System.out.println("'Price' Exception: " + f);
                }
            }
        }
        try {
            int testMax = Integer.parseInt(max);
            int testMin = Integer.parseInt(min);
            if(testMin >= testMax) {
                exceptionMessage += " - Minimum value must be less than Maximum value\n";
            }
            if(testMax <= 0 || testMin <= 0) {
                exceptionMessage += " - Minimum and Maximum values must be greater than 0\n";
            }
        } catch (NumberFormatException e) {
            System.out.println("'Min & Max' Exception: " + e);
            if(max.isEmpty()) {
                exceptionMessage += " - Product requires a maximum value\n";
            } else {
                try {
                    Integer.parseInt(max);
                } catch(NumberFormatException f) {
                    exceptionMessage += " - Maximum value must be a number\n";
                    System.out.println("'Maximum' Exception: " + f);
                }
            }
            if(min.isEmpty()) {
                exceptionMessage += " - Product requires a minimum value\n";
            } else {
                try {
                    Integer.parseInt(min);
                } catch(NumberFormatException f) {
                    exceptionMessage += " - Minimum value must be a number\n";
                    System.out.println("'Minimum' Exception: " + f);
                }
            }
        }

        inputValid = exceptionMessage.isEmpty();
        return inputValid;
    }

    /**
     * This method initializes the Add Product window of the application, including displaying all the parts from the Parts list and displaying a table to add parts into.
     * @param url a standard parameter when running JavaFX initialization
     * @param resourceBundle a standard parameter when running JavaFX initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Add Product Initialized");

        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocPartTableView.setItems(newProduct.getAllAssociatedParts());
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * This method allows the user to search for a specific part(s) by either a character, name, or ID number in the search field.
     */
    public void onInputSearchParts() {

        ObservableList<Part> foundParts = Inventory.lookupPart(searchPartTxt.getText());
        try {
            int partId = Integer.parseInt(searchPartTxt.getText());
            Part foundPart = Inventory.lookupPart(partId);
            if (foundPart != null && !(foundParts.contains(foundPart))) {
                foundParts.add(foundPart);
            }
        } catch (NumberFormatException e) {
            // ignore
        }
        partTableView.setItems(foundParts);

    }

    /**
     * This method allows the user to select a part from the upper part table and click Add to add it to the product's associated parts list.
     * A warning window will pop up if no part is selected.
     */
    @FXML
    public void onActionAddAssocPart() {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if(selectedPart != null) {
            newProduct.addAssociatedPart(selectedPart);
            System.out.println("Part added successfully");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setContentText("No part has been selected.");
            alert.showAndWait();
            System.out.println("No part selected to add.");
        }

    }

    /**
     * This method will allow you to remove a selected part from the lower Parts table if you click the Remove Associated Part button.
     * A warning window will pop up asking if you wish to proceed in removing the associated part from the product.
     * A warning window will pop up if no part is selected.
     */
    @FXML
    public void onActionRemoveAssocPart() {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Part?");
            alert.setTitle("Removal Warning");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (newProduct.deleteAssociatedPart(selectedPart)) {
                    System.out.println("Part removed successfully");
                } else {
                    System.out.println("Part removal failure");
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setContentText("No part has been selected.");
            alert.showAndWait();
            System.out.println("No part selected to remove");
        }

    }

    /**
     * This method sends you back to the main menu if you click the Cancel button, but asks for confirmation if you had left anything inputted.
     * @param event the Action Event of pressing the Cancel button
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException {

        if(productNameTxt.getText().isEmpty() && productInventoryTxt.getText().isEmpty() && productPriceTxt.getText().isEmpty() && productMaxTxt.getText().isEmpty() && productMinTxt.getText().isEmpty() && newProduct.getAllAssociatedParts().isEmpty()) {
            callDirectory(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?  Inputs will not be saved.");
            alert.setTitle("Exit Warning");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                callDirectory(event);
            }
        }

    }

    /**
     * This method will return to the main menu with a newly created Product if you click the Save button, granted all text fields have valid input.
     * @param event the Action Event of pressing the Save button
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionSaveProduct(ActionEvent event) throws IOException {

        if(inputValidation(productNameTxt.getText(), productInventoryTxt.getText(), productPriceTxt.getText(), productMinTxt.getText(),productMaxTxt.getText())) {
            int id;
            if ((Inventory.getAllParts()).size() > 0) {
                id = ((Inventory.getAllParts()).get(Inventory.getAllParts().size() - 1)).getId() + 1;
            } else {
                id = 1;
            }

            newProduct.setId(id);
            newProduct.setName(productNameTxt.getText());
            newProduct.setStock(Integer.parseInt(productInventoryTxt.getText()));
            newProduct.setPrice(Double.parseDouble(productPriceTxt.getText()));
            newProduct.setMin(Integer.parseInt(productMinTxt.getText()));
            newProduct.setMax(Integer.parseInt(productMaxTxt.getText()));
            Inventory.addProduct(newProduct);
            System.out.println("Product added successfully");
            callDirectory(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(exceptionMessage);
            alert.showAndWait();
        }

    }

}