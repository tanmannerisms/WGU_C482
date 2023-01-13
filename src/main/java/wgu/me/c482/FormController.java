package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class FormController {
    @FXML
    private Button addProduct;
    private Button changeProduct;
    private Button deleteProduct;

    public Scene newScene(String form, int width, int height) {
        return null;
    }
    @FXML
    protected void addProduct() throws IOException {

        InvManagement.switchScene(addProduct);
    }
}
