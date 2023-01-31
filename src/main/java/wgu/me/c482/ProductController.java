package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
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
    protected void setFields() {
        nameField.setText(importedProduct.getName());
        stockField.setText(Integer.toString(importedProduct.getStock()));
        minField.setText(Integer.toString(importedProduct.getMin()));
        priceField.setText(Double.toString(importedProduct.getPrice()));
        maxField.setText(Integer.toString(importedProduct.getMax()));
    }

    /**
     * Creates a new Product and adds it to the Inventory.
     *
     */
    @FXML
    private void addProduct() {
        try {
            getFormData();
        } catch (IOException e) {
            openErrorWindow(e);
        }
        Product newProduct = new Product(
                Inventory.productIdIterator, name, price, stock, min, max
        );
        System.out.println("Part " + newProduct.getName() + " has been successfully created.");
        Inventory.addProduct(newProduct);
    }
}
