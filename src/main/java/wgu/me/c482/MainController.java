package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public MainController() {
        super();
    }
    MainController(Inventory inventory) {
        super(inventory);
    }
    @FXML
    private void addProductSceneChange() {
        ProductController addProductController = new ProductController(inventory);
        Window addProduct = new Window("add-product.fxml", "Add-Product", addProductController);
        addProduct.showWindow();
    }
    @FXML
    private void changeProductSceneChange() {
        ProductController changeProductController = new ProductController(inventory);
        Window changeProduct = new Window("modify-product.fxml", "Change Product", changeProductController);
        changeProduct.showWindow();
    }
    @FXML
    private void addPartSceneChange() {
        PartController addPartController = new PartController(inventory);
        Window addPart = new Window("add-part.fxml", "Add-Part", addPartController);
        addPart.showWindow();
    }
    @FXML
    private void changePartSceneChange() {
        PartController changePartController = new PartController(inventory);
        Window changePart = new Window("modify-part.fxml", "Alter Part", changePartController);
        changePart.showWindow();
    }
}
