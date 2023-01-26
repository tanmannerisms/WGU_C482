package wgu.me.c482;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;

public class InvManagement extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Inventory inventory = new Inventory();
        Part sample0 = new InHouse(0, "SamplePart0", 10.00, 5, 0, 10, 0);
        Part sample1 = new InHouse(1, "SamplePart1", 0.99, 5, 0, 10, 0);
        inventory.addPart(sample0);
        inventory.addPart(sample1);
        Product sample10 = new Product(10, "SampleProduct", 10.99, 1, 0, 100);
        inventory.addProduct(sample10);

        MainController mainFormController = new MainController(inventory);
        Window mainWindow = new Window("main-form.fxml", "Home", mainFormController);
        mainWindow.showWindow();
/*
        Controller main = new MainController("main-form.fxml", "Home", inventory);
        main.showStage();
*/
    }
}
