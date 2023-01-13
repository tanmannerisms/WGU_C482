package wgu.me.c482;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;

public class InvManagement extends Application {
    private Stage mainStage;
    public Stage secondaryStage;
    FXMLLoader addPartLoader = new FXMLLoader(InvManagement.class.getResource("add-part.fxml"));

    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        FXMLLoader mainForm = new FXMLLoader(InvManagement.class.getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm.load(), 900, 500);
        this.mainStage.setTitle("Home Page");
        this.mainStage.setScene(mainScene);
        this.mainStage.setResizable(false);
        this.mainStage.show();
    }

    @FXML
    public void switchScene() {
        try {
            secondaryStage = new Stage();
            Scene addPartScene = new Scene(addPartLoader.load());
            secondaryStage.setScene(addPartScene);
            secondaryStage.sizeToScene();
            secondaryStage.setResizable(false);
            secondaryStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit() {
        this.mainStage.close();
    }
}
