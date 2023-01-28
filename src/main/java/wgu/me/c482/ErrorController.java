package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController extends Controller implements Initializable {
    @FXML
    private Text errorTextField;
    private String errorMessage = "Error! Check values and try again.";
    ErrorController(){
        super();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setErrorTextField(errorMessage);
    }
    public void setErrorTextField(String text) {
        errorMessage = text;
        errorTextField.setText(errorMessage);
    }
}
