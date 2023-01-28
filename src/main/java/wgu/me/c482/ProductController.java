package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController extends Controller implements Initializable {
    private String productName;
    private int productId, productInventory, productMin, productMax;
    private double productPrice;
    private TextField idField, nameField, inventoryField, priceField, minField, maxField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public ProductController() {
        super();
    }
    @FXML
    private void addProduct() {
        getFormData();
        Product newProduct = new Product(
                productId, productName, productPrice, productInventory, productMin, productMax
        );
        System.out.println("Part " + newProduct.getName() + " has been successfully created.");
    }
    private void getFormData() {
        productName = nameField.getText();
        productInventory = getIntFromTextField(inventoryField);
        productPrice = getDoubleFromTextField(priceField);
        productMin = getIntFromTextField(minField);
        productMax = getIntFromTextField(maxField);
        productId = createProductId();
    }
    private int createProductId() {
        int id = Inventory.productId;
        Inventory.productId++;
        return id;
    }
}
