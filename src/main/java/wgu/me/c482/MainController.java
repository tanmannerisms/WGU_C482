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
    private void addProductSceneChange() {
        newSceneFile = "add-product.fxml";
        newSceneTitle = "Add-Product";
        ProductController addProductController = new ProductController(newSceneFile, newSceneTitle);
        addProductController.showStage();
    }
    @FXML
    private void changeProductSceneChange() {
        newSceneFile = "change-product.fxml";
        newSceneTitle = "Change Product";

    }
    @FXML
    private void addPartSceneChange() {
        newSceneFile = "add-part.fxml";
        newSceneTitle = "Add-Part";
        PartController addpartController = new PartController(newSceneFile, newSceneTitle);
        addpartController.showStage();
    }
    @Override
    void cancel() {

    }
}
