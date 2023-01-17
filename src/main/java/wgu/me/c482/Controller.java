package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

abstract class Controller {
    private String fxmlFile;
    private String title;
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private Scene scene;

    private static Stage nextStage;
    private static FXMLLoader nextLoader;
    private static Scene nextScene;

    public Controller() {
        title = "Default Title";
    }
    public Controller(String file, String title) {
        setFile(file);
        setTitle(title);
        setFxmlLoader();
        setScene();
        setStage();
    }
    private void setFile(String file) {
        fxmlFile  = file;
    }
    private void setTitle(String title) {
        this.title = title;
    }
    private void setFxmlLoader() {
        fxmlLoader = new FXMLLoader(Controller.class.getResource(fxmlFile));
    }
    private void setScene() {
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("XML file " + fxmlFile + " could not be loaded");
        }
    }
    private void setStage() {
        stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
    }
    public void showStage() {
        stage.show();
    }
    protected void closeWindow() {
        this.stage.close();
    }
    abstract void cancel();
    public static void newWindow(String file, String title) {
        createFXMLLoader(file);
        createScene();
        createStage();
        nextStage.setTitle(title);
        nextStage.setScene(nextScene);
        nextStage.showAndWait();
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
    private static void createStage() {
        nextStage = new Stage();
    }
}
