package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Controller{
    private static String addPartXml = "add-part.fxml";
    private static String addProductXml = "add-product.fxml";

    @FXML
    public void addPartSceneChange() {
        newWindow(addPartXml);
    }

    @FXML
    public void addProductSceneChange() {
        newWindow(addProductXml);
    }
    @Override
    void exit() {

    }
    @Override
    void cancel() {

    }
}
