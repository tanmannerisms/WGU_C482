package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Window {
    private final String fxmlFile;
    private final String windowTitle;
    private Stage stage;
    protected final FXMLLoader fxmlLoader;
    private Scene scene;

    Window(String file, String title) {
        fxmlFile = file;
        windowTitle = title;
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        setScene();
        setStage();
    }
    Window(String file, String title, Part part) {
        fxmlFile = file;
        windowTitle = title;
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        setScene();
        setStage();
        PartController partController = fxmlLoader.getController();
        partController.importedPart = part;
        partController.setFields();
    }
    Window(String file, String title, Product product) {
        fxmlFile = file;
        windowTitle = title;
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        setScene();
        setStage();
        ProductController productController = fxmlLoader.getController();
        productController.importedProduct = product;
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
        stage.setTitle(windowTitle);
        stage.setScene(scene);
    }
    protected void showWindow() {
        stage.show();
    }
    protected void showWindowAndWait() {
        stage.showAndWait();
    }
}
