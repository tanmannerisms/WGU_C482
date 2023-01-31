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

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    private TableView productsTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn, partStockColumn, productIdColumn, productStockColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn, productNameColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn, productPriceColumn;
    @FXML
    private TextField partSearchField, productSearchField;

    /**
     * Method used for establishing the tables in the window.
     *
     * @param url not used.
     * @param resourceBundle not used.
     * @see #setTableColumns()
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumns();
        updatePartsTable(Inventory.getAllParts());
        updateProductsTable(Inventory.getAllProducts());
    }
    public MainController() {
        super();
    }

    /**
     * Method used for opening the add-product window.
     *
     * @param actionEvent consumed.
     * @see Window#Window(String, String)
     */
    @FXML
    private void addProductSceneChange(ActionEvent actionEvent) {
        Window addProduct = new Window("add-product.fxml", "Add-Product");
        addProduct.showWindowAndWait();
        actionEvent.consume();
    }

    /**
     * Method used for opening the change-product window. Passes the selected product to the ProductController instance.
     *
     * @param actionEvent consumed.
     * @see Window#Window(String, String, Product)  Window
     * @see #getSelectedProduct()
     */
    @FXML
    private void changeProductSceneChange(ActionEvent actionEvent) {
        if (getSelectedProduct() == null) {
            openNotifyWindow("Please select a product.");
        }
        else {
            Window changeProduct = new Window("modify-product.fxml", "Change Product", getSelectedProduct());
            changeProduct.showWindowAndWait();
            actionEvent.consume();
        }
    }

    /**
     * Method used for opening the add-part window.
     *
     * @param actionEvent consumed.
     * @see Window#Window(String, String)
     */
    @FXML
    private void addPartSceneChange(ActionEvent actionEvent) {
        Window addPart = new Window("add-part.fxml", "Add-Part");
        addPart.showWindowAndWait();
        actionEvent.consume();
    }

    /**
     * Method used for opening the change-part window if there is a part selected. Passes the selected part to the
     * PartController instance.
     * Will open up a notification window if nothing is selected in the Parts TableView
     *
     * @param actionEvent consumed.
     * @see #getSelectedPart()
     * @see Controller#openNotifyWindow(String)
     * @see Window#Window(String, String, Part)
     * @see #getSelectedPart()
     */
    @FXML
    private void changePartSceneChange(ActionEvent actionEvent) {
        if (getSelectedPart() == null) {
            openNotifyWindow("Please select a part.");
        }
        else {
            Window changePart = new Window("modify-part.fxml", "Alter Part", getSelectedPart());
            changePart.showWindowAndWait();
            actionEvent.consume();
        }
    }

    private void updateProductsTable(ObservableList<Product> productList) {
        productsTable.setItems(productList);
    }

    /**
     * Method for establishing the properties for the columns in the two TableViews in the main-form window.
     */
    private void setTableColumns() {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Method used to delete a Product from the Inventory when there is a Part selected in the Table Opens error
     * window if nothing selected.
     *
     * @see #getSelectedPart()
     * @see Controller#openNotifyWindow(String)
     */
    @FXML
    private void deletePart() {
        boolean partDeleted;
        partDeleted = Inventory.deletePart(getSelectedPart());
        if (getSelectedPart() != null) {
            if (!partDeleted) {
                openNotifyWindow("Part unsuccessfully deleted.");
            }
            else {
                openNotifyWindow("Part successfully deleted");
            }
        }
        else {
            openNotifyWindow("No part selected.");
        }
    }

    /**
     * Method used to delete a Product from the Inventory when there is a Product selected in the Table. Opens error
     * window if nothing selected.
     *
     * @see #getSelectedProduct()
     * @see Controller#openNotifyWindow(String)
     */
    @FXML
    private void deleteProduct() {
        boolean productDeleted;
        if (getSelectedPart() != null) {
            productDeleted = Inventory.deleteProduct(getSelectedProduct());
            if (!productDeleted) {
                openNotifyWindow("Product unsuccessfully deleted");
            } else {
                openNotifyWindow("Product successfully deleted");
            }
        }
        else {
            openNotifyWindow("No product selected.");
        }
    }
    // Need to add these two methods to Controller.java instead.

    /**
     * Method used to get the currently selected part from the main-form window.
     *
     * @return the Part that is selected.
     * @see #partsTable
     */
    private Part getSelectedPart() {
        return (Part) partsTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Method used to get the currently selected Product from the main-form window.
     *
     * @return the Product that is selected.
     * @see #productsTable
     */
    private Product getSelectedProduct() {
        return (Product) productsTable.getSelectionModel().getSelectedItem();
    }

    /**
     *  The method called when the Enter button is pressed while typing in one of the TextFields of the main-form.
     *
     * @param actionEvent the source of the action. Used to determine which search function to call
     * @see Controller#searchParts(TextField)
     * @see Controller#updatePartsTable(ObservableList)
     * @see #searchProducts(TextField)
     * @see #updateProductsTable(ObservableList)
     */
    @FXML
    private void onSearchAction(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(partSearchField)) {
            updatePartsTable(searchParts(partSearchField));
        }
        if (actionEvent.getSource().equals(productSearchField)) {
            updateProductsTable(searchProducts(productSearchField));
        }
    }

    /**
     * Searches for the products using the ID or Name taken from the TextField passed in
     *
     * @param searchParam the TextField used to get the Product(s) that match the parameters
     * @return the list of Product(s) found matching the parameters.
     */
    private ObservableList<Product> searchProducts(TextField searchParam) {
        ObservableList<Product> searchResults = FXCollections.observableArrayList();
        try {
            int productId = getIntFromTextField(searchParam);
            searchResults = FXCollections.observableArrayList(Inventory.lookupProduct(productId));
        } catch (NumberFormatException e) {
            searchResults = Inventory.lookupProduct(searchParam.getText());
        }
        return searchResults;
    }
}
