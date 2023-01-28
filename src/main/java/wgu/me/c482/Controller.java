package wgu.me.c482;

import javafx.scene.control.TextField;
import java.io.IOException;

public abstract class Controller {
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
}
