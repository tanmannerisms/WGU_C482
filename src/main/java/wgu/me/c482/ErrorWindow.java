package wgu.me.c482;

public class ErrorWindow extends Window{

    private static final String title = "Error!";
    private static final String file = "input-error.fxml";

    /**
     * The constructor used for instantiating a window to display an error message to the user.
     *
     * @param e the exception that will be used to generate the error message that will be displayed.
     */
    ErrorWindow(Exception e) {
        super(file, title);
        ErrorController controller = fxmlLoader.getController();
        controller.setErrorTextField(e.getMessage());
        e.printStackTrace();
    }

    /**
     * Alternate constructor for generating notification messages. Can be useful for when an error needs to be thrown,
     * but with no legitimate exception
     *
     * @param errorMessage the string that will be used when displaying the error window. .
     */
    ErrorWindow(String errorMessage) {
        super(file, title);
        ErrorController controller = fxmlLoader.getController();
        controller.setErrorTextField(errorMessage);
    }
}
