package wgu.me.c482;

import javafx.fxml.FXML;

public class MainController extends Controller{

    public MainController() {
        super();
    }
    MainController(String file, String title, Inventory inventory) {
        super(file, title, inventory);
    }
    @FXML
    private void addProductSceneChange() {
        newSceneFile = "add-product.fxml";
        newSceneTitle = "Add-Product";
        ProductController addProductController = new ProductController(newSceneFile, newSceneTitle, this.inventory);
        addProductController.showStage();
    }
    @FXML
    private void changeProductSceneChange() {
        newSceneFile = "modify-product.fxml";
        newSceneTitle = "Edit Product";
        ProductController changeProductController = new ProductController(newSceneFile, newSceneTitle, this.inventory);
        changeProductController.showStage();
    }
    @FXML
    private void addPartSceneChange() {
        newSceneFile = "add-part.fxml";
        newSceneTitle = "Add Part";
        PartController addpartController = new PartController(newSceneFile, newSceneTitle, this.inventory);
        addpartController.showStage();
    }
    @FXML
    private void changePartSceneChange() {
        newSceneFile = "modify-part.fxml";
        newSceneTitle = "Edit Part";
        PartController changePartController = new PartController(newSceneFile, newSceneTitle, this.inventory);
        changePartController.showStage();
    }
    @Override
    void cancel() {

    }
}
