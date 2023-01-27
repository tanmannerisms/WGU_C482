package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProductController extends Controller{
    private String productName;
    private int productId, productInventory, productMin, productMax;
    private double productPrice;
    private TextField idField, nameField, inventoryField, priceField, minField, maxField;
    public ProductController() {
        super();
    }
    ProductController(Inventory inventory) {
        super(inventory);
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
        int id = inventory.productId;
        inventory.productId++;
        return id;
    }
}
