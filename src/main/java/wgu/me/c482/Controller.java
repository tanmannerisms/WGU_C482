package wgu.me.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    static IOException InvalidNumericInput = new IOException("Invalid numeric input. Check inputs and try again.");
    static IOException StockOutOfBounds = new IOException("Stock level is out of bounds for specified min & max.");
    static IOException MinTooLow = new IOException("Min cannot be below 0.");

    public Controller() {
        System.out.println("No arg Controller constructor called");
    }

    protected static int getIntFromTextField(TextField textField) throws NumberFormatException {
        String text = textField.getText();
        return Integer.parseInt(text);
    }
    protected static double getDoubleFromTextField(TextField textField) throws NumberFormatException, NullPointerException {
        String text = textField.getText();
        return Double.parseDouble(text);
    }
    protected void openErrorWindow(Exception e) {
        ErrorWindow errorWindow = new ErrorWindow(e);
        errorWindow.showWindowAndWait();
    }
    @FXML
    protected void closeWindow(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        actionEvent.consume();
    }
}
