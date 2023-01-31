package wgu.me.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController extends Controller implements Initializable {
    protected Product importedProduct;
    @FXML
    private TableView associatedPartsTable;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableColumn<Part, Integer> partIdColumn, partStockColumn, associatedPartIdColumn, associatedPartStockColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn, associatedPartNameColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn, associatedPartPriceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumns();
        updatePartsTable(Inventory.getAllParts());
    }
    public ProductController() {
        super();
    }
    protected void setImportedProductInfo() {
        setFields();
        updateAssociatedPartsTable();
    }
    private void setFields() {
        nameField.setText(importedProduct.getName());
        stockField.setText(Integer.toString(importedProduct.getStock()));
        minField.setText(Integer.toString(importedProduct.getMin()));
        priceField.setText(Double.toString(importedProduct.getPrice()));
        maxField.setText(Integer.toString(importedProduct.getMax()));
    }
    private void setTableColumns() {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    private void updateAssociatedPartsTable() {
        associatedPartsTable.setItems(importedProduct.getAllAssociatedParts());
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
    @FXML
    private void onSearchAction(ActionEvent actionEvent) {
        updatePartsTable(searchParts((TextField) actionEvent.getSource()));
    }
}
