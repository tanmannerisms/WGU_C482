package wgu.me.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    private TableView productsTable, partsTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn, partStockColumn, productIdColumn, productStockColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn, productNameColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn, productPriceColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumns();
        updateTables();
    }
    public MainController() {
        super();
    }
    @FXML
    private void addProductSceneChange(ActionEvent actionEvent) {
        Window addProduct = new Window("add-product.fxml", "Add-Product");
        addProduct.showWindowAndWait();
        actionEvent.consume();
    }
    @FXML
    private void changeProductSceneChange(ActionEvent actionEvent) {
        Window changeProduct = new Window("modify-product.fxml", "Change Product");
        changeProduct.showWindowAndWait();
        actionEvent.consume();
    }
    @FXML
    private void addPartSceneChange(ActionEvent actionEvent) {
        Window addPart = new Window("add-part.fxml", "Add-Part");
        addPart.showWindowAndWait();
        actionEvent.consume();
    }
    @FXML
    private void changePartSceneChange(ActionEvent actionEvent) {
        Window changePart = new Window("modify-part.fxml", "Alter Part");
        changePart.showWindowAndWait();
        actionEvent.consume();
    }
    @FXML
    private void updateTables() {
        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());
        System.out.println("Tables updated");
    }
    private void setTableColumns() {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
    }
    @FXML
    private void deletePart() {
        boolean partDeleted;
        partDeleted = Inventory.deletePart(getSelectedPart());
        if (!partDeleted) {
            ErrorWindow errorWindow = new ErrorWindow("Part unsuccessfully deleted.");
        }
        else {
            ErrorWindow errorwindow = new ErrorWindow("Part successfully deleted");
        }
    }
    @FXML
    private void deleteProduct() {
        boolean productDeleted;
        productDeleted = Inventory.deleteProduct(getSelectedProduct());
        if (!productDeleted) {
            ErrorWindow errorWindow = new ErrorWindow("Product unsuccessfully deleted");
        }
        else {
            ErrorWindow errorwindow = new ErrorWindow("Product successfully deleted");
        }
    }
    // Need to add these two methods to Controller.java instead.
    private Part getSelectedPart() {
        return (Part) partsTable.getSelectionModel().getSelectedItem();
    }
    private Product getSelectedProduct() {
        return (Product) productsTable.getSelectionModel().getSelectedItem();
    }
}
