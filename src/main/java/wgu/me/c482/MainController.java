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
    @FXML
    private void addProductSceneChange() {
        Window addProduct = new Window("add-product.fxml", "Add-Product");
        addProduct.showWindow();
    }
    @FXML
    private void changeProductSceneChange() {
        Window changeProduct = new Window("modify-product.fxml", "Change Product");
        changeProduct.showWindow();
    }
    @FXML
    private void addPartSceneChange() {
        Window addPart = new Window("add-part.fxml", "Add-Part");
        addPart.showWindow();
    }
    @FXML
    private void changePartSceneChange() {
        Window changePart = new Window("modify-part.fxml", "Alter Part");
        changePart.showWindow();
    }
}
