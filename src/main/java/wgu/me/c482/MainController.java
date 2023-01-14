package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Controller{
    private static String addPartXml = "add-part.fxml";
    private static String addPartTitle = "Add-Part";
    private static String addProductXml = "add-product.fxml";
    private static String addProductTitle = "Add-Product";

    @FXML
    public void addPartSceneChange() {
        newWindow(addPartXml, addPartTitle);
    }

    @FXML
    public void addProductSceneChange() {
        newWindow(addProductXml, addProductTitle);
    }
    @Override
    void cancel() {

    }
}
