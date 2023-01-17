package wgu.me.c482;

import javafx.fxml.FXML;

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
        newSceneFile = "modify-product.fxml";
        newSceneTitle = "Change Product";
        ProductController changeProductController = new ProductController(newSceneFile, newSceneTitle);
        changeProductController.showStage();
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
