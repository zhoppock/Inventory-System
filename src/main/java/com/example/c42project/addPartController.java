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
 * This class allows a user to create a new Part.
 * @author Zachary Hoppock
 */
public class addPartController implements Initializable {

    @FXML
    private Label partTypeParameter;

    @FXML
    private TextField partTypeTxt;

    @FXML
    private RadioButton inHouseSelected;

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

    private String exceptionMessage = "";

    /**
     * This method is used to transfer back to the main menu.
     * @param event used to signify that a certain button was pressed
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    public void callDirectory(ActionEvent event) throws IOException {
        Parent scene;
        Stage stage;
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/c42project/mainForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method runs through all the text fields to check if any input validation errors occurred.
     * <p><b>RUNTIME_ERROR</b></p>
     * <p>This method was made in conjunction to the onActionSavePart method.
     * It was made to not clutter the aforementioned method and to check all the parameters that had to be met for each input.
     * While I was programming this I had originally initialized the exceptionMessage variable and printed it inside this method, before I made it a class variable and printed it in the aforementioned method.
     * If I hit Save with no inputs filled, I would get compiler error messages in the console, but if I filled everything up except the name field and hit Save, it would go through.
     * With the exceptionMessage being initialized and printed within this method, the program wasn't exactly adding any string text to the variable, so the method would return true each time.
     * Because of this, onActionSavePart would keep going and because an empty name string still counted as input, it would save the part with no name.
     * That is when I realized that I needed to initialize the exceptionMessage variable outside the method, in the class, and display the message only if this method returned false inside the Save method.</p>
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
     * This method initializes the Add Part window of the application.
     * @param url a standard parameter when running JavaFX initialization
     * @param resourceBundle a standard parameter when running JavaFX initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Part Initialized");
    }

    /**
     * This method sends you back to the main menu if you click the Cancel button, but asks for confirmation if you had left anything inputted.
     * @param event the Action Event of pressing the Cancel button
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException {

        if(partNameTxt.getText().isEmpty() && partInventoryTxt.getText().isEmpty() && partPriceTxt.getText().isEmpty() && partMaxTxt.getText().isEmpty() && partMinTxt.getText().isEmpty() && partTypeTxt.getText().isEmpty()) {
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
     * This method will return to the main menu with a newly created Part if you click the Save button, granted all text fields have valid input.
     * @param event the Action Event of pressing the Save button
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @FXML
    public void onActionSavePart(ActionEvent event) throws IOException {

        if(inputValidation(partNameTxt.getText(), partInventoryTxt.getText(), partPriceTxt.getText(), partMinTxt.getText(),partMaxTxt.getText(), partTypeTxt.getText())) {
            int id;
            if ((Inventory.getAllParts()).size() > 0) {
                id = ((Inventory.getAllParts()).get(Inventory.getAllParts().size() - 1)).getId() + 1;
            } else {
                id = 1;
            }

            if (partTG.getSelectedToggle() == inHouseSelected) {
                InHouse newInHouse = new InHouse(0, "", 0, 0, 0, 0, 0);
                newInHouse.setId(id);
                newInHouse.setName(partNameTxt.getText());
                newInHouse.setStock(Integer.parseInt(partInventoryTxt.getText()));
                newInHouse.setPrice(Double.parseDouble(partPriceTxt.getText()));
                newInHouse.setMin(Integer.parseInt(partMinTxt.getText()));
                newInHouse.setMax(Integer.parseInt(partMaxTxt.getText()));
                newInHouse.setMachineId(Integer.parseInt(partTypeTxt.getText()));
                Inventory.addPart(newInHouse);
                System.out.println("In-House Part added successfully");
            } else {
                Outsourced newOutsourced = new Outsourced(0, "", 0, 0, 0, 0, "");
                newOutsourced.setId(id);
                newOutsourced.setName(partNameTxt.getText());
                newOutsourced.setStock(Integer.parseInt(partInventoryTxt.getText()));
                newOutsourced.setPrice(Double.parseDouble(partPriceTxt.getText()));
                newOutsourced.setMin(Integer.parseInt(partMinTxt.getText()));
                newOutsourced.setMax(Integer.parseInt(partMaxTxt.getText()));
                newOutsourced.setCompanyName(partTypeTxt.getText());
                Inventory.addPart(newOutsourced);
                System.out.println("Outsourced Part added successfully");
            }
            callDirectory(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(exceptionMessage);
            alert.showAndWait();
        }
    }
}