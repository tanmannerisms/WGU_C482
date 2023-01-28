package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Window {
    private final String fxmlFile;
    private final String windowTitle;
    private Stage stage;
    private final FXMLLoader fxmlLoader;
    private Scene scene;
    private final Controller controller;

    Window(String file, String title, Controller controller) {
        fxmlFile = file;
        windowTitle = title;
        this.controller = controller;
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        setSceneController();
        setScene();
        setStage();
    }


    private void setScene() {
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("XML file " + fxmlFile + " could not be loaded");
        }
    }
    private void setSceneController() {
        fxmlLoader.setController(controller);
    }
    private void setStage() {
        stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(scene);
    }
    protected void showWindow() {
        stage.show();
    }
    protected void showWindowAndWait() {
        stage.showAndWait();
    }
    protected void closeWindow() {
        stage.close();
    }
}
