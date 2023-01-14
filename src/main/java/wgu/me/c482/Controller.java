package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

abstract class Controller {
    protected String title;
    protected Stage currentStage;
    protected FXMLLoader currentLoader;
    protected Scene currentScene;

    private static Stage nextStage;
    private static FXMLLoader nextLoader;
    private static Scene nextScene;

    Controller() {
        title = "Title";
    }

    abstract void exit();
    abstract void cancel();
    protected void newWindow(String file) {
        createFXMLLoader(file);
        createScene();
        createStage();
        nextStage.setTitle(title);
        nextStage.setScene(nextScene);
        nextStage.showAndWait();
    }

    private void createStage() {
        nextStage = new Stage();
        nextStage.setScene(nextScene);

    }
    private void createFXMLLoader(String file) {
        nextLoader = new FXMLLoader(Controller.class.getResource(file));

    }
    private void createScene() {
        try {
            nextScene = new Scene(nextLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
