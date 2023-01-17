package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Controller{

    public MainController() {
        super();
    }
    MainController(String file, String title) {
        super(file, title);
    }
    @FXML
    private void addPartSceneChange() {
        String addPartXml = "add-part.fxml";
        String addPartTitle = "Add-Part";
        PartController addpartController = new PartController(addPartXml, addPartTitle);
        addpartController.showStage();
    }

    @FXML
    private void addProductSceneChange() {
        String addProductXml = "add-product.fxml";
        String addProductTitle = "Add-Product";
        Controller addProductController = new ProductController(addProductXml, addProductTitle);
        addProductController.showStage();
    }
    @Override
    void cancel() {

    }
}
