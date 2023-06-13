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
 * This class is the main menu of the application.
 * @author Zachary Hoppock
 */
public class mainController implements Initializable {

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
    private TableColumn<Product, Integer > productIdCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TextField searchPartTxt;

    @FXML
    private TextField searchProductTxt;

    /**
     * This method is used to transfer you to a specified file directory.
     * @param event used to signify that a certain button was pressed
     * @param directory the file directory to execute
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    public void callDirectory(ActionEvent event, String directory) throws IOException {

        Stage stage;
        Parent scene;
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(directory)));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method initializes the Modify Product window of the application, including displaying all the parts from the Parts list and displaying all the products from the Products list.
     * @param url a standard parameter when running JavaFX initialization
     * @param resourceBundle a standard parameter when running JavaFX initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        System.out.println("Main Initialized");

    }

    /**
     * This method allows the user to search for a specific part(s) by either a character, name, or ID number in the respective search field.
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
     * This method allows the user to search for a specific product(s) by either a character, name, or ID number in the respective search field.
     */
    public void onInputSearchProducts() {

        ObservableList<Product> foundProducts = Inventory.lookupProduct(searchProductTxt.getText());
        try {
            int productId = Integer.parseInt(searchProductTxt.getText());
            Product foundProduct = Inventory.lookupProduct(productId);
            if (foundProduct != null && !(foundProducts.contains(foundProduct))) {
                foundProducts.add(foundProduct);
            }
        } catch (NumberFormatException e) {
            // ignore
        }
        productTableView.setItems(foundProducts);

    }

    /**
     * This method sends you to the Add Part menu.
     * @param event the Action Event of pressing the Add button in the Part section
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionAddPart(ActionEvent event) throws IOException {

        callDirectory(event,"/com/example/c42project/addPartForm.fxml");

    }

    /**
     * This method sends you to the Add Product menu.
     * @param event the Action Event of pressing the Add button in the Product section
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionAddProduct(ActionEvent event) throws IOException {

        callDirectory(event,"/com/example/c42project/addProductForm.fxml");

    }

    /**
     * This method will allow you to remove a selected part from the Parts table if you click the respective Delete button.
     * A warning window will pop up asking if you wish to proceed in deleting the part from the Parts list.
     * A warning window will pop up if no part is selected.
     */
    @FXML
    public void onActionDeletePart() {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Part?");
            alert.setTitle("Deletion Warning");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (Inventory.deletePart(selectedPart)) {
                    System.out.println("Part deletion successful");
                } else {
                    System.out.println("Part deletion failure");
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setContentText("No part has been selected.");
            alert.showAndWait();
            System.out.println("No part selected to delete");
        }
    }

    /**
     * This method will allow you to remove a selected product from the Products table if you click the respective Delete button.
     * A warning window will pop up asking if you wish to proceed in deleting the product from the Products list.
     * A warning window will pop up if no product is selected.
     */
    @FXML
    public void onActionDeleteProduct() {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Product?");
            alert.setTitle("Deletion Warning");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (Inventory.deleteProduct(selectedProduct)) {
                    System.out.println("Product deletion successful");
                } else {
                    System.out.println("Product deletion failure");
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setContentText("No product has been selected.");
            alert.showAndWait();
            System.out.println("No product selected to delete");
        }
    }

    /**
     * This method will end the application if Exit is pressed, asking if you are sure you want to proceed doing so.
     */
    @FXML
    public void onActionExit() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the application?");
        alert.setTitle("Exit Warning");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }

    /**
     * This method sends you to the Modify Part menu as long as a part in the respective table is selected.
     * A warning window will pop up if no part is selected.
     * @param event the Action Event of pressing the Add button in the Part section
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionModifyPart(ActionEvent event) throws IOException {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        int partIndex = Inventory.getAllParts().indexOf(selectedPart);
        if(selectedPart != null) {
            modifyPartController.getSelectedPart(selectedPart, partIndex);
            callDirectory(event,"/com/example/c42project/modifyPartForm.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setContentText("No part has been selected.");
            alert.showAndWait();
            System.out.println("No part selected to update");
        }

    }

    /**
     * This method sends you to the Modify Product menu as long as a part in the respective table is selected.
     * A warning window will pop up if no product is selected.
     * @param event the Action Event of pressing the Add button in the Product section
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionModifyProduct(ActionEvent event) throws IOException {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        int productIndex = Inventory.getAllProducts().indexOf(selectedProduct);
        if(selectedProduct != null) {
            modifyProductController.getSelectedProduct(selectedProduct, productIndex);
            callDirectory(event,"/com/example/c42project/modifyProductForm.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setContentText("No product has been selected.");
            alert.showAndWait();
            System.out.println("No product selected to update");
        }

    }

}