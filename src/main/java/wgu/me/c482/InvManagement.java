package wgu.me.c482;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class InvManagement extends Application {
    public Stage mainStage;
    public Scene addPartScene;
    public Scene addProductScene;
    @Override
    public void start(Stage mainStage) throws Exception {
        FXMLLoader mainForm = new FXMLLoader(InvManagement.class.getResource("main-form.fxml"));
        Scene mainScene = new Scene(mainForm.load(), 900, 500);
        mainStage.setTitle("Home Page");
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.show();


    }

    public void switchScene(Scene nextScene) {
        mainStage.setScene(nextScene);
        mainStage.show();
    }
}
