package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

abstract class Controller {
    protected String title;
    protected Stage stage;
    protected FXMLLoader loader;
    protected Scene scene;

    private static Stage nextStage;
    private static FXMLLoader nextLoader;
    private static Scene nextScene;

    Controller() {
        title = "Default Title";
    }
    Controller(String file, String title) {
        this.title = title;
        this.loader = new FXMLLoader(Controller.class.getResource(file));
        try {
            this.scene = new Scene(this.loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("XML file could not be loaded.");
        }
        this.stage = new Stage();
    }

    public void closeWindow() {
        this.stage.close();
    }
    abstract void cancel();
    protected static void newWindow(String file, String title) {
        createFXMLLoader(file);
        createScene();
        createStage();
        nextStage.setTitle(title);
        nextStage.setScene(nextScene);
        nextStage.showAndWait();
    }

    private static void createStage() {
        nextStage = new Stage();
    }
    private static void createFXMLLoader(String file) {
        nextLoader = new FXMLLoader(Controller.class.getResource(file));
    }
    private static void createScene() {
        try {
            nextScene = new Scene(nextLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("XML file could not be loaded.");
        }
    }
}
