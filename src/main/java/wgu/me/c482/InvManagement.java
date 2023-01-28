package wgu.me.c482;

import javafx.application.Application;
import javafx.stage.Stage;


public class InvManagement extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        createTestData();

        Window mainWindow = new Window("main-form.fxml", "Home");
        mainWindow.showWindow();
    }
    private void createTestData() {
        Part sample0 = new InHouse(0, "SamplePart0", 10.00, 5, 0, 10, 0);
        Part sample1 = new InHouse(1, "SamplePart1", 0.99, 5, 0, 10, 0);
        Inventory.addPart(sample0);
        Inventory.addPart(sample1);
        Product sample10 = new Product(10, "SampleProduct", 10.99, 1, 0, 100);
        Inventory.addProduct(sample10);
    }
}
