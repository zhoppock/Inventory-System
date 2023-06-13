package com.example.c42project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MAIN FILE - This class boots up the java program's application.
 * <p><b>FUTURE_ENHANCEMENT</b></p>
 * <p>Something that would probably enhance the functionality of this program would be the requirement of parts to a product.
 * You should have, say for example, at least 5 parts added to a product before you can add or modify it to the products list.
 * Without parts, how would the product be made up of anything?
 * Not only that, but the price of the product should be determined by a sum of the associated parts' prices instead of inputted normally.
 * Take that sum of the parts' prices and double it (for labor/manufacturing fee reasons) and that will be the price of the product.</p>
 * @author Zachary Hoppock
 */
public class FXMLMainSystemLoader extends Application {

    /**
     * This method executes the main menu file of the application.
     * @param stage used to call and execute a file
     * @throws IOException an I/O exception may be thrown if there is an issue executing a file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainSystemLoader.class.getResource("mainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 901.0, 411.0);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method, and it gets called once you begin running the java program.
     * <p>The JavaDocs are located at \C42Project\JavaDoc</p>
     * @param args a standard parameter when running your main method
     */
    public static void main(String[] args) {

        InHouse part1 = new InHouse(1, "Screw", 0.24, 40, 1, 100, 13);
        Outsourced part2 = new Outsourced(2, "Bolt", 0.45, 71, 1, 100, "Home Depot");
        Outsourced part3 = new Outsourced(3, "Girder", 2.78, 50, 1, 50, "Lowes");
        Product product1 = new Product(1, "Jetta", 123.24, 20, 2, 50);
        Product product2 = new Product(2, "Julius Bistro", 144.45, 15, 2, 60);

        Inventory.addPart(part1);   Inventory.addPart(part2);   Inventory.addPart(part3);
        Inventory.addProduct(product1); Inventory.addProduct(product2);
        product1.addAssociatedPart(part1);  product1.addAssociatedPart(part1);  product1.addAssociatedPart(part2);
        product2.addAssociatedPart(part3);  product2.addAssociatedPart(part1);

        launch();
    }
}