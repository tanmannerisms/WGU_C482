package wgu.me.c482;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

abstract class Controller {
    protected static String newSceneFile;
    protected static String newSceneTitle;

    private String fxmlFile;
    private String title;
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private Scene scene;
    protected Inventory inventory;

    public Controller() {
        title = "Default Title";
    }
    public Controller(String file, String title, Inventory inventory) {
        setFile(file);
        setTitle(title);
        setFxmlLoader();
        setScene();
        setStage();
        setInventory(inventory);
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
    protected void showStage() {
        stage.show();
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public static int getIntFromTextField(TextField textField) {
        String text = textField.getText();
        return Integer.parseInt(text);
    }
    public static double getDoubleFromTextField(TextField textField) {
        String text = textField.getText();
        return Double.parseDouble(text);
    }
    protected void closeWindow() {
        this.stage.close();
    }
    abstract void cancel();
}
