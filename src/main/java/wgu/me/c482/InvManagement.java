package wgu.me.c482;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class InvManagement  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(InvManagement.class.getResource("main-form.fxml"));
        Scene scene = new Scene(loader.load(), 900, 500);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
