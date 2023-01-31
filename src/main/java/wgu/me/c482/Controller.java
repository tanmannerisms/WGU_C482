package wgu.me.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    @FXML
    protected String name;
    @FXML
    protected int id, stock, min, max;
    @FXML
    protected double price;
    @FXML
    protected TextField nameField, stockField, priceField, minField, maxField;
    @FXML
    protected TableView partsTable;
    static IOException InvalidNumericInput = new IOException("Invalid numeric input. Check inputs and try again.");
    static IOException StockOutOfBounds = new IOException("Stock level is out of bounds for specified min & max.");
    static IOException MinTooLow = new IOException("Min cannot be below 0.");

    public Controller() {
        System.out.println("No arg Controller constructor called");
    }

    /** NEED TO SIMPLIFY
     * Method to convert a String to an Integer. String is parsed from TextField that is passed in.
     *
     * @param textField the TextField that is to be parsed.
     * @return the integer that was converted from the String.
     * @throws NumberFormatException when parsed String is not an Integer.
     */
    protected static int getIntFromTextField(TextField textField) throws NumberFormatException {
        String text = textField.getText();
        return Integer.parseInt(text);
    }

    /** NEED TO SIMPLIFY
     * Method to convert a String to a Double. String is parsed from TextField that is passed in.
     *
     * @param textField the TextField that is to be parsed.
     * @return the double that was converted from the String.
     * @throws NumberFormatException when parsed String is not a Double.
     * @throws NullPointerException when TextField is empty.
     */
    protected static double getDoubleFromTextField(TextField textField) throws NumberFormatException, NullPointerException {
        String text = textField.getText();
        return Double.parseDouble(text);
    }

    protected ObservableList<Part> searchParts(TextField searchParam) {
        ObservableList<Part> searchResults;
        try {
            int partId = getIntFromTextField(searchParam);
            searchResults = FXCollections.observableArrayList(Inventory.lookupPart(partId));
        } catch (NumberFormatException e) {
            searchResults = Inventory.lookupPart(searchParam.getText());
        }
        return searchResults;
    }
    protected void updatePartsTable(ObservableList<Part> partList) {
        partsTable.setItems(partList);
    }

                                   /**
     * Method to open up a new window with an error message
     *
     * @param e the exception that is passed in from a catch statement.
     */
    protected void openErrorWindow(Exception e) {
        ErrorWindow errorWindow = new ErrorWindow(e);
        errorWindow.showWindowAndWait();
    }


    /**
     * ADD A openNotifyWindow HERE.
     */



    /**
     * Method used to close the active window
     *
     * @param actionEvent is used to gather the information needed to close the window.
     */
    @FXML
    protected void closeWindow(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        actionEvent.consume();
    }
}
