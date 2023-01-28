package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ErrorController extends Controller{
    @FXML
    private Text errorMessage;
    ErrorController(){
        super();
    }
    public void setErrorMessage(String text) {
        errorMessage.setText(text);
    }
}
