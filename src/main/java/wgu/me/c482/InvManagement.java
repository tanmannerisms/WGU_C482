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
        /*
        FXMLLoader mainForm = new FXMLLoader(InvManagement.class.getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm.load(), 900, 500);
        stage.setTitle("Home Page");
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
         */

        Controller main = new MainController("main-form.fxml", "Home");
        main.showStage();
        Inventory inventory = new Inventory();
    }
}
