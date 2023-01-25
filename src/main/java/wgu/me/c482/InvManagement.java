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
    @Override
    public void start(Stage stage) throws Exception {

        Inventory inventory = new Inventory();
        Controller main = new MainController("main-form.fxml", "Home", inventory);
        main.showStage();
    }
}
