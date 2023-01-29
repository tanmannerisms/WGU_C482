package wgu.me.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    private TableView productsTable, partsTable;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTables();
    }
    public MainController() {
        super();
    }
    @FXML
    private void addProductSceneChange(ActionEvent actionEvent) {
        Window addProduct = new Window("add-product.fxml", "Add-Product");
        addProduct.showWindow();
        actionEvent.consume();
    }
    @FXML
    private void changeProductSceneChange(ActionEvent actionEvent) {
        Window changeProduct = new Window("modify-product.fxml", "Change Product");
        changeProduct.showWindow();
        actionEvent.consume();
    }
    @FXML
    private void addPartSceneChange(ActionEvent actionEvent) {
        Window addPart = new Window("add-part.fxml", "Add-Part");
        addPart.showWindow();
        actionEvent.consume();
    }
    @FXML
    private void changePartSceneChange(ActionEvent actionEvent) {
        Window changePart = new Window("modify-part.fxml", "Alter Part");
        changePart.showWindow();
        actionEvent.consume();
    }
    @FXML
    private void updateTables() {
        productsTable.setItems(Inventory.getAllProducts());
        partsTable.setItems(Inventory.getAllParts());
    }
}
