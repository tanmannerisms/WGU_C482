package wgu.me.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

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
        updateAssociatedPartsTable(importedProduct.getAllAssociatedParts());
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
    private void updateAssociatedPartsTable(ObservableList<Part> associatedParts) {
        associatedPartsTable.setItems(associatedParts);
    }

    /**
     * Creates a new Product and adds it to the Inventory.
     *
     */
    @FXML
    private void addNewProduct(ActionEvent actionEvent) {
        try {
            getFormData();
        } catch (IOException e) {
            openErrorWindow(e);
        }
        Product newProduct = new Product(
                Inventory.productIdIterator, name, price, stock, min, max
        );
        newProduct.addAssociatedParts(associatedParts);
        System.out.println("Part " + newProduct.getName() + " has been successfully created.");
        Inventory.addProduct(newProduct);
        closeWindow(actionEvent);
    }
    private void updateProduct(ActionEvent actionEvent) {
        try {
            getFormData();
        } catch (IOException e) {
            openErrorWindow(e);
            return;
        }
        importedProduct.setName(name);
        importedProduct.setPrice(price);
        importedProduct.setStock(stock);
        importedProduct.setMin(min);
        importedProduct.setMax(max);
        importedProduct.deleteAllAssociatedParts();
        importedProduct.addAssociatedParts(associatedParts);
        closeWindow(actionEvent);
    }
    @FXML
    private void onSearchAction(ActionEvent actionEvent) {
        updatePartsTable(searchParts((TextField) actionEvent.getSource()));
    }
    @FXML
    private void onAddAssociatedPartClick(ActionEvent actionEvent) {
        if (getSelectedTableItem(partsTable) != null) {
            associatedParts.add((Part) getSelectedTableItem(partsTable));
            updateAssociatedPartsTable(associatedParts);
        }
        else openNotifyWindow("Please select a part.");
        actionEvent.consume();
    }
    @FXML
    private void onRemoveAssociatedPartClick(ActionEvent actionEvent) {
        if (getSelectedTableItem(associatedPartsTable) != null) {
            associatedParts.remove((Part) getSelectedTableItem(associatedPartsTable));
        }
        else openNotifyWindow("Please select a part.");
        actionEvent.consume();
    }
    @FXML
    private void onSaveClick(ActionEvent actionEvent) {
        if (importedProduct == null) {
            addNewProduct(actionEvent);
        }
        else updateProduct(actionEvent);
    }
}
