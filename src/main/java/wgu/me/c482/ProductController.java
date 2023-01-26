package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProductController extends Controller{
    private String partName;
    private int partInventory, partMin, partMax;
    private double partPrice;
    private TextField idField, nameField, inventoryField, priceField, minField, maxField,
    public ProductController() {
        super();
    }
    ProductController(Inventory inventory) {
        super(inventory);
    }
    @FXML
    private void addProduct() {
        partName = nameField.getText();
        partInventory = getIntFromTextField(inventoryField);
        partPrice = getDoubleFromTextField(priceField);
        partMin = getIntFromTextField(minField);
        partMax = getIntFromTextField(maxField);
        Product newProduct = new Product(productId, productName, productPrice, productInventory, productMin, productMax);
        System.out.println("Part " + newProduct.getName() + " has been successfully created.");
    }
}
