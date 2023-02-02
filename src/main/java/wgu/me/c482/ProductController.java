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
    private TextField partSearchField, idField;
    @FXML
    private TableColumn<Part, Integer> partIdColumn, partStockColumn, associatedPartIdColumn, associatedPartStockColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn, associatedPartNameColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn, associatedPartPriceColumn;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Initialization of the ProductController to set the Table columns and load all the Parts in the Inventory onto
     * the upper TableView.
     *
     * @param url not used.
     * @param resourceBundle not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumns();
        updatePartsTable(Inventory.getAllParts());
    }
    public ProductController() {
        super();
    }

    /**
     * Gets the information from the importedProduct and loads it into their corresponding fiedls.
     * Also gets the associated parts with the importedProduct and loads them into the associated parts table.
     */
    protected void setImportedProductInfo() {
        setFields();
        updateAssociatedPartsTable(importedProduct.getAllAssociatedParts());
        idField.setText(Integer.toString(importedProduct.getId()));
    }

    /**
     * Sets all the TextFields with the information gathered from the importedProduct.
     */
    private void setFields() {
        idField.setText(Integer.toString(importedProduct.getId()));
        nameField.setText(importedProduct.getName());
        stockField.setText(Integer.toString(importedProduct.getStock()));
        minField.setText(Integer.toString(importedProduct.getMin()));
        priceField.setText(Double.toString(importedProduct.getPrice()));
        maxField.setText(Integer.toString(importedProduct.getMax()));
    }

    /**
     * Sets the categories of the Columns for properly loading the information.
     */
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

    /**
     * Refreshes the associatedPartsTable with the associatedParts from the importedProduct.
     *
     * @param associatedParts the observable list that is used to update the table.
     */
    private void updateAssociatedPartsTable(ObservableList<Part> associatedParts) {
        associatedPartsTable.setItems(associatedParts);
    }

    /**
     * Creates a new Product and adds it to the Inventory.
     *
     * @param actionEvent the action event used to close the window after Product is added.
     * @see Controller#getFormData()
     * @see Controller#validateFormData()
     */
    @FXML
    private void addNewProduct(ActionEvent actionEvent) {
        try {
            getFormData();
            validateFormData();
        } catch (IOException e) {
            openErrorWindow(e);
            return;
        }
        Product newProduct = new Product(
                Inventory.productIdIterator, name, price, stock, min, max
        );
        newProduct.addAssociatedParts(associatedParts);
        System.out.println("Part " + newProduct.getName() + " has been successfully created.");
        Inventory.addProduct(newProduct);
        closeWindow(actionEvent);
    }

    /**
     * Sets all the information taken from the fields of the importedProduct. Does not create a new Product.
     *
     * @param actionEvent used to close the window if the Product is updated.
     */
    private void updateProduct(ActionEvent actionEvent) {
        try {
            getFormData();
            validateFormData();
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
        importedProduct.setAssociatedParts(associatedParts);
        closeWindow(actionEvent);
    }

    /**
     * Action event to handle the search feature.
     *
     * @param actionEvent the ActionEvent used to pull the text from the search box.
     * @see #searchParts(TextField)
     * @see #updatePartsTable(ObservableList)
     */
    @FXML
    private void onSearchAction(ActionEvent actionEvent) {
        updatePartsTable(searchParts((TextField) actionEvent.getSource()));
    }

    /**
     * Handles the Add Part button action. Adds the selected part to the table of associated parts. Does not update the
     * associated parts list for the importedProduct.
     *
     * @param actionEvent consumes the ActionEvent.
     * @see Controller#getSelectedTableItem(TableView)
     * @see #updateAssociatedPartsTable(ObservableList)
     * @see Controller#openNotifyWindow(String)
     */
    @FXML
    private void onAddAssociatedPartClick(ActionEvent actionEvent) {
        if (getSelectedTableItem(partsTable) != null) {
            Part partToAdd = (Part) getSelectedTableItem(partsTable);
            associatedParts.add(partToAdd);
            updateAssociatedPartsTable(associatedParts);
            openNotifyWindow("Part " + partToAdd.getName() + " added to " + importedProduct.getName() + ".");
        }
        else openNotifyWindow("Please select a part.");
        actionEvent.consume();
    }

    /**
     * Handles the Remove Part Button action. Removes the part selected from the associated parts table. Does not update
     * the list of associated parts on the importedPart.
     *
     * @param actionEvent consumes the ActionEvent.
     * @see Controller#getSelectedTableItem(TableView)
     * @see Controller#openNotifyWindow(String)
     */
    @FXML
    private void onRemoveAssociatedPartClick(ActionEvent actionEvent) {
        if (getSelectedTableItem(associatedPartsTable) != null) {
            Part partToRemove = (Part) getSelectedTableItem(associatedPartsTable);
            associatedParts.remove(partToRemove);
            updateAssociatedPartsTable(associatedParts);
            openNotifyWindow("Part " + partToRemove.getName() + " removed from " + importedProduct.getName() + ".");
        }
        else openNotifyWindow("Please select a part.");
        actionEvent.consume();
    }

    /**
     * Handles the Save button click action.
     *
     * @param actionEvent passed to addNewProduct or updateProduct methods.
     * @see #addNewProduct(ActionEvent)
     * @see #updateProduct(ActionEvent)
     */
    @FXML
    private void onSaveClick(ActionEvent actionEvent) {
        if (importedProduct == null) {
            addNewProduct(actionEvent);
        }
        else updateProduct(actionEvent);
    }
}
