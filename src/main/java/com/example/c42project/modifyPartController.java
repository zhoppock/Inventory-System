package com.example.c42project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class allows a user to modify an existing Part.
 * @author Zachary Hoppock
 */
public class modifyPartController implements Initializable {

    @FXML
    private Label partTypeParameter;

    @FXML
    private TextField partTypeTxt;

    @FXML
    private RadioButton inHouseSelected;

    @FXML
    private RadioButton outsourcedSelected;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField partInventoryTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private ToggleGroup partTG;

    private static Part partToUpdate = null;

    private static int partIndex;

    private String exceptionMessage = "";

    /**
     * This method is used to transfer back to the main menu.
     * @param event used to signify that a certain button was pressed
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    public void callDirectory(ActionEvent event) throws IOException {

        Stage stage;
        Parent scene;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c42project/mainForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method retrieves the attribute values and list index of the selected part from the main menu's part table.
     * @param selectedPart the part that was selected to be modified
     * @param index the list index of the selected part
     */
    public static void getSelectedPart(Part selectedPart, int index) {

        partToUpdate = selectedPart;
        partIndex = index;

    }

    /**
     * This method runs through all the text fields to check if any input validation errors occurred.
     * @param name the text value in the name field
     * @param stock the text value in the stock field
     * @param price the text value in the price field
     * @param min the text value in the min field
     * @param max the text value in the max field
     * @param type the text value in the type field
     * @return whether it is 'true' that all the text fields have valid input or not
     */
    public boolean inputValidation(String name, String stock, String price, String min, String max, String type) {

        exceptionMessage = "";
        boolean inputValid;

        if(name.isEmpty()) {
            exceptionMessage += " - Part requires a name\n";
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
                exceptionMessage += " - Part requires inventory\n";
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
                exceptionMessage += " - Part requires a price\n";
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
                exceptionMessage += " - Part requires a maximum value\n";
            } else {
                try {
                    Integer.parseInt(max);
                } catch(NumberFormatException f) {
                    exceptionMessage += " - Maximum value must be a number\n";
                    System.out.println("'Maximum' Exception: " + f);
                }
            }
            if(min.isEmpty()) {
                exceptionMessage += " - Part requires a minimum value\n";
            } else {
                try {
                    Integer.parseInt(min);
                } catch(NumberFormatException f) {
                    exceptionMessage += " - Minimum value must be a number\n";
                    System.out.println("'Minimum' Exception: " + f);
                }
            }
        }
        if(partTG.getSelectedToggle() == inHouseSelected) {
            if (type.isEmpty()) {
                exceptionMessage += " - Part requires a machine ID\n";
            } else {
                try {
                    Integer.parseInt(type);
                } catch(NumberFormatException e) {
                    exceptionMessage += " - Machine ID must be a number\n";
                    System.out.println("'Machine ID' Exception: " + e);
                }
            }
        } else {
            if (type.isEmpty()) {
                exceptionMessage += " - Part requires a company name\n";
            }
        }

        inputValid = exceptionMessage.isEmpty();
        return inputValid;

    }

    /**
     * This method initializes the Modify Part window of the application, including displaying the current attribute values of the selected part.
     * @param url a standard parameter when running JavaFX initialization
     * @param resourceBundle a standard parameter when running JavaFX initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Modify Part Initialized");

        partIdTxt.setText(String.valueOf(partToUpdate.getId()));
        partNameTxt.setText(partToUpdate.getName());
        partInventoryTxt.setText(String.valueOf(partToUpdate.getStock()));
        partPriceTxt.setText(String.valueOf(partToUpdate.getPrice()));
        partMaxTxt.setText(String.valueOf(partToUpdate.getMax()));
        partMinTxt.setText(String.valueOf(partToUpdate.getMin()));
        if (partToUpdate instanceof InHouse) {
            partTypeTxt.setText(String.valueOf(((InHouse) partToUpdate).getMachineId()));
        } else if (partToUpdate instanceof Outsourced) {
            partTypeParameter.setText("Company Name:");
            partTG.selectToggle(outsourcedSelected);
            partTypeTxt.setText(((Outsourced) partToUpdate).getCompanyName());
        }

    }

    /**
     * This method sends you back to the main menu if you click the Cancel button, warning you of any changes to be lost if you do so.
     * @param event the Action Event of pressing the Cancel button
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?  Changes will not be saved.");
        alert.setTitle("Exit Warning");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            callDirectory(event);
        }

    }

    /**
     * This method changes the type parameter at the bottom to "Machine ID" if the In-House radio button is toggled.
     */
    @FXML
    public void onActionInHouseSelected() {
        partTypeParameter.setText("Machine ID:");
    }

    /**
     * This method changes the type parameter at the bottom to "Company Name" if the Outsourced radio button is toggled.
     */
    @FXML
    public void onActionOutsourcedSelected() {
        partTypeParameter.setText("Company Name:");
    }

    /**
     * This method will return to the main menu with the updated selected Part if you click the Save button, granted all text fields have valid input.
     * @param event the Action Event of pressing the Save button
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionSavePart(ActionEvent event) throws IOException {

        if (inputValidation(partNameTxt.getText(), partInventoryTxt.getText(), partPriceTxt.getText(), partMinTxt.getText(), partMaxTxt.getText(), partTypeTxt.getText())) {
            if (partTG.getSelectedToggle() == inHouseSelected) {
                InHouse updatedInHouse = new InHouse(0, "", 0, 0, 0, 0, 0);
                updatedInHouse.setId(Integer.parseInt(partIdTxt.getText()));
                updatedInHouse.setName(partNameTxt.getText());
                updatedInHouse.setStock(Integer.parseInt(partInventoryTxt.getText()));
                updatedInHouse.setPrice(Double.parseDouble(partPriceTxt.getText()));
                updatedInHouse.setMin(Integer.parseInt(partMinTxt.getText()));
                updatedInHouse.setMax(Integer.parseInt(partMaxTxt.getText()));
                updatedInHouse.setMachineId(Integer.parseInt(partTypeTxt.getText()));
                Inventory.updatePart(partIndex, updatedInHouse);
            } else {
                Outsourced updatedOutsourced = new Outsourced(0, "", 0, 0, 0, 0, "");
                updatedOutsourced.setId(Integer.parseInt(partIdTxt.getText()));
                updatedOutsourced.setName(partNameTxt.getText());
                updatedOutsourced.setStock(Integer.parseInt(partInventoryTxt.getText()));
                updatedOutsourced.setPrice(Double.parseDouble(partPriceTxt.getText()));
                updatedOutsourced.setMin(Integer.parseInt(partMinTxt.getText()));
                updatedOutsourced.setMax(Integer.parseInt(partMaxTxt.getText()));
                updatedOutsourced.setCompanyName(partTypeTxt.getText());
                Inventory.updatePart(partIndex, updatedOutsourced);
            }
            System.out.println("Part updated successfully");
            callDirectory(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(exceptionMessage);
            alert.showAndWait();
        }
    }
}