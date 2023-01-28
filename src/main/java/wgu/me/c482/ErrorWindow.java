package wgu.me.c482;

public class ErrorWindow extends Window{
    private static final String title = "Error!";
    private static final String file = "input-error.fxml";
    ErrorWindow(Exception e) {
        super(file, title);
        ErrorController controller = fxmlLoader.getController();
        controller.setErrorTextField(e.getMessage());
    }
}
