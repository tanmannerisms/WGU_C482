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

    /**
     * Method used for establishing the tables in the window.
     *
     * @param url not used.
     * @param resourceBundle not used.
     * @see #setTableColumns()
     * @see #updateTables()
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumns();
        updateTables();
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

    /** NEED TO ADD IF THEN LIKE WITH CHANGEPARTSCENECHANGE
     * Method used for opening the change-product window. Passes the selected product to the ProductController instance.
     *
     * @param actionEvent consumed.
     * @see Window#Window(String, String, Product)  Window
     * @see #getSelectedProduct()
     */
    @FXML
    private void changeProductSceneChange(ActionEvent actionEvent) {
        Window changeProduct = new Window("modify-product.fxml", "Change Product", getSelectedProduct());
        changeProduct.showWindowAndWait();
        actionEvent.consume();
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
     * @see ErrorWindow#ErrorWindow(String)
     * @see Window#Window(String, String, Part)
     * @see #getSelectedPart()
     */
    @FXML
    private void changePartSceneChange(ActionEvent actionEvent) {
        if (getSelectedPart() == null) {
            ErrorWindow errorWindow = new ErrorWindow("Please select a part.");
            errorWindow.showWindowAndWait();
        }
        else {
            Window changePart = new Window("modify-part.fxml", "Alter Part", getSelectedPart());
            changePart.showWindowAndWait();
            actionEvent.consume();
        }
    }

    /**
     * Method for updating the items in the two tables.
     * Currently only used for setting the tables on the initialization of the window. May not need a method for this.
     *
     * @see Inventory#getAllParts()
     * @see Inventory#getAllProducts()
     */
    @FXML
    private void updateTables() {
        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());
        System.out.println("Tables updated");
    }

    /**
     * Method for establishing the properties for the columns in the two TableViews in the main-form window.
     */
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

    /**
     * Method used to delete a Product from the Inventory when there is a Part selected in the Table Opens error
     * window if nothing selected.
     *
     * @see #getSelectedPart()
     * @see ErrorWindow#ErrorWindow(String)
     */
    @FXML
    private void deletePart() {
        boolean partDeleted;
        partDeleted = Inventory.deletePart(getSelectedPart());
        ErrorWindow errorWindow;
        if (getSelectedPart() != null) {
            if (!partDeleted) {
                errorWindow = new ErrorWindow("Part unsuccessfully deleted.");
            }
            else {
                errorWindow = new ErrorWindow("Part successfully deleted");
            }
        }
        else {
            errorWindow = new ErrorWindow("No part selected.");
        }
        errorWindow.showWindow();
    }

    /**
     * Method used to delete a Product from the Inventory when there is a Product selected in the Table. Opens error
     * window if nothing selected.
     *
     * @see #getSelectedProduct()
     * @see ErrorWindow#ErrorWindow(String)
     */
    @FXML
    private void deleteProduct() {
        boolean productDeleted;
        ErrorWindow errorWindow;
        if (getSelectedPart() != null) {
            productDeleted = Inventory.deleteProduct(getSelectedProduct());
            if (!productDeleted) {
                errorWindow = new ErrorWindow("Product unsuccessfully deleted");
            } else {
                errorWindow = new ErrorWindow("Product successfully deleted");
            }
        }
        else {
            errorWindow = new ErrorWindow("No product selected.");
        }
        errorWindow.showWindow();
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
}
