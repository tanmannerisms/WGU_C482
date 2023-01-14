package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Controller{
    private static String addPartXml = "add-part.fxml";

    @FXML
    public void addPartSceneChange() {
        newWindow(addPartXml);
    }
    @Override
    void exit() {

    }
}
