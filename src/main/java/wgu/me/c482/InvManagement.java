package wgu.me.c482;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * RUNTIME ERROR: When altering a part, the part will automatically be set to outsourced when trying to edit it for a
 * second time.
 *
 * Ex. Added an InHouse part named Test to the inventory through the add-part form. Select the part and hit change.
 *      It opens up the change-part form with the OutSourced radio button selected. Change it back to InHouse and save.
 *      Open it back up under the change-part form. InHouse radio button is selected as expected. Click save and open
 *      it back up again for the same part. Now the OutSourced radio button is selected again. Clicking on cancel
 *      instead of save will circumvent this.
 *
 * After having run the program in debug mode and crawling through the code, line by line, I found that the boolean
 * value inHouseSelected was not being set on initialization of the change-part form. I added that functionality into
 * the setFields() method.
 */

/**
 * FUTURE ENHANCEMENTS: More advanced error handling to allow for a Textfield to be passed in to the error handling
 * system, allowing to reduce the amount of code while still maintaining the functionality of the specification for the
 * location of the error.
 */

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
