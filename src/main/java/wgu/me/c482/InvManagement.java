package wgu.me.c482;

import javafx.application.Application;
import javafx.stage.Stage;




public class InvManagement extends Application {
    /**
     * Creates a window with the main form and creates the test data.
     *
     * @param stage not used
     * @throws Exception not thrown.
     */
    @Override
    public void start(Stage stage) throws Exception {
        createTestData();

        Window mainWindow = new Window("main-form.fxml", "Home");
        mainWindow.showWindow();
    }

    /**
     * Creates two sample Parts and one sample Product to use in testing.
     */
    private void createTestData() {
        Part sample0 = new InHouse(Inventory.partIdIterator, "SamplePart0", 10.00, 5, 0, 10, 1245);
        Inventory.addPart(sample0);
        Part sample1 = new Outsourced(Inventory.partIdIterator, "SamplePart1", 0.99, 5, 0, 10, "Pseudo Company");
        Inventory.addPart(sample1);
        Product sample10 = new Product(Inventory.productIdIterator, "SampleProduct", 10.99, 1, 0, 100);
        Inventory.addProduct(sample10);
    }
}
