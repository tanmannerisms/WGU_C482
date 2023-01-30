package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController extends Controller implements Initializable {
    protected Product importedProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public ProductController() {
        super();
    }

    /**
     * Creates a new Product and adds it to the Inventory.
     *
     */
    @FXML
    private void addProduct() {
        getFormData();
        Product newProduct = new Product(
                id, name, price, stock, min, max
        );
        System.out.println("Part " + newProduct.getName() + " has been successfully created.");
        Inventory.addProduct(newProduct);
    }

    /**
     * TO BE DELETED. SEE PartController#getPartFormInfo()
     *
     */
    private void getFormData() {
        name = nameField.getText();
        stock = getIntFromTextField(stockField);
        price = getDoubleFromTextField(priceField);
        min = getIntFromTextField(minField);
        max = getIntFromTextField(maxField);
        id = createProductId();
    }
    private int createProductId() {
        return Inventory.productIdIterator++;
    }
}
