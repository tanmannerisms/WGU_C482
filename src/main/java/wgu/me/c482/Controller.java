package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

class Controller {
    protected Inventory inventory;
    static IOException InvalidNumericInput = new IOException("Invalid input. Check numeric inputs and try again.");

    public Controller() {
        System.out.println("No arg Controller constructor called");
    }
    public Controller(Inventory inventory) {
        System.out.println("Begin controller constructor call with args");
        setInventory(inventory);
        System.out.println("Controller constructor with args finished");
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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
        ErrorController errorController = new ErrorController();
        Window error = new Window("input-error.fxml", "Error!", errorController);
        errorController.setErrorMessage(e.getMessage());
        error.showWindowAndWait();
    }
}
